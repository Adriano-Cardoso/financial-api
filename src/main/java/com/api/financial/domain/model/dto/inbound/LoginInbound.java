package com.api.financial.domain.model.dto.inbound;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginInbound {

    private String email;

    private String password;
}
