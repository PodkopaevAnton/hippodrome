import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void checkConstructorNameForExceptionAndMessage(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null,0,0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { " ", "\u0020", "\u0009","","\n" })
    void checkConstructorTabulationNamesForExceptionAndMessage(String param){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(param,0,0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void checkForConstructorSpeedForExceptionAndMessage(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("name",-1,0)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void checkForConstructorDistanceForExceptionAndMessage(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("name",1,-1)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getHorseName() {
        String name = "name";
        Horse horse = new Horse(name,1,1);
        assertEquals(name,horse.getName());
    }

    @Test
    void getHorseSpeed() {
        double speed = 8;
        Horse horse = new Horse("name",speed,1);
        assertEquals(speed,horse.getSpeed());
    }

    @Test
    void getHorseDistanceForConstructorWithThreeParameters() {
        double distance = 8;
        Horse horse = new Horse("name",1,distance);
        assertEquals(distance,horse.getDistance());
    }

    @Test
    void getHorseDistanceForConstructorWithTwoParameters() {
        Horse horse = new Horse("name",1);
        assertEquals(0,horse.getDistance());
    }

    @Test
    void verifyGetRandomDouble() {
        try(MockedStatic< Horse> horseMockedStatic =  Mockito.mockStatic( Horse.class)){
            new Horse("name",1,1).move();
            horseMockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({" 30, 10","35, 1","40, 0"})
    void verifyHorseDistance(double speed,double distance) {
        try(MockedStatic< Horse> horseMockedStatic =  Mockito.mockStatic( Horse.class)){
            Horse horse = new Horse("name",speed,distance);
            horseMockedStatic.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(5.0);
            horse.move();
            assertEquals(distance + speed * 5.0, horse.getDistance());
        }
    }

}