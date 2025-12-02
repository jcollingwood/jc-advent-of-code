package year2025.solutions

import Solution
import java.math.BigInteger

class SolutionDay2: Solution {
    data class IdRange(val start: Long, val end: Long)

    override fun part1(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        val idRanges = parseIdRanges(inputLines)
        var sumInvalidIds = 0L
        idRanges.forEach { r ->
            for(id in r.start..r.end) {
                if(isInvalidIdPart1(id.toString())) {
                    sumInvalidIds += id
                }
            }
        }
        println("sumInvalidIds: $sumInvalidIds")
        return sumInvalidIds.toInt()
    }

    override fun part2(rscPath: String): Int {
        val inputLines = getResource(rscPath)
        val idRanges = parseIdRanges(inputLines)
        var sumInvalidIds = 0L
        idRanges.forEach { r ->
            for(id in r.start..r.end) {
                if(isInvalidIdPart2(id.toString())) {
                    sumInvalidIds += id
                }
            }
        }
        println("sumInvalidIds: $sumInvalidIds")
        return sumInvalidIds.toInt()

    }

    private fun parseIdRanges(lines: List<String>): List<IdRange> {
        return lines.joinToString("")
            .split(",")
            .map {
                val parts = it.split("-")
                IdRange(parts[0].toLong(), parts[1].toLong())
            }
    }

    private fun isInvalidIdPart1(id: String): Boolean {
        // leading zero
        if(id.startsWith('0')) return true
        // odd length
        if(id.length % 2 != 0) return false
        // first half equals second half
        val halfLength = id.length / 2
        return id.substring(0, halfLength) == id.substring(halfLength)
    }

    private fun isInvalidIdPart2(id: String): Boolean {
        // leading zero
        if(id.startsWith('0')) return true
        // check all subsequences by length, any repeated subsequence means invalid
        for(subSeqLength in 1..(id.length / 2)) {
            // ignore if not divisible
            if(id.length % subSeqLength != 0) continue

            // save first subsequence
            val currSubSeq = id.substring(0, subSeqLength)
            // check remaining subsequences and continue on first non-match
            var allMatch = true
            var index = subSeqLength
            while(index < id.length) {
                val nextSubSeq = id.substring(index, index + subSeqLength)
                if (nextSubSeq != currSubSeq) {
                    allMatch = false
                    break
                }
                index += subSeqLength
            }
            if(!allMatch) continue
            // if we made it here, all subsequences matched
            println("Invalid id found: $id with subsequence length $subSeqLength")
            return true
        }
        return false
    }
}
