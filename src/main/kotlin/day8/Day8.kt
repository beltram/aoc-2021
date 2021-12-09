package day8

import day8.Digit.Companion.intoDigit
import readInput

fun main() {

    fun List<String>.part1(): Int {
        return flatMap { Line.from(it).digitsOut }.count()
    }

    check(readInput(8, "-test").part1() == 26)
    println(readInput(8).part1())
}

internal data class Line(val digitsIn: List<Digit>, val digitsOut: List<Digit>) {

    companion object {
        fun from(raw: String): Line {
            return raw.split('|')
                .map { it.split(' ').mapNotNull { it.intoDigit() } }
                .let { (input, output) -> Line(input, output) }
        }
    }

    override fun toString(): String {
        return "[$digitsIn | $digitsOut]"
    }
}

internal data class Digit(val value: Int) {

    companion object {
        fun String.intoDigit(): Digit? = when (toCharArray().size) {
            2 -> 1
            3 -> 7
            4 -> 4
            7 -> 8
            else -> null
        }?.let { Digit((it)) }
    }

    override fun toString() = "$value"


}