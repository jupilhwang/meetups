# Choose a JDK
<!-- 
https://www.youtube.com/watch?v=YTPUNesUIbI
-->
컨테이너에서는 어떤 버전의 JDK를 사용하나요?

## Container Aware
  - OpenJDK 8u192 or above
  - JDK 11 lts 


### HeapMemory

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

Native Memory on 8u232
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



```bash
docker run -it --rm -m 256m -c 2 openjdk:8u232-slim java -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal -XX:+PrintActiveCpus -XX:+UseParallelGC -XX:NativeMemoryTracking=summary -XX:+PrintNMTStatistics

Native Memory Tracking:

Total: reserved=1459239KB, committed=38667KB
-                 Java Heap (reserved=129024KB, committed=8192KB)
                            (mmap: reserved=129024KB, committed=8192KB)

-                     Class (reserved=1058924KB, committed=6636KB)
                            (classes #451)
                            (malloc=2156KB #129)
                            (mmap: reserved=1056768KB, committed=4480KB)

-                    Thread (reserved=10300KB, committed=10300KB)
                            (thread #10)
                            (stack: reserved=10256KB, committed=10256KB)
                            (malloc=32KB #53)
                            (arena=12KB #18)

-                      Code (reserved=249640KB, committed=2576KB)
                            (malloc=40KB #327)
                            (mmap: reserved=249600KB, committed=2536KB)

-                        GC (reserved=7032KB, committed=6644KB)
                            (malloc=2312KB #108)
                            (mmap: reserved=4720KB, committed=4332KB)

-                  Compiler (reserved=132KB, committed=132KB)
                            (malloc=1KB #24)
                            (arena=131KB #5)

-                  Internal (reserved=2259KB, committed=2259KB)
                            (malloc=2227KB #1323)
                            (mmap: reserved=32KB, committed=32KB)

-                    Symbol (reserved=1462KB, committed=1462KB)
                            (malloc=942KB #90)
                            (arena=520KB #1)

-    Native Memory Tracking (reserved=36KB, committed=36KB)
                            (malloc=3KB #35)
                            (tracking overhead=33KB)

-               Arena Chunk (reserved=431KB, committed=431KB)
                            (malloc=431KB)

```


  


## OOMKilled
  (Cloud Foundry Buildpack Memory Calculator)
  https://github.com/cloudfoundry/java-buildpack-memory-calculator

![](img/java-buildpack-memory-calculator.png)

    - thread-count
    - total-memory 

```bash
java-buildpack-memory-calculator --loaded-class-count=1000 --thread-count=100 --total-memory=512M --jvm-options "-Xss512k"
-XX:MaxDirectMemorySize=10M -XX:MaxMetaspaceSize=19335K -XX:ReservedCodeCacheSize=240M -Xmx197752K
```
- JAVA_OPTIONS 에 적용
