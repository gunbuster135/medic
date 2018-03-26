package io.einharjar.medic.healthcheck

import java.util.concurrent.ConcurrentHashMap

class ServiceRepository(private val serviceMap: ConcurrentHashMap<String, Service>) {

    fun get(id: String): Service? {
        return serviceMap[id]
    }

    fun getAll(): List<Service> {
        return serviceMap.values.toList()
    }

    fun put(service: Service) {
        serviceMap.put(service.id, service)
    }
}