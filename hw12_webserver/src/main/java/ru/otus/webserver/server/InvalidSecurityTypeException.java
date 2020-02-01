package ru.otus.webserver.server;

class InvalidSecurityTypeException extends RuntimeException {

    InvalidSecurityTypeException(SecurityType securityType) {
        super(String.format("Invalid security type exception: %s", securityType));
    }
}
