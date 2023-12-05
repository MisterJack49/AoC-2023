package common

data class Coordinates(val x: Int, val y: Int) {

    fun isAdjacent(other: Coordinates): Boolean =
        other.x in (x - 1..x + 1) && other.y in (y - 1..y + 1)

    fun getPosition(other: Coordinates): MutableList<Alignement> {
        val alignment = mutableListOf<Alignement>()
        if (isAlignedRow(other)) alignment.add(Alignement.ROW)
        if (isAlignedColumn(other)) alignment.add(Alignement.COLUMN)
        if (isAbove(other)) alignment.add(Alignement.ABOVE)
        if (isBelow(other)) alignment.add(Alignement.BELOW)
        if (isBefore(other)) alignment.add(Alignement.BEFORE)
        if (isAfter(other)) alignment.add(Alignement.AFTER)

        return alignment
    }

    operator fun plus(other: Coordinates) = Coordinates(x + other.x, y + other.y)

    fun isAlignedRow(other: Coordinates) =
        y == other.y

    fun isAlignedColumn(other: Coordinates) =
        x == other.x

    fun isAbove(other: Coordinates) = y > other.y

    fun isBelow(other: Coordinates) = y < other.y

    fun isBefore(other: Coordinates) = x < other.x

    fun isAfter(other: Coordinates) = x > other.x

    override fun toString(): String {
        return "($x,$y)"
    }

    companion object {
        val Right = Coordinates(1, 0)
        val Left = Coordinates(-1, 0)
        val Up = Coordinates(0, 1)
        val Down = Coordinates(0, -1)
    }
}

enum class Alignement {
    ROW, COLUMN, ABOVE, BELOW, BEFORE, AFTER
}
