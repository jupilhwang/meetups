# JIB

## Simple Helloworld Spring Boot Apps

  - starter.spring.io
    - Spring Reactive Web
    - Spring Actuator
    - Lombok

  ```kotlin

  @RestController
  class HelloController {
    @GetMapping("/hello/{name}") 
    fun getMessage(@PathVariable name: String): Mono<String>  {
      return "Hello World !!! ${name}".toMono()
    }
  }

  ```

  build.gradle.kt
  ```gradle
  ...
  plugins {
    ...
    id("com.google.cloud.tools.jib") version "1.8.0"
    ...
  }

  jib {
    from {
      image="openjdk:8-jdk-alpine"
    }
    to {
      image="namoo4u/jib-demo"
      auth {
        username=username
        password=password
      }
    }
    container {
      ports = listOf("8080")
      environments = mapOf(
        "SPRING_OUTPUT_ANSI_ENABLED" to "ALWAYS",
        "SPRING_CLOUD_BOOTSTRAP_LOCATION" to "/path/to/bootstrap.yml"
      )
      useCurrentTimestamp = true
    }
    setAllowInsecureRegistries(true)
  }
  ...
  ```

  - Docker Container Image 만들기
  ```bash
  $ gradle jib

  $ docker run -it --rm -p 8080:8080 namoo4u/jib-demo

  $ http :8080/hello/jhwang
  HTTP/1.1 200 OK
  Content-Length: 14
  Content-Type: text/plain;charset=UTF-8

  Hello World jp

  ```

