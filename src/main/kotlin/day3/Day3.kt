package day3

import readInput
import kotlin.math.absoluteValue

fun main() {
    fun List<String>.part1(len: Int): Long {
        return map { it.toCharArray().map { c -> c.digitToInt() }.toIntArray() }
            .fold(IntArray(len)) { acc, it -> acc.merge(it) }
            .map { it.cmp(size / 2) }
            .joinToString("")
            .computeResult()
    }

    fun List<List<Int>>.oxygen(len: Int, idx: Int = 0): List<Int> = when {
        size > 1 -> when {
            count { it[idx] == 1 }.toFloat() >= middle() -> filter { it[idx] == 1 }
            else -> filter { it[idx] == 0 }
        }.oxygen(len, idx + 1)
        else -> flatten()
    }

    fun List<List<Int>>.co2(len: Int, idx: Int = 0): List<Int> = when {
        size > 1 -> {
            when {
                count { it[idx] == 0 }.toFloat() <= middle() -> filter { it[idx] == 0 }
                else -> filter { it[idx] == 1 }
            }.co2(len, idx + 1)
        }
        else -> first()
    }

    fun List<String>.part2(len: Int): Long {
        return sorted()
            .map { it.toCharArray().map { c -> c.digitToInt(2) } }
            .run { oxygen(len).toLong() * co2(len).toLong() }
    }

    check(readInput(3, "-part1-test").part1(5) == 198L)
    check(readInput(3).part1(12) == 2972336L)
    check(readInput(3, "-part1-test").part2(5) == 230L)
    check(readInput(3).part2(12) == 3368358L)
}

private fun String.computeResult(): Long = let { gamma -> gamma.toLong(2) * gamma.inv().toLong(2) }
private fun IntArray.merge(other: IntArray) = zip(other) { a, b -> a + b }.toIntArray()
private fun Int.cmp(other: Int) = if (this >= other) 1 else 0
private fun String.inv() = toCharArray().map { it.digitToInt(2) }.map { (it - 1).absoluteValue }.joinToString("")
private fun List<*>.middle() = (size.div(2.0)).toFloat()
private fun List<Int>.toLong() = joinToString("").toLong(2)