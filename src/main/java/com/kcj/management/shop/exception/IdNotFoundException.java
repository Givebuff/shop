package com.kcj.management.shop.exception;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String className) {
        super(className + " class id not found error!!");
    }
}
