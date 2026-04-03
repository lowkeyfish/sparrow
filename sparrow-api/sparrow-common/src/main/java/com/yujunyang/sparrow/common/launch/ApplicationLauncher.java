package com.yujunyang.sparrow.common.launch;

import com.yujunyang.sparrow.common.environment.EnvironmentType;
import com.yujunyang.sparrow.common.environment.EnvironmentUtils;
import com.yujunyang.sparrow.common.log4j2.Log4j2Configurator;
import com.yujunyang.sparrow.common.utils.CheckUtils;
import com.yujunyang.sparrow.common.vertx.config.ApplicationConfigManager;
import com.yujunyang.sparrow.common.vertx.config.DefaultApplicationConfig;
import com.yujunyang.sparrow.common.vertx.config.VertxApplicationConfigurator;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.VerticleBase;
import io.vertx.core.Vertx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ApplicationLauncher {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationLauncher.class);

    private ApplicationLauncher() {}

    public static <E1 extends DefaultApplicationConfig, E2 extends VerticleBase> Future<?> start(
            Vertx vertx, Class<E1> applicationConfigClass, Class<E2> mainVerticleClass) {
        return start(EnvironmentUtils.getEnvironment(), vertx, applicationConfigClass, mainVerticleClass);
    }

    public static <E1 extends DefaultApplicationConfig, E2 extends VerticleBase> Future<?> start(
            EnvironmentType environmentType,
            Vertx vertx,
            Class<E1> applicationConfigClass,
            Class<E2> mainVerticleClass) {
        CheckUtils.notNull(environmentType, "未提供环境");
        CheckUtils.notNull(vertx, "vertx不能为null");
        CheckUtils.notNull(applicationConfigClass, "applicationConfigClass不能为null");
        CheckUtils.notNull(mainVerticleClass, "mainVerticleClass不能为null");

        initializeLog4j2Config(environmentType);
        initializeApplicationConfig(environmentType, vertx, applicationConfigClass);
        return deployVerticle(vertx, mainVerticleClass);
    }

    private static void initializeLog4j2Config(EnvironmentType environmentType) {
        // 初始log4j2配置文件
        Log4j2Configurator.initialize(environmentType);
    }

    private static <E extends DefaultApplicationConfig> void initializeApplicationConfig(
            EnvironmentType environmentType, Vertx vertx, Class<E> applicationConfigClass) {
        // 初始应用配置文件
        ApplicationConfigManager.initialize(
                VertxApplicationConfigurator.initialize(environmentType, vertx, applicationConfigClass));
    }

    private static <E extends VerticleBase> Future<?> deployVerticle(Vertx vertx, Class<E> mainVerticleClass) {
        DeploymentOptions deploymentOptions = new DeploymentOptions()
                .setInstances(ApplicationConfigManager.get().getVertx().getDeploymentInstance());
        return vertx.deployVerticle(mainVerticleClass, deploymentOptions).onSuccess(r -> {
            LOGGER.debug("Verticle部署成功");
        });
    }
}
