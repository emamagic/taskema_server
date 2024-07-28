package com.emamagic.taskema.config.locale.message;

public sealed interface IResponseMessageCode permits ResponseMessageCode {
    int getResponseMessageCode();
}
