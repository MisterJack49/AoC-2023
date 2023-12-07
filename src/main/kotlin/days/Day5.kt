package days

class Day5 : Day(5) {
    override fun partOne(): Any {
        val almanac = inputString.split(Regex("\\r\\n\\r\\n"))

        val seeds = almanac[0].split(":")[1].split(" ").filterNot { it.isEmpty() }.map { it.toLong() }

        val mappers = almanac.drop(1)
            .mapIndexed { idx, mapper ->
                mapper.split(Regex("\\r\\n"))
                    .filter { it.isNotEmpty() }
                    .drop(1)
                    .map {
                        it.split(" ")
                    }
                    .map {
                        Range(it[0].toLong(), it[1].toLong(), it[2].toLong())
                    }.run { Mapper(Category.values()[idx], Category.values()[idx + 1], this) }
            }

        return seeds.minOf { seed ->
            mappers.findLocation(seed)
        }
    }

    override fun partTwo(): Any {
        val almanac = inputString.split(Regex("\\r\\n\\r\\n"))

        val seedRanges = almanac[0].split(":")[1]
            .split(" ")
            .filterNot { it.isEmpty() }
            .map { it.toLong() }
            .chunked(2)

        val mappers = almanac.drop(1)
            .mapIndexed { idx, mapper ->
                mapper.split(Regex("\\r\\n"))
                    .filter { it.isNotEmpty() }
                    .drop(1)
                    .map {
                        it.split(" ")
                    }
                    .map {
                        Range(it[0].toLong(), it[1].toLong(), it[2].toLong())
                    }.run { Mapper(Category.values()[idx], Category.values()[idx + 1], this) }
            }

        return seedRanges.minOf {
            mappers.findMin(it.first(), it.last())
        }

    }
}

private fun List<Mapper>.findMin(start: Long, length: Long): Long {

    if (length == 1L) return minOf(findLocation(start), findLocation(start + 1))


    val stepSize = length / 2
    val middle = start + stepSize

    val startLocation = findLocation(start)
    val middleLocation = findLocation(middle)
    val endLocation = findLocation(start + length)

    var foundMin = Long.MAX_VALUE

    if (startLocation + stepSize != middleLocation) {
        foundMin = findMin(start, stepSize)
    }

    if (middleLocation + (length - stepSize) != endLocation) {
        foundMin = minOf(foundMin, findMin(middle, length - stepSize))
    }

    return foundMin
}

private fun List<Mapper>.findLocation(seed: Long) =
    fold(seed) { acc, mapper ->
        mapper.convert(acc)
    }

private data class Range(val destination: Long, val source: Long, val size: Long) {
    fun isMapping(value: Long): Boolean =
        (source until source + size).contains(value)

    fun convert(value: Long): Long =
        if (isMapping(value).not()) value
        else destination + (value - source)
}

private data class Mapper(val from: Category, val to: Category, val ranges: List<Range>) {
    fun convert(value: Long): Long =
        ranges.firstOrNull { it.isMapping(value) }?.convert(value) ?: value

}

private enum class Category {
    Seed, Soil, Fertilizer, Water, Light, Temperature, Humidity, Location
}
