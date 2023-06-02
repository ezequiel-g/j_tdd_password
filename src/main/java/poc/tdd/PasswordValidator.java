package poc.tdd;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final int MIN_LENGTH = 8;
    private static final String LINE_BREAK = "\n";

    private static final String MSG_TOO_SHORT = "Password must be at least 8 characters";
    private static final String MSG_TWO_DIGITS = "The password must contain at least 2 numbers";
    private static final String MSG_CAPITAL_LETTER = "password must contain at least one capital letter";
    private static final String MSG_SPECIAL_CHARACTERS = "password must contain at least one special character";

    public static final Pattern PATTERN_HAS_TWO_DIGITS = Pattern.compile("\\d.*\\d");
    public static final Pattern PATTERN_HAS_CAPITAL_LETTERS = Pattern.compile("[A-Z]");
    public static final Pattern PATTERN_HAS_SPECIAL_CHARACTERS = Pattern.compile("[+\\-%]");

    public Validation validate(final String password) {

        final StringBuilder sb = new StringBuilder();

        // TODO: can we make it "true" more close to the return?
        boolean isValid = true;

        if (password.length() <= MIN_LENGTH) {
            sb.append(MSG_TOO_SHORT);
            isValid = false;
        }

        isValid = isValidForPattern(password, sb, isValid, PATTERN_HAS_TWO_DIGITS, MSG_TWO_DIGITS);

        isValid = isValidForPattern(password, sb, isValid, PATTERN_HAS_CAPITAL_LETTERS, MSG_CAPITAL_LETTER);

        isValid = isValidForPattern(password, sb, isValid, PATTERN_HAS_SPECIAL_CHARACTERS, MSG_SPECIAL_CHARACTERS);

        // TODO: extend PasswordValidator and try to remove isValid variable using sb.isEmpty()
        return new Validation(isValid, sb.toString());
    }

    private boolean isValidForPattern(final String password, final StringBuilder sb, boolean isValid, final Pattern pattern, final String msg) {
        if (!pattern.matcher(password).find()) {
            addNewLine(sb);
            isValid = false;
            sb.append(msg);
        }
        return isValid;
    }

    private void addNewLine(final StringBuilder sb) {
        if (!sb.isEmpty()) {
            sb.append(LINE_BREAK);
        }
    }

}
