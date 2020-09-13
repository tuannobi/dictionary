//package com.tuan.dictionary.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.time.LocalDateTime;
//
//@ControllerAdvice
//@RestController
//public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest webRequest){
//       ExceptionResponse exceptionResponse= new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),webRequest.getDescription(false));
//       return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(ServiceException.class)
//    public final ResponseEntity<Object> handleServiceException(Exception ex, WebRequest webRequest){
//        ExceptionResponse exceptionResponse= new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),webRequest.getDescription(false));
//        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ControllerException.class)
//    public final ResponseEntity<Object> handleControllerException(Exception ex, WebRequest webRequest){
//        ExceptionResponse exceptionResponse= new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),webRequest.getDescription(false));
//        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }
//}
