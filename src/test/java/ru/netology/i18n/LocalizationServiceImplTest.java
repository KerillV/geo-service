package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    private final LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    void testLocaleForRussia() {
        // given
        Country russia = Country.RUSSIA;

        // when
        String result = localizationService.locale(russia);

        // then
        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    void testLocaleForOtherCountries() {
        // given
        Country germany = Country.GERMANY;
        Country usa = Country.USA;
        Country brazil = Country.BRAZIL;

        // when
        String resultGermany = localizationService.locale(germany);
        String resultUsa = localizationService.locale(usa);
        String resultBrazil = localizationService.locale(brazil);

        // then
        Assertions.assertEquals("Welcome", resultGermany);
        Assertions.assertEquals("Welcome", resultUsa);
        Assertions.assertEquals("Welcome", resultBrazil);
    }
}