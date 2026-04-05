package com.yujunyang.sparrow.common.vertx.handler;

import com.yujunyang.sparrow.common.utils.TraceIdUtils;
import com.yujunyang.sparrow.common.vertx.RoutingContextUtils;
import io.reactiverse.contextual.logging.ContextualData;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class TraceIdHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext ctx) {
        String traceId = TraceIdUtils.generateTraceId();
        RoutingContextUtils.putTraceId(ctx, traceId);
        ContextualData.put("traceId", traceId);
        ctx.next();
    }
}
