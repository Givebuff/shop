package com.kcj.management.shop.exception;

public class NotExistException extends RuntimeException{
    public NotExistException(String className) {
        super(className + " class no exist value!!");
    }
}
