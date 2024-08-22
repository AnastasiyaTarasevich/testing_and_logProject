import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    public void testConstructorForNull()
    {
        Throwable exception= assertThrows(IllegalArgumentException.class,()->
        {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.",exception.getMessage());

    }

    @Test
    public void testConstructorForVoidList()
    {
        List<Horse> horses=new ArrayList<>();
        Throwable ex=assertThrows(IllegalArgumentException.class,()->
        {
            new Hippodrome(horses);
        });
        assertEquals("Horses cannot be empty.",ex.getMessage());
    }
    @Test
    void getHorses() {
        List <Horse> horses=new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(" "+i,i,i));

        }
        Hippodrome hippodrome=new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));

        }
        Hippodrome hippodrome=new Hippodrome(horses);

        hippodrome.move();

        for (Horse hors : horses) {
           verify(hors).move();
        }


    }

    @Test
    void getWinner() {

        Horse horse1=new Horse("nfsjf",1,3);
        Horse horse2=new Horse("nfsjf",1,1);
        Horse horse3=new Horse("nfsjf",1,5);

        Hippodrome hippodrome=new Hippodrome(List.of(horse1,horse2,horse3));
        assertSame(horse3,hippodrome.getWinner());
    }
}