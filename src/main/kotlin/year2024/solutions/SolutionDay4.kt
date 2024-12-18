package year2024.solutions

class SolutionDay4 : Solution {
    companion object {
        val XMAS_LETTERS = listOf('X', 'M', 'A', 'S')
    }

    // direction enum with util to find next coordinate in that direction
    enum class Dir {
        U, D, L, R, UL, UR, DL, DR;

        fun nextCoord(c: Pair<Int, Int>): Pair<Int, Int> {
            return when (this) {
                U -> Pair(c.first + 1, c.second)
                D -> Pair(c.first - 1, c.second)
                L -> Pair(c.first, c.second - 1)
                R -> Pair(c.first, c.second + 1)
                UL -> Pair(c.first + 1, c.second - 1)
                UR -> Pair(c.first + 1, c.second + 1)
                DL -> Pair(c.first - 1, c.second - 1)
                DR -> Pair(c.first - 1, c.second + 1)
            }
        }
    }

    override fun part1(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        return findXmases(inputLines)
    }

    override fun part2(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        return findMasInXes(inputLines)
    }

    private fun findXmases(inputLines: List<String>): Int {
        var numXmases = 0
        for (x in 0 until inputLines[0].length) {
            for (y in 0 until inputLines.size) {
                // loop over every direction
                numXmases += Dir.entries.map { checkLetters(XMAS_LETTERS, inputLines, Pair(x, y), it) }.sum()
            }
        }
        return numXmases
    }

    private fun checkLetters(letters: List<Char>, inputLines: List<String>, c: Pair<Int, Int>, dir: Dir): Int {
        // return 0 if params outside of grid
        if (c.first < 0 || c.first >= inputLines[0].length || c.second < 0 || c.second >= inputLines.size) return 0
        else if (letters.size == 1 && inputLines[c.second][c.first] == letters[0]) {
            return 1
        } else if (inputLines[c.second][c.first] != letters[0])
            return 0
        else {
            val nextLetters = letters.subList(1, letters.size)
            // check all characters in provided direction
            return checkLetters(nextLetters, inputLines, dir.nextCoord(c), dir)
        }
    }

    private fun findMasInXes(inputLines: List<String>): Int {
        var numMasInX = 0
        for (x in 0 until inputLines[0].length) {
            for (y in 0 until inputLines.size) {
                if (isExpectedLetter('A', inputLines, Pair(x, y)) && isMasOrSameX(inputLines, Pair(x, y))) {
                    numMasInX++
                }
            }
        }
        return numMasInX
    }

    private fun isMasOrSameX(inputLines: List<String>, c: Pair<Int, Int>): Boolean {
        val isMasTopDown = isExpectedLetter('M', inputLines, Pair(c.first - 1, c.second + 1)) &&
                isExpectedLetter('S', inputLines, Pair(c.first + 1, c.second - 1))
        val isSamTopDown = isExpectedLetter('S', inputLines, Pair(c.first - 1, c.second + 1)) &&
                isExpectedLetter('M', inputLines, Pair(c.first + 1, c.second - 1))
        val isMasBottomUp = isExpectedLetter('M', inputLines, Pair(c.first - 1, c.second - 1)) &&
                isExpectedLetter('S', inputLines, Pair(c.first + 1, c.second + 1))
        val isSamBottomUp = isExpectedLetter('S', inputLines, Pair(c.first - 1, c.second - 1)) &&
                isExpectedLetter('M', inputLines, Pair(c.first + 1, c.second + 1))
        return (isMasTopDown || isSamTopDown) && (isMasBottomUp || isSamBottomUp)
    }

    private fun isExpectedLetter(letter: Char, inputLines: List<String>, c: Pair<Int, Int>): Boolean {
        if (c.first < 0 || c.first >= inputLines[0].length || c.second < 0 || c.second >= inputLines.size) return false
        return inputLines[c.second][c.first] == letter
    }

}