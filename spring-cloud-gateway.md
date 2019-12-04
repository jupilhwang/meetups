# Spring Cloud Gateway

```java
@Bean
	public RouteLocator customGatewayRouteLoator(RouteLocatorBuilder builder)	{
		return builder.routes()
				.route("quotes_route", r -> r.path("/v1/**")
						.filters(f -> f.addResponseHeader("X-Spring-Cloud-Meetup-Pivotal", "HelloWorld-Welcome"))
						.uri("lb://quotes-service"))
				.build();
	}
```

application.yaml
```yaml
spring:
  cloud:
    discovery:
      enabled: true
    gateway:
      discovery:
        locator:
          enabled: true
      routes:
      - id: user_route
        uri: http://httpbin.org
        predicates:
        - Host=**.pivotal.io
        filters:
        - name: Hystrix
          args:
            name: fallbackcmd
            fallbackUri: forwad:/myfallback
      - id: account_route
        uri: http://httpbin.org
        predicates:
        - Host=**.example.com
        filters:
        - name: CircuitBreaker
          args:
            name: myCircuitBreaker
            fallbackUri: forward:/myfallback          

```