package ru.itmo.starshipsservice.config

import org.apache.cxf.Bus
import org.apache.cxf.bus.spring.SpringBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver
import java.util.*

@Configuration
@Import(value = [EndpointConfig::class])
open class WebServiceConfig {

    @Suppress("unused")
    @Bean(name = [Bus.DEFAULT_BUS_ID])
    open fun springBus(): SpringBus {
        return SpringBus()
    }
}
