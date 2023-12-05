package days

import kotlin.math.pow

class Day4 : Day(4) {
    override fun partOne() =
        inputList
            .filterNot { it.isEmpty() }
            .parseCards().sumOf { it.points }


    override fun partTwo() =
        Deck(inputList
            .filterNot { it.isEmpty() }
            .parseCards())
            .run {
                processCards()
                cards
            }.sumOf { it.instances }

}

val cardRegex = Regex("(Card\\s+(?<id>\\d+)):\\s+(?<winning>(\\d+(?>\\s*))+)\\|\\s+(?<drawn>(\\d+(?>\\s*))+)")

data class Deck(val cards: List<Card>) {
    fun processCards() {
        cards.forEach { card ->

            if(card.matches().none()) return@forEach
            
            val win = card.id + 1..card.id + card.matches().count()

            for (i in win) {
                cards[i-1].instances += card.instances
            }
        }
    }
}

data class Card(
    val id: Int,
    val winning: List<Int>,
    val drawn: List<Int>,
    var points: Int = 0,
    var instances: Int = 1
) {
    init {
        points = (if (matches().isEmpty()) 0.0 else 2.0.pow(matches().count() - 1)).toInt()
    }
    fun matches() =
        drawn.intersect(winning.toSet())
}

fun List<String>.parseCards() =
    map { string ->
        val match = cardRegex.find(string)!!
        Card(match.groups["id"]!!.value.toInt(),
            match.groups["winning"]!!.value.split(" ").filterNot { it.isEmpty() }.map { it.toInt() },
            match.groups["drawn"]!!.value.split(" ").filterNot { it.isEmpty() }.map { it.toInt() }
        )
    }
