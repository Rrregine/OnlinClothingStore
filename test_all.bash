#!/usr/bin/env bash
#
# Sample usage:
#   ./test_all.bash start stop
#   start and stop are optional
#
#   HOST=localhost PORT=7000 ./test-em-all.bash
#
# When not in Docker
#: ${HOST=localhost}
#: ${PORT=7000}

# When in Docker
: ${HOST=localhost}
: ${PORT=8080}

#array to hold all our test data ids
allTestClientIds=()
allTestSaleIds=()

function assertCurl() {

  local expectedHttpCode=$1
  local curlCmd="$2 -w \"%{http_code}\""
  local result=$(eval $curlCmd)
  local httpCode="${result:(-3)}"
  RESPONSE='' && (( ${#result} > 3 )) && RESPONSE="${result%???}"

  if [ "$httpCode" = "$expectedHttpCode" ]
  then
    if [ "$httpCode" = "200" ]
    then
      echo "Test OK (HTTP Code: $httpCode)"
    else
      echo "Test OK (HTTP Code: $httpCode, $RESPONSE)"
    fi
  else
      echo  "Test FAILED, EXPECTED HTTP Code: $expectedHttpCode, GOT: $httpCode, WILL ABORT!"
      echo  "- Failing command: $curlCmd"
      echo  "- Response Body: $RESPONSE"
      exit 1
  fi
}

function assertEqual() {

  local expected=$1
  local actual=$2

  if [ "$actual" = "$expected" ]
  then
    echo "Test OK (actual value: $actual)"
  else
    echo "Test FAILED, EXPECTED VALUE: $expected, ACTUAL VALUE: $actual, WILL ABORT"
    exit 1
  fi
}

#have all the microservices come up yet?
function testUrl() {
    url=$@
    if curl $url -ks -f -o /dev/null
    then
          echo "Ok"
          return 0
    else
          echo -n "not yet"
          return 1
    fi;
}

#prepare the test data that will be passed in the curl commands for posts and puts
function setupTestdata() {

##CREATE SOME CUSTOMER TEST DATA - THIS WILL BE USED FOR THE POST REQUEST
#
body=\
'{
     "clientId": "1048b354-c18f-4109-8282-2a85485bfa5a",
     "username": "Regine",
     "emailAddress": "aaa@gmail.com",
     "streetAddress": "something",
     "city": "something else",
     "province": "qc",
     "country": "ca",
     "postalCode": "AAAAAA",
     "phoneNumbers": [
         {
             "type": "WORK",
             "number": "111-111-1111"
         },
         {
             "type": "WORK",
             "number": "222-111-1111"
         }
     ]
}'
    recreateClient 1 "$body"
#

##CREATE SOME SALE TEST DATA - THIS WILL BE USED FOR THE POST REQUEST
#
body=\
''
recreateClientSale 1 "$body" "product10"

} #end of setupTestdata


#USING CUSTOMER TEST DATA - EXECUTE POST REQUEST
function recreateClient() {
    local testId=$1
    local aggregate=$2

    #create the customer and record the generated customerId
    clientId=$(curl -X POST http://$HOST:$PORT/api/v1/clients -H "Content-Type:
    application/json" --data "$aggregate" | jq '.clientId')
    allTestClientIds[$testId]=$clientId
    echo "Added Client with clientId: ${allTestClientIds[$testId]}"
}

#USING SALE TEST DATA - EXECUTE POST REQUEST
function recreateClientSale(){
  local testId=$1
  local aggregate=$2
  local clientId=$3

  #create the sale and record the generated saleId
  saleId=$(curl -X POST http://$HOST:$PORT/api/v1/clients/$clientId/sales -H "Content-Type:
  application/json" --data "$aggregate" | jq '.saleId')
  allTestSaleIds[$testId]=$saleId
  echo "Added Sale with saleId: ${allTestSaleIds[$testId]}"
}


#don't start testing until all the microservices are up and running
function waitForService() {
    url=$@
    echo -n "Wait for: $url... "
    n=0
    until testUrl $url
    do
        n=$((n + 1))
        if [[ $n == 100 ]]
        then
            echo " Give up"
            exit 1
        else
            sleep 6
            echo -n ", retry #$n "
        fi
    done
}

#start of test script
set -e

echo "HOST=${HOST}"
echo "PORT=${PORT}"

if [[ $@ == *"start"* ]]
then
    echo "Restarting the test environment..."
    echo "$ docker-compose down"
    docker-compose down
    echo "$ docker-compose up -d"
    docker-compose up -d
fi

#try to delete an entity/aggregate that you've set up but that you don't need. This will confirm that things are working
waitForService curl -X DELETE http://$HOST:$PORT/api/v1/clients/1048b354-c18f-4109-8282-2a85485bfa5a

setupTestdata

#EXECUTE EXPLICIT TESTS AND VALIDATE RESPONSE
#
##verify that a get all customers works
echo -e "\nTest 1: Verify that a get all clients works"
assertCurl 200 "curl http://$HOST:$PORT/api/v1/clients -s"
assertEqual 11 $(echo $RESPONSE | jq ". | length")
#
#
## Verify that a normal get by id of earlier posted customer works
echo -e "\nTest 2: Verify that a normal get by id of earlier posted customer works"
assertCurl 200 "curl http://$HOST:$PORT/api/v1/clients/${allTestClientIds[1]} '${body}' -s"
assertEqual ${allTestClientIds[1]} $(echo $RESPONSE | jq .clientId)
assertEqual "\"Regine\"" $(echo $RESPONSE | jq ".username")
#
#
## Verify that an update of an earlier posted customer works - put at api-gateway has no response body
echo -e "\nTest 3: Verify that an update of an earlier posted customer works"
body=\
'{
      "clientId": "1048b354-c18f-4109-8282-2a85485bfa5a",
      "username": "Regine UPDATED",
      "emailAddress": "aaa@gmail.com",
      "streetAddress": "something",
      "city": "something else",
      "province": "qc",
      "country": "ca",
      "postalCode": "AAAAAA",
      "phoneNumbers": [
          {
              "type": "WORK",
              "number": "111-111-1111"
          },
          {
              "type": "WORK",
              "number": "222-111-1111"
          }
      ]
 }'
assertCurl 200 "curl -X PUT http://$HOST:$PORT/api/v1/clients/${allTestClientIds[1]} -H \"Content-Type: application/json\" -d '${body}' -s"
#
#
## Verify that a delete of an earlier posted customer works
echo -e "\nTest 4: Verify that a delete of an earlier posted customer works"
assertCurl 204 "curl -X DELETE http://$HOST:$PORT/api/v1/clients/${allTestClientIds[1]} -s"
#
#
## Verify that a 404 (Not Found) status is returned for a non existing customerId (c3540a89-cb47-4c96-888e-ff96708db4d7)
echo -e "\nTest 5: Verify that a 404 (Not Found) error is returned for a get customer request with a non existing customerId"
assertCurl 404 "curl http://$HOST:$PORT/api/v1/clients/c3540a89-cb47-4c96-888e-ff96708db4d7 -s"
#
#
## Verify that a 422 (Unprocessable Entity) status is returned for an invalid customerId (c3540a89-cb47-4c96-888e-ff96708db4d)
echo -e "\nTest 6: Verify that a 422 (Unprocessable Entity) status is returned for a get customer request with an invalid customerId"
assertCurl 422 "curl http://$HOST:$PORT/api/v1/clients/c3540a89 -s"
#
#
## Verify that a get all client sales works
echo -e "\nTest 1: Verify that a get all client sales works"
assertCurl 200 "curl http://$HOST:$PORT/api/v1/clients/1048b354-c18f-4109-8282-2a85485bfa5a/sales -s"
assertEqual 2 $(echo $RESPONSE | jq ". | length")
#
#
## Verify that a get client sales works
echo -e "\nTest 1: Verify that a get all client sales works"
assertCurl 200 "curl http://$HOST:$PORT/api/v1/clients/1048b354-c18f-4109-8282-2a85485bfa5a/sales/${allTestSaleIds[1]} -H \"Content-Type: application/json\" -d '${body}' -s"
assertEqual ${allTestSaleIds[1]} $(echo $RESPONSE | jq ".saleId")
assertEqual "\"SALE_OFFER\"" $(echo $RESPONSE | jq ".saleStatus")
assertEqual "\"Alick\"" $(echo $RESPONSE | jq ".clientFirstName")
assertEqual "\"Vilma\"" $(echo $RESPONSE | jq ".clientLastName")
assertEqual "\"TOYATA\"" $(echo $RESPONSE | jq ".vehicleMake")
#
#
## Verify that an update of an earlier posted client sales works
body=\
'json body here'
echo -e "\nTest 1: Verify that an update of an earlier posted client sales works"
assertCurl 200 "curl http://$HOST:$PORT/api/v1/clients/1048b354-c18f-4109-8282-2a85485bfa5a/sales/${allTestSaleIds[1]} -H \"Content-Type: application/json\" -d '${body}' -s"
## Verify changed
assertEqual ${allTestSaleIds[1]} $(echo $RESPONSE | jq ".saleId")
assertEqual "\"SALE_COMPLETED\"" $(echo $RESPONSE | jq ".saleStatus") ## SAME AS THE BODY ABOVE!!!
assertEqual "\"Alick\"" $(echo $RESPONSE | jq ".clientFirstName")
assertEqual "\"Vilma\"" $(echo $RESPONSE | jq ".clientLastName")
assertEqual "\"TOYATA\"" $(echo $RESPONSE | jq ".vehicleMake")
#
#
# TODO: ADD verification of vehicle status change to SOLD
#
#
## Verify that a delete of an earlier posted client sale works
echo -e "\nTest 1: Verify that a delete of an earlier posted client sale works"
assertCurl 204 "curl -X http://$HOST:$PORT/api/v1/clients/1048b354-c18f-4109-8282-2a85485bfa5a/sales/${allTestSaleIds[1]} -s"
#
#
## Verify 404 not found for existing clientId
echo -e "\nTest 5: Verify that a 404 (Not Found) error is returned for a get client sale request with a non existing customerId"
assertCurl 404 "curl http://$HOST:$PORT/api/v1/clients/c3540a89-cb47-4c96-888e-ff96708db4d9/sales/${allTestSaleIds[1]} -s"
#
#
## Verify 422 not found invalid clientId
echo -e "\nTest 5: Verify that a 422 (Unprocessable Entity) error is returned for a get client sale request with a invalid customerId"
assertCurl 422 "curl http://$HOST:$PORT/api/v1/clients/c3540a89-cb47-4c96-888e-ff96708db4/sales/${allTestSaleIds[1]} -s"
#
#
## Verify 404 not found for non existing saleId
echo -e "\nTest 5: Verify that a 404 (Not Found) error is returned for a get client sale request with a non existing customerId"
assertCurl 404 "curl http://$HOST:$PORT/api/v1/clients/c3540a89-cb47-4c96-888e-ff96708db4d9/sales/c3540a89-cb47-4c96-888e-ff96708db4d9 -s"
#
#


if [[ $@ == *"stop"* ]]
then
    echo "We are done, stopping the test environment..."
    echo "$ docker-compose down"
    docker-compose down
fi