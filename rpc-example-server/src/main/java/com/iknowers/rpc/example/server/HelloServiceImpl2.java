package com.iknowers.rpc.example.server;

import com.iknowers.rpc.example.api.HelloService;
import com.iknowers.rpc.example.api.Person;
import com.iknowers.rpc.server.RpcService;

@RpcService(value = HelloService.class, version = "sample.hello2")
public class HelloServiceImpl2 implements HelloService {

    @Override
    public String hello(String name) {
        return "你好! " + name;
    }

    @Override
    public String hello(Person person) {
        return "你好! " + person.getFirstName() + " " + person.getLastName();
    }
}
