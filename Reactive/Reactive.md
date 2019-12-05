  
# Reactive

## Reactive Foundation
<img src=../img/reactivefoundation.png width=40%>

<pre>
The Linux Foundation has announced the launch of the Reactive Foundation, a new community that aims to promote the use of reactive programming in networked applications.

The founder members of the new foundation are Alibaba, Facebook, Lightbend, Netifi and Pivotal, and the foundation is taking under its wing open source reactive specifications starting with RSocket.
</pre>

- Member
![](../img/reactivefoundation-members.png)
Facebook ...

- Projects
![](../img/rsocket.svg)


## R2DBC 1.0 RC1 GA (https://r2dbc.io/)

- ConnectionFactory
- ResourceDdatab asePopulator / ScriptUtils
- TransactionDefinition
- Entity-level converter
- Kotlin extensions for reifed generics and Coroutines
- Add pluggable mechanism to register dialects
- API refinements

### Supported Database
- PostgreSQL
- SQL Server
- MySQL
- H2
- Spanner


```xml
<dependency>
  <groupId>org.springframework.data</groupId>
  <artifactId>spring-data-r2dbc</artifactId>
  <version>${version}.RELEASE</version>
</dependency>
```

- the latest snapshots
```xml
<dependency>
  <groupId>org.springframework.data</groupId>
  <artifactId>spring-data-r2dbc</artifactId>
  <version>${version}.BUILD-SNAPSHOT</version>
</dependency>

<repository>
  <id>spring-libs-snapshot</id>
  <name>Spring Snapshot Repository</name>
  <url>https://repo.spring.io/libs-snapshot</url>
</repository>
```

```java
public interface PersonRepository extends CrudRepository<Person, Long> {

  @Query("SELECT * FROM person WHERE lastname = :lastname")
  Flux<Person> findByLastname(String lastname);

  @Query("SELECT * FROM person WHERE firstname LIKE :lastname")
  Flux<Person> findByFirstnameLike(String firstname);
}

@Service
public class MyService {

  private final PersonRepository repository;

  public MyService(PersonRepository repository) {
    this.repository = repository;
  }

  public void doWork() {

    repository.deleteAll().block();

    Person person = new Person();
    person.setFirstname("Mark");
    person.setLastname("Paluch");
    repository.save(person).block();

    Flux<Person> lastNameResults = repository.findByLastname("Paluch");
    Flux<Person> firstNameResults = repository.findByFirstnameLike("M%");
 }
}

@Configuration
@EnableR2dbcRepositories
class ApplicationConfig extends AbstractR2dbcConfiguration {

  @Bean
  public ConnectionFactory connectionFactory() {
    return ConnectionFactories.get("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
  }
}
```