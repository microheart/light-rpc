package com.iknowers.rpc.example.server.service;

import com.iknowers.rpc.example.api.HelloService;
import com.iknowers.rpc.example.api.Person;
import com.iknowers.rpc.server.RpcService;

@RpcService(value = HelloService.class, version = "v2")
public class HelloServiceImpl2 implements HelloService {

    @Override
    public String hello(String name) {
        return "你好! " + name;
    }

    @Override
    public String hello(Person person) {
        return String.format("您好, %s %s,  欢迎来到RPC世界。", person.getFirstName(), person.getLastName());
    }
}
