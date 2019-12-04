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
}
```


### Repository
  - CrudRepository --> ReactiveCrudRepository

### Service
  - User --> Mono<User>

###