package poc.tdd;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class PasswordValidatorTest {

    private static final String MSG_SHORT = "Password must be at least 8 characters";
    private static final String MSG_TWO_NUMBERS = "The password must contain at least 2 numbers";
    private static final String MSG_CAPITAL = "password must contain at least one capital letter";
    private static final String MSG_SPECIAL_CHARACTER = "password must contain at least one special character";
    private static final String PASSWORD_TOO_SHORT = "5h0rT+";
    private static final String PASSWORD_WITHOUT_DIGITS = "No+Digits";
    private static final String PASSWORD_WITH_ONLY_ONE_DIGIT = "OnlyOneDigit1-";
    private static final String PASSWORD_SHORT_WITHOUT_DIGITS = "Short%";
    private static final String PASSWORD_WITHOUT_CAPITAL_LETTERS = "nocapitalletters12+";
    private static final String PASSWORD_WITHOUT_SPECIAL_CHARACTERS = "MissingSpecialCharacters12";

    private final PasswordValidator validator = new PasswordValidator();

    private static Stream<Arguments> testCases() {
        return Stream.of(
                // FIXME: add test case for valid password
                // FIXME: add test case for failing all conditions
                // TODO: how does it works for ñ and Ñ?
                Arguments.of(PASSWORD_WITHOUT_SPECIAL_CHARACTERS, new Validation(false, MSG_SPECIAL_CHARACTER)),
                Arguments.of(PASSWORD_WITHOUT_CAPITAL_LETTERS, new Validation(false, MSG_CAPITAL)),
                Arguments.of(PASSWORD_SHORT_WITHOUT_DIGITS, new Validation(false, MSG_SHORT + "\n" + MSG_TWO_NUMBERS)),
                Arguments.of(PASSWORD_WITH_ONLY_ONE_DIGIT, new Validation(false, MSG_TWO_NUMBERS)),
                Arguments.of(PASSWORD_WITHOUT_DIGITS, new Validation(false, MSG_TWO_NUMBERS)),
                Arguments.of(PASSWORD_TOO_SHORT, new Validation(false, MSG_SHORT))
        );
    }

    @ParameterizedTest(name= "when password is: {0} should return: {1}")
    @MethodSource("testCases")
    void test(final String input, final Validation expected) {
        assertThat(validator.validate(input)).isEqualTo(expected);
    }

    // TODO: create parametrized test to validate regular expressions.

}
