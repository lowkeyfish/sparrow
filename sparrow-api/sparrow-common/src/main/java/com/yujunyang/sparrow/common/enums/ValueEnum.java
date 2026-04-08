/*
 *  SPDX-FileCopyrightText: 2026 Yu Junyang (https://github.com/lowkeyfish)
 *  SPDX-License-Identifier: AGPL-3.0-or-later
 */

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
