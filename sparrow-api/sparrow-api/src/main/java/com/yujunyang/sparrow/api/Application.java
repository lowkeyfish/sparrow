package com.yujunyang.sparrow.api;

import com.yujunyang.sparrow.api.config.ApplicationConfig;
import com.yujunyang.sparrow.api.verticle.MainVerticle;
import com.yujunyang.sparrow.api.verticle.OtelHttpServerVerticle;
import com.yujunyang.sparrow.common.launch.ApplicationLauncher;
import com.yujunyang.sparrow.common.vertx.config.ApplicationConfigManager;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public static void main(String... args) {
        Vertx vertx = Vertx.vertx();
        try {
            ApplicationLauncher.start(vertx, ApplicationConfig.class, v -> {
                        ApplicationConfig applicationConfig = ApplicationConfigManager.get();
                        DeploymentOptions deploymentOptions = new DeploymentOptions()
                                .setInstances(applicationConfig.getVertx().getDeploymentInstance());

                        return vertx.deployVerticle(MainVerticle.class, deploymentOptions)
                                .compose(r -> vertx.deployVerticle(OtelHttpServerVerticle.class, deploymentOptions))
                                .onSuccess(r -> System.out.println("Verticle全部部署成功"));
                    })
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
