package day6

import readInput

fun main() {

    fun MutableList<Long>.elapseTime(days: Int): Long = when (days) {
        0 -> sum().toLong()
        else -> {
            val fishesAtZero = first()
            drop(1).toMutableList()
                .also { it[6] = it[6] + fishesAtZero; it.add(fishesAtZero) }
                .elapseTime(days - 1)
        }
    }

    fun String.toAges() = split(',').map { it.toInt() }
        .let { ages -> MutableList(9) { index -> ages.count { it == index }.toLong() } }
        .toMutableList()

    check(readInput(6, "-test").first().toAges().elapseTime(18) == 26L)
    check(readInput(6, "-test").first().toAges().elapseTime(80) == 5934L)
    check(readInput(6).first().toAges().elapseTime(80) == 391671L)
    check(readInput(6, "-test").first().toAges().elapseTime(256) == 26984457539L)
    check(readInput(6).first().toAges().elapseTime(256) == 1754000560399L)
}