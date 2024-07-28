package com.emamagic.taskema.config.locale;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class AppLocaleResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {


    @Nonnull
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        var headerLang = request.getHeader("Accept-Language");
        if (headerLang == null || headerLang.isEmpty())
            return Locale.getDefault();
        else
            return Locale.lookup(Locale.LanguageRange.parse(headerLang), AppLocale.LOCALES);
    }

//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        var rs = new ResourceBundleMessageSource();
//        rs.setBasename("messages");
//        rs.setDefaultEncoding("UTF-8");
//        rs.setUseCodeAsDefaultMessage(true);
//        return rs;
//    }
}
