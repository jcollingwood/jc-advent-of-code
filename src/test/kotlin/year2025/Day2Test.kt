package year2025

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import year2024.SolutionTestCase
import year2024.solutions.SolutionDay1
import year2024.testSolutionTestCase


class Day2Test {
    companion object {
        @JvmStatic
        fun testCases() = listOf(
            SolutionTestCase(2025, 2, 1, true, 1227775554),
            // long to int output problematic with how I set up the solution interface
            SolutionTestCase(2025, 2, 1, false, 31839939622L.toInt()),
            SolutionTestCase(2025, 2, 2, true, 4174379265L.toInt()),
            SolutionTestCase(2025, 2, 2, false, 41662374059L.toInt())
        )
    }

    val solution = year2025.solutions.SolutionDay2()

    @ParameterizedTest
    @MethodSource("testCases")
    fun `day 2 tests`(source: SolutionTestCase) {
        testSolutionTestCase(solution, source)
    }
}