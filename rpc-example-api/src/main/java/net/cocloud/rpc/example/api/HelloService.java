package net.cocloud.rpc.example.api;

public interface HelloService {

    String hello(String name);

    String hello(Person person);
}
