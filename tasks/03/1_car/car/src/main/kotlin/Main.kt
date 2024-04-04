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
            currentSpeed == 0 -> Direction.STANDING_STILL
            currentSpeed > 0 && currentGear == -1 -> Direction.BACKWARD
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
        if (engineOn && currentSpeed == 0 && currentGear == Gear.NEUTRAL) {
            engineOn = false
            return true
        }
        return false
    }

    fun SetGear(gear: Int): Boolean {
        val newGear = when (gear) {
            in Gear.entries.toTypedArray().indices -> Gear.entries[gear]
            else -> null
        }
        if (newGear != null && (newGear == Gear.NEUTRAL || (engineOn && currentSpeed in newGear.range))) {
            currentGear = newGear
            return true
        }
        return false
    }

    fun SetSpeed(speed: Int): Boolean {
        return if (engineOn && currentGear != Gear.NEUTRAL && speed in currentGear.range) {
            currentSpeed = speed
            true
        } else false
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

            else -> println("Unknown command")
        }
    }
}
