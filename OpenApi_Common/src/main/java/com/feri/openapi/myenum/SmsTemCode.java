package com.feri.openapi.myenum;

public enum SmsTemCode {
    YanZhengMa("SMS_115250125");
    private String code;
    private SmsTemCode(String c){
        code=c;
    }
    public String getCode(){
        return code;
    }
}
