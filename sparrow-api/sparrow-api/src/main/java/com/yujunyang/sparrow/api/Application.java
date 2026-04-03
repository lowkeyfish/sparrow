package com.yujunyang.sparrow.api;

import com.yujunyang.sparrow.api.config.ApplicationConfig;
import com.yujunyang.sparrow.api.verticle.MainVerticle;
import com.yujunyang.sparrow.common.launch.ApplicationLauncher;
import io.vertx.core.Vertx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String... args) {
        Vertx vertx = Vertx.vertx();
        try {
            ApplicationLauncher.start(vertx, ApplicationConfig.class, MainVerticle.class)
                    .onFailure(e -> {
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                        vertx.close();
                    });
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            vertx.close();
        }
    }
}
