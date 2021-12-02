package day2

import readInput

fun main() {
    fun List<String>.part1() = map { Move.from(it) }.fold(InstructionsResult()) { a, m -> a.applyMove1(m) }.compute()
    fun List<String>.part2() = map { Move.from(it) }.fold(InstructionsResult()) { a, m -> a.applyMove2(m) }.compute()

    check(readInput(2, "-part1-test").part1() == 150)
    println(readInput(2).part1())
    check(readInput(2, "-part1-test").part2() == 900)
    println(readInput(2).part2())
}

private data class InstructionsResult(val horizontal: Int = 0, val depth: Int = 0, val aim: Int = 0) {
    fun compute() = horizontal * depth

    fun applyMove1(move: Move) = when (move.direction) {
        Direction.FORWARD -> copy(horizontal = horizontal + move.offset)
        Direction.UP -> copy(depth = depth - move.offset)
        Direction.DOWN -> copy(depth = depth + move.offset)
    }

    fun applyMove2(move: Move) = when (move.direction) {
        Direction.FORWARD -> copy(horizontal = horizontal + move.offset, depth = depth + (aim * move.offset))
        Direction.UP -> copy(aim = aim - move.offset)
        Direction.DOWN -> copy(aim = aim + move.offset)
    }
}

private data class Move(val direction: Direction, val offset: Int) {
    companion object {
        fun from(value: String) = value.split(' ').let { (d, o) -> Move(Direction.valueOf(d.uppercase()), o.toInt()) }
    }
}

internal enum class Direction {
    UP, DOWN, FORWARD;
}