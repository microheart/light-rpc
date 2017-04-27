package net.cocloud.rpc.example.server;

import net.cocloud.rpc.example.api.HelloService;
import net.cocloud.rpc.example.api.Person;
import net.cocloud.rpc.server.RpcService;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String hello(Person person) {
        return "Hello! " + person.getFirstName() + " " + person.getLastName();
    }
}
