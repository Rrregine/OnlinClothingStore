#!/usr/bin/env bash

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=clients-service \
--package-name=com.onlineclothingstore.clients \
--groupId=com.onlineclothingstore.clients \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
clients-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=employees-service \
--package-name=com.onlineclothingstore.employees \
--groupId=com.onlineclothingstore.employees \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
employees-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=inventory-service \
--package-name=com.onlineclothingstore.inventory \
--groupId=com.onlineclothingstore.inventory \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
inventory-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=sales-service \
--package-name=com.onlineclothingstore.sales \
--groupId=com.onlineclothingstore.sales \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
sales-service

spring init \
--boot-version=3.2.3 \
--build=gradle \
--type=gradle-project \
--java-version=17 \
--packaging=jar \
--name=api-gateway \
--package-name=com.onlineclothingstore.apigateway \
--groupId=com.onlineclothingstore.apigateway \
--dependencies=web,webflux,validation \
--version=1.0.0-SNAPSHOT \
api-gateway

