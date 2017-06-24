package com.iknowers.rpc.example.client.config;

import com.iknowers.rpc.client.RpcProxy;
import com.iknowers.rpc.registry.ServiceDiscovery;
import com.iknowers.rpc.registry.zookeeper.ZooKeeperServiceDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:rpc.properties")
public class ApplicationConfig {

    @Bean
    public ServiceDiscovery serviceDiscovery(@Value("${rpc.registry_address}") String zookeeperAddress ) {
        return new ZooKeeperServiceDiscovery(zookeeperAddress);
    }

    @Bean
    @Autowired
    public RpcProxy rpcProxy(ServiceDiscovery serviceDiscovery) {
        return new RpcProxy(serviceDiscovery);
    }
}
