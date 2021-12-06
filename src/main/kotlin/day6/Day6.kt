package day6

import readInput

fun main() {

    fun List<Fish>.elapseTime(days: Int): Int {
        return if (days == 0) {
            count()
        } else {
            flatMap { it.elapse() }.elapseTime(days - 1)
        }
    }

    fun String.part1(days: Int): Int {
        return split(',').map { Fish.from(it) }.elapseTime(days)
    }

    check(readInput(6, "-test").first().part1(18) == 26)
    check(readInput(6, "-test").first().part1(80) == 5934)
    println(readInput(6).first().part1(80))
}

internal data class Fish(val ttl: Int) {
    companion object {
        fun from(raw: String) = Fish(raw.toInt())
        fun reset() = Fish(6)
        fun newBaby() = Fish(8)
    }

    fun elapse(): List<Fish> = when (ttl) {
        0 -> listOf(reset(), newBaby())
        else -> listOf(Fish(ttl - 1))
    }
}