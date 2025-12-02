package year2024.solutions

import Solution

class SolutionDay5 : Solution {
    data class OrderRule(val before: Int, val after: Int)
    data class ParsedInput(val rules: List<OrderRule>, val updates: List<List<Int>>)

    override fun part1(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        val parsed = parseInput(inputLines)
        return parsed.updates.map { u ->
            if (isUpdateValid(u, parsed.rules)) u[u.size / 2]
            else 0
        }.sum()
    }

    override fun part2(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        val parsed = parseInput(inputLines)
        return parsed.updates.map { u ->
            if (isUpdateValid(u, parsed.rules)) 0
            else makeUpdateValid(u, parsed.rules)[u.size / 2]
        }.sum()
    }

    private fun makeUpdateValid(update: List<Int>, rules: List<OrderRule>): List<Int> {
        val updateMap = HashMap(update.mapIndexed { i, u -> u to i }.toMap())
        rules.forEach { r ->
            val before = updateMap[r.before]
            val after = updateMap[r.after]
            if (!(before == null || after == null) && before >= after) {
                updateMap[r.after] = before + 1
            }
       }

        return updateMap.toSortedMap().map { it.key }.toList()
    }

    private fun isUpdateValid(update: List<Int>, rules: List<OrderRule>): Boolean {
        val updateMap = update.mapIndexed { i, u -> u to i }.toMap()
        return rules.all {
            (updateMap[it.before] ?: -1) < (updateMap[it.after] ?: update.size)
        }
    }

    private fun parseInput(inputLines: List<String>): ParsedInput {
        var switch = false
        val lines = inputLines.iterator()
        val rules: MutableList<OrderRule> = mutableListOf()
        val updates: MutableList<List<Int>> = mutableListOf()
        while (lines.hasNext()) {
            val l = lines.next()
            if (l.isEmpty()) switch = true
            else if (!switch) {
                val split = l.split('|')
                rules.add(OrderRule(split[0].toInt(), split[1].toInt()))
            } else {
                updates.add(l.split(',').map { it.toInt() })
            }
        }
        return ParsedInput(rules, updates)
    }

}