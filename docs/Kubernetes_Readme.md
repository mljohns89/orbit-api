# Start minikube
`minikube start`

# List Kubernetes Contexts
`kubectl config get-contexts`

# Set Kubernetes Context
`kubectl config use-context minikube`

# Create Kubernetes Deployment based on yml file
`kubectl apply -f ./kubernetes/orbit-api.yml`

# Describe the deployment
`kubectl describe deployment orbit-api`

# Describe the service
`kubectl describe service orbit-api`

# Get the service URL
`minikube service orbit-api --url` *Note that terminal must be open to access the service from browser*

# Clean Up
`kubectl delete -f ./kubernetes/orbit-api.yml`