/*
 *  SPDX-FileCopyrightText: 2026 Yu Junyang (https://github.com/lowkeyfish)
 *  SPDX-License-Identifier: AGPL-3.0-or-later
 */

package com.yujunyang.sparrow.api.web.router;

import com.yujunyang.sparrow.common.vertx.RoutingContextUtils;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class OtelHttpServerRouter {
    public void appendTo(Router router) {
        router.route().handler(BodyHandler.create());

        router.get("/").handler(routingContext -> {
            RoutingContextUtils.response(routingContext, "welcome to sparrow otel http");
        });

        router.get("/health").handler(routingContext -> RoutingContextUtils.response(routingContext, "ok"));
    }
}
