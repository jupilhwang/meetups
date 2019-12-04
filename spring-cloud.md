
## Spring Cloud
### The Standard for Cloud Native Java
- Spring Boot
- Spring Cloud
- Spring Data

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


<!--

#### Spring Cloud CLI
```bash
# SDKman use
# sdk install springboot 2.1.4.RELEASE

$ spring version
Spring CLI v2.2.1.RELEASE

# Install the Spring Cloud plugin:
$ spring install org.springframework.cloud:spring-cloud-cli:2.2.0.BUILD-SNAPSHOT
Installing into: /Users/jhwang/.sdkman/candidates/springboot/2.2.1.RELEASE/lib/ext

# spring cloud -list to list available services

# launch default set of services
$ spring cloud eureka configserver h2 kafka zipkin 
``

- Eureka Server : http://localhost:8761 : for service registration and discovery. All the other service show up in it catalog by default
- Config server : [http://localhost:8888](http://localhost:8888/actuator/info) : running in the native profile and serving configuration from the local diretory ./launcher
- H2 database : 9095 port(console) : Relation database service, Use a file path for {data}
- Hystrix Dashboard : http://localhost:7979 : Any Spring Cloud app that declares Hystrix circuit breakers publishes metrics on /hystrix.stream. Type that address into the dashboad to visualize all the metrics

#### Writing Simple application 

app.groovy
```groovy
@EnableDiscoveryClient
@RestController
class Service {
  @GetMapping('/')
  def helloworld() {
    [message: 'Hello']
  }
}
```

-->


## Pivotal Bank Demo 
- PWS
  https://github.com/jupilhwang/pivotal-bank-demo-kr

  ![](https://raw.githubusercontent.com/jupilhwang/pivotal-bank-demo-kr/master/docs/base-architecture-diagram.png)




# BUT
https://spring.io/blog/2018/12/12/spring-cloud-greenwich-rc1-available-now

|Current|REPLACEMENT|
|---|---|
|Hystrix|Resillence4j|
|Hystrix Dashboard / Turbine|Micrometer + Monitoring System|
|Riboon|Spring Cloud Loadbalancer|
|Zuul 1|Spring Cloud Gateway|
|Archaius 1|Spring Boot external config + Spring Cloud Config|


Hystrix and Ribbon --> **maintenance mode**

"Placing a module in maintenance mode means that the Spring Cloud team will no longer be adding new features to the module. We will fix blocker bugs and security issues, and we will also consider and review small pull requests from the community."

- Spring Cloud Circuit Breaker 
  - supprts : Netflix Hystrix, Resilience4j, Sentinel and Spring Retry

#### Netflix OSS and Spring Boot — Coming Full Circle
![](https://cdn-images-1.medium.com/max/800/1*5OHBUUDITAouvo1tWyxG1g.jpeg)
source : https://medium.com/netflix-techblog/netflix-oss-and-spring-boot-coming-full-circle-4855947713a0