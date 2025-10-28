package lab_3.car_3_1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CarTest {
    private lateinit var car: Car

    @BeforeEach
    fun setup() {
        car = Car()
    }

    @Test
    fun testIsTurnedOn() {// testturnonengine
        car.TurnOffEngine()
        assertFalse(car.IsTurnedOn())
        car.TurnOnEngine()
        assertTrue(car.IsTurnedOn())
    }

    @Test
    fun testGetDirection() {//разделить двиг вперб двиг назад . стоим
        assertEquals(Direction.STANDING_STILL, car.GetDirection())
        car.TurnOnEngine()
        car.SetGear(-1)
        assertEquals(Direction.STANDING_STILL, car.GetDirection())
        car.SetSpeed(10)
        assertEquals(Direction.BACKWARD, car.GetDirection())
        car.SetGear(0)
        assertEquals(Direction.BACKWARD, car.GetDirection())
        car.SetSpeed(0)
        car.SetGear(1)
        assertEquals(Direction.STANDING_STILL, car.GetDirection())
        car.SetSpeed(10)
        assertEquals(Direction.FORWARD, car.GetDirection())
        car.SetGear(0)
        assertEquals(Direction.FORWARD, car.GetDirection())
        car.SetSpeed(0)
        assertEquals(Direction.STANDING_STILL, car.GetDirection())
        car.SetGear(-1)
        assertEquals(Direction.STANDING_STILL, car.GetDirection())
        car.SetSpeed(10)
        assertEquals(Direction.BACKWARD, car.GetDirection())
    }

    @Test
    fun testGetSpeed() {
        car.TurnOnEngine()
        assertEquals(0, car.GetSpeed())
        car.SetGear(1)
        car.SetSpeed(30)
        assertEquals(30, car.GetSpeed())
        car.SetGear(3)
        assertEquals(30, car.GetSpeed())
        car.SetSpeed(60)
        assertEquals(60, car.GetSpeed())
        car.SetSpeed(60)
        assertEquals(60, car.GetSpeed())
        car.SetSpeed(30)
        assertEquals(30, car.GetSpeed())
        car.SetGear(0)
        car.SetSpeed(1)
        assertEquals(1, car.GetSpeed())
        car.SetSpeed(0)
        assertEquals(0, car.GetSpeed())
        car.SetGear(-1)
        assertEquals(0, car.GetSpeed())
        car.SetSpeed(20)
        assertEquals(20, car.GetSpeed())
    }

    @Test
    fun testGetGear() {
        car.TurnOnEngine()
        assertEquals(0, car.GetGear())
        car.SetGear(2)
        assertEquals(0, car.GetGear())
        car.SetGear(1)
        assertEquals(1, car.GetGear())
        car.SetGear(-1)
        assertEquals(-1, car.GetGear())
        car.SetSpeed(5)
        car.SetGear(1)
        assertEquals(-1, car.GetGear())
        car.SetGear(0)
        assertEquals(0, car.GetGear())
        car.SetSpeed(0)
        car.SetGear(1)
        assertEquals(1, car.GetGear())
        car.SetSpeed(30)
        car.SetGear(3)
        assertEquals(3, car.GetGear())
        car.SetSpeed(60)
        car.SetGear(5)
        assertEquals(5, car.GetGear())
    }

    @Test
    fun testTurnOnEngine() {
        assertFalse(car.IsTurnedOn())
        assertTrue(car.TurnOnEngine())
        assertTrue(car.IsTurnedOn())
        assertTrue(car.TurnOnEngine())
        assertTrue(car.IsTurnedOn())
        assertTrue(car.TurnOffEngine())
        assertTrue(car.TurnOnEngine())
    }

    @Test
    fun testTurnOffEngine() {
        assertTrue(car.TurnOffEngine())
        assertTrue(car.TurnOnEngine())
        assertTrue(car.TurnOffEngine())
        assertTrue(car.TurnOnEngine())
        assertTrue(car.SetGear(1))
        assertFalse(car.TurnOffEngine())
        assertTrue(car.SetSpeed(20))
        assertFalse(car.TurnOffEngine())
        assertTrue(car.SetGear(2))
        assertFalse(car.TurnOffEngine())
        assertTrue(car.SetGear(0))
        assertTrue(car.SetSpeed(0))
        assertTrue(car.TurnOffEngine())
        assertFalse(car.IsTurnedOn())
    }

    @Test
    fun testSetGear() {
        assertTrue(car.SetGear(0))
        assertFalse(car.SetGear(1))
        assertTrue(car.TurnOnEngine())
        assertFalse(car.SetGear(10))
        assertTrue(car.SetGear(-1))
        assertTrue(car.SetGear(0))
        assertTrue(car.SetGear(1))
        assertTrue(car.SetSpeed(30))
        assertFalse(car.SetGear(4))
        assertTrue(car.SetGear(0))
        assertTrue(car.SetSpeed(20))
        assertTrue(car.SetGear(2))
        assertTrue(car.SetSpeed(50))
        assertFalse(car.SetGear(-1))
        assertTrue(car.SetGear(0))
        assertFalse(car.SetGear(1))
        assertTrue(car.SetGear(2))
        assertTrue(car.SetGear(3))
        assertTrue(car.SetGear(4))
        assertTrue(car.SetGear(5))
        assertTrue(car.SetGear(0))
        assertTrue(car.SetSpeed(10))
        assertTrue(car.SetGear(1))
        assertTrue(car.SetSpeed(0))
        assertTrue(car.SetGear(-1))
        assertTrue(car.SetSpeed(20))
        assertTrue(car.SetGear(0))
        assertFalse(car.SetGear(1))
        assertFalse(car.SetGear(2))
        assertFalse(car.SetGear(3))
        assertFalse(car.SetGear(4))
        assertFalse(car.SetGear(5))
    }

    @Test
    fun testSetSpeed() {
        // initialize

        // turn on engine
        // set speed

        // action

        // engine off

        // assert


        assertFalse(car.SetSpeed(30))
        assertTrue(car.TurnOnEngine())
        assertTrue(car.SetGear(1))
        assertTrue(car.SetSpeed(30))
        assertTrue(car.SetGear(1))
        assertFalse(car.SetSpeed(40))
        assertTrue(car.SetSpeed(20))
        assertTrue(car.SetGear(0))
        assertFalse(car.SetSpeed(21))
        assertTrue(car.SetSpeed(20))
        assertTrue(car.SetSpeed(0))
        assertTrue(car.SetGear(-1))
        assertTrue(car.SetSpeed(20))
        assertTrue(car.SetGear(0))
        assertTrue(car.SetSpeed(10))
    }
}