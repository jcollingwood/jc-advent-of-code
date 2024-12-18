package year2024

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import year2024.solutions.SolutionDay1


class Day1Test {
    companion object {
        @JvmStatic
        fun testCases() = listOf(
            SolutionTestCase(2024, 1, 1, true, 11),
            SolutionTestCase(2024, 1, 1, false, 2769675),
            SolutionTestCase(2024, 1, 2, true, 31),
            SolutionTestCase(2024, 1, 2, false, 24643097)
        )
    }

    val solution = SolutionDay1()

    @ParameterizedTest
    @MethodSource("testCases")
    fun `day 1 tests`(source: SolutionTestCase) {
        testSolutionTestCase(solution, source)
    }
}