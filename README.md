# light-rpc
A lightweight RPC framework based on Netty, ZooKeeper and Spring


## 技术选型

- Spring：依赖注入框架(IOC & DI)。
- Netty：高性能网络通信框架。
- Protostuff：基于 Protobuf 序列化框架。
- ZooKeeper：分布式协调服务，提供服务注册与发现。

## 框架说明

### 定义接口

#### 接口

    package com.iknowers.rpc.example.api;
    
    public interface HelloService {
    
        String hello(String name);
    
        String hello(Person person);
    }


### 发布RPC服务

#### 接口依赖
    ...
    <parent>
        <groupId>com.iknowers</groupId>
        <artifactId>rpc</artifactId>
        <version>1.0.0</version>
    </parent>

    <artifactId>rpc-example-server</artifactId>

    <dependencies>
        <!-- RPC Example API -->
        <dependency>
            <groupId>com.iknowers</groupId>
            <artifactId>rpc-example-api</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!-- RPC Server -->
        <dependency>
            <groupId>com.iknowers</groupId>
            <artifactId>rpc-server</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>
    ...

- `rpc-example-api`: 接口定义模块
- `rpc-server`：RPC服务端框架
实现接口，并通过RPC服务端框架提供RPC服务。

#### RPC服务端配置

    <context:property-placeholder location="classpath:rpc.properties"/>

    <bean id="serviceRegistry" class="com.iknowers.rpc.registry.zookeeper.ZooKeeperServiceRegistry">
        <constructor-arg name="zkAddress" value="${rpc.registry_address}"/>
    </bean>

    <bean id="rpcServer" class="com.iknowers.rpc.server.RpcServer">
        <constructor-arg name="serviceAddress" value="${rpc.service_address}"/>
        <constructor-arg name="serviceRegistry" ref="serviceRegistry"/>
    </bean>

rpc.properties

    rpc.service_address=127.0.0.1:8000
    rpc.registry_address=127.0.0.1:2181
    
- rpc.service_address：RPC服务发布地址
- rpc.registry_address：ZooKeeper服务器地址

#### 启动RPC服务

    public class RpcBootstrap {
    
        private static final Logger LOGGER = LoggerFactory.getLogger(RpcBootstrap.class);
    
        public static void main(String[] args) {
            LOGGER.debug("start server");
            new ClassPathXmlApplicationContext("spring.xml");
        }
    }
    
### RPC服务客户端

#### 接口依赖

    <parent>
        <groupId>com.iknowers</groupId>
        <artifactId>rpc</artifactId>
        <version>1.0.0</version>
    </parent>

    <artifactId>rpc-example-client</artifactId>

    <dependencies>
        ...
        <!-- RPC Example API -->
        <dependency>
            <groupId>com.iknowers</groupId>
            <artifactId>rpc-example-api</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!-- RPC Client -->
        <dependency>
            <groupId>com.iknowers</groupId>
            <artifactId>rpc-client</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>

- `rpc-example-api`: 接口定义模块
- `rpc-client`：RPC客户端框架

#### RPC客户端配置

    <context:property-placeholder location="classpath:rpc.properties"/>

    <bean id="serviceDiscovery" class="com.iknowers.rpc.registry.zookeeper.ZooKeeperServiceDiscovery">
        <constructor-arg name="zkAddress" value="${rpc.registry_address}"/>
    </bean>

    <bean id="rpcProxy" class="com.iknowers.rpc.client.RpcProxy">
        <constructor-arg name="serviceDiscovery" ref="serviceDiscovery"/>
    </bean>
    
rpc.properties

    rpc.registry_address=127.0.0.1:2181
    
- rpc.registry_address：ZooKeeper服务器地址

#### 调用RPC服务

    public class HelloClient {
    
        public static void main(String[] args) throws Exception {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
            RpcProxy rpcProxy = context.getBean(RpcProxy.class);
    
            HelloService helloService = rpcProxy.create(HelloService.class);
            String result = helloService.hello("World");
            System.out.println(result);
    
            System.exit(0);
        }
    }