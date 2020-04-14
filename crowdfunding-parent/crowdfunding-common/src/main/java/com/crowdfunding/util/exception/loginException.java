package com.crowdfunding.util.exception;

/**
 * 自定义登陆异常
 */
public class loginException extends RuntimeException {
    public loginException(String message){
        super(message);
    }
}
