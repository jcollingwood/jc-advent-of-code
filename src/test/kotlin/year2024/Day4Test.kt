package year2024

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import year2024.solutions.SolutionDay4


class Day4Test {
    companion object {
        @JvmStatic
        fun testCases() = listOf(
            SolutionTestCase(2024, 4, 1, true, 18),
            SolutionTestCase(2024, 4, 1, false, 2545),
            SolutionTestCase(2024, 4, 2, true, 9),
            SolutionTestCase(2024, 4, 2, false, 1886)
        )
    }

    val solution = SolutionDay4()

    @ParameterizedTest
    @MethodSource("testCases")
    fun `day 4 tests`(source: SolutionTestCase) {
        testSolutionTestCase(solution, source)
    }
}