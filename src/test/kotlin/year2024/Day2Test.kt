package year2024

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import year2024.solutions.SolutionDay2


class Day2Test {
    companion object {
        @JvmStatic
        fun testCases() = listOf(
            SolutionTestCase(2024, 2, 1, true, 2),
            SolutionTestCase(2024, 2, 1, false, 486),
            SolutionTestCase(2024, 2, 2, true, 4),
            // 521 is too low, didn't get real solution yet
            SolutionTestCase(2024, 2, 2, false, 521)
        )
    }

    val solution = SolutionDay2()

    @ParameterizedTest
    @MethodSource("testCases")
    fun `day 2 tests`(source: SolutionTestCase) {
        testSolutionTestCase(solution, source)
    }
}