package year2024.solutions

import kotlin.math.absoluteValue

class SolutionDay1 : Solution {
    override fun part1(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        val (left, right) = getLeftAndRightLists(inputLines)

        left.sort()
        right.sort()

        var sum = 0
        for (i in left.indices) {
            val diff = (left[i] - right[i]).absoluteValue
            sum += diff
        }

        return sum
    }

    override fun part2(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        val (left, right) = getLeftAndRightLists(inputLines)

        val rightListCount = mutableMapOf<Int, Int>()
        right.forEach {
            rightListCount[it] = (rightListCount[it] ?: 0) + 1
        }

        val similarityScores = mutableMapOf<Int, Int>()
        left.forEach {
            val simScore = it * (rightListCount[it] ?: 0)
            similarityScores[it] = (similarityScores[it] ?: 0) + simScore
        }

        return similarityScores.values.sum()
    }

    fun getLeftAndRightLists(inputLines: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        inputLines.forEach { line ->
            val (a, b) = line.split("   ")
            left.add(a.toInt())
            right.add(b.toInt())
        }
        return Pair(left, right)
    }
}