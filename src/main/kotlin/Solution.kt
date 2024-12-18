interface Solution {
    fun part1(rscPath: String): Int
    fun part2(rscPath: String): Int

    fun getResource(path: String): List<String> {
        return getResourceByLine(path)
    }

    fun getResourceByLine(path: String): List<String> =
        object {}.javaClass.getResourceAsStream(path)?.bufferedReader()?.readLines()!!

    fun getResourceAsLine(path: String): String =
        object {}.javaClass.getResourceAsStream(path)?.bufferedReader()?.readText()!!
}