package io.einharjar.medic.task

import io.einharjar.medic.healthcheck.ServiceRepository
import io.vertx.core.logging.LoggerFactory

class HealthCheckTask(private val serviceRepository: ServiceRepository) {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    fun run(id: Long){
        logger.info("Ran task!")
    }
}