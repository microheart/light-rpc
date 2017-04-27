package net.cocloud.rpc.example.server;

import net.cocloud.rpc.example.api.HelloService;
import net.cocloud.rpc.example.api.Person;
import net.cocloud.rpc.server.RpcService;

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
