package com.api.financialmanager.application.mapper;

import com.api.financialmanager.domain.model.dto.outbound.LoginOutbound;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-20T16:27:11-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class TokenMapperImpl implements TokenMapper {

    @Override
    public LoginOutbound mapToDto(String token) {
        if ( token == null ) {
            return null;
        }

        String token1 = null;

        token1 = token;

        String type = "bearer";

        LoginOutbound loginOutbound = new LoginOutbound( token1, type );

        return loginOutbound;
    }
}
