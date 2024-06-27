package com.api.financialmanager.domain.model.dto.inbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInbound {

    private String name;
    private String email;
    private String password;
}
