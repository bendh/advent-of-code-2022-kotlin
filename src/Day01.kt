fun main() {
    fun part1(input: List<String>): Int {
        var max = 0
        var temp = 0
        input.forEach {
            if(it.isNotEmpty()) {
                temp += it.toInt()
            } else {
                max = if (temp > max) temp else max
                temp = 0
            }
        }
        return max
    }

    fun part2(input: List<String>): Int {
        var temp = 0
        val caloryPerElf = ArrayList<Int>()
        input.forEach {
            if(it.isNotEmpty()) {
                temp += it.toInt()
            } else {
                caloryPerElf.add(temp)
                temp = 0
            }
        }
        caloryPerElf.sort()
        return caloryPerElf.takeLast(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 15)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
