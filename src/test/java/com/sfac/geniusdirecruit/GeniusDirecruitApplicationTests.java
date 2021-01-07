package com.sfac.geniusdirecruit;

import com.sfac.geniusdirecruit.common.utile.SmsSend;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeniusDirecruitApplication.class)
class GeniusDirecruitApplicationTests {

    @Autowired
    SmsSend smsSend;

//    @Autowired
//    @Test
//    void contextLoads() {
//
//    }

    @Test
    public void smsSend(){
        Random rd = new Random();
        int code = rd.nextInt(1000);
        String info = smsSend.send("13036593571",code+"");


    }




//    测试发送短信成功
/*    public static class SendSms {
        public static void main(String[] args) {
            DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G6V3pjuAZroxueWKyGZ", "Bxru1Cnrdzv2evWTA9ogOMYrVAq47o");
            IAcsClient client = new DefaultAcsClient(profile);

            CommonRequest request = new CommonRequest();
            request.setSysMethod(MethodType.POST);
            request.setSysDomain("dysmsapi.aliyuncs.com");
            request.setSysVersion("2017-05-25");
            request.setSysAction("SendSms");

            request.putQueryParameter("RegionId", "cn-hangzhou");
            //接收人电话
            request.putQueryParameter("PhoneNumbers","13036593571");

            request.putQueryParameter("SignName","PHOENIX商城");
            request.putQueryParameter("TemplateCode","SMS_205885553");
            request.putQueryParameter("TemplateParam","{'code':'1223'}");

            try {
                CommonResponse response = client.getCommonResponse(request);
                System.out.println(response.getData());
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }
    }*/



}
