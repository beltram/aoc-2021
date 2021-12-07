package day7

import readInput
import kotlin.math.absoluteValue

fun main() {

    fun Int.cumulativeSum() = (0..this).sum()
    fun Int.delta(i: Int) = (this - i).absoluteValue

    fun List<Int>.part1() = (0 until maxOf { it }).minOf { i -> sumOf { c -> c.delta(i) } }
    fun List<Int>.part2() = (0 until maxOf { it }).minOf { i -> sumOf { c -> c.delta(i).cumulativeSum() } }

    fun String.positions() = split(',').map { it.toInt() }

    check(readInput(7, "-test").first().positions().part1() == 37)
    println(readInput(7).first().positions().part1())
    check(readInput(7, "-test").first().positions().part2() == 168)
    println(readInput(7).first().positions().part2())
}