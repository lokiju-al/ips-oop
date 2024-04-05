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
    fun testIsTurnedOn() {
        car.TurnOffEngine()
        assertFalse(car.IsTurnedOn())
        car.TurnOnEngine()
        assertTrue(car.IsTurnedOn())
    }

    @Test
    fun testGetDirection() {
        assertEquals(Direction.STANDING_STILL, car.GetDirection())
        car.TurnOnEngine()
        car.SetGear(-1)
        assertEquals(Direction.BACKWARD, car.GetDirection())
        car.SetSpeed(10)
        assertEquals(Direction.BACKWARD, car.GetDirection())
        car.SetGear(0)
        assertEquals(Direction.STANDING_STILL, car.GetDirection())
        car.SetGear(1)
        assertEquals(Direction.FORWARD, car.GetDirection())
        car.SetSpeed(10)
        assertEquals(Direction.FORWARD, car.GetDirection())
    }

    @Test
    fun testGetSpeed() {
        assertEquals(0, car.GetSpeed())
        car.SetSpeed(50)
        assertEquals(50, car.GetSpeed())
    }

    @Test
    fun testGetGear() {
        assertEquals(0, car.GetGear())
        car.SetGear(2)
        assertEquals(2, car.GetGear())
    }

    @Test
    fun testTurnOnEngine() {
        assertFalse(car.IsTurnedOn())
        assertTrue(car.TurnOnEngine())
        assertTrue(car.IsTurnedOn())
    }

    @Test
    fun testTurnOffEngine() {
        assertFalse(car.TurnOffEngine()) // Engine is already off
        car.TurnOnEngine()
        assertFalse(car.TurnOffEngine()) // Can't turn off engine while moving
        car.SetGear(1)
        car.SetSpeed(20)
        assertFalse(car.TurnOffEngine()) // Can't turn off engine while moving
        car.SetSpeed(0)
        assertTrue(car.TurnOffEngine()) // Engine turned off
        assertFalse(car.IsTurnedOn())
    }

    @Test
    fun testSetGear() {
        assertTrue(car.SetGear(2)) // Can set any gear when engine is off
        assertFalse(car.SetGear(1)) // Can't set gear without turning on engine
        car.TurnOnEngine()
        assertTrue(car.SetGear(1)) // Can set gear after turning on engine
        assertFalse(car.SetGear(10)) // Invalid gear
        assertTrue(car.SetGear(-1)) // Can set reverse gear
        assertTrue(car.SetGear(0)) // Can set neutral gear
        car.SetSpeed(20)
        assertFalse(car.SetGear(4)) // Can't set gear while moving
        assertTrue(car.SetGear(0)) // Can set gear when speed is 0
    }

    @Test
    fun testSetSpeed() {
        assertFalse(car.SetSpeed(30)) // Can't set speed without turning on engine
        car.TurnOnEngine()
        assertTrue(car.SetSpeed(30)) // Can set speed after turning on engine
        car.SetGear(1)
        assertFalse(car.SetSpeed(40)) // Can't exceed speed limit for gear
        assertTrue(car.SetSpeed(20)) // Can set valid speed
        car.SetGear(0)
        assertFalse(car.SetSpeed(10)) // Can't accelerate on neutral gear
    }
}