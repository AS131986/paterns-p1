package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;


public class FormPage {

    public void sendValidForm(DataHelper.FormInfo formInfo) {
        $x("//input[@placeholder='Город']").val(formInfo.getCity());
        $x("//input[@placeholder='Дата встречи']").click();
        $x("//input[@placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").val(formInfo.getDate());
        $x("//input[@name='name']").val(formInfo.getFullName());
        $x("//input[@name='phone']").val(formInfo.getPhoneNumber());
        $x("//*[@class='checkbox__box']").click();
        $(withText("Запланировать")).click();
        $x("//*[contains(text(),'Успешно!')]").should(Condition.appear, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + DataHelper.generateDate(7)), Duration.ofSeconds(15));

    }
}
