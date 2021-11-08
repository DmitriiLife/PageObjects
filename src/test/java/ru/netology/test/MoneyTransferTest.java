package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    @Test
    void shouldReplenishmentOfTheFirstCard() {
        int amount = 100 + (int) (Math.random() * 5000);
        var authInfo = DataHelper.getAuthInfo();
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboard = verificationPage.validVerify(verificationCode);
        var cardBalanceFirst = dashboard.getFirstCardBalance();
        var cardBalanceSecond = dashboard.getSecondCardBalance();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferMoney = dashboard.firstCardButtonClick();
        transferMoney.transfer(cardInfo, amount);
        var cardBalanceAfterSendFirst = DataHelper.cardBalanceAfterGetMoney(cardBalanceFirst, amount);
        var cardBalanceAfterSendSecond = DataHelper.cardBalanceAfterSendMoney(cardBalanceSecond, amount);
        assertEquals(cardBalanceAfterSendFirst, dashboard.getFirstCardBalance());
        assertEquals(cardBalanceAfterSendSecond, dashboard.getSecondCardBalance());
    }

    @Test
    void shouldReplenishmentOfTheSecondCard() {
        int amount = 100 + (int) (Math.random() * 5000);
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboard = verificationPage.validVerify(verificationCode);
        var cardBalanceFirst = dashboard.getFirstCardBalance();
        var cardBalanceSecond = dashboard.getSecondCardBalance();
        var cardInfo = DataHelper.getFirstCardInfo();
        var transferMoney = dashboard.secondCardButtonClick();
        transferMoney.transfer(cardInfo, amount);
        var cardBalanceAfterSendFirst = DataHelper.cardBalanceAfterSendMoney(cardBalanceFirst, amount);
        var cardBalanceAfterSendSecond = DataHelper.cardBalanceAfterGetMoney(cardBalanceSecond, amount);
        assertEquals(cardBalanceAfterSendFirst, dashboard.getFirstCardBalance());
        assertEquals(cardBalanceAfterSendSecond, dashboard.getSecondCardBalance());
    }
}
