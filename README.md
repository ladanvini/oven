# oven
## PREREQUISITES:

*Docker container*

If you do not have docker on your system, download and install from [Docker](https://docs.docker.com/get-docker/)

## Build oven-svc
Build with Maven & package into a jar file
From the root of this repository, navigate to the project:
`cd oven/oven/`
Once there, use maven to build and package the project:

`./mvnw clean package -DskipTests`

 **_SIDE NOTE:_** To later on run this build in the docker container
 
Copy jar file to docker folder

`cp ./target/<package.version>-SNAPSHOT].jar ./src/main/docker/`
`cp ./target/oven-0.0.1-SNAPSHOT.jar ./src/main/docker/`

## Build states-svc
Build with Maven & package into a jar file
From the root of this repository, navigate to the project:

`cd ovenstate/ovenstate/`

Once there, use maven to build and package the project:

`./mvnw clean package -DskipTests`

 **_SIDE NOTE:_** To later on run this build in the docker container
 
Copy jar file to docker folder

`cp ./target/<package.version>-SNAPSHOT].jar ./src/main/docker/`
`cp ./target/ovenstate-0.0.1-SNAPSHOT.jar ./src/main/docker/`

## Run services via Docker
Run both services so oven-svc can call into states-svc.
### Summary
Navigate to docker folder of each project

`/src/main/docker`

Run `docker-compose up`
### Step-by-step
Navigate to docker folder in oven
`cd oven/oven/src/main/docker`
Making sure your docker is running, run
`docker-compose up`


Navigate to docker folder in ovenstate
`cd ovenstate/ovenstate/src/main/docker`


Making sure your docker is running, run
`docker-compose up`

Now the services are running and you can access oven-svc in `http://127.0.0.1:8080`


To enter bash for oven service:
 `docker exec -it oven_svc bash`

 
To enter bash for states service:
`docker exec -it statessvc bash`

## Invoking the APIs 

### OVEN SERVICE APIs: 
~~~
 GET : Greeting
 `curl http://127.0.0.1:8080/`
~~~

~~~
 GET : Show all ovens
 `curl http://127.0.0.1:8080/ovens`
~~~

~~~
 GET : Show Specific Oven
 `curl http://127.0.0.1:8080/ovens/{oven_key}`
 this endpoint calls into states-svc to get a new oven state.
~~~

~~~
 POST : Create new Oven
 `curl -d "{\"key\":\"<oven_key>\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/ovens/new`
~~~
 
### STATES SERVICE API:
 get the state of a given oven:
 `curl -d "{\"key\":\"<oven_key>\"}" -H "Content-Type: application/json" -X POST http://localhost:8088/states/8`
 This endpoint generates a random state for an oven.
 
 **_SIDE NOTE:_** To later on run this build in the docker container
 You can pass in any valid Oven object to the post. e.g.
 ```json
 {
   "key": "Severus",
   "light": true,
   "grill_temp": 0,
 }
 ```

## Notes on the Persistent Storage
Currently the data is persisted to a folder under the oven project
`oven/oven/src/main/docker/data`
This could be persisted into an external persistent storage or Kubernetese persistent volume.
However, for simplicity purposes currently the data is persisted to a folder. 