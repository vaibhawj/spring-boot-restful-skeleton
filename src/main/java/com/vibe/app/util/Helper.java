package com.vibe.app.util;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Helper {

    public static final DateTimeFormatter getFormatter(String format) {
        return DateTimeFormatter.ofPattern(format).withLocale(Locale.ENGLISH);
    }

}
