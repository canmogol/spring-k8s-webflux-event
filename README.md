# Spring Boot Kubernetes

SpringBoot with K8S examples.

## Compile

Here are the steps to compile the Java application and create the docker image. Please see the "bootstrap.yaml" configuration file for K8S integration.

**How to compile Java application**
```bash
mvn clean install
```

**How to create Java docker image**
```bash
docker build -t spring-k8s .
```

## Deploy

Tag and push the docker image to remote repository.

```bash
docker tag spring-k8s:latest canmogol/spring-k8s:0.0.1
```

Apply the K8S configuration files.

```bash
# apply the RBAC; creates the 'config-reader' role.
kubectl apply -f k8s/rbac.yaml

# apply the ConfigMap; creates the 'spring-k8s-config' configuration.
kubectl apply -f k8s/configmap.yaml

# apply the Application; creates the service, deployment and the pod.
kubectl apply -f k8s/application.yaml
```

## Config Changes

Send a request to the http://spring-k8s-entrypoint/ endpoint to get the latest configuration changes.
```bash
curl http://spring-k8s-entrypoint/
# should return something similar to this {"key1":"kubernetes-value1","key2":"kubernetes-value2"}
```

Whenever the 'spring-k8s-config' ConfigMap changes, the application should get the event and refresh the configuration with the new values.
Edit the 'spring-k8s-config' configuration and change the value of the key1 to "kubernetes-new-value1", and the key2 to "kubernetes-new-value2".
```bash
kubectl edit configmap/spring-k8s-config
```

Send a request to the http://spring-k8s-entrypoint/ endpoint to get the latest configuration changes.
```bash
curl http://spring-k8s-entrypoint/
# should return something similar to this {"key1":"value1","key2":"value2"}
```

Send another request to the http://spring-k8s-entrypoint/ endpoint to get the latest configuration changes.
```bash
curl http://spring-k8s-entrypoint/
# should return something similar to this {"key1":"kubernetes-new-value1","key2":"kubernetes-new-value2"}
```

Also, the ConfigMap change event and the configuration refresh logs should be visible in the application logs.
