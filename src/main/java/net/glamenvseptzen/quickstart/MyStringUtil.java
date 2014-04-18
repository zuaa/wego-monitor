package net.glamenvseptzen.quickstart;

import org.apache.commons.lang.StringUtils;

public class MyStringUtil {

    static String[] splitAndCapitalize(String source, String separator) {
        String[] splits = StringUtils.split(source, separator);
        String[] r = new String[splits.length];
        for (int i = 0; i < splits.length; i++) {
            r[i] = StringUtils.capitalise(splits[i]);
        }
        return r;
    }
}
