# Spring Cloud Kubernetes

![](spring-cloud-kubernetes/img/spring-cloud-k8s.png)

### ConfigMaps
- [ConfigMap/Secret](configmaps.md)

### Istio
- [Istio](istio.md)

### Spring Cloud Kubernetes

https://github.com/spring-cloud/spring-cloud-kubernetes

- Service Discovery
  - Spring Discovery Client using Kubernetes Service Discovery

- ConfigMap Property Source
  - Use Kubernetes ConfigMap/Secrets as Spring Property Source
  - Reload application properties when a ConfigMap or Secret changes

- Client-side Load Balaner
  - Using server list obtained from Kubernetes Endpoints

- Zipkin Service Discovery
  - Using Zipkin with Kubernetes for distributed tracing

#### Samples
- DiscoveryClient for Kubernetes
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-kubernetes</artifactId>
</dependency>
```
DiscoveryClient를 사용하기위해 @EnableDiscoveryClient를 추가
```java
@SpringBootApplication
@EnableDiscoveryClient
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
```
inject the client in a code
```java
@Autowired
private DiscoveryClient discoveryClient;
```

- Ribbon Discovery for Kubernetes
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-kubernetes-ribbon</artifactId>
</dependency>
```

