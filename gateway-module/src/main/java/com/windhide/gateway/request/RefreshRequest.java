package com.windhide.gateway.request;

import lombok.Data;

@Data
public class RefreshRequest {
    private String userId;

    private String refreshToken;
}
