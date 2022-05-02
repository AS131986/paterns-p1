package ru.netology.web.test;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.FormPage;


import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class SendFormPage {

    public String generateDate2 (int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    String planningDate2 = generateDate2(9);

    @Test
    void shouldSendFormPlanning() {
        holdBrowserOpen = true;
        open("http://localhost:9999/");
        var formPage = new FormPage();
        var formInfo = DataHelper.FormInfo.getFormInfo();
        formPage.sendValidForm(formInfo);
    }
    @Test
    void shouldSendFormRePlanning() {
        holdBrowserOpen = true;
        open("http://localhost:9999/");
        var formPage = new FormPage();
        var formInfo = DataHelper.FormInfo.getFormInfo();
        formPage.sendValidForm(formInfo);
        $x("//input[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").val(generateDate2(9));
        $(withText("Запланировать")).click();
        $x("//*[contains(text(),'Необходимо подтверждение')]").should(Condition.appear, Duration.ofSeconds(15));
        $x("//*[contains(text(),'У вас уже запланирована встреча на другую дату. Перепланировать?')]").should(Condition.appear);
        $$x("//*[@class='button__content']").filter(Condition.visible).last().click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + planningDate2), Duration.ofSeconds(15));
    }
}
