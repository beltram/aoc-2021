package day3

import readInput
import kotlin.math.absoluteValue

fun main() {
    fun List<String>.part1(len: Int): Long {
        val middle = size / 2
        val gamma = map { it.toCharArray().map { c -> c.digitToInt() }.toIntArray() }
            .fold(IntArray(len)) { acc, it -> acc.merge(it) }
            .map { it.cmp(middle) }
            .joinToString("")
        val epsilon = gamma.inv()
        return gamma.toLong(2) * epsilon.toLong(2)
    }

    check(readInput(3, "-part1-test").part1(5) == 198L)
    println(readInput(3).part1(12))
//    check(readInput(3, "-part1-test").part2() == 900)
//    println(readInput(3).part2())
}

private fun IntArray.merge(other: IntArray) = zip(other) { a, b -> a + b }.toIntArray()
private fun Int.cmp(other: Int) = if (this > other) 1 else 0
private fun String.inv() = toCharArray().map { it.digitToInt(2) }.map { (it - 1).absoluteValue }.joinToString("")