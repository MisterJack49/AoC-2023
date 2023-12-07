package days

class Day6 : Day(6) {
    override fun partOne() =
        inputList.map { entry ->
            entry.split(":")[1].split(" ").filter { it.isNotEmpty() }.map { it.toLong() }
        }.run {
            first().mapIndexed { index, i ->
                Race(i, last()[index])
            }
        }.map { race ->
            race.simulateRecordCount()
        }.fold(1) { acc, i -> acc * i }

    override fun partTwo() =
        inputList.map { entry ->
            entry.split(":")[1].replace(" ", "").toLong()
        }.run {
            Race(this.first(), this.last())
                .extrapolateRecordCount()
        }
}

private data class Race(val time: Long, val distance: Long) {

    private fun getDistance(hold: Long): Long =
        (time - hold) * hold
    
    fun simulateRecordCount(): Int {
        return (0..time).map {
            getDistance(it)
        }.count { it > distance }
    }

    fun extrapolateRecordCount(): Int {
        val mid = (if (time % 2 == 1L) time -1 else time) / 2
        val firstRecord = findFirstRecordTime(0, mid)
        return (firstRecord..time - firstRecord).count()
    }

    private fun findFirstRecordTime(low: Long, high: Long): Long {
        if (low > high) return -1

        val stepSize = (high - low) / 2
        val middle = low + stepSize
        
        val middleDistance = getDistance(middle)
        
        return if (middleDistance > distance) {
            val res = findFirstRecordTime(low, middle - 1)
            if (res != -1L) res else middle
        } else {
            findFirstRecordTime(middle + 1, high)
        }
    }
}
