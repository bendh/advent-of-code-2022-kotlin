fun main() {

    fun charToValue(char: Char) = if (char.code >= 97) char.code - 96 else char.code - 38

    fun part1(input: List<String>): Int {
        return input.sumOf { rucksack ->
            val chunks = rucksack.chunked(rucksack.length / 2).map { it.toCharArray() }.toTypedArray()
            val compartment1 = chunks[0].toSet()
            val compartment2 = chunks[1].toSet()

            (compartment1 intersect compartment2).sumOf {
                charToValue(it)
            }
        }
    }

    fun part2(input: List<String>): Int {
        var totalPrio = 0
        input.map { it.toCharArray().toSet() }.chunked(3).forEach { group ->
            totalPrio += (group[0] intersect group[1] intersect group[2]).sumOf {
                    char -> charToValue(char)
            }
        }
        return totalPrio
    }

    // test if implementation meets criteria from the description, like:
     val testInput = readLines("Day03_test")
     check(part1(testInput) == 157)
     check(part2(testInput) == 70)
//
    val input = readLines("Day03")
    println(part1(input))
    println(part2(input))
}
