package com.api.financialmanager.application.mapper;

import com.api.financialmanager.domain.model.dto.outbound.LoginOutbound;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TokenMapper {

    @Mapping(target = "token", source = "token")
    @Mapping( target = "type", constant = "bearer")
    LoginOutbound mapToDto(String token);
}
