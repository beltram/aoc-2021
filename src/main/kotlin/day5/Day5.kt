package day5

import readInput

fun main() {
    fun List<String>.part1(): Int {
        return map { Line.from(it) }
            .filter { it.isOrthogonal() }
            .flatMap { it.pointsCovered() }
            .groupBy { it }
            .values
            .map { it.count() }
            .count { it > 1 }
    }

    check(readInput(5, "-test").part1() == 5)
    println(readInput(5).part1())
}

internal data class Line(val begin: Point, val end: Point) {
    companion object {
        fun from(raw: String) = raw.split("->").map { Point.from(it) }.let { (b, e) -> Line(b, e) }
    }

    private fun isHorizontal() = begin.y == end.y
    private fun isVertical() = begin.x == end.x
    fun isOrthogonal() = isHorizontal() || isVertical()

    fun pointsCovered() = when {
        isHorizontal() -> range(begin.x, end.x).map { Point(it, begin.y) }
        else -> range(begin.y, end.y).map { Point(begin.x, it) }
    }

    private fun range(a: Int, b: Int) = if (a < b) (a..b) else (b..a)
}

internal data class Point(val x: Int, val y: Int) {
    companion object {
        fun from(raw: String) = raw.trim().split(',').map { it.toInt() }.let { (x, y) -> Point(x, y) }
    }
}

