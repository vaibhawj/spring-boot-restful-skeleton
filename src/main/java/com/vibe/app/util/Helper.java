package com.vibe.app.util;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Helper {
    public static final DateTimeFormatter MM_SLASH_DD_SLASH_YYYY = DateTimeFormatter.ofPattern("MM/dd/yyyy").withLocale(Locale.ENGLISH);

}
