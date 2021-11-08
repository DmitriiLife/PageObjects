package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void TransferV1() {
        int amount = 100 + (int) (Math.random() * 5000);
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardBalanceFirst = dashboardPage.getFirstCardBalance();
        var cardBalanceSecond = dashboardPage.getSecondCardBalance();
        var cardInfo = DataHelper.getSecondCardInfo();
        var transferMoney = dashboardPage.firstCardButtonClick();
        transferMoney.transfer(cardInfo, amount);
        var cardBalanceAfterSendFirst = DataHelper.cardBalanceAfterGetMoney(cardBalanceFirst, amount);
        var cardBalanceAfterSendSecond = DataHelper.cardBalanceAfterSendMoney(cardBalanceSecond, amount);
        assertEquals(cardBalanceAfterSendFirst, dashboardPage.getFirstCardBalance());
        assertEquals(cardBalanceAfterSendSecond, dashboardPage.getSecondCardBalance());
    }
}
