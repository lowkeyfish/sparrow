package com.yujunyang.sparrow.api.web.router;

import com.yujunyang.sparrow.common.vertx.RoutingContextUtils;
import io.vertx.ext.web.Router;

public class TestRouter {
    public void appendTo(Router router) {
        router.get("/").handler(routingContext -> {
            RoutingContextUtils.response(routingContext, "welcome to sparrow api");
        });

        router.get("/test/:type").handler(ctx -> {
            RoutingContextUtils.response(ctx, ctx.pathParam("type"));
        });
    }
}
