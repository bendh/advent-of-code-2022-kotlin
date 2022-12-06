fun main() {

    fun readInitialSupplyStackData(input: List<String>): List<ArrayDeque<String>> {
        // Create deque
        val supplyStackData = input.subList(0, input.indexOf(""))
        val lastStackNumber = supplyStackData.last().last().digitToInt()
        val supplyStacks = (1..lastStackNumber).map { ArrayDeque<String>() }
        val initialSupplyPositionData = supplyStackData.subList(0,supplyStackData.lastIndex)
        initialSupplyPositionData.forEach {
            val chuncks = it.chunked(4)
            chuncks.forEachIndexed {
                index, data ->
                if (data.isNotBlank()) {
                    supplyStacks[index].addLast(data[1].toString())
                }
            }
        }
        return supplyStacks
    }

    fun List<ArrayDeque<String>>.moveSupply(from: Int, to: Int){
        val supply = this[from-1].removeFirst()
        this[to-1].addFirst(supply)
    }

    fun List<ArrayDeque<String>>.getTopSupplies(): String {
        return this.filter { it.isNotEmpty() }
                .map { it.first() }
                .reduce { acc, s -> acc+s }
    }

    fun List<ArrayDeque<String>>.moveMultipleSupply(noOfSupplies: Int, from: Int, to: Int){
        val fromList = this[from-1]
        val toList = this[to-1]
        val supplies = this[from-1].take(noOfSupplies)
        val newFromContent = fromList.slice(noOfSupplies..fromList.lastIndex)
        fromList.clear()
        fromList.addAll(newFromContent)
        toList.addAll(0, supplies)
    }

    fun String.getMove(): Triple<Int, Int, Int> {
        val moveRegex = Regex("""^move (\d+) from (\d+) to (\d+)$""")
        val match = moveRegex.find(this)
        if (this.matches(moveRegex)) {
            val move = match?.destructured?.toList()?.map { it.toInt() }
            if (move != null) {
                return Triple(move[0], move[1], move[2] )
            }
        }
        return Triple(0, 0, 0)
    }

    fun part1(input: List<String>): String {
        val stacks = readInitialSupplyStackData(input)
        val moves = input.subList(input.indexOf("")+1, input.lastIndex+1)
        moves.forEach {
            val (count, from, to) = it.getMove()
            if (count == 1) {
                stacks.moveSupply(from, to)
            } else {
                (1..count).forEach { _ ->
                    stacks.moveSupply(from, to)
                }
            }
        }
        return stacks.getTopSupplies()
    }

    fun part2(input: List<String>): String {
        val stacks = readInitialSupplyStackData(input)
        val moves = input.subList(input.indexOf("")+1, input.lastIndex+1)
        moves.forEach {
            val (noOfSupplies, from, to) = it.getMove()
            stacks.moveMultipleSupply(noOfSupplies, from, to)
        }
        return stacks.getTopSupplies()
    }

    // test if implementation meets criteria from the description, like:
     val testInput = readLines("Day05_test")
     check(part1(testInput) == "CMZ")
     check(part2(testInput) == "MCD")
//
    val input = readLines("Day05")
    println(part1(input))
    println(part2(input))
}
