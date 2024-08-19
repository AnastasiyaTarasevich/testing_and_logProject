import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {



    @Test
    public void testConstructorForName()
    {
        Throwable exception= assertThrows(IllegalArgumentException.class,()->
        {
            new Horse(null,9,0);
        });
        assertEquals("Name cannot be null.",exception.getMessage());

    }
    @ParameterizedTest
    @ValueSource(strings = {" ","\t","\n","\r"})
    public void testWhitespaces (String arg) {
        Throwable exception= assertThrows(IllegalArgumentException.class,()->
        {
            new Horse(arg,9,0);
        });
        assertEquals("Name cannot be blank.",exception.getMessage());
    }

    @Test
    public void testNegativeSpeed()
    {
        Throwable ex=assertThrows(
                IllegalArgumentException.class,
                ()->{
                    new Horse("dkdk",-1,0);
                }
        );
        assertEquals("Speed cannot be negative.",ex.getMessage());
    }

    @Test
    public void testNegativeDistance()
    {
        Throwable ex=assertThrows(
                IllegalArgumentException.class,
                ()->{
                    new Horse("dkdk",0,-2);
                }
        );
        assertEquals("Distance cannot be negative.",ex.getMessage());
    }

    @Test
    void getName() {

        String expected="Spirit";
        Horse horse=new Horse(expected,0,0);

        String actual=horse.getName();
        assertEquals(expected,actual);
    }

    @Test
    void getSpeed() {
        double expectedSpeed=0;
        Horse horse=new Horse("hfahf",expectedSpeed,0);

        double actualSpeed=horse.getSpeed();
        assertEquals(expectedSpeed,actualSpeed);
    }

    @Test
    void getDistance() {
        double expectedDistance=0;
        Horse horse=new Horse("hfahf",0,expectedDistance);

        double actualDistance=horse.getDistance();
        assertEquals(expectedDistance,actualDistance);

        Horse horse1=new Horse("hfahf",0);
        double actualDistance1=horse1.getDistance();
        assertEquals(0,actualDistance1);
    }

    @Test
    void move() {
    }
}