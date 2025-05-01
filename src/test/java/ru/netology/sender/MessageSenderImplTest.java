package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    // различные наборы данных для тестирования
    private static Stream<Arguments> dataProvider() {
        List<Arguments> argumentsList = new ArrayList<>();

        // набор данных для RUSSIA, USA и GERMANY
        argumentsList.add(Arguments.of("172.0.32.11", Country.RUSSIA, "Добро пожаловать"));
        argumentsList.add(Arguments.of("96.44.189.15", Country.USA, "Welcome"));
        argumentsList.add(Arguments.of("88.198.15.16", Country.GERMANY, "Welcome"));

        return argumentsList.stream();
    }

    @ParameterizedTest
    @MethodSource("dataProvider") // уточняет источник данных для параметризованного теста
    void parameterizedTest(String ipAddress, Country country, String expectedResult) {
        // создание моков
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);

        // настройка поведения моков
        Mockito.when(geoService.byIp(ipAddress)).thenReturn(new Location("", country, "", 0)); // Используем произвольные координаты
        Mockito.when(localizationService.locale(country)).thenReturn(expectedResult);

        // создаём экземпляр класса MessageSenderImpl с мокированными зависимостями
        MessageSenderImpl messageSenderImpl = new MessageSenderImpl(geoService, localizationService);

        // создаём заголовки с указанным IP-адресом
        Map<String, String> headers = Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);

        // Выполняем тестирование
        String actualResult = messageSenderImpl.send(headers);

        // Проверяем равенство ожидаемого и фактического результатов
        assertEquals(expectedResult, actualResult);
    }
}