# oven

Build with Maven & package into a jar file

`./mvnw clean package -DskipTests`

Copy jar file to docker folder

`cp .\target\[pacjage.version-SNAPSHOT].jar .\src\main\docker\`

Navigate to docker folder of each project
...... `/src/main/docker`

Run `docker-compose up`

------ 

To enter bach for oven service:
 `docker exec -it oven_svc bash`
 
 To enter bach for states service:
`docker exec -it statessvc bash`
 
OVEN SERVICE APIs: 
 `curl http://127.0.0.1:8080/`
 Show all ovens
 `curl http://127.0.0.1:8080/ovens`
 Show Specific Oven
 `curl http://127.0.0.1:8080/ovens/{oven_key}`
 Create new Oven
 `curl -d "{\"key\":\"<oven_key>\"}" -H "Content-Type: application/json" -X POST http://localhost:8080/ovens/new`
 
 STATES SERVICE API:
 get the state of a given oven:
 `curl -d "{\"key\":\"<oven_key>\"}" -H "Content-Type: application/json" -X POST http://localhost:8088/states/8`
 You can pass in any valid Oven object to the post. e.g.
 ```json
 {
   "key": "Severus",
   "light": true,
   "grill_temp": 0,
 }
 ```
