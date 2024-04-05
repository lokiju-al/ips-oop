enum class Gear(val range: IntRange) {
    REVERSE(0..20),
    NEUTRAL(Int.MIN_VALUE..Int.MAX_VALUE),
    FIRST(0..30),
    SECOND(20..50),
    THIRD(30..60),
    FOURTH(40..90),
    FIFTH(50..150)
}

enum class Direction {
    FORWARD,
    BACKWARD,
    STANDING_STILL
}

class Car {
    private var engineOn: Boolean = false
    private var currentGear: Gear = Gear.NEUTRAL
    private var currentSpeed: Int = 0

    fun isEngineOn(): Boolean {
        return engineOn
    }

    fun getDirection(): Direction {
        return when {
            currentGear == Gear.REVERSE -> Direction.BACKWARD
            currentSpeed == 0 -> Direction.STANDING_STILL
            currentGear == Gear.NEUTRAL -> Direction.STANDING_STILL
            currentSpeed > 0 -> Direction.FORWARD
            else -> Direction.STANDING_STILL
        }
    }

    fun getSpeed(): Int {
        return currentSpeed
    }

    fun getGear(): Int {
        return when (currentGear) {
            Gear.REVERSE -> -1
            Gear.NEUTRAL -> 0
            else -> currentGear.ordinal
        }
    }

    fun turnOnEngine(): Boolean {
        if (!engineOn) {
            engineOn = true
            currentGear = Gear.NEUTRAL
            return true
        }
        return false
    }

    fun turnOffEngine(): Boolean {
        if (engineOn && currentSpeed == 0 && currentGear == Gear.NEUTRAL) {
            engineOn = false
            return true
        }
        return false
    }

    fun setGear(gear: Int): Boolean {
        val newGear = when (gear) {
            in Gear.values().indices -> Gear.values()[gear]
            else -> null
        }
        if (newGear != null && (newGear == Gear.NEUTRAL || (engineOn && currentSpeed in newGear.range))) {
            currentGear = newGear
            return true
        }
        return false
    }

    fun setSpeed(speed: Int): Boolean {
        return if (engineOn && currentGear != Gear.NEUTRAL && speed in currentGear.range) {
            currentSpeed = speed
            true
        } else false
    }
}

fun main() {
    val car = Car()

    while (true) {
        val input = readLine()?.trim() ?: break
        val parts = input.split(" ")
        when (parts[0]) {
            "Info" -> {
                println("Engine: ${if (car.isEngineOn()) "on" else "off"}")
                println("Direction: ${car.getDirection()}")
                println("Speed: ${car.getSpeed()}")
                println("Gear: ${car.getGear()}")
            }
            "EngineOn" -> {
                if (car.turnOnEngine()) println("Engine is turned on.")
                else println("Engine is already turned on.")
            }
            "EngineOff" -> {
                if (car.turnOffEngine()) println("Engine is turned off.")
                else println("Engine can't be turned off at the moment.")
            }
            "SetGear" -> {
                val gear = parts[1].toIntOrNull()
                if (gear != null) {
                    if (car.setGear(gear)) println("Gear set to $gear.")
                    else println("Can't set gear to $gear.")
                } else {
                    println("Invalid input for gear.")
                }
            }
            "SetSpeed" -> {
                val speed = parts[1].toIntOrNull()
                if (speed != null) {
                    if (car.setSpeed(speed)) println("Speed set to $speed.")
                    else println("Can't set speed to $speed.")
                } else {
                    println("Invalid input for speed.")
                }
            }
            else -> println("Unknown command")
        }
    }
}