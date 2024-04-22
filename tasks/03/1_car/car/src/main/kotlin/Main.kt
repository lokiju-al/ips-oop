enum class Direction {
    FORWARD,
    BACKWARD,
    STANDING_STILL
}
// раскидать по файликам
@Suppress("DEPRECATED_IDENTITY_EQUALS")
class Car {
    private var isEngineOn: Boolean = false
    private var currentGear: Int = 0
    private var currentSpeed: Int = 0
    private var isMovingBackward: Boolean = false

    private fun GetSpeedRangeForGear(gear: Int): IntRange {
        return when (gear) {
            -1 -> 0..20
            0 -> 0..Int.MAX_VALUE
            1 -> 0..30
            2 -> 20..50
            3 -> 30..60
            4 -> 40..90
            5 -> 50..150
            else -> 0..0
        }
    }// setspeed без параметра падает

    fun IsTurnedOn(): Boolean {
        return isEngineOn
    }

    fun GetDirection(): Direction {
        return when { // убрать избыточность в тестах
            currentSpeed > 0 && isMovingBackward -> Direction.BACKWARD
            currentSpeed == 0 -> Direction.STANDING_STILL
            else -> Direction.FORWARD
        }
    }

    fun GetSpeed(): Int {
        return currentSpeed
    }

    fun GetGear(): Int {
        return currentGear
    }

    fun TurnOnEngine(): Boolean {
        isEngineOn = true
        return true
    }

    fun TurnOffEngine(): Boolean {
        if (currentSpeed == 0 && currentGear == 0) {
            isEngineOn = false
            return true
        }
        return false
    }

    fun SetGear(gear: Int): Boolean {
        val newGear = when { // isEngineOn вверх
            gear == -1 && currentSpeed == 0 -> -1 // gear а не -1
            gear == 0 -> 0
            gear in 1..5 && !isMovingBackward -> gear
            else -> null
        }
        if (newGear != null && (newGear == 0 || (isEngineOn && currentSpeed in this.GetSpeedRangeForGear(gear)))) {
            currentGear = newGear
            return true
        }
        return false
    }

    fun SetSpeed(speed: Int): Boolean {
        if (isEngineOn && currentGear == 0 && speed <= currentSpeed && speed in this.GetSpeedRangeForGear(0)) {
            if (speed == 0) {
                isMovingBackward = false
            }
            currentSpeed = speed
            return true
        } else if (isEngineOn && currentGear !== 0 && speed in this.GetSpeedRangeForGear(currentGear)) {
            isMovingBackward = speed > 0 && currentGear == -1
            currentSpeed = speed
            return true
        } else { // eубрать elsees
            return false
        }
    }
}

fun main() {
    val car = Car()

    while (true) {
        val input = readlnOrNull()?.trim() ?: break
        val parts = input.split(" ")
        when (parts[0]) {
            "Info" -> {
                println("Engine: ${if (car.IsTurnedOn()) "on" else "off"}")
                println("Direction: ${car.GetDirection()}")
                println("Speed: ${car.GetSpeed()}")
                println("Gear: ${car.GetGear()}")
            }

            "EngineOn" -> {
                if (car.TurnOnEngine()) {
                    println("Engine is turned on")
                }
            }

            "EngineOff" -> {
                if (car.TurnOffEngine()) println("Engine is turned off")
                else println("Engine can't be turned off at the moment")
            }

            "SetGear" -> {
                val gear = parts[1].toIntOrNull()
                if (gear != null) {
                    if (car.SetGear(gear)) println("Gear set to $gear")
                    else println("Can't set gear to $gear")
                } else {
                    println("Invalid input for gear")
                }
            }

            "SetSpeed" -> {
                val speed = parts[1].toIntOrNull()
                if (speed != null) {
                    if (car.SetSpeed(speed)) {
                        println("Speed set to $speed")
                    } else {
                        println("Can't set speed to $speed")
                    }
                } else {
                    println("Invalid input for speed")
                }
            }

            "exit" -> {
                return
            }

            else -> println("Unknown command")
        }
    }
}
