# service-mesh-istio-example
A simple example for service mesh using Istio.

## How to run
1. Create the project service-mesh-istio-example.
2. Enable Google API.
3. Create a new cluster.
    ```
    gcloud container clusters create istio-cluster --cluster-version=1.10.5-gke.4 --zone us-east4-a --num-nodes 2 --project service-mesh-istio-example
    ```
     > Please use Powershell for Windows platform.
4. Retrieve your credentials for kubectl.
    ```
    gcloud container clusters get-credentials istio-cluster --zone us-east4-a --project service-mesh-istio-example
    ```
5. Grant cluster administrator (admin) permissions to the current user. To create the necessary RBAC rules for Istio, the current user requires admin permissions.
    ```
    kubectl create clusterrolebinding cluster-admin-binding --clusterrole=cluster-admin --user=$(gcloud config get-value core/account)
    ```
6. Download the istio and go to istio istallation directory.
    ```
    cd .\istio-1.1.0.snapshot.0\
    ```
7. Install Istio’s Custom Resource Definitions via `kubectl apply`.
    ```
    kubectl apply -f install/kubernetes/helm/istio/templates/crds.yaml
    ```
8. Install Istio without mutual TLS authentication between sidecars.
    ```
    kubectl apply -f install/kubernetes/istio-demo.yaml
    ```
    > For more information about istio setup, [please click here.](https://istio.io/docs/setup/kubernetes/quick-start/)
9. Build docker images and push it to Google registry.
    1. Start your local docker deamon service to build docker images.
    2. Clone the repository.
    3. Go to addition-service root directory
        ```
        cd ./addition-service/
        ```
    4. Build docker image.
        ```
        mvn clean install
        ```
    5. Push image to Goggle Registry.
        ```
        kubectl push us.gcr.io/service-mesh-istio-example/addition-service
        ```
    6. Create the deployment file with istio properties.
        ```
        istioctl kube-inject -f deployment.yaml
        ```
    7. We may copy it and save it as deployment-with-istio.yaml.
        ```
        kubectl apply -f deployment-with-istio.yaml
        ```
10. Do the same process for `addition-client-service`.
11. Based on your external host ip of addition-client-service, you can reach out to addition-client-service.
    `http://<host-ip>:8100/client-api/add/1/2`