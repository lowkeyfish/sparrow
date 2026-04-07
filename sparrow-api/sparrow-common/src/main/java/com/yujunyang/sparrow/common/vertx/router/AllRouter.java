package com.yujunyang.sparrow.common.vertx.router;

import com.yujunyang.sparrow.common.vertx.handler.AccessHandler;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class AllRouter {
    public void appendTo(Router router) {
        router.route().handler(new AccessHandler());
        router.route().handler(BodyHandler.create());
    }
}
