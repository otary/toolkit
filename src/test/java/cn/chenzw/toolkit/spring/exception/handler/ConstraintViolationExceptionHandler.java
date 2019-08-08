package cn.chenzw.toolkit.spring.exception.handler;

import cn.chenzw.toolkit.validation.exception.ConstraintViolationExceptionWrapper;
import cn.chenzw.toolkit.validation.exception.DefaultConstraintViolationExceptionWrapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ConstraintViolationExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Object handleConstraintViolationException(ConstraintViolationException e) {
        ConstraintViolationExceptionWrapper constraintViolationExceptionWrapper = new DefaultConstraintViolationExceptionWrapper(
                e);

        System.out.println("handleConstraintViolationException:" + constraintViolationExceptionWrapper.toHumanString());

        return new HttpEntity<>(constraintViolationExceptionWrapper.toHumanString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ConstraintViolationExceptionWrapper constraintViolationExceptionWrapper = new DefaultConstraintViolationExceptionWrapper(
                e);

        System.out.println(
                "handleMethodArgumentNotValidException:" + constraintViolationExceptionWrapper.toHumanString());

        return new HttpEntity<>(constraintViolationExceptionWrapper.toHumanString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e) {
        ConstraintViolationExceptionWrapper constraintViolationExceptionWrapper = new DefaultConstraintViolationExceptionWrapper(
                e);

        System.out.println("handleBindException:" + constraintViolationExceptionWrapper.toHumanString());

        return new HttpEntity(constraintViolationExceptionWrapper.toHumanString());
    }

}
