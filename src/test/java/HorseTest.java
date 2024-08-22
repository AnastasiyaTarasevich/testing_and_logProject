import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

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
    void getName() throws NoSuchFieldException, IllegalAccessException {

        String expected="Spirit";
        Horse horse=new Horse(expected,0,0);
       Field name= Horse.class.getDeclaredField("name");
        String actual=(String)name.get(horse);
        assertEquals(expected,actual);
    }

    @Test
    void getSpeed() throws NoSuchFieldException, IllegalAccessException {
        double expectedSpeed=0;
        Horse horse=new Horse("hfahf",expectedSpeed,0);

       Field speed=Horse.class.getDeclaredField("speed");
       double actualSpeed=(double) speed.get(horse);
        assertEquals(expectedSpeed,actualSpeed);
    }

    @Test
    void getDistance() throws NoSuchFieldException, IllegalAccessException {
        double expectedDistance=0;
        Horse horse=new Horse("hfahf",0,expectedDistance);

        Field distance=Horse.class.getDeclaredField("distance");
        double actualDistance=(double) distance.get(horse);
        assertEquals(expectedDistance,actualDistance);

        Horse horse1=new Horse("hfahf",0);
        double actualDistance1=horse1.getDistance();
        assertEquals(0,actualDistance1);
    }

    @ParameterizedTest
    @CsvSource({
            "5.0, 0.5, 2.5",  // speed=5.0, getRandomDouble=0.5, expected distance increase=2.5
            "10.0, 0.8, 8.0", // speed=10.0, getRandomDouble=0.8, expected distance increase=8.0
            "0.0, 0.3, 0.0"   // speed=0.0, getRandomDouble=0.3, expected distance increase=0.0
    })
    void move(double speed, double randomDoubleValue, double expectedDistanceIncrease) {
        try(MockedStatic<Horse> horseMockedStatic= Mockito.mockStatic(Horse.class))
        {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDoubleValue);
            Horse horse=new Horse("jxjij",speed);
            horse.move();
            horseMockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9));
            assertEquals(expectedDistanceIncrease, horse.getDistance());
        }

    }
}