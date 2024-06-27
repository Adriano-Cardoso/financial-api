package com.api.financial.util;

import com.api.financial.domain.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum Message {

    IS_PRESENT_USER("O usuário já existe", HttpStatus.BAD_REQUEST),
    NAME_PROFILE_NOT_FOUND("O Perfil selecionado nâo existe", HttpStatus.NOT_FOUND),
    EMAIL_NOT_FOUND("O email não foi encontrado para o usuário", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("O Usuário não foi encontrado", HttpStatus.NOT_FOUND),
    INVALID_CREDENTIALS("Credenciais de autenticação inválidas.", HttpStatus.UNAUTHORIZED),
    TOKEN_ERROR("Token invalido", HttpStatus.FORBIDDEN);

    private String value;
    private String description;
    private HttpStatus statusCode;

    Message(String value, HttpStatus statusCode) {
        this.value = value;
        this.statusCode = statusCode;
    }


    public String getMessage() {
        return this.value;
    }

    public HttpStatus getStatus() {
        return this.statusCode;
    }

    public String getDescription() {
        return description;
    }

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatus())
                .code(String.valueOf(this.getStatus().value())).message(this.getMessage())
                .description(this.getDescription()).build();
    }
}
