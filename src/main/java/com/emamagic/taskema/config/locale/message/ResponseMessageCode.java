package com.emamagic.taskema.config.locale.message;

public enum ResponseMessageCode implements IResponseMessageCode {
    ERROR(1001),
    SUCCESS(1002),

    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(1500),
    USER_NOT_FOUND_EXCEPTION(1501),
    USER_DUPLICATE_FOUND_EXCEPTION(1502),
    ;

    private final int value;
    ResponseMessageCode(int value) {
        this.value = value;
    }

    @Override
    public int getResponseMessageCode() {
        return value;
    }
}
