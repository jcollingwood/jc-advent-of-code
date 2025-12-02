package year2025

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import year2024.SolutionTestCase
import year2024.solutions.SolutionDay1
import year2024.testSolutionTestCase


class Day1Test {
    data class EdgeCase(
        val curr: Int,
        val inst: year2025.solutions.SolutionDay1.DialInstruction,
        val expected: year2025.solutions.SolutionDay1.InstructionResult
    )

    companion object {
        @JvmStatic
        fun testCases() = listOf(
            SolutionTestCase(2025, 1, 1, true, 3),
            SolutionTestCase(2025, 1, 1, false, 1139),
            SolutionTestCase(2025, 1, 2, true, 6),
            SolutionTestCase(2025, 1, 2, false, 6684)
        )

        @JvmStatic
        fun edgeCases() = listOf(
            EdgeCase(
                curr = 50,
                inst = year2025.solutions.SolutionDay1.DialInstruction(year2025.solutions.SolutionDay1.Direction.R, 50),
                expected = year2025.solutions.SolutionDay1.InstructionResult(0, 1)
            ),
            EdgeCase(
                curr = 50,
                inst = year2025.solutions.SolutionDay1.DialInstruction(year2025.solutions.SolutionDay1.Direction.L, 50),
                expected = year2025.solutions.SolutionDay1.InstructionResult(0, 1)
            ),
            EdgeCase(
                curr = 0,
                inst = year2025.solutions.SolutionDay1.DialInstruction(year2025.solutions.SolutionDay1.Direction.L, 100),
                expected = year2025.solutions.SolutionDay1.InstructionResult(0, 1)
            ),
            EdgeCase(
                curr = 0,
                inst = year2025.solutions.SolutionDay1.DialInstruction(year2025.solutions.SolutionDay1.Direction.L, 1),
                expected = year2025.solutions.SolutionDay1.InstructionResult(99, 0)
            ),
            EdgeCase(
                curr = 50,
                inst = year2025.solutions.SolutionDay1.DialInstruction(year2025.solutions.SolutionDay1.Direction.L, 650),
                expected = year2025.solutions.SolutionDay1.InstructionResult(0, 7)
            ),
        )
    }

    val solution = year2025.solutions.SolutionDay1()

    @ParameterizedTest
    @MethodSource("testCases")
    fun `day 1 tests`(source: SolutionTestCase) {
        testSolutionTestCase(solution, source)
    }

    @ParameterizedTest
    @MethodSource("edgeCases")
    fun `2025 day 1 edge cases test`(source: EdgeCase) {
        val result = solution.calculateInstructionResult(source.curr, source.inst)
        assert(result == source.expected) {
            "For current ${source.curr} and instruction ${source.inst}, expected ${source.expected} but got $result"
        }

    }
}