package io.einharjar.medic.healthcheck


data class MonitoredService(val service: Service, val status: Status)

data class Service(val id: String, val url: String)

data class Status(val timestamp: Long, val health: Health)

enum class Health {
    OK,
    DEGRADING,
    DOWN,
    UNKNOWN
}