fun main() {

    fun String.toIntRange(): IntRange {
        val rangeRegex = Regex(pattern = """^\d+-\d+$""")
        return if (rangeRegex.matches(this)) {
            val values = this.split("-").map { it.toInt() }
            values[0]..values[1]
        } else {
            0..0
        }
    }

    fun hasCompleteOverlap(group1: String, group2: String): Boolean   {
        val range1 = group1.toIntRange()
        val range2 = group2.toIntRange()
        val intersection = range1.intersect(range2).sorted()
        return intersection.size == range1.count() || intersection.size == range2.count()
    }

    fun hasOverlap(group1: String, group2: String): Boolean   {
        val range1 = group1.toIntRange()
        val range2 = group2.toIntRange()
        val intersection = range1.intersect(range2).sorted()
        return intersection.isNotEmpty()
    }

    fun part1(input: List<String>): Int {
        return input.map {
            val groups = it.split(",")
            hasCompleteOverlap(groups[0], groups[1])
        }.count { it }
    }

    fun part2(input: List<String>): Int {
        return input.map {
            val groups = it.split(",")
            hasOverlap(groups[0], groups[1])
        }.count { it }
    }

    // test if implementation meets criteria from the description, like:
     val testInput = readLines("Day04_test")
     check(part2(testInput) == 4)
//
    val input = readLines("Day04")
    println(part1(input))
    println(part2(input))
}
