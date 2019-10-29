package com.feri.openapi.myenum;

public enum UserLogType {
    Register(1),Login(2);
    private int value;
    private UserLogType(int v){
        value=v;
    }
    public int getValue(){
        return value;
    }
}
