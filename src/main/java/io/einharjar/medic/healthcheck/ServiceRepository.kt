package io.einharjar.medic.healthcheck

import java.time.Instant
import java.util.concurrent.ConcurrentHashMap

class ServiceRepository(services: List<Service>) {
    private val serviceMap: ConcurrentHashMap<String, MonitoredService>

    init {
        serviceMap = ConcurrentHashMap(
                services.map { service ->
                    MonitoredService(service, Status(Instant.now().epochSecond, Health.UNKNOWN))
                }.associate {
                    Pair(it.service.id, it)
                }
        )
    }

    fun get(id: String): MonitoredService? {
        return serviceMap[id]
    }

    fun getAll(): List<MonitoredService> {
        return serviceMap.values.toList()
    }

    fun put(service: MonitoredService) {
        serviceMap.put(service.service.id, service)
    }
}