package io.einharjar.medic

import io.einharjar.medic.config.ConfigurationLoader
import io.einharjar.medic.healthcheck.ServiceRepository
import io.einharjar.medic.task.HealthCheckTask
import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.logging.LoggerFactory
import io.vertx.ext.web.Router
import java.util.concurrent.TimeUnit

class MedicVerticle : AbstractVerticle() {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    override fun start() {
        val config = ConfigurationLoader().loadConfig()

        val serviceRepository = ServiceRepository(config.services)

        val vertx = Vertx.vertx()
        setupTasks(vertx, serviceRepository)

        val router = Router.router(vertx)
        setupRouting(router)

        vertx.createHttpServer().requestHandler(router::accept).listen(config.serverConfig.port) {
            if (it.succeeded()) logger.info("Server listening at ${config.serverConfig.port}")
            else println(it.cause())
        }
    }

    fun setupRouting(router: Router) {
        val controller = MedicController()
        router.route("/ping").handler(controller::ping)
    }

    fun setupTasks(vertx: Vertx, serviceRepository: ServiceRepository){
        val healthCheckTask = HealthCheckTask(serviceRepository)
        vertx.setPeriodic(TimeUnit.SECONDS.toMillis(10), healthCheckTask::run)
    }
}