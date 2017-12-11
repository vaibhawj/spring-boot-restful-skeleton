package com.vibe.app.util;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Helper {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy").withLocale(Locale.ENGLISH);

}
