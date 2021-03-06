package com.iknowers.rpc.example.server.service;

import com.iknowers.rpc.example.api.HelloService;
import com.iknowers.rpc.example.api.Person;
import com.iknowers.rpc.server.RpcService;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String hello(Person person) {
        return String.format("Hi, %s %s,  welcome to RPC world.", person.getFirstName(), person.getLastName());
    }
}
