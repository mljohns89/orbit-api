# Create Everything on Openshift

## Background Info

Image Repository:
image-registry.openshift-image-registry.svc:5000/mljohns89-dev/orbit-api@sha256:876ebf20adf8f1b3a42d2673bbf8977e3533d5545df2d6073016324b005d361b


## Create Application

```bash
  oc new-app --name=orbit-api-app image-registry.openshift-image-registry.svc:5000/mljohns89-dev/orbit-api:latest
  
  oc new-app --name=orbit-api-app mljohns89-dev/orbit-api:latest
```

## Create Service

```bash
  oc expose svc/orbit-api-app
```