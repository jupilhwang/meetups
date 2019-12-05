# Deployment

## Create YML files
```bash
kubectl run nginx --image=nginx
```

```bash
kubectl create deployment demo-app --image=nginx --dry-run -o yaml > k8s/deployment.yaml

kubectl create service clusterip demo-app --tcp 8080:8080 --dry-run -o yaml > k8s/service.yaml
```

## Apply YML Files
```bash
kubectl apply -f k8s/

kubectl port-foward service/demo-app 8080:8080
```

## Resource Limits

```yaml
    resources:
      requests:
        memory: 256M
        cpu: 1000m
      limits:
        memory: 512M
        cpu: 1000m
```

## livenessProbe  / ReadinessProbe

```yaml
    livenessProbe:
      initialDelaySeconds: 30s
      httpGet:
        port: 8080
        path: /actuator/health
    readinessProbe:
      httpGet:
        port: 8080
        path: /actuator/health
      periodSeconds: 5
      failureThreshold: 1
```


또는

## Using Skaffold
- Google 에서 만들 CI(?) Pipeline for K8s

https://skaffold.dev/


- skaffold.yaml
```yaml
apiVersion: skaffold/v1beta14
kind: Config
build:
  artifacts:
    - image: namoo4u/jib-demo
      jib: {}
        args: ['-x test']
        type: gradle
deploy:
  kubectl:
    manifests:
      - k8s/*.yaml
```

- run skaffold dev
```bash
skaffold dev
```

  - Reference Links: https://skaffold.dev/docs/references/yaml/


## Cloud Code
- 만약 IntelliJ IDEA 를 사용하면 "Cloud Code" Plugin 을 사용해 보자

