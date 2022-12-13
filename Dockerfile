FROM maven:3.8.3-amazoncorretto-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip

FROM amazoncorretto:11
COPY --from=build /home/app/target/Eco-0.0.1-SNAPSHOT.jar /usr/local/lib/eco.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/eco.jar"]