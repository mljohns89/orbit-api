# Build Docker Image

```bash
docker build -t mljohns89-dev/orbit-api .

docker buildx build --platform=linux/amd64 -t mljohns89-dev/orbit-api .
```

# Create Docker Container

```bash
docker run -d -p 8080:8080 --name orbit-api mljohns89-dev/orbit-api
```

# Push Image to OpenShift Internal Registry

## Login to OpenShift
Goto:  https://oauth-openshift.apps.sandbox-m2.ll9k.p1.openshiftapps.com/oauth/token/display
C/P Command into CLI


## Docker Login (do if not done already)

```bash
docker login -u openshift -p $(oc whoami -t) default-route-openshift-image-registry.apps.sandbox-m2.ll9k.p1.openshiftapps.com
```

## Tag Image with OpenShift Internal Registry

```bash
docker tag mljohns89-dev/orbit-api default-route-openshift-image-registry.apps.sandbox-m2.ll9k.p1.openshiftapps.com/mljohns89-dev/orbit-api:docker
```

## Push Image to OpenShift Internal Registry

```bash
docker push default-route-openshift-image-registry.apps.sandbox-m2.ll9k.p1.openshiftapps.com/mljohns89-dev/orbit-api:docker
```

## Update the sha in the service config
The `docker push` command outputs a sha that needs to be updated in the service config in the UI