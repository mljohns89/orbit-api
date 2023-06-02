# Create Everything on Openshift

## Local Openshift Cluster

You can create a Local Openshift Cluster using the `crc` cli.

[Openshift Local](https://console.redhat.com/openshift/create/local)
[crc_documentation](https://access.redhat.com/documentation/en-us/red_hat_openshift_local/2.18/html/getting_started_guide/installation_gsg)

### Steps I took
1. `crc setup`  (This step took less than 1 minute)
2. `crc start`  (This step took a long time, ~8 minutes)
    ```bash
    Started the OpenShift cluster.
    
    The server is accessible via web console at:
      https://console-openshift-console.apps-crc.testing
    
    Log in as administrator:
      Username: kubeadmin
      Password: znkoy-vFHGh-LptL4-KU6qS
    
    Log in as user:
      Username: developer
      Password: developer
    
    Use the 'oc' command line interface:
      $ eval $(crc oc-env)
      $ oc login -u developer https://api.crc.testing:6443
    ```
3. `crc console` (Opens the local Openshift UI)
4. `oc login -u kubeadmin https://api.crc.testing:6443` (Login to the cluster as an admin)
5. `oc new-project orbit-api` (Create a new project/namespace)
6. `oc adm policy add-scc-to-user anyuid -z orbit-api-tekton-pipelines-controller` (Give the orbit-api-tekton-pipelines-controller service account the anyuid security context)
7. `oc adm policy add-scc-to-user anyuid -z orbit-api-tekton-pipelines-webhook` (Give the orbit-api-tekton-pipelines-webhook service account the anyuid security context)
8. `oc apply --filename https://storage.googleapis.com/tekton-releases/pipeline/latest/release.notags.yaml` (Install Tekton Pipelines)
9. `oc get pods --namespace orbit-api --watch` (Watch the pods until they are all running)


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

# CRC
1. `crc daemon`
2. `crc start`


## Doing everything manually (Works)
1. `oc create imagestream orbit-api`
2. `oc get route -n openshift-image-registry`
3. `docker login -u `oc whoami` -p `oc whoami --show-token` default-route-openshift-image-registry.apps-crc.testing `
4. `docker build -t default-route-openshift-image-registry.apps-crc.testing/orbit-api/orbit-api:latest .`
5. `docker tag orbit-api:latest default-route-openshift-image-registry.apps-crc.testing/orbit-api-is/orbit-api:latest`
6. `docker push default-route-openshift-image-registry.apps-crc.testing/orbit-api/orbit-api:latest`

## Via yml (works)
1. `oc login -u kubeadmin -p "znkoy-vFHGh-LptL4-KU6qS" https://api.crc.testing:6443`
2. https://federico.is/posts/2020/08/01/setup-crc-with-internal-image-registry/
3. `oc apply -f kubernetes/orbit-api-v2.yml`

## Via auto-generated yml (works)
1. `oc new-app . -o yaml > openshift/Build.yml`
2. (Delete any existing stuff for orbit-api)
   1. `oc get all --selector app=orbit-api -o name`
   2. `oc delete all --selector app=orbit-api`
3. `oc apply -f openshift/Build.yml`
4. Note:  This process will create a Build in the UI (but not run the Build) where as with orbit-api-v2.yml does not

oc image mirror registry.access.redhat.com/ubi8/ubi:latest=default-route-openshift-image-registry.apps-crc.testing/orbit-api/ubi8:latest --insecure=true --filter-by-os=linux/amd64

oc image mirror amazoncorretto:latest=default-route-openshift-image-registry.apps-crc.testing/orbit-api/amazoncorretto:latest --insecure=true --filter-by-os=linux/amd64
