fun main() {

    val scoreMap = mapOf(
        "A X" to 4,
        "B Y" to 5,
        "C Z" to 6,
        "C X" to 7,
        "A Y" to 8,
        "B Z" to 9,
        "A Z" to 3,
        "B X" to 1,
        "C Y" to 2)

    fun part1(input: List<String>): Int {
        return input.sumOf { scoreMap.getOrDefault(it, 0) }
    }

    val stragetyMap = mapOf(
        "A X" to 3,
        "B Y" to 5,
        "C Z" to 7,
        "C X" to 2,
        "A Y" to 4,
        "B Z" to 9,
        "A Z" to 8,
        "B X" to 1,
        "C Y" to 6)

    fun part2(input: List<String>): Int {
        return input.sumOf { stragetyMap.getOrDefault(it, 0) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
