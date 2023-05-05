# Build Docker Image

```bash
podman build -t mljohns89-dev/orbit-api .
```

# Create Docker Container

```bash
podman run -d -p 8080:8080 --name orbit-api mljohns89-dev/orbit-api
```

# Push Image to OpenShift Internal Registry

## Docker Login (do if not done already)

```bash
podman login -u openshift -p $(oc whoami -t) default-route-openshift-image-registry.apps.sandbox-m2.ll9k.p1.openshiftapps.com
```

## Tag Image with OpenShift Internal Registry

```bash
podman tag mljohns89-dev/orbit-api default-route-openshift-image-registry.apps.sandbox-m2.ll9k.p1.openshiftapps.com/mljohns89-dev/orbit-api
```

## Push Image to OpenShift Internal Registry

```bash
podman push default-route-openshift-image-registry.apps.sandbox-m2.ll9k.p1.openshiftapps.com/mljohns89-dev/orbit-api
```