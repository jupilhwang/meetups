
## ConfigMaps and Secrets
컨테이너에서 필요한 환경설정 내용을 컨테이너와 분리해서 제공해 주기 위한 기능으로 key/value 저장소이다. Secret는 암호화된(base64) 값으로 저장한다

### ConfigMaps
Kubernetes cluster 어디서든 어떤 형태로든지(커맨드라인 or 환경변수 or 파일 마운트) 사용할 수 있게 해준다. 또한 설정이 변경되었다고 하더라도, Application을 재배포하지 않아도 된다

#### ConfigMap 생성
- CLI로 생성
```bash
$ kubectl create configmap fortune-config --from-literal=sleep-interval=25 --from-literal=wakeup-interval=30
```
- yaml파일로 생성
```bash
$ kubectl apply -f fortune-config.yaml  
```
- 파일,디렉토리를 지정하여 생성
```bash
$ kubectl create configmap my-config --from-file=config-file.conf
$ kubectl create configmap my-config --from-file=customkey=config-file.conf
$ kubectl create configmap my-config --from-file=/path/to/dir
```
#### ConfigMap 사용
- ConfigMap as Environment variables
ConfigMap의 특정 값을 환경변수로 가져오기
```yml
apiVersion: v1
kind: Pod
metadata:
  name: fortune-env-from-configmap
spec: 
    containers:
    - image: luksa/fortune:env
      env:
      - name: INTERVAL
        valueFrom:
          configMapKeyRef:
            name: fortune-config
            key: sleep-interval
```
or

ConfigMap 전체를 환경변수로 가져오기
```yml
apiVersion: v1
kind: Pod
metadata:
  name: fortune-env-from-configmap
spec: 
    containers:
    - image: luksa/fortune:env
      envFrom:
      - configMapRef:
        name: fortune-config
```

- ConfigMap as Volumes
```yml
apiVersion: v1
kind: Pod
metadata:
  name: fortune-configmap-volume
spec:
  containers:
  - image: nginx:alpine
    name: web-server
    volumeMount: 
    ...
    - name: config
      mountPath: /etc/nginx/conf.d
      readOnly: true
    ...
  volumes:
  ...
  - name: config
    configMap:
      name: fortune-config
```

#### ConfigMap 의 reload
volume을 통해서 ConfigMap을 사용하게 되면, pod이나 container에 대한 재생성/재시작 없이 configuration 변경이 가능하다. 하지만 해당 configuration을 reload 할지 말지는 Application의 정책이지, Kubernetes가 이를 보장해주지는 않는다.

기본적으로 : 1분 이상 소요 (--sync-frequency=1m)


#### ConfigMap을 사용할 때
- Pod이 생성되는 시점에 Name 을 무조건 알고 있어야 한다. 
- Update Latency : 1 분 이상
- 데이터가 많아지면 편집하기가 쉽지 않다
- ConfigMapRef이 configmap 이 만들어져 있어야 한다. 
  - k8s 1.6에서 optional 추가


#### 만약 Dynamic reload를 원하면 Api Call로 직접 ConfigMap을 읽어와야 한다.
 