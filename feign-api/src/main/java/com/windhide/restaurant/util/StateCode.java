package com.windhide.restaurant.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StateCode {
    SUCCESS(200,"成功"),
    ERROR(404,"错误"),
    WARNING(500,"警告");

    private int code;
    private String messeage;

}
