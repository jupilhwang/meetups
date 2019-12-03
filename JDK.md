# Choose a JDK
https://www.youtube.com/watch?v=YTPUNesUIbI
어떤 버전의 JDK를 사용하나요?

## Container Aware
  - OpenJDK 8u192 or above
  - 


```bash
docker run -it --rm --memory=256m --cpus=1 openjdk:8u141-slim java -XX:+PrintFlagsFinal | grep MaxHeap
    uintx MaxHeapFreeRatio                          = 100                                 {manageable}
    uintx MaxHeapSize                              := 1035993088                          {product}
```
- cgroup으로 256m를 지정하였으나, 실제로는 1GB를 사용

### thread
```
docker run -it --rm --memory=256m --cpus=1 openjdk:8u141-slim java -XX:+PrintFlagsFinal | grep Thread
     bool BindGCTaskThreadsToCPUs                   = false                               {product}
     bool CompilerThreadHintNoPreempt               = true                                {product}
     intx CompilerThreadPriority                    = -1                                  {product}
     intx CompilerThreadStackSize                   = 0                                   {pd product}
    uintx ConcGCThreads                             = 0                                   {product}
     intx DefaultThreadPriority                     = -1                                  {product}
    uintx G1ConcRefinementThreads                   = 0                                   {product}
    uintx HeapSizePerGCThread                       = 87241520                            {product}
     bool LoadExecStackDllInVMThread                = true                                {product}
    uintx NewSizeThreadIncrease                     = 5320                                {pd product}
    uintx ParallelGCThreads                         = 4                                   {product}
     intx ThreadPriorityPolicy                      = 0                                   {product}
     bool ThreadPriorityVerbose                     = false                               {product}
    uintx ThreadSafetyMargin                        = 52428800                            {product}
     intx ThreadStackSize                           = 1024                                {pd product}
     bool TraceDynamicGCThreads                     = false                               {product}
     bool UseBoundThreads                           = true                                {product}
     bool UseDynamicNumberOfGCThreads               = false                               {product}
     bool UseLinuxPosixThreadCPUClocks              = true                                {product}
     bool UseThreadPriorities                       = true                                {pd product}
     bool VMThreadHintNoPreempt                     = false                               {product}
     intx VMThreadPriority                          = -1                                  {product}
     intx VMThreadStackSize                         = 1024                                {pd product}
```
```java
...
  Runtime.getAvailableProcessor()
...
```

Native Memory

```bash
docker run -it --rm --memory=256m --cpus=1 openjdk:8u232-slim java -XX:+PrintFlagsFinal | grep MaxHeap
    uintx MaxHeapFreeRatio                          = 70                                  {manageable}
    uintx MaxHeapSize                              := 132120576                           {product}
```

### CGroup Memroy Limit for JVM Heap

```bash
docker run -it --rm -m 1g openjdk:8u232-slim java -XX:+PrintFlagsFinal -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap | grep -i maxheap

    uintx MaxHeapFreeRatio                          = 70                                  {manageable}
    uintx MaxHeapSize                              := 268435456                           {product}
```

- Use all of the RAM for heap  --> 조심조심
```
docker run -it --rm -m 1g openjdk:8u232-slim java -XX:+PrintFlagsFinal -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 | grep -i maxheap
```
- -XX:MaxRAM 설정 이나 -XX:MaxRAMFraction 은 권고하는 방법은 아니다

### JDK 10+ 에서는
- -XX:+UseCGroupMemroyLimitFroHeap        (**deprecated**)
- -XX:+UseContainerSupport                (**default**)
- -XX:MaxRAMPercentage

```bash
docker run -it --rm -m 1g openjdk:11-jdk-slim java -XX:+PrintFlagsFinal -XX:+UseContainerSupprt | grep -i maxheap
    uintx MaxHeapFreeRatio                         = 70                                     {manageable} {default}
   size_t MaxHeapSize                              = 268435456                                 {product} {ergonomic}


docker run -it --rm -m 1g openjdk:11-jdk-slim java -XX:+PrintFlagsFinal -XX:+UseContainerSupport -XX:MaxRAMPercentage=50 | grep -i maxheap
    uintx MaxHeapFreeRatio                         = 70                                     {manageable} {default}
   size_t MaxHeapSize                              = 536870912                                 {product} {ergonomic}
```
- -XX:MAXRAMPercentage 를 통해서 컨테이너 메모리의 몇퍼센티지를 Heap으로 할당할 지 정한다.




### Natvie Memory Tracking
  - -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics -XX:+UnlockDiagnosticVMOptions


  


## OOMKilled
  (Cloud Foundry Buildpack Memory Calculator)
  https://github.com/cloudfoundry/java-buildpack-memory-calculator


