package com.yash.ota.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    public static boolean validateTestDuration(String testDuration) {
        String pattern = "(?:[01]\\d|2[0123]):(?:[012345]\\d):(?:[012345]\\d)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(testDuration);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
