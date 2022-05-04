package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Value
public class DataHelper {
    private DataHelper() {
    }

    private static Faker faker;

    @BeforeAll
    static void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planningDate = generateDate(7);


    public static String generateDate2(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planningDate2 = generateDate2(9);

    @Value
    public static class FormInfo {
        private String city;
        private String date;
        private String fullName;
        private String phoneNumber;

        public static FormInfo getFormInfo() {
            faker = new Faker(new Locale("ru"));
            String city = faker.address().city();
            String fullName = faker.name().fullName();
            String date = generateDate(7);
            String phoneNumber = faker.phoneNumber().phoneNumber();
            return new FormInfo(city, date, fullName, phoneNumber);
        }
    }
}