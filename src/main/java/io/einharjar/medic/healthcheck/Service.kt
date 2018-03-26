package io.einharjar.medic.healthcheck

import java.net.URL

data class Service(val id: String, val url: URL, val status: Status)

data class Status(val timestamp: Long, val health: Health)

enum class Health {
    OK,
    DEGRADING,
    DOWN,
}