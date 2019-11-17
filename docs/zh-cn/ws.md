## ws

webservice工具类

### SoapOkHttpClient

- 同步请求
``` java
SoapOkHttpClient soapOkHttpClient = SoapOkHttpClient.create()
       .addInterceptor(new SoapRequestInteceptor())  // 添加拦截器
       .build();

// 多次请求
for (int i = 0; i < 3; i++) {
   SoapRequest soapRequest = SoapRequest.create()
        .url("http://www.webxml.com.cn/WebServices/ValidateEmailWebService.asmx?wsdl")
        .body(SoapBodyBuilders.create()
            .methodName("ValidateEmailAddress")
            .namespaceURI("http://WebXml.com.cn/")
            .addMethodParameter("theEmail", "656469722@qq.com")
            .build())
        .build();


   Response response = soapOkHttpClient.newCall(soapRequest).execute();

    if (response.isSuccessful()) {
        String content = SoapUtils.getContent(response.body().string());

        Assert.assertEquals("<ValidateEmailAddressResult xmlns=\"http://WebXml.com.cn/\">1</ValidateEmailAddressResult>", content);
    } else {
        System.out.println("异常:" + response.message());
    }

}
```

- 异步请求

``` java
SoapOkHttpClient soapOkHttpClient = SoapOkHttpClient.create()
                .addInterceptor(new SoapRequestInteceptor())  // 添加拦截器
                .build();

// 多次请求
for (int i = 0; i < 3; i++) {
    SoapRequest soapRequest = SoapRequest.create()
           .url("http://www.webxml.com.cn/WebServices/ValidateEmailWebService.asmx?wsdl")
           .body(SoapBodyBuilders.create()
                .methodName("ValidateEmailAddress")
                .namespaceURI("http://WebXml.com.cn/")
                .addMethodParameter("theEmail", "656469722@qq.com")
                .build())
           .build();

     soapOkHttpClient.newCall(soapRequest).enqueue(new Callback() {

           @Override
           public void onFailure(Call call, IOException e) {
                System.out.println("异常:" + e.getMessage());
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               String content = SoapUtils.getContent(response.body().string());

               Assert.assertEquals("<ValidateEmailAddressResult xmlns=\"http://WebXml.com.cn/\">1</ValidateEmailAddressResult>", content);

            }
     });

 }
```