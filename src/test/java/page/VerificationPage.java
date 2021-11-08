package page;

import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeFiel = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeFiel.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
