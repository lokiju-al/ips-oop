package lab_2.primeNumbers_2_4_4

import kotlin.math.sqrt

fun generatePrimeNumbersSet(upperBound: Int): Set<Int> {
    if (upperBound < 2) return emptySet()

    val isPrime = BooleanArray(upperBound + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    for (i in 2..sqrt(upperBound.toDouble()).toInt()) {
        if (isPrime[i]) {
            for (j in i * i..upperBound step i) {
                isPrime[j] = false
            }
        }
    }

    val primeNumbers = mutableSetOf<Int>()
    for (i in 2..upperBound) {
        if (isPrime[i]) {
            primeNumbers.add(i)
        }
    }

    return primeNumbers
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Ошибка: Укажите значение верхней границы множества как параметр командной строки")
        return
    }

    val upperBound = args[0].toIntOrNull()

    when {
        upperBound == null -> {
            println("Ошибка: Значение верхней границы множества должно быть целым числом")
            return
        }

        upperBound < 0 || upperBound > 100_000_000 -> {
            println("Ошибка: Максимальное значение верхней границы множества должно быть от 0 до 100_000_000")
            return
        }
    }

    val primes = generatePrimeNumbersSet(upperBound!!)

    println("Элементы множества простых чисел от 0 до $upperBound:")
    println(primes.joinToString(", "))
}