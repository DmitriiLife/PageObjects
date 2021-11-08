package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private ElementsCollection refillButton = $$("[data-test-id='action-deposit']");

    public int getCardBalance(String id) {
        var text = cards.find(Condition.text(id)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getFirstCardBalance() {
        return getCardBalance("01");
    }

    public int getSecondCardBalance() {
        return getCardBalance("02");
    }

    public TransferPage firstCardButtonClick() {
        refillButton.first().click();
        return new TransferPage();
    }
}