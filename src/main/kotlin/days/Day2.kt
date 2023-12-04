package days

class Day2 : Day(2) {

    override fun partOne(): Any {
        val max = Set(mapOf(Color.Red to 12, Color.Green to 13, Color.Blue to 14))

        return inputList.parseInput()
            .map { game ->
                game to game.sets.all { it <= max }
            }.filter { it.second }
            .sumOf { it.first.id }
    }

    override fun partTwo(): Any =
        inputList.parseInput()
            .map { it.getMinimumSet() }
            .map { it.colors.values }
            .sumOf { it.fold(1, operation = { acc: Int, i: Int -> acc * i }) }

}

data class Set(val colors: Map<Color, Int>) {
    operator fun compareTo(other: Set): Int {
        if (colors == other.colors) return 0
        return if (colors.all { it.value <= other.colors[it.key]!! }) -1
        else 1
    }
}

data class Game(val id: Int, val sets: List<Set>) {
    fun getMinimumSet() =
        Set(Color.values().associateWith { color -> sets.maxOf { it.colors[color]!! } })
}

enum class Color { Blue, Red, Green }

val colorReg = Regex("((?<blue>\\d+) blue)|((?<red>\\d+) red)|((?<green>\\d+) green)+")

fun String.getColor(color: Color) =
    colorReg.findAll(this)
        .map { result -> result.groups[color.name.lowercase()]?.value }
        .filterNotNull()
        .firstOrNull()
        ?.toInt() ?: 0


fun List<String>.parseInput() =
    map {
        Game(
            it.split(":")[0].removePrefix("Game ").toInt(),
            it.split(":")[1].split(";").map {
                Set(Color.values().associateWith { color -> it.getColor(color) })
            }
        )
    }
