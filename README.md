# Simple Keycloak + Spring Cloud Gateway application

## About

This simple demo shows how you can secure a gateway with Keycloak and then, for each authenticated request, send it (route it)
downstream to a microservice with an injected JWT Token in the header.

Demo uses: 
- Baeldung's tutorial on Spring Security + Keycloak - https://www.baeldung.com/spring-boot-keycloak
- Spring Cloud Gateway MVC tutorial/docs - https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway-server-mvc/starter.html
- Dockerized Keycloak on port 8080
- Dockerized Postgres with db named 'postgres' on port 5432 (for Keycloak)
