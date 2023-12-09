package days

class Day9 : Day(9) {

    override fun partOne(): Any {
        return inputList
            .map { line -> line.split(" ").map { it.toInt() } }
            .map { it.extrapolate() }
            .sumOf { it.last() }
    }

    override fun partTwo(): Any {
        return inputList
            .map { line -> line.split(" ").map { it.toInt() } }
            .map { it.reversed().extrapolate() }
            .sumOf { it.last() }
    }

    private fun List<Int>.differential(): List<Int> =
        zipWithNext().map { it.second - it.first }
    
    private fun List<Int>.chainDifferential(): List<List<Int>> =
        generateSequence(this) { it.differential() }
            .takeWhile {list ->  list.any { it != 0 } }
            .let { it.plusElement(it.last().differential()) }
            .toList()
    
    private fun List<Int>.extrapolate(diff: Int) =
        plus(last() + diff)
    
    private fun List<Int>.extrapolate() =
        chainDifferential()
            .reversed()
            .foldIndexed(emptyList<Int>()) { index, previous, current ->
                if (index == 0) current.plus(0) else current.extrapolate(previous.last())
            }
}


