package com.iknowers.rpc.example.client;

import com.iknowers.rpc.client.RpcProxy;
import com.iknowers.rpc.example.api.HelloService;
import com.iknowers.rpc.example.client.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloClient {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        RpcProxy rpcProxy = context.getBean(RpcProxy.class);

        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello("World");
        System.out.println(result);

        HelloService helloService2 = rpcProxy.create(HelloService.class, "v2");
        String result2 = helloService2.hello("世界");
        System.out.println(result2);

        System.exit(0);
    }
}
