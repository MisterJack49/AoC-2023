package days

class Day10 : Day(10) {

    override fun partOne(): Any {
        return inputList
    }

    override fun partTwo(): Any {
        return inputList
    }

}

enum class Tiles(val c: Char, val accept:Pair<Direction>) {
    Vertical('|'),
    Horizontal('-'),
    NinetyTopRight('L'),
    NinetyTopLeft('J'),
    NinetyBottomRight('F'),
    NinetyBottomLeft('7'),
    Ground('.'),
    Start('S');

    companion object {
        fun fromChar(c: Char) = values().first { it.c == c }
    }
}


enum class Direction{ North, South, East, West}
