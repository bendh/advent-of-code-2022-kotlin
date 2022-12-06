import kotlin.streams.toList

fun main() {

    fun getUniqueBlockIndexEndOf(input: String, window: Int) =
        (input.windowed(window)
            .indexOfFirst {
                it.toSet().size == window
            }) + window

    fun part1(input: String): Int {
        return getUniqueBlockIndexEndOf(input, 4)
    }

    fun part2(input: String): Int {
        return getUniqueBlockIndexEndOf(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readText("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readText("Day06")
    println(part1(input))
    println(part2(input))
}
