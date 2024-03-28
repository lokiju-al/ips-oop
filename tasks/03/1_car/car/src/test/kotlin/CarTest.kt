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
    fun testIsEngineOn() {
        assertFalse(car.isEngineOn())
        car.turnOnEngine()
        assertTrue(car.isEngineOn())
    }

    @Test
    fun testGetDirection() {
        assertEquals(Direction.STANDING_STILL, car.getDirection())
        car.setGear(-1)
        assertEquals(Direction.BACKWARD, car.getDirection())
        car.setSpeed(10)
        assertEquals(Direction.BACKWARD, car.getDirection())
        car.setSpeed(0)
        assertEquals(Direction.STANDING_STILL, car.getDirection())
        car.setGear(1)
        assertEquals(Direction.FORWARD, car.getDirection())
    }

    @Test
    fun testGetSpeed() {
        assertEquals(0, car.getSpeed())
        car.setSpeed(50)
        assertEquals(50, car.getSpeed())
    }

    @Test
    fun testGetGear() {
        assertEquals(0, car.getGear())
        car.setGear(2)
        assertEquals(2, car.getGear())
    }

    @Test
    fun testTurnOnEngine() {
        assertFalse(car.isEngineOn())
        assertTrue(car.turnOnEngine())
        assertTrue(car.isEngineOn())
    }

    @Test
    fun testTurnOffEngine() {
        assertFalse(car.turnOffEngine()) // Engine is already off
        car.turnOnEngine()
        assertFalse(car.turnOffEngine()) // Can't turn off engine while moving
        car.setGear(1)
        car.setSpeed(20)
        assertFalse(car.turnOffEngine()) // Can't turn off engine while moving
        car.setSpeed(0)
        assertTrue(car.turnOffEngine()) // Engine turned off
        assertFalse(car.isEngineOn())
    }

    @Test
    fun testSetGear() {
        assertTrue(car.setGear(2)) // Can set any gear when engine is off
        assertFalse(car.setGear(1)) // Can't set gear without turning on engine
        car.turnOnEngine()
        assertTrue(car.setGear(1)) // Can set gear after turning on engine
        assertFalse(car.setGear(10)) // Invalid gear
        assertTrue(car.setGear(-1)) // Can set reverse gear
        assertTrue(car.setGear(0)) // Can set neutral gear
        car.setSpeed(20)
        assertFalse(car.setGear(4)) // Can't set gear while moving
        assertTrue(car.setGear(0)) // Can set gear when speed is 0
    }

    @Test
    fun testSetSpeed() {
        assertFalse(car.setSpeed(30)) // Can't set speed without turning on engine
        car.turnOnEngine()
        assertTrue(car.setSpeed(30)) // Can set speed after turning on engine
        car.setGear(1)
        assertFalse(car.setSpeed(40)) // Can't exceed speed limit for gear
        assertTrue(car.setSpeed(20)) // Can set valid speed
        car.setGear(0)
        assertFalse(car.setSpeed(10)) // Can't accelerate on neutral gear
    }
}