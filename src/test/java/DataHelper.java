
import lombok.Data;
import lombok.RequiredArgsConstructor;


public class DataHelper {

    @Data
    @RequiredArgsConstructor
    public static class AuthInfo {
       private final String login;
       private final String password;
    }

    @Data
    @RequiredArgsConstructor
    public static class VerificationCode {
        private final String code;
    }

    @Data
    @RequiredArgsConstructor
    public static class Card {
        private final String number;
        private final int balance;
    }
}
