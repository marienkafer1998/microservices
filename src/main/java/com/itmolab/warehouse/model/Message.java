package com.itmolab.warehouse.model;

public class Message {
    private String message;
    private int code;

    public Message (String message, int code) {
        this.message=message;
        this.code=code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }

}
