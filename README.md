# Music-App
Simple Music App project with Springboot


### Import your Project in your IDE (for example Eclipse)

Update your Project with right click on your Project > Maven > Update Project :

Make sure your java library installed with check it in lists of Maven Dependency

Your also can update your project with terminal : 
```
mvn clean install
```
It will auto downloading your dependencies, wait until complete and BUILD SUCCESS

### Running your springboot application

First make sure you have create the database with same name in application.properties in the project

Then you can run your Project rigth on your IDE with spring-boot:run in run configuration of your project

Or run build it with Maven that will be produce an file with .jar extenstion

So, you can run the .jar file in your terminal :

```
java -jar <file-path>
```



## Swagger Documentations

To show swagger API documentations, you can type this on web browser.

```
http://localhost:8081/swagger-ui.html
```

