package com.yujunyang.sparrow.common.utils;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

public final class RequestIdUtils {
    public static String generateRequestId() {
        return StringUtils.remove(UUID.randomUUID().toString(), '-');
    }
}
