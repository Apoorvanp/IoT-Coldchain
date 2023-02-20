# Coldchain solution for transporting vaccines
## Let's get started
Clone the project
```shell
git clone https://github.com/Apoorvanp/IoT-Coldchain.git
```
Navigate to project directory
```shell
cd IoT-Coldchain
```
Checkout demo branch
```shell
git checkout feature/added-truck-id
```
## Running the project
Navigate to Kotlin project directory
```shell
cd coldchain
```
Building the Spring Boot application
```shell
./gradlew dockerBuildImage
```
Going back to main directory
```shell
cd ../
```
Starting all the services
```shell
docker compose up -d
```

## Accessing Endpoints

* InfluxDB - localhost:8086
* Grafana - localhost:3000
* OpenHAB - localhost:9876

## Credentials

* InfluxDB
  * username: admin
  * password: helloworld

* Grafana
  * username: admin
  * password: helloworld



