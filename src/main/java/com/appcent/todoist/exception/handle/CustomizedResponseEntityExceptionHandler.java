package com.appcent.todoist.exception.handle;

import com.appcent.todoist.exception.EntityNotFoundException;
import com.appcent.todoist.response.RestErrorResponse;
import com.appcent.todoist.response.RestResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){

        String message = ex.getMessage();
        String detail = webRequest.getDescription(false);

        return getResponseEntity(message, detail, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler
    public final ResponseEntity<Object> handleItemNotFoundExceptionExceptions(EntityNotFoundException ex, WebRequest webRequest){

        String message = ex.getMessage();
        String detail = webRequest.getDescription(false);

        return getResponseEntity(message, detail, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = "Validation failed!";
        StringBuilder detail = new StringBuilder();

        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        if(!errorList.isEmpty()){

            for (ObjectError objectError : errorList) {
                String defaultMessage = objectError.getDefaultMessage();

                detail.append(defaultMessage).append("\n");
            }
        } else {
            detail = new StringBuilder(ex.getBindingResult().toString());
        }

        return getResponseEntity(message, detail.toString(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> getResponseEntity(String message, String detail, HttpStatus httpStatus) {

        Date errorDate = new Date();

        RestErrorResponse restErrorResponse = new RestErrorResponse(errorDate, message, detail);

        RestResponse<RestErrorResponse> restResponse = RestResponse.error(restErrorResponse);
        restResponse.setMessages(message);

        return new ResponseEntity<>(restResponse, httpStatus);
    }
}
