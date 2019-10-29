package com.feri.openapi.filter;

import com.alibaba.fastjson.JSON;
import com.feri.openapi.config.RedisKeyConfig;
import com.feri.openapi.dto.SmsDto;
import com.feri.openapi.redis.RedissionUtil;
import com.feri.openapi.util.RUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 15:17
 */
@Component
public class SmsUrlFilter extends ZuulFilter {
    //类型 前：pre  后：post  转发中：routing  错误：error
    @Override
    public String filterType() {
        return "PRE";
    }
    //优先级 越小优先级越高
    @Override
    public int filterOrder() {
        return 1;
    }
    //是否启用该过滤器
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //核心 过滤处理
    /**
     *  1分钟内短信发送条数不超过：1
     * 	1小时内短信发送条数不超过：5
     * 	1个自然日内短信发送条数不超过：10
     * 	Redis
     * 	*/
    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request=requestContext.getRequest();
        HttpServletResponse response=requestContext.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //1、校验是否在请求短信发送接口
        if(request.getRequestURI().endsWith("api/sms/sendcode.do")){
            try {
                InputStream is=request.getInputStream();
                StringBuffer buffer=new StringBuffer();
                byte[] data=new byte[1024];
                int len;
                while ((len=is.read(data))!=-1){
                    buffer.append(new String(data,0,len));
                }
                //2、获取手机号
                SmsDto smsDto= JSON.parseObject(buffer.toString(),SmsDto.class);
                String phone=smsDto.getReceivePhone();
                //3、校验 从大到小 天 小时 分钟
                if(RedissionUtil.checkKey(RedisKeyConfig.SMS_DAY+phone)){
                    //校验今日有没有超出
                    int c=Integer.parseInt(RedissionUtil.getStr(RedisKeyConfig.SMS_DAY+phone));
                    if(c>=10){
                        //今日已达上限  拦截
                        requestContext.setSendZuulResponse(false);
                        response.getWriter().println(JSON.toJSONString(RUtil.setERROR("今日短信发送已达上限")));
                    }
                }
                if(RedissionUtil.checkKey(RedisKeyConfig.SMS_HOU+phone)){
                    //校验今日有没有超出
                    int c=Integer.parseInt(RedissionUtil.getStr(RedisKeyConfig.SMS_HOU+phone));
                    if(c>=5){
                        //本小时已达上限  拦截
                        requestContext.setSendZuulResponse(false);
                        response.getWriter().println(JSON.toJSONString(RUtil.setERROR("当前小时短信发送已达上限")));

                    }
                }
                if(RedissionUtil.checkKey(RedisKeyConfig.SMS_MIN+phone)){
                    //一分钟发过短信 不再发送 拦截
                    requestContext.setSendZuulResponse(false);
                    response.getWriter().println(JSON.toJSONString(RUtil.setERROR("一分钟内只能发送一次")));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return null;
    }
}
