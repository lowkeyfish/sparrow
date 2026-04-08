/*
 *  SPDX-FileCopyrightText: 2026 Yu Junyang (https://github.com/lowkeyfish)
 *  SPDX-License-Identifier: AGPL-3.0-or-later
 */

package com.yujunyang.sparrow.common.vertx.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerConfig {
    @JsonProperty("port")
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
