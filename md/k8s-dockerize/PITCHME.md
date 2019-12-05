# Dockerizing

## Using Dockerfile & docker build
### Downloading the application to Dockerize
참고: 
https://www.callicoder.com/spring-boot-websocket-chat-example/

```
git clone https://github.com/callicoder/spring-boot-websocket-chat-demo
```

### Docker
```dockerfile
# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine
# Add Maintainer Info
LABEL maintainer="callicoder@gmail.com"
# Add a volume pointing to /tmp
VOLUME /tmp
# Make port 8080 available to the world outside this container
EXPOSE 8080
# The application's jar file
ARG JAR_FILE=target/websocket-demo-0.0.1-SNAPSHOT.jar
# Add the application's jar to the container
ADD ${JAR_FILE} websocket-demo.jar
ENV SPRING_OPTS=""
ENV JAVA_OPTS="-Xmx128m -Djava.security.egd=file:/dev/./urandom $SPRING_OPTS"
# Run the jar file 
ENTRYPOINT ["java","$JAVA_OPTS","-jar","/websocket-demo.jar"]
```

```
$ docker build -t spring-boot-websocket-chat-demo .
```
or
```
$ mvn package
```

### docker-maven-plugin
![](https://raw.githubusercontent.com/fabric8io/fabric8-maven-plugin/master/doc/sample-demo.gif)

```xml
  <plugin>
    <groupId>io.fabric8</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>${fmp.version}</version>
  </plugin>
```
or fabric8-maven-plugin
```xml
      <plugin>
        <groupId>io.fabric8</groupId>
        <artifactId>fabric8-maven-plugin</artifactId>
        <version>${fabric8.maven.plugin.version}</version>
        <executions>
          <execution>
            <goals>
              <goal>resource</goal>
              <goal>build</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
```

Build Docker images
```bash
mvn package docker:build
```
or
```bash
mvn fabric8:build
```

### docker-gradle-plugin

```build
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.bmuschko:gradle-docker-plugin:3.0.3'
    }
}

apply plugin: 'com.bmuschko.docker-remote-api'
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage

docker {
    if (System.env.containsKey('DOCKER_HOST') && System.env.containsKey('DOCKER_CERT_PATH')) {
        url = System.env.DOCKER_HOST.replace("tcp", "https")
        certPath = new File(System.env.DOCKER_CERT_PATH)
    }
}

task buildImage(type: DockerBuildImage) {
    dependsOn assemble
    inputDir = project.rootDir
    tag = "arquillian/game-service:${project.version}"
}
```

