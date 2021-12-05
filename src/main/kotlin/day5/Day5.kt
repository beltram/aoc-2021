package day5

import readInput
import kotlin.math.absoluteValue

fun main() {
    fun List<String>.compute(condition: (Line) -> Boolean): Int {
        return map { Line.from(it) }.filter(condition).flatMap { it.pointsCovered() }
            .groupBy { it }.values.map { it.count() }.count { it > 1 }
    }

    fun List<String>.part1() = compute { it.isOrthogonal() }
    fun List<String>.part2() = compute { it.isOrthonormal() }

    check(readInput(5, "-test").part1() == 5)
    println(readInput(5).part1())
    check(readInput(5, "-test").part2() == 12)
    println(readInput(5).part2())
}

internal data class Line(val begin: Point, val end: Point) {
    companion object {
        fun from(raw: String) = raw.split("->").map { Point.from(it) }.let { (b, e) -> Line(b, e) }
    }

    private fun isHorizontal() = begin.y == end.y
    private fun isVertical() = begin.x == end.x
    fun isOrthogonal() = isHorizontal() || isVertical()
    private fun isDiagonal() = (begin.x - end.x).absoluteValue == (begin.y - end.y).absoluteValue
    fun isOrthonormal() = isOrthogonal() || isDiagonal()

    fun pointsCovered() = when {
        isHorizontal() -> range(begin.x, end.x).map { Point(it, begin.y) }
        isVertical() -> range(begin.y, end.y).map { Point(begin.x, it) }
        isDiagonal() -> (range(begin.x, end.x).zip(range(begin.y, end.y))).map { (x, y) -> Point(x, y) }
        else -> throw IllegalStateException("oops")
    }

    private fun range(a: Int, b: Int) = if (a < b) (a..b) else (a downTo b)
}

internal data class Point(val x: Int, val y: Int) {
    companion object {
        fun from(raw: String) = raw.trim().split(',').map { it.toInt() }.let { (x, y) -> Point(x, y) }
    }
}

