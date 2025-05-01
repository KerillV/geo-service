package ru.netology.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest_byCoordinates {

    @Test
    void testByCoordinatesThrowsException() {
        // Arrange
        GeoService geoService = new GeoServiceImpl();

        // Act & Assert
        assertThrows(RuntimeException.class,
                () -> geoService.byCoordinates(55.75, 37.62),
                "Метод 'byCoordinates' должен выбросить исключение 'Not implemented'");
    }
}