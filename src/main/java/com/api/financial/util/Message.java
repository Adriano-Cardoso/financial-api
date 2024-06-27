package com.api.financial.util;

import com.api.financial.domain.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum Message {

    IS_PRESENT_USER("O usuário já existe", HttpStatus.BAD_REQUEST),
    NAME_PROFILE_NOT_FOUND("O Perfil selecionado não existe", HttpStatus.NOT_FOUND),
    EMAIL_NOT_FOUND("O email não foi encontrado para o usuário", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("O Usuário não foi encontrado", HttpStatus.NOT_FOUND),
    INVALID_CREDENTIALS("Credenciais de autenticação inválidas.", HttpStatus.UNAUTHORIZED),
    TOKEN_ERROR("Token inválido", HttpStatus.FORBIDDEN),
    OPERATION_NOT_PERMITTED("Operação não permitida: saldo resultante negativo", HttpStatus.BAD_REQUEST),
    TRANSACTION_ID_NOT_FOUND("Id da transação não encontrada", HttpStatus.NOT_FOUND),
    ACCOUNT_NOT_FOUND("A conta não existe em nossa base de dados", HttpStatus.BAD_REQUEST);

    private final String defaultMessage;
    private final HttpStatus statusCode;

    Message(String defaultMessage, HttpStatus statusCode) {
        this.defaultMessage = defaultMessage;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return this.defaultMessage;
    }

    public HttpStatus getStatus() {
        return this.statusCode;
    }

    public BusinessException asBusinessException() {
        return asBusinessException(this.defaultMessage);
    }

    public BusinessException asBusinessException(String customMessage) {
        return BusinessException.builder()
                .httpStatusCode(this.getStatus())
                .code(String.valueOf(this.getStatus().value()))
                .message(customMessage)
                .description(customMessage)
                .build();
    }
}
