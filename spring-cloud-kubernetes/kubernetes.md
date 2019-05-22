## Kubernetes

"두말하면 잔소리" - 플랫폼이라고 부르는 인프라 추상화의 사실상 표준

### VM vs Container
수많은 Hypervisor를 사용한는 VM의 IaaS/Storage/Network이나 Bare-metal환경에서  어플리케이션을 다른 환경으로 이동하기에 힘들다.

다른 인프라환경이라 하더라도 Container-Orchestration 환경에서 Application을 위한 추상화 Layer를 제공하여 어플리케이션을 다른 환경으로 손쉽게 이동하기에 쉽다.

### Kubernetes의 등장 (2014)
![](https://logos-download.com/wp-content/uploads/2018/09/Kubernetes_Logo.png)
- Docker(2013)등장 이후
- Container orchestration system
- Google Borg System에 영감을 받아서 만들었다고 함
- v1.0 : July 21, 2015
- 커뮤니티의 힘, echo systems

#### CNCF landscape - Kubernetes Echosystems
![](img/k8s-cncf-landscape.png)

source: https://landscape.cncf.io/images/landscape.png


#### A basic overview of the kubernetes architecture
![](img/k8s-basic-arch.jpg)

source: Kubernetes In Action (book)

#### Kubernetes architecture diagram
![](https://upload.wikimedia.org/wikipedia/commons/b/be/Kubernetes.png)

source: https://en.wikipedia.org/wiki/Kubernetes

#### Kubernetes Objects
- Pods
- Services
- Volumnes
  - persistentvolume
  - persistentvolumeclaim
  - storageclass
- Namespacs
- Labels and selector
- 다양한 배포방식
  - Daemon set
  - Deployment / Replicaset
  - Stateful Sets / Replicaset
  - Cronjob / job
  - Replication Controller
- Configmaps / Secrets
- Ingress / Ingress Controller
- CRD (Custom Resource Definition)
- HPA (Horizontal Pod Autoscaling)
- Interfaces
  - CRI (Container Runtime Interface) : container-d, rkt, cri-o
  - CNI (Container Network Interface) : flannel, weave, cillium, nsx-t
  - CSI (Container Storage Interface)

#### Kubernetes Services

![](https://techblog.rakuten.co.jp/images/k8s-routing/k8s-routing_ingress.png)

출처: [Kubernetes Traffic Routing](https://techblog.rakuten.co.jp/2017/09/28/k8s-routing2/)

