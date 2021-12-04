package day4

import readInput

fun main() {
    fun List<String>.part1() = Game.from(this).solve()
    fun List<String>.part2() = Game.from(this).solve(first = false)

    check(readInput(4, "-test").part1() == 4512)
    println(readInput(4).part1())
    check(readInput(4, "-test").part2() == 1924)
    println(readInput(4).part2())
}

internal data class Game(val draw: List<Int>, val grids: List<Grid>) {
    companion object {
        fun from(raw: List<String>): Game {
            val draw = raw[0].split(',').map { it.toInt() }
            val grids = raw.drop(2).filter { it.isNotBlank() }.windowed(5, 5).map { Grid.from(it) }
            return Game(draw, grids)
        }
    }

    fun solve(idx: Int = 0, first: Boolean = true) = if (first) solveFirst(idx) else grids.solveLast(idx)

    private fun solveFirst(idx: Int): Int {
        val current = draw[idx]
        grids.forEach { it.applyPoint(current) }
        return grids.firstOrNull { it.isSolved() }
            ?.let { score(current, it) }
            ?: solveFirst(idx + 1)
    }

    private fun List<Grid>.solveLast(idx: Int): Int {
        val current = draw[idx]
        forEach { it.applyPoint(current) }
        val unsolved = filterNot { it.isSolved() }
        return if (unsolved.isEmpty()) score(current, first())
        else unsolved.solveLast(idx + 1)
    }

    private fun score(last: Int, grid: Grid) = last * grid.unmarkedSum()
}

internal data class Grid(val rows: List<List<Point>>) {

    companion object {
        fun from(raw: List<String>): Grid {
            return Grid(raw
                .filter { it.isNotBlank() }
                .map { it.split(' ').filter { it.isNotBlank() }.map { Point(it.toInt()) } }
            )
        }
    }

    fun applyPoint(value: Int) {
        rows.forEach { r ->
            r.forEach { p -> p.applyValue(value) }
        }
    }

    fun isSolved() = isSolvedHorizontal() || isSolvedVertical()
    private fun isSolvedHorizontal() = rows.any { it.all { it.marked } }
    private fun isSolvedVertical() = (rows.indices).any { rows.all { r -> r[it].marked } }
    fun unmarkedSum() = rows.flatten().filterNot { it.marked }.sumOf { it.value }
}

internal data class Point(val value: Int, var marked: Boolean = false) {
    fun applyValue(v: Int) {
        if (v == value) marked = true
    }
}