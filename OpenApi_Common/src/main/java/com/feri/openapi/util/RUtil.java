package com.feri.openapi.util;

import com.feri.openapi.vo.R;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-28 15:29
 */
public class RUtil {
    public static R setOK(){
        R r=new R();
        r.setCode(0);
        r.setMsg("OK");
        r.setData(null);
        return r;
    }
    public static R setOK(Object data){
        R r=new R();
        r.setCode(0);
        r.setMsg("OK");
        r.setData(data);
        return r;
    }
    public static R setERROR(){
        R r=new R();
        r.setCode(1);
        r.setMsg("ERROR");
        r.setData(null);
        return r;
    }
    public static R setERROR(String msg){
        R r=new R();
        r.setCode(1);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }
    public static R setR(boolean issuccess){
        if(issuccess){
            return setOK();
        }else {
            return setERROR();
        }
    }
}
