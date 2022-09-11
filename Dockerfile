FROM java:11
EXPOSE 8080
ADD target/Todoist.jar Todoist.jar

ENTRYPOINT ["java","-jar","Todoist.jar"]