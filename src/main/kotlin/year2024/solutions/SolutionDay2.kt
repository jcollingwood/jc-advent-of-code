package year2024.solutions

import Solution
import kotlin.math.absoluteValue

class SolutionDay2 : Solution {
    override fun part1(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        var safeCount = 0

        inputLines.forEach { line ->
            val report = parseReport(line)
            if (isSafe(report)) safeCount++
        }

        return safeCount
    }

    override fun part2(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        var safeCount = 0

        inputLines.forEach { line ->
            val report = parseReport(line)
            if (isSafe(report, true)) safeCount++
        }

        return safeCount
    }

    private fun isSafe(report: List<Int>, useDampener: Boolean = false): Boolean {
        var dampenerUsed = false
        val isAscending = report[0] < report[1]
        for (i in 0 until report.size - 1) {
            val curr = report[i]
            val next = report[i + 1]
            if (!isCurrValidWithNext(isAscending, curr, next)) {
                if (!useDampener || dampenerUsed) return false
                // if next is last element and dampener not used yet, we can just remove last and be safe
                if (i + 2 == report.size) return true
                // if next isn't valid, check if it would be valid against the following level
                else if (i + 2 < report.size && !isCurrValidWithNext(isAscending, curr, report[i + 2]))
                // if first level, skip it
                    if (i != 0) return false
                // to confirm dampener not used more than once
                dampenerUsed = true
            }
        }
        return true
    }

    private fun isCurrValidWithNext(isAscending: Boolean, curr: Int, next: Int): Boolean {
        // ensure either ascending or descending order
        if (isAscending && curr > next) {
            return false
        } else if (!isAscending && curr < next) {
            return false
        }

        // ensure change is 1-3
        val diff = (next - curr).absoluteValue
        return !(diff < 1 || diff > 3)
    }

    private fun parseReport(inputLines: String): List<Int> {
        return inputLines.split(" ").map { it.toInt() }
    }
}