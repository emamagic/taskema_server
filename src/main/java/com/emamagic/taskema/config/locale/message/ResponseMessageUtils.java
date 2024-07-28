package com.emamagic.taskema.config.locale.message;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
@UtilityClass
public class ResponseMessageUtils {
    private final static String RESOURCE_BUNDLE_NAME = "messages";
    private final static String SPECIAL_CHARACTER = "__";

    public String getMessage(IResponseMessageCode messageCode) {
        String messageKey = null;
        try {
            Locale locale = LocaleContextHolder.getLocale();
            messageKey = messageCode.getClass().getSimpleName() + SPECIAL_CHARACTER + messageCode;
            ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
            return resourceBundle.getString(messageKey);
        } catch (MissingResourceException missingResourceException) {
            log.error("Friendly message not found for key: {}", messageKey);
            return null;
        }
    }
}
