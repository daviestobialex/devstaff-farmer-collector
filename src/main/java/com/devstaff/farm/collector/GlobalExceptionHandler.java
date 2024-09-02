package com.devstaff.farm.collector;

import com.devstaff.farm.collector.models.BaseResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BaseResponse> handleException(EntityNotFoundException ex) {
        log.error("Entity Not Found Exception ::: {}", ex);
        BaseResponse response = new BaseResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<BaseResponse> handleException(HttpServerErrorException ex) {
        log.error("HttpServerErrorException Exception ::: {}", ex);
        BaseResponse response = new BaseResponse("third party bad request error");
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleException(MethodArgumentNotValidException ex) {
        log.error("Method Argument Not Valid Exception ::: {}", ex);

        BindingResult result = ex.getBindingResult();

        StringBuilder errorList = new StringBuilder();

        List<String> errors = result.getFieldErrors().stream()
                .map(fieldError
                        -> errorList.append(fieldError.getField().concat(" : ")
                        .concat(fieldError.getDefaultMessage())
                        .concat(" : rejected value ["))
                        .append(fieldError.getRejectedValue()).append("]")
                        .toString())
                .collect(Collectors.toList());

        result.getGlobalErrors().forEach(
                fieldError -> errors.add(fieldError.getDefaultMessage()));

        errors.removeAll(errors.stream()
                .filter(er -> er.contains("violation")).collect(Collectors.toList()));

        String ermsg = errors.stream()
                .filter(er -> er.contains("monthly"))
                .findFirst()
                .orElse(
                        errors.stream()
                                .filter(er -> er.contains("weekly"))
                                .findFirst()
                                .orElse(
                                        errors.stream()
                                                .filter(er -> er.contains("daily"))
                                                .findFirst()
                                                .orElse(errors.get(errors.size() - 1))
                                )
                );

        BaseResponse response = new BaseResponse(ermsg);
        response.setErrors(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<BaseResponse> handleException(MissingServletRequestPartException ex) {
        log.error("Missing Servlet RequestPart Exception ::: {}", ex);

        BaseResponse response = new BaseResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<BaseResponse> handleException(DataIntegrityViolationException ex, WebRequest request) {
        log.error("Data Integrity Violation Exception ::: {}", ex);

        String message = ex.getMostSpecificCause().getMessage();

        if (message != null) {
            message = message.split("for")[0];
        }

        BaseResponse baseResponse = new BaseResponse(message);

        return new ResponseEntity<>(baseResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse> handleException(ConstraintViolationException ex) {
        log.error("Constraint Violation Exception ::: {}", ex);
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        String errorMessage = "";
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            violations.forEach(violation -> builder.append("\n").append(violation.getMessage()));
            errorMessage = builder.toString();
        }
        BaseResponse baseResponse = new BaseResponse(errorMessage);

        return new ResponseEntity<>(baseResponse, HttpStatus.CONFLICT);
    }
}
