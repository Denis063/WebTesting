package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardOrderTest {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");

        //damn lazy developer!!!
        //SelenideElement form =$("[data-test-id=callback-form]");
        SelenideElement form =$(".form_theme_alfa-on-white");

        form.$("[data-test-id=name] input").setValue("Иван Иванов");
        form.$("[data-test-id=phone] input").setValue("+79000000000");
        form.$("[data-test-id=agreement]").click();

        //damn lazy developer!!!
        //form.$("[data-test-id=submit]").click();
        form.$(".button_theme_alfa-on-white").click();

        //$(".alert-success").shouldHave(exactText("Ваша заявка успешно отправлена!"));
        $("[data-test-id=order-success]").shouldHave(matchText("Ваша заявка успешно отправлена!*"));
    }
}