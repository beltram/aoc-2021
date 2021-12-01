package day1

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        var count = 0
        input.map { it.toInt() }.reduce { a, b -> if (a < b) count++; b }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        val nums = input.map { it.toInt() }
        val sums = nums.mapIndexed { idx, i -> i + nums.getOrElse(idx + 1) { 0 } + nums.getOrElse(idx + 2) { 0 } }
        sums.reduce { a, b -> if (a < b) count++; b }
        return count
    }

    check(part1(readInput(1, "-part1-test")) == 7)
    println(part1(readInput(1)))
    check(part2(readInput(1, "-part2-test")) == 5)
    println(part2(readInput(1)))
}
