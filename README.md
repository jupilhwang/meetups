---
marp: true
---
# Meetups for Pivotal user group

00:00:00 - Intro
00:00:00 - Spring Cloud
00:00:00 - Reactive
00:00:00 - Kubernetes


  Contents : http://bit.ly/2OO3AOq

---
- Who am I?
```
{[
  {"name":"황주필, Jupil Hwang"},
  {"email":"jhwang@pivotal.io"},
  {"title":"Senior Platform Architect"},
  {"twitter-account":"@jupilhwang"},
  {"github-account":"jupilhwang"}
]}
```
---
- Spring Boot for Cloud Native Application
  - [Microservice / Cloud Native Application](msa.md)
  - [Pivotal Bank Demo Apps](https://github.com/jupilhwang/pivotal-bank-demo-kr)
  - [Traditional Spring Cloud](spring-cloud.md)
---
- [Reactive](Reactive/Reactive.md)
  - [Spring WebFlux](spring-webflux.md)
  - RSocket
  - R2DBC
  - Spring Cloud Gateway 
    - [Spring Cloud Gateway Overview](https://cloud.spring.io/spring-cloud-gateway/reference/html/)
    - [Spring Cloud Gateway](https://github.com/jupilhwang/pivotal-bank-demo-kr/blob/master/docs/lab_spring_cloud_gateway.md)
        - [gateway-service](spring-cloud-gateway.md)

---
- Spring Cloud Kubernetes
  - [Kubernetes](spring-cloud-kubernetes/kubernetes.md)
  - [어떤 JVM을 선택할 것인가?](JDK.md)
  - [Containerized Image는 어떻게 만드나?](spring-cloud-kubernetes/dockerize.md)
    - 그냥 Dockerfile
    - Source2Image 를 사용
    - [JIB 사용](jib.md)
    - [Cloud Native Buildpack](cloud-native-buildpack.md)
  - [애플리케이션 배포](spring-cloud-kubernetes/deployment.md)
    - [kpack](spring-cloud-kubernetes/kpack.md)
  - [Spring Cloud Kubernetes](spring-cloud-kubernetes/spring-cloud-kubernetes.md)

---
- [Survey](survey.md)