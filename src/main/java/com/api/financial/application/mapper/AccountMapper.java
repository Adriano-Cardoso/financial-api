package com.api.financial.application.mapper;

import com.api.financial.domain.model.Account;
import com.api.financial.domain.model.dto.inbound.AccountInbound;
import com.api.financial.domain.model.dto.outbound.AccountOutbound;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toRepresentationConvertionInboundToAccount(AccountInbound accountInbound);

    AccountOutbound toRepresentationConvertionAccountToOutbound(Account account);
}

