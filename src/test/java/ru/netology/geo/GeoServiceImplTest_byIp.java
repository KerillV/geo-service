package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest_byIp {
// Arrange
    private final GeoService geoService = new GeoServiceImpl();

    // Тестируем метод byIp() с различными IP адресами
    @ParameterizedTest
    @ValueSource(strings = {
            "127.0.0.1",
            "172.0.32.11",
            "96.44.183.149",
            "172.0.0.0",
            "96.0.0.0",
            "unknown.ip.address" // случай, когда IP не известен
    })
// Act
    void testByIp(String ipAddress) {
        Location expectedLocation;

        switch (ipAddress) {
            case "127.0.0.1" -> expectedLocation = new Location(null, null, null, 0);
            case "172.0.32.11" -> expectedLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
            case "96.44.183.149" -> expectedLocation = new Location("New York", Country.USA, "10th Avenue", 32);
            case "172.0.0.0" -> expectedLocation = new Location("Moscow", Country.RUSSIA, null, 0);
            case "96.0.0.0" -> expectedLocation = new Location("New York", Country.USA, null, 0);
            default -> expectedLocation = null; // Ожидаемый результат — null для неизвестных IP
        }
// Assert
        Assertions.assertEquals(expectedLocation, geoService.byIp(ipAddress));

    }
}