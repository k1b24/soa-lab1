package ru.itmo.spacemarinesservice.configuration

import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.ext.Provider

@Provider
class CorsFilter2 : ContainerRequestFilter {

    override fun filter(requestContext: ContainerRequestContext) {
        if (!requestContext.headers.containsKey("Access-Control-Allow-Origin")) {
            requestContext.headers.add("Access-Control-Allow-Origin", "*")
        }
        if (!requestContext.headers.containsKey("Access-Control-Allow-Headers")) {
            requestContext.headers.add("Access-Control-Allow-Headers", "Content-Type, Origin, X-Requested-With")
        }
        if (!requestContext.headers.containsKey("Access-Control-Allow-Methods")) {
            requestContext.headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, HEAD, OPTIONS")
        }
    }
}