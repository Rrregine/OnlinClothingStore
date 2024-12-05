package com.onlineclothingstore.apigateway.utils;

import com.onlineclothingstore.apigateway.utils.exceptions.BadPostalCodeException;
import com.onlineclothingstore.apigateway.utils.exceptions.InvalidInputException;
import com.onlineclothingstore.apigateway.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class GlobalControllerExceptionHandlerTest {
    private final GlobalControllerExceptionHandler exceptionHandler = new GlobalControllerExceptionHandler();

    @Test
    public void testHandleBadPostalCodeException() {
        // Arrange
        BadPostalCodeException ex = new BadPostalCodeException("Invalid postal code");

        // Act
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());
        HttpErrorInfo errorInfo = exceptionHandler.handleBadPostalCodeException(request, ex);

        // Assert
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, errorInfo.getHttpStatus());
        assertEquals("Invalid postal code", errorInfo.getMessage());
    }

    @Test
    public void testHandleNotFoundException() {
        // Arrange
        NotFoundException ex = new NotFoundException("Resource not found");

        // Act
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());
        HttpErrorInfo errorInfo = exceptionHandler.handleNotFoundException(request, ex);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, errorInfo.getHttpStatus());
        assertEquals("Resource not found", errorInfo.getMessage());
    }

    @Test
    public void testHandleInvalidInputException() {
        // Arrange
        InvalidInputException ex = new InvalidInputException("Invalid input");

        // Act
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());
        HttpErrorInfo errorInfo = exceptionHandler.handleInvalidInputException(request, ex);

        // Assert
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, errorInfo.getHttpStatus());
        assertEquals("Invalid input", errorInfo.getMessage());
    }
}