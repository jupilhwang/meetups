@EnableDiscoveryClient
@RestController
@EnableHystrix
class Service {
  @GetMapping('/')
  def helloworld() {
    [message: 'Hello']
  }
}
