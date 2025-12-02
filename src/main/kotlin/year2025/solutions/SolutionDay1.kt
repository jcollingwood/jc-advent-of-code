package year2025.solutions

import Solution
import kotlin.math.abs

class SolutionDay1: Solution {
    enum class Direction { L, R }
    data class DialInstruction(val direction: Direction, val distance: Int)
    data class InstructionResult(val newCurrentNumber: Int, val passedZeroCount: Int)

    override fun part1(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        val instructions = inputLines.map { parseDirections(it) }

        var leftOnZeroCount = 0
        var currentNumber = 50

        instructions.forEach { i ->
            currentNumber = if(i.direction == Direction.R) (currentNumber + i.distance) % 100
                            else (currentNumber - i.distance + 100) % 100

            if(currentNumber == 0) leftOnZeroCount++
        }

        return leftOnZeroCount
    }

    override fun part2(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        val instructions = inputLines.map { parseDirections(it) }

        var leftPassedZeroCount = 0
        var currentNumber = 50

        instructions.forEach { i ->
            val result = calculateInstructionResult(currentNumber, i)
            currentNumber = result.newCurrentNumber
            leftPassedZeroCount += result.passedZeroCount
        }

        return leftPassedZeroCount
    }

    private fun parseDirections(line: String): DialInstruction {
        // comes as L50, R45, ...
        val direction = if (line[0] == 'L') Direction.L else Direction.R
        val distance = line.substring(1).toInt()
        return DialInstruction(direction, distance)
    }

    fun calculateInstructionResult(currentNumber: Int, instruction: DialInstruction): InstructionResult {
        print("start: $currentNumber moving ${if(instruction.direction == Direction.R) "right" else "left"} ${instruction.distance}, end: ")
        var passedZeroCount = 0

        var newCurrentNumber = if(instruction.direction == Direction.R) currentNumber + instruction.distance
                                else currentNumber - instruction.distance

        // if lands on zero directly count it and escape early
        if(newCurrentNumber == 0) {
            passedZeroCount++
            println("0, +$passedZeroCount")
            return InstructionResult(newCurrentNumber, passedZeroCount)
        }

        // get value back in range 0 and 99 and count number of times adjusted
        while(newCurrentNumber < 0 || newCurrentNumber > 99) {
            if(newCurrentNumber > 99) {
                passedZeroCount++
                newCurrentNumber -= 100
            } else {
                if(abs(newCurrentNumber) != instruction.distance) passedZeroCount++
                newCurrentNumber += 100
                if(newCurrentNumber == 0) passedZeroCount++

            }
        }
        println("$newCurrentNumber, +$passedZeroCount")

        return InstructionResult(newCurrentNumber, passedZeroCount)
    }
}
