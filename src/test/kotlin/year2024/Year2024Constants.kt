package year2024

import org.junit.jupiter.api.Assertions.assertEquals

data class SolutionTestCase(
    val year: Int,
    val day: Int,
    val part: Int,
    val isSample: Boolean,
    val expected: Int
)

fun getRscPath(testCase: SolutionTestCase): String =
    getRscPath(testCase.year, testCase.day, testCase.part, testCase.isSample)

fun getRscPath(year: Int, day: Int, part: Int, isSample: Boolean): String =
    "/year${year}/day${day}/part${part}${if (isSample) "_sample" else ""}.txt" // "/${RSC_DAY_1}/part1_sample.txt"

fun testSolutionTestCase(solution: Solution, case: SolutionTestCase) {
    val actual = if (case.part == 1) {
        solution.part1(getRscPath(case))
    } else {
        solution.part2(getRscPath(case))
    }
    assertEquals(case.expected, actual)
}