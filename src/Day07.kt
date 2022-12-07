fun main() {

    data class File(val name: String, val size: Int)
    class Directory(val name: String, var parent: Directory? = null) {
        val files = mutableListOf<File>()
        val childDirectories = mutableListOf<Directory>()
        var dirSize = 0
    }

    fun diskSizeData(input: List<String>): List<Directory> {
        val root = Directory("/")
        val allDirs = mutableListOf(root)
        var pwd = root
        input.drop(1).forEach {
                command ->
            when {
                command == "$ cd .." -> pwd = pwd.parent!!
                command.startsWith("$ cd") -> {
                    val substringAfter = command.substringAfter("$ cd ")
                    pwd = pwd.childDirectories.find { directory -> directory.name == substringAfter }!!
                }

                command.startsWith("dir") -> {
                    val dir = Directory(command.substringAfter("dir "), pwd)
                    pwd.childDirectories.add(dir)
                    allDirs.add(dir)
                }

                command[0].isDigit() -> {
                    val (size, name) = command.split(" ")
                    pwd.dirSize += size.toInt()
                    pwd.files.add(File(name, size.toInt()))
                    var up = pwd.parent
                    while (up != null) {
                        up.dirSize += size.toInt()
                        up = up.parent
                    }
                }
            }
        }
        return allDirs
    }

    /**
     * To begin, find all of the directories with a total size of at most 100000,
     * then calculate the sum of their total sizes.
     */
    fun part1(input: List<String>): Int {
        return diskSizeData(input)
            .filter { directory -> directory.dirSize <= 100_000 }
            .sumOf { it.dirSize }
    }

    fun part2(input: List<String>): Int {
        val maxDiskSize = 70_000_000
        val neededSpace = 30_000_000
        val used = diskSizeData(input)[0].dirSize
        val available = maxDiskSize - used
        val requiredDirToDeleteSize = neededSpace - available

        return diskSizeData(input)
                .filter { it.dirSize >= requiredDirToDeleteSize }
                .sortedBy { it.dirSize }[0].dirSize
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readLines("Day07_test")
    part1(testInput)
      check(part1(testInput) == 95437)
      check(part2(testInput) == 24933642)

      val input = readLines("Day07")
      println(part1(input))
      println(part2(input))


}
