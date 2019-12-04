# Spring Cloud Kubernetes

![](img/spring-cloud-k8s.png)

<!--
### ConfigMaps
- [ConfigMap/Secret](configmaps.md)

### Istio
- [Istio](istio.md)

-->

## Spring Cloud Kubernetes

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

## Samples
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

## 주의 사항
- Role, RoleBinding, ServiceAccount

```log
io.fabric8.kubernetes.client.KubernetesClientException: Failure executing: GET at: https://kubernetes.default.svc/api/v1/namespaces/apps/services. Message: Forbidden!Configured service account doesn't have access. Service account may have been revoked. services is forbidden: User "system:serviceaccount:apps:default" cannot list services in the namespace "apps".
```

spring-cloud-kubernetes-clusterrole.yml
```yaml
kind: ClusterRole
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  name: service-discovery-client
rules:
- apiGroups: ["", "extensions", "apps"] # "" indicates the core API group
  resources: ["services", "pods", "configmaps", "endpoints", "secrets"]
  verbs: ["get", "watch", "list"]
```

또는 Role 을 생성하고 namespace를 지정할 수 있다.


```bash
# cluster-role 적용
kubectl apply -f spring-cloud-kubernetes-clusterrole.yml

# service accoiunt 에 role 바인딩
kubectl create rolebinding default:service-discovery-client --clusterrole service-discovery-client --serviceaccount <namespace>:<service account name>
```


** 참고 URL : https://github.com/spring-cloud/spring-cloud-kubernetes/blob/master/docs/src/main/asciidoc/security-service-accounts.adoc

