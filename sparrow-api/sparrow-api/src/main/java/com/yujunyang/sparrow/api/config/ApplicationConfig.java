package com.yujunyang.sparrow.api.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yujunyang.sparrow.common.vertx.config.DefaultApplicationConfig;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationConfig extends DefaultApplicationConfig {
    @JsonProperty("test")
    private int test;

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }
}
