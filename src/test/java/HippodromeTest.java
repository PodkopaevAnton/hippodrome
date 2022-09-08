import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    @Test
    void checkConstructorWithNullForExceptionAndMessage(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void checkConstructorWithEmptyListForExceptionAndMessage(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHippodromeHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30;i++){
            horses.add(new Horse("Horse" + i, i,0));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertIterableEquals(horses,hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50;i++){
            horses.add(Mockito.mock(Horse.class));
        }
        new Hippodrome(horses).move();
        for (Horse horse: horses)
            Mockito.verify(horse).move();
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Буцефал", 2.4,0);
        Horse horse2 = new Horse("Туз Пик", 2.5,1);
        Horse horse3 = new Horse("Зефир", 2.6,2);
        Horse horse4 = new Horse("Пожар", 2.7,3);
        Horse horse5 = new Horse("Лобстер", 2.8,4);
        Horse horse6 = new Horse("Пегас", 2.9,5);
        Horse horse7 = new Horse("Вишня", 3,100);
        List<Horse> horses = List.of(horse1,horse2,horse3,horse4,horse5,horse6,horse7);
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horse7,hippodrome.getWinner());
    }
}