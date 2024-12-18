package year2024

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import year2024.solutions.SolutionDay3


class Day3Test {
    companion object {
        @JvmStatic
        fun testCases() = listOf(
            SolutionTestCase(2024, 3, 1, true, 161),
            SolutionTestCase(2024, 3, 1, false, 189527826),
            SolutionTestCase(2024, 3, 2, true, 48),
            SolutionTestCase(2024, 3, 2, false, 63013756)
        )
    }

    val solution = SolutionDay3()

    @ParameterizedTest
    @MethodSource("testCases")
    fun `day 3 tests`(source: SolutionTestCase) {
        testSolutionTestCase(solution, source)
    }
}