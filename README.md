# Otus Homework № 2

This is a simple application that can do `CRUD` operation on User entities. User entity has fields

    String username

    String firstName

    String lastName

    String email

    String phone

Service provides endpoints at url `/user` and implements [this](https://app.swaggerhub.com/apis/otus55/users/1.0.0)
OpenAPI

#### How to deploy this application in the Docker environment?

You can use `docker-compose up -d .` Additional information about Database credentials and other misc stuff can be found
inside the `docker-compose.yaml` file

#### How to deploy this application to the Kubernetes cluster?

0. Install [helm](https://helm.sh/)
1. Write `helm install simple-restful-crud helm/simple-restful-crud/ --values helm/simple-restful-crud/values.yaml` in
   the terminal. After installation process you can access services via `arch.homework` `grafana.arch.homework` and `prometheus.arch.homework` URLs. You have to have route to your Kubernetes cluster.

2. (Optional) You can pass `--namespace [namespace]` option to the `helm install` if you want to deploy in specific
   namespace
3. Uninstall: `helm uninstall simple-restful-crud`

#### Tests

You can use postman collection that is provided in this repo `Otus Homework #2 Tests.postman_collection.json`
It has tests written for each endpoint and collection variables that are preconfigured to work with `arch.homework` host
