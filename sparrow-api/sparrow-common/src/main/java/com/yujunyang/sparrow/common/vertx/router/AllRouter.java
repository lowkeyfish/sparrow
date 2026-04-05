package com.yujunyang.sparrow.common.vertx.router;

import com.yujunyang.sparrow.common.vertx.handler.AccessHandler;
import com.yujunyang.sparrow.common.vertx.handler.TraceIdHandler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class AllRouter {
    public void appendTo(Router router) {
        router.route().handler(new TraceIdHandler()).handler(new AccessHandler());
        router.route().handler(BodyHandler.create());
    }
}
