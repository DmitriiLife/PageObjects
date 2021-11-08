package ru.netology.data;

import lombok.*;
import ru.netology.page.DashboardPage;

@Value
public class DataHelper {

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class Card {
        private String number;
        private int balance;
    }

    public static Card getFirstCardInfo() {
        var dashboard = new DashboardPage();
        return new Card("5559 0000 0000 0001", dashboard.getCardBalance("01"));
    }

    public static Card getSecondCardInfo() {
        var dashboard = new DashboardPage();
        return new Card("5559 0000 0000 0002", dashboard.getCardBalance("02"));
    }

    public static Card getWrongCardInfo() {
        var dashboard = new DashboardPage();
        return new Card("5559 0000 0000 0003", 10000);
    }

    public static int cardBalanceAfterSendMoney(int balance, int amount) {
        int total = balance - amount;
        return total;
    }

    public static int cardBalanceAfterGetMoney(int balance, int amount) {
        int total = balance + amount;
        return total;
    }
}
