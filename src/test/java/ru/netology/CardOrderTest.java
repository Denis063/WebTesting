package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardOrderTest {

    @Test
    void shouldSendValidForm() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $(".button_theme_alfa-on-white").click();
        $("[data-test-id=order-success]").shouldHave(matchText("Ваша заявка успешно отправлена!*"));
    }

    @Test
    void shouldSendInvalidName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Ivan Ivanov");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_text .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSendEmptyName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_text .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendInvalidNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("+799999999999");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_tel .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSendEmptyNumber() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_type_tel .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldInvalidCheckbox() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text")
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных" +
                        " и разрешаю сделать запрос в бюро кредитных историй"));
    }
}