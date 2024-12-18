package year2024.solutions

class SolutionDay3 : Solution {
    val mulRegex = "(mul\\([0-9]{1,3},[0-9]{1,3}\\))".toRegex()
    val mulPartsRegex = "mul\\(([0-9]{1,3}),([0-9]{1,3})\\)".toRegex()

    data class Mul(val left: Int, val right: Int)

    override fun part1(rscPath: String): Int {
        val inputLines = getResourceAsLine(rscPath)
        return getMul(inputLines).map { it.left * it.right }.sum()
    }

    override fun part2(rscPath: String): Int {
        val inputLines = getResourceAsLine(rscPath)
        val trimmedDo = trimDo(inputLines)
        return getMul(trimmedDo).map { it.left * it.right }.sum()
    }

    private fun getMul(line: String): List<Mul> {
        val matches = mulRegex.findAll(line)
        return matches.map { m ->
            val (left, right) = mulPartsRegex.find(m.value)!!.destructured
            Mul(left.toInt(), right.toInt())
        }.toList()
    }

    private fun trimDo(line: String): String {
        var startIndex = 0
        var endIndex = 0
        var trimmedString = ""
        while (endIndex != -1 && startIndex != -1) {
            // find next end index
            endIndex = line.indexOf("don't()", startIndex)
            // append valid part
            trimmedString += if (endIndex == -1) {
                line.substring(startIndex)
            } else {
                line.substring(startIndex, endIndex)
            }
            // find next start index
            if (endIndex != -1)
                startIndex = line.indexOf("do()", endIndex)
        }
        return trimmedString
    }
}