
## Spring Cloud
### Spring by Pivotal : The Standard for Cloud Native Java
- Spring Boot
- Spring Cloud
- Spring Cloud Data Flow

<!-- ### Spring Cloud Service
- Config Server for PCF
  - Delivers a dynamic, central configuration service to manager an application's external properties across all environments
- Service Registry for PCF
  - Provides an implementation of the NetflixOSS Eureka Service Discovery patter, as a service
- Circuit Breaker Dashboard for PCF
  - Visualizes a stream of Turbin health and metric data from the circuit breakers inside your microservices or applications
- Distributed Tracing -->

### Spring Cloud & Netflix OSS
![](https://cdn-images-1.medium.com/max/800/0*xqgKATYfpKsSnNJR.)


### Reference Architecture for Spring Cloud 
![](https://res.infoq.com/articles/spring-cloud-azure/en/resources/spring-cloud-azure-1541139702005.jpg)



#### Spring Cloud CLI
```bash
# SDKman use
# sdk install springboot 2.1.4.RELEASE

$ spring version
Spring CLI v2.1.4.RELEASE

# Install the Spring Cloud plugin:
$ spring install org.springframework.cloud:spring-cloud-cli:2.1.0.BUILD-SNAPSHOT
Installing into: /Users/jhwang/.sdkman/candidates/springboot/2.1.4.RELEASE/lib/ext

# spring cloud -list to list available services

# launch default set of services
$ spring cloud eureka configserver h2 zipkin hystrixdashboard
```

- Eureka Server : http://localhost:8761 : for service registration and discovery. All the other service show up in it catalog by default
- Config server : [http://localhost:8888](http://localhost:8888/actuator/info) : running in the native profile and serving configuration from the local diretory ./launcher
- H2 database : 9095 port(console) : Relation database service, Use a file path for {data}
- Hystrix Dashboard : http://localhost:7979 : Any Spring Cloud app that declares Hystrix circuit breakers publishes metrics on /hystrix.stream. Type that address into the dashboad to visualize all the metrics

#### Writing Simple application 
```java
@EnableEurekaClient
@RestController
class Service {
  @GetMapping('/')
  def helloworld() {
    [message: 'Hello']
  }
}
```