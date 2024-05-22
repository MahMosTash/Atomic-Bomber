package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Fields {
    validUsername("[a-zA-Z_\\d]{5,}"),
    strongPassword("(?=.*[a-zA-Z])(?=.*[!@#$%^&*])(?=.*\\d)(\\S{8,})"),;
    private final String pattern;

    Fields(String pattern) {
        this.pattern = pattern;
    }
    public Matcher getMather(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
