package ru.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {

    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void shouldRegister() {
        open("http://localhost:9999/");

        $("[data-test-id='city'] input").setValue("Казань");
        String currentDate = generateDate(3, "dd.ММ.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='data'] input").sendKeys(currentDate);
        $x("//span[contains(text(),'Фамилия и имя')]").setValue("Исаева Елена");
        $x("//span[contains(text(),'Мобильный телефон')]").setValue("+71234567890");
        $x("//span[contains(text(),'Я соглашаюсь')]").click();
        $x("//span[contains(text(),'Забронировать')]").click();
        $x("//span[contains(text(),'Успешно!')]").should(appear, Duration.ofSeconds(15));
    }
}
