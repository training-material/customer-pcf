# Pivotal Cloud foundry:<br />

## PCF Service Creation :
> ClearDB MySQL Database (Internal from Market Place)

   cf create-service cleardb spark mydb  
              
   ![alt text]( https://github.com/training-material/customer-pcf/blob/master/service.PNG )<br />
  
> Logging service - papertrial( external)

   cf cups customer-log-service -l syslog://logs4.papertrailapp.com:12064
 
  ![alt text](https://raw.githubusercontent.com/training-material/customer-pcf/master/papertrial-log.PNG)<br />
  
> Have binded services via manifest file. 

## PCF Deployment : 

 - cf push will automatically detect manifest file in current or parent directory. if other directory
 - cf push -f path of the manifest file.
  
  ### Application in PCF
    
   ![alt text](https://github.com/training-material/customer-pcf/blob/master/app-console.PNG)<br />
   
  ### Healthcheck in PCF
  
     ![alt text](https://github.com/training-material/customer-pcf/blob/master/healthcheck.PNG)<br />


## Swagger-URI:  Rest API - CRUD operations

   **(http://customer-service-demo.cfapps.io/swagger-ui.html)** <br/>

![alt text](https://raw.githubusercontent.com/training-material/customer-pcf/master/swagger-ui.PNG)<br />

## Application Flow
    RestController --> service --> Repository--> cloud db or Local H2 db (Based on Profile will decide)
    manifest.yml -> which contains PCF deployment details along with service & profile.
