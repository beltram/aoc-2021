package day1

import readInput

fun main() {
    fun part1(input: List<String>) = input.map { it.toInt() }.windowed(2).count { (a, b) -> a < b }

    fun part2(input: List<String>) = input.map { it.toInt() }.windowed(3).map { (a, b, c) -> a + b + c }.windowed(2).count { (a, b) -> a < b }

    check(part1(readInput(1, "-part1-test")) == 7)
    println(part1(readInput(1)))
    check(part2(readInput(1, "-part2-test")) == 5)
    println(part2(readInput(1)))
}
