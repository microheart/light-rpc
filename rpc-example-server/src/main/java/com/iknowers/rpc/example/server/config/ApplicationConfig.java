package com.iknowers.rpc.example.server.config;

import com.iknowers.rpc.registry.ServiceRegistry;
import com.iknowers.rpc.registry.zookeeper.ZooKeeperServiceRegistry;
import com.iknowers.rpc.server.RpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:rpc.properties")
@ComponentScan("com.iknowers.rpc.example.server.service")
public class ApplicationConfig {

    @Bean
    public ServiceRegistry serviceRegistry(@Value("${rpc.registry_address}") String zookeeperAddress ) {
        System.out.println(zookeeperAddress);
        return new ZooKeeperServiceRegistry(zookeeperAddress);
    }

    @Bean
    @Autowired
    public RpcServer RpcServer(@Value("${rpc.service_address}") String serviceAddress, ServiceRegistry serviceRegistry) {
        System.out.println(serviceAddress);
        return new RpcServer(serviceAddress, serviceRegistry);
    }
}
