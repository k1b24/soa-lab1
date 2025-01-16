package ru.itmo.starshipsservice.config

import jakarta.xml.ws.Endpoint
import org.apache.cxf.Bus
import org.apache.cxf.interceptor.LoggingInInterceptor
import org.apache.cxf.interceptor.LoggingOutInterceptor
import org.apache.cxf.jaxws.EndpointImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.ws.config.annotation.WsConfigurerAdapter
import ru.itmo.starshipsservice.controller.StarshipsService


@Configuration
open class EndpointConfig @Autowired constructor(private val bus: Bus) : WsConfigurerAdapter() {

    @Bean
    open fun endpoint(eventEndpoint: StarshipsService): Endpoint {
        val endpoint = EndpointImpl(bus, eventEndpoint)
        endpoint.publish("/starships")
        endpoint.server.endpoint.inInterceptors.add(LoggingInInterceptor())
        endpoint.server.endpoint.outInterceptors.add(LoggingOutInterceptor())
        return endpoint
    }
}