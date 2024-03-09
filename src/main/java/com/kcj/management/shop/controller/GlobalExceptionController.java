package com.kcj.management.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

//@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "/error/error";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundError(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "/error/error";
    }
}
