package com.yujunyang.sparrow.common.vertx.handler;

import com.yujunyang.sparrow.common.utils.RequestIdUtils;
import com.yujunyang.sparrow.common.vertx.RoutingContextUtils;
import io.reactiverse.contextual.logging.ContextualData;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class RequestIdHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext ctx) {
        String requestId = RequestIdUtils.generateRequestId();
        RoutingContextUtils.putRequestId(ctx, requestId);
        ContextualData.put("requestId", requestId);
        ctx.next();
    }
}
