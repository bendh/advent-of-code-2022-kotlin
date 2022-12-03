fun main() {

    fun part1(input: List<String>): Int {
        var combinedPrio = 0
        input.forEach { rucksack ->
            val chuncks = rucksack.chunked(rucksack.length / 2).map { it.toCharArray() }.toTypedArray()
            combinedPrio += chuncks[0].intersect(chuncks[1].toSet()).map {
                if (it.code >= 97) it.code - 96 else it.code - 38
            }.sum()
        }
        return combinedPrio
    }

    fun part2(input: List<String>): Int {
        var totalPrio = 0
        input.map { it.toCharArray() }.chunked(3).forEach { group ->
            totalPrio += group[0].intersect(group[1].toSet())
                .intersect(group[2].toSet())
                .map { char->
                    println(char)
                    if (char.code >= 97) char.code - 96 else char.code - 38
                }.sum()
        }
        return totalPrio
    }

    // test if implementation meets criteria from the description, like:
     val testInput = readInput("Day03_test")
     check(part2(testInput) == 70)
//
    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
