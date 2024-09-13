FROM eclipse-temurin:21.0.2_13-jre-jammy

WORKDIR /app

COPY target/demo-gateway.jar demo-gateway.jar

EXPOSE 8080

ENTRYPOINT ["java"]
CMD ["-jar", "/app/demo-gateway.jar"]

