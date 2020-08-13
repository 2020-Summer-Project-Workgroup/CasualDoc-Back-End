package com.sprint.summerproject.services;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sprint.summerproject.storages.CodeStorage;
import com.sprint.summerproject.utils.CodeGenerator;

public class TelService {

    private CodeStorage codeStorage;

    public TelService(CodeStorage codeStorage) {
        this.codeStorage = codeStorage;
    }

    public void sendCode(String tel) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        String code = CodeGenerator.generateCode();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", tel);
        request.putQueryParameter("SignName", "轻松文档");
        request.putQueryParameter("TemplateCode", "SMS_199222535");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");

        CommonResponse response = client.getCommonResponse(request);
        System.out.println(response.getData());
        codeStorage.put(tel, code);
    }

    public String retrieveCode(String tel) {
        return codeStorage.get(tel);
    }

}