package com.api.financial.domain.model.dto.outbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutbound {

    private Long userId;
    private String name;
    private String email;
    private String password;
    private String cpf;
}
