package io.einharjar.medic

import io.vertx.ext.web.RoutingContext

class MedicController {
    fun ping(routingContext: RoutingContext){
        routingContext.response().end("pong")
    }
}