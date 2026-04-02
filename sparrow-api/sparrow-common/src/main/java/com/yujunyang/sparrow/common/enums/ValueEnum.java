package com.yujunyang.sparrow.common.enums;

/** 具有值枚举类型 */
public interface ValueEnum<T> {

    /**
     * 获取枚举值
     *
     * @return
     */
    T getValue();
}
