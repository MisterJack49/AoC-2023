package days

import common.Coordinates

class Day3 : Day(3) {
    override fun partOne() =
        inputList.toSchematic()
            .identifyParts()
            .sumOf { it.value }
    
    override fun partTwo() =
        inputList.toSchematic()
            .identifyGearParts()
            .identifyGearSystems()
            .map { it.calculateRatio() }
            .sumOf { it.value }

}

typealias Line = CharArray
typealias GearSystem = Pair<Symbol, List<Number>>

data class Number(
    val value: Int = 0,
    val line: Int = 0,
    val start: Int = 0,
    val end: Int = 0
) {
    fun getAdjacentCoordinates(): List<Coordinates> {

        val adjacent = mutableListOf<Coordinates>()
        adjacent.add(Coordinates(start - 1, line))
        adjacent.add(Coordinates(end + 1, line))

        for (i in start - 1..end + 1) {
            adjacent.add(Coordinates(i, line - 1))
            adjacent.add(Coordinates(i, line + 1))
        }

        return adjacent
    }
}

data class Symbol(val value: Char, val line: Int, val index: Int)
data class GearPart(val number: Number, val gear: Symbol)
data class Schematic(val data: Array<Line>) {
    private val width get() = data.first().size - 1
    private val height get() = data.size - 1

    private fun scanNumbers(): List<Number> =
        data.mapIndexed { idx, line ->
            line.scanNumbers().map {
                it.copy(line = idx)
            }
        }.flatten()

    private fun getAdjacent(coordinates: List<Coordinates>): List<Symbol> =
        coordinates.map {
            Symbol(data[it.y][it.x], it.y, it.x)
        }

    fun identifyParts(): List<Number> =
        scanNumbers()
            .filter { number ->
                getAdjacent(
                    number.getAdjacentCoordinates()
                        .filterOutbound(width, height)
                ).map { it.value }.hasSymbols()
            }

    fun identifyGearParts(): List<GearPart> =
        scanNumbers().mapNotNull { number ->
            val gear = getAdjacent(
                number.getAdjacentCoordinates()
                    .filterOutbound(width, height)
            ).firstOrNull { it.value == '*' }

            if (gear != null) GearPart(number, gear)
            else null
        }
}

fun List<GearPart>.identifyGearSystems(): List<GearSystem> =
    groupBy({ it.gear }, { it.number })
        .filterValues { it.size == 2 }
        .toList()

fun GearSystem.calculateRatio() =
    second.reduce { acc, number -> Number(value = acc.value * number.value) }


fun List<Char>.hasSymbols() =
    filterNot { it.isDigit() }
        .filterNot { it == '.' }
        .any()

fun List<Coordinates>.filterOutbound(x: Int, y: Int) =
    filterNot { it.x < 0 || it.y < 0 }
        .filterNot { it.x > x || it.y > y }

fun Line.scanNumbers(): List<Number> {

    var acc = ""
    val numbers = mutableListOf<Number>()
    var number = Number()

    forEachIndexed { idx, char ->
        if (char.isDigit() && acc.isEmpty()) {
            acc += char
            number = number.copy(start = idx)
        } else if (char.isDigit()) {
            acc += char
        }

        if (char.isDigit().not() && acc.isNotEmpty()) {
            number = number.copy(end = idx - 1)
            numbers.add(number.copy(value = acc.toInt()))
            number = Number()
            acc = ""
        }
    }

    if (acc.isNotEmpty()) {
        number = number.copy(end = size - 1)
        numbers.add(number.copy(value = acc.toInt()))
    }

    return numbers
}

fun List<String>.toSchematic(): Schematic =
    Schematic(
        map { it.toCharArray() }
            .toTypedArray()
    )
        
