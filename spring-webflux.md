# Spring WebFlux

## 기존 user-service 를 webflux 로 변경하기

- build.gradle
```gradle
buildscript {
    ext {
        springBootVersion = '2.2.1.RELEASE'
    }
//...

ext {
//	springCloudServicesVersion = '2.0.1.RELEASE'
	springCloudServicesVersion = '2.1.4.RELEASE'
//	springCloudVersion = 'Finchley.RELEASE'
    set('springCloudVersion', "Hoxton.RELEASE")
}

dependencies {

  //compile "org.springframework.boot:spring-boot-starter-web"
  compile "org.springframework.boot:spring-boot-starter-webflux"

//    testCompile "org.springframework.boot:spring-boot-starter-test"
//    testCompile "junit:junit"
    testCompile "io.projectreactor:reactor-test"

    compile "org.springframework.cloud:spring-cloud-starter-security"

}
```

% 주의 : Pivotal Web Service 에서 Spring Cloud Service를 사용하는 경우 무조건 WebFluxSecurity 를 추가해줘야 한다.

```java
@EnableWebFluxSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        return http.build();
    }
```

## RestController
<!--
	return Mono.just(ResponseEntity.status(HttpStatus.OK).body(userResponse));
-->
```java
  return ResponseEntity.status(HttpStatus.OK).body(Mono.just(userResponse)); 
```