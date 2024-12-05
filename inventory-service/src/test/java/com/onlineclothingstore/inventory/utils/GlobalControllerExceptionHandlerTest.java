package com.onlineclothingstore.inventory.utils;

import com.onlineclothingstore.inventory.utils.exceptions.NotFoundException;
import com.onlineclothingstore.inventory.utils.exceptions.NullInventoryException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GlobalControllerExceptionHandlerTest {

    private final GlobalControllerExceptionHandler exceptionHandler = new GlobalControllerExceptionHandler();

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
    public void testHandleNullInventoryException() {
        // Arrange
        NullInventoryException ex = new NullInventoryException("Null inventory");
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());

        // Act
        HttpErrorInfo errorInfo = exceptionHandler.handleNullInventoryException(request, ex);

        // Assert
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, errorInfo.getHttpStatus());
        assertEquals("Null inventory", errorInfo.getMessage());
    }
}