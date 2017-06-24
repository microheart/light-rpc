package com.iknowers.rpc.example.client;

import com.iknowers.rpc.client.RpcProxy;
import com.iknowers.rpc.example.api.HelloService;
import com.iknowers.rpc.example.api.Person;
import com.iknowers.rpc.example.client.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloClient2 {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        RpcProxy rpcProxy = context.getBean(RpcProxy.class);

        HelloService helloService = rpcProxy.create(HelloService.class);
        String result = helloService.hello(new Person("Yong", "Huang"));
        System.out.println(result);

        System.exit(0);
    }
}
