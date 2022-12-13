## **How to run the project**

1. download git and docker 
2. open git bush and run this command `git clone https://github.com/BabSarg/Eco.git`
3. open powershell in project directory `...\Eco\Eco` 
4. run `docker-compose up` in powershell

## **if you want to restart the application please run the following commands**

1. docker-compose down`
2. `docker rmi eco:latest`
3. `docker-compose up`

## **If you want to stop the application please run the following commands**

1. `docker-compose down`
2. `docker rmi eco:latest`

## **If there is any changes in the backend project you need to run these commands**

1. `docker-compose down`
2. `docker rmi eco:latest`
3. `git pull`
4. `docker-compose up`
