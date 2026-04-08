/*
 *  SPDX-FileCopyrightText: 2026 Yu Junyang (https://github.com/lowkeyfish)
 *  SPDX-License-Identifier: AGPL-3.0-or-later
 */

package com.yujunyang.sparrow.common.log4j2;

import com.yujunyang.sparrow.common.environment.EnvironmentType;
import com.yujunyang.sparrow.common.environment.EnvironmentUtils;
import com.yujunyang.sparrow.common.utils.CheckUtils;
import java.net.URI;
import java.text.MessageFormat;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.Configurator;

public final class Log4j2Configurator {

    public static void initialize(EnvironmentType environmentType) {
        CheckUtils.notNull(environmentType, "未提供环境");
        String configFilePath = MessageFormat.format("log4j2/log4j2-{0}.xml", environmentType.getValue());
        CheckUtils.notNull(
                Log4j2Configurator.class.getClassLoader().getResource(configFilePath),
                "classpath下不存在log4j2配置文件:{0}",
                configFilePath);
        String configFileClassPath = MessageFormat.format("classpath:{0}", configFilePath);
        Configuration configuration =
                ConfigurationFactory.getInstance().getConfiguration(null, null, URI.create(configFileClassPath));
        Configurator.reconfigure(configuration);

        Logger logger = LogManager.getLogger(Log4j2Configurator.class);
        logger.debug(DataMessage.of("log4j2配置初始化完成", Map.of("path", configFileClassPath)));
    }

    public static void initialize() {
        initialize(EnvironmentUtils.getEnvironment());
    }
}
