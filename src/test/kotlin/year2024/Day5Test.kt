package year2024

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import year2024.solutions.SolutionDay5


class Day5Test {
    companion object {
        @JvmStatic
        fun testCases() = listOf(
            SolutionTestCase(2024, 5, 1, true, 143),
            SolutionTestCase(2024, 5, 1, false, 5248),
            // never got around to finishing day 5 part 2
//            SolutionTestCase(2024, 5, 2, true, 123),
//            SolutionTestCase(2024, 5, 2, false, 0)
        )
    }

    val solution = SolutionDay5()

    @ParameterizedTest
    @MethodSource("testCases")
    fun `day 5 tests`(source: SolutionTestCase) {
        testSolutionTestCase(solution, source)
    }
}