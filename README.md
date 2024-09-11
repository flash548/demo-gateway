# Simple Keycloak + Spring Cloud Gateway application

## About

This simple demo shows how you can secure a gateway with Keycloak and then, for each authenticated request, send it (route it)
downstream to a microservice with an injected JWT Token in the header.

Demo uses: 
- Baeldung's tutorial on Spring Security + Keycloak - https://www.baeldung.com/spring-boot-keycloak
- Spring Cloud Gateway MVC tutorial/docs - https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway-server-mvc/starter.html
- Dockerized Keycloak on port 8080
- Dockerized Postgres with db named 'postgres' on port 5432 (for Keycloak)

## ENV VARs

Need to define client-id and other secrets for Keycloak

Example

```agsl
SPRING_CLOUD_GATEWAY_MVC_ROUTES_0_ID=route1
SPRING_CLOUD_GATEWAY_MVC_ROUTES_0_URI=http://localhost:8084
SPRING_CLOUD_GATEWAY_MVC_ROUTES_0_PREDICATES_0=Path=/api/route1/**
SPRING_CLOUD_GATEWAY_MVC_ROUTES_0_FILTERS_0=TokenRelay

SPRING_CLOUD_GATEWAY_MVC_ROUTES_1_ID=ui-route
SPRING_CLOUD_GATEWAY_MVC_ROUTES_1_URI=http://localhost:8084
SPRING_CLOUD_GATEWAY_MVC_ROUTES_1_PREDICATES_0=Path=/**

# more routes as needed...

SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_KEYCLOAK_CLIENT_ID=<some client id>
SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_KEYCLOAK_CLIENT_SECRET=<some client secret>
SPRING_SECURITY_OAUTH2_CLIENT_PROVIDER_KEYCLOAK_ISSUER_URI=<isser uri>
SPRING_SECURITY_OAUTH2_CLIENT_RESOURCESERVER_JWT_ISSUER_URI=${SPRING_SECURITY_OAUTH2_CLIENT_PROVIDER_KEYCLOAK_ISSUER_URI}
```
