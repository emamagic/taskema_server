package com.emamagic.taskema.config.locale;

import java.util.List;
import java.util.Locale;

public class AppLocale {
    public static final List<Locale> LOCALES;
    static {
        LOCALES = List.of(
                new Locale.Builder().setLanguage(AppLanguage.EN.name()).build(),
                new Locale.Builder().setLanguage(AppLanguage.FA.name()).build()
        );
    }
}
