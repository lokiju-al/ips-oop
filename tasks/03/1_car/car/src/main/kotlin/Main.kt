enum class Direction {
    FORWARD,
    BACKWARD,
    STANDING_STILL
}

class Car {
    private var engineOn: Boolean = false
    private var currentGear: Int = 0
    private var currentSpeed: Int = 0

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
    }

    fun IsTurnedOn(): Boolean {
        return engineOn
    }

    fun GetDirection(): Direction {
        return when {
            currentGear == -1 -> Direction.BACKWARD
            currentSpeed == 0 && currentGear == 0 -> Direction.STANDING_STILL
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
        engineOn = true
        return true
    }

    fun TurnOffEngine(): Boolean {
        if (currentSpeed == 0 && currentGear == 0) {
            engineOn = false
            return true
        }
        return false
    }

    fun SetGear(gear: Int): Boolean {
        val newGear = when {
            gear == -1 && currentSpeed == 0 -> -1
            gear == 0 -> 0
            gear in 1..5 && this.GetDirection() !== Direction.BACKWARD -> gear
            else -> null
        }
        if (newGear != null && (newGear == 0 || (engineOn && currentSpeed in this.GetSpeedRangeForGear(gear)))) {
            currentGear = newGear
            return true
        }
        return false
    }

    fun SetSpeed(speed: Int): Boolean {
        if (engineOn && currentGear == 0 && speed <= currentSpeed && speed in this.GetSpeedRangeForGear(0)) {
            currentSpeed = speed
            return true
        } else if (engineOn && speed in this.GetSpeedRangeForGear(currentGear)) {
            currentSpeed = speed
            return true
        } else return false
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
                    println("Engine is turned on.")
                } else {
                    println("Engine is already turned on.")
                }
            }

            "EngineOff" -> {
                if (car.TurnOffEngine()) println("Engine is turned off.")
                else println("Engine can't be turned off at the moment.")
            }

            "SetGear" -> {
                val gear = parts[1].toIntOrNull()
                if (gear != null) {
                    if (car.SetGear(gear)) println("Gear set to $gear.")
                    else println("Can't set gear to $gear.")
                } else {
                    println("Invalid input for gear.")
                }
            }

            "SetSpeed" -> {
                val speed = parts[1].toIntOrNull()
                if (speed != null) {
                    if (car.SetSpeed(speed)) {
                        println("Speed set to $speed.")
                    } else {
                        println("Can't set speed to $speed.")
                    }
                } else {
                    println("Invalid input for speed.")
                }
            }

            "exit" -> {
                return
            }

            else -> println("Unknown command")
        }
    }
}
