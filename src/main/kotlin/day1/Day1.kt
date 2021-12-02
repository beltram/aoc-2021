package day1

import readInput

fun main() {
    fun List<String>.part1() = map { it.toInt() }.windowed(2).count { (a, b) -> a < b }

    fun List<String>.part2() = map { it.toInt() }.windowed(3).map { (a, b, c) -> a + b + c }.windowed(2).count { (a, b) -> a < b }

    check(readInput(1, "-part1-test").part1() == 7)
    println(readInput(1).part1())
    check(readInput(1, "-part2-test").part2() == 5)
    println(readInput(1).part2())
}
