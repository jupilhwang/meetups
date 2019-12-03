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
  ```kotlin
  ...
  plugins {
    ...
    id("com.google.cloud.tools.jib") version "1.8.0"
    ...
  }

  jib {
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

  ```