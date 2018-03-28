package io.einharjar.medic.config

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.einharjar.medic.healthcheck.Service
import io.vertx.core.json.Json

const val CONFIG_FILE = "medic.json"

class ConfigurationLoader {
    init {
        Json.mapper.apply {
            registerKotlinModule()
        }
        Json.prettyMapper.apply {
            registerKotlinModule()
        }
    }

    private val objectMapper = Json.mapper

    fun loadConfig(): Configuration {
        return objectMapper.readValue(getConfigFile())
    }

    private fun getConfigFile(): String {
        return this::class.java.classLoader.getResource(CONFIG_FILE).readText()
    }
}


data class Configuration(@JsonProperty("server")
                         val serverConfig: ServerConfig,
                         @JsonProperty("services")
                         val services: List<Service>)

data class ServerConfig(val port: Int)