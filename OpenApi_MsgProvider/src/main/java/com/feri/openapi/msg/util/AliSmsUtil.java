package com.feri.openapi.msg.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.feri.openapi.myenum.SmsTemCode;

/**
 * @program: OpenApi
 * @description:
 * @author: Feri
 * @create: 2019-10-29 10:15
 */
public class AliSmsUtil {
    public static String sendSms(String temCode,String phone,String msg){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAIhTvqTSmlmjeQ",
                "X7X9w0Ck5GEIWgP9tl0Q6sgmFjQuMv");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "18515990152");
        request.putQueryParameter("SignName", "来自邢朋辉的短信");
        request.putQueryParameter("TemplateCode", temCode);
        request.putQueryParameter("TemplateParam", msg);
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
