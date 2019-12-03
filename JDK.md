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

```
docker run -it --rm --memory=256m --cpus=1 openjdk:8u232-slim java -XX:+PrintFlagsFinal | grep MaxHeap
    uintx MaxHeapFreeRatio                          = 70                                  {manageable}
    uintx MaxHeapSize                              := 132120576                           {product}
```


## JMV Options 

### Natvie Memory Tracking
  - -XX:NativeMemoryTracking=summary --XX:+PrintNMTStatics


  


## OOMKilled
  (Cloud Foundry Buildpack Memory Calculator)
  https://github.com/cloudfoundry/java-buildpack-memory-calculator


