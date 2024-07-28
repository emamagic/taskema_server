package com.emamagic.taskema.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppUtil {

    public String getCurrentMethodName() {
        return StackWalker.getInstance()
                .walk(frames -> frames
                        .skip(1) // Skip the current frame to get the caller method
                        .findFirst()
                        .map(StackWalker.StackFrame::getMethodName)
                        .orElse("Unknown Method"));
    }

    public String getCallerMethodName() {
        return StackWalker.getInstance()
                .walk(frames -> frames
                        .skip(2) // Skip the current frame and its caller to get the grandcaller method
                        .findFirst()
                        .map(StackWalker.StackFrame::getMethodName)
                        .orElse("Unknown Method"));
    }

    public <T> String getOperationName(Class<T> tClass) {
        return tClass.getSimpleName().concat(":").concat(getCallerMethodName());
    }
}
