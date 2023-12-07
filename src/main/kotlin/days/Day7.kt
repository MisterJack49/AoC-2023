package days

import days.Day7.CardAlternate.As
import days.Day7.CardAlternate.Joker
import days.Day7.HandType.FiveOfKind
import days.Day7.HandType.FourOfKind
import days.Day7.HandType.FullHouse
import days.Day7.HandType.None
import days.Day7.HandType.OnePair
import days.Day7.HandType.ThreeOfKind
import days.Day7.HandType.TwoPair

class Day7 : Day(7) {

    override fun partOne() =
        inputList.map { line ->
            Hand(
                line.split(" ")[0].map { CardRegular.fromString(it) },
                line.split(" ")[1].toInt()
            )
        }.sorted()
            .foldIndexed(0) { index, acc, hand -> acc + hand.bid * (index + 1) }


    override fun partTwo() =
        inputList.map { line ->
            Hand(
                line.split(" ")[0].map { CardAlternate.fromString(it) },
                line.split(" ")[1].toInt()
            )
        }.sorted()
            .foldIndexed(0) { index, acc, hand -> acc + hand.bid * (index + 1) }


    data class Hand(val hand: List<Card>, val bid: Int, var kind: HandType = None) : Comparable<Hand> {

        init {
            require(hand.size == 5)

            kind = hand.groupBy { it }
                .let { if (hand.first() is CardAlternate) it.swapJokers() else it }
                .let { groups ->
                    if (groups.size == 1) return@let FiveOfKind
                    if (groups.values.any { it.size == 4 }) return@let FourOfKind
                    if (groups.size == 2 && groups.values.any { it.size == 3 }) return@let FullHouse
                    if (groups.values.any { it.size == 3 }) return@let ThreeOfKind
                    if (groups.values.count { it.size == 2 } == 2) return@let TwoPair
                    if (groups.values.any { it.size == 2 }) return@let OnePair
                    HandType.HighCard
                }
        }

        private fun Map<Card, List<Card>>.swapJokers(): Map<Card, List<Card>> =
            (minus(Joker).values.maxByOrNull { it.size }?.firstOrNull() ?: As)
                .let { strongCard ->
                    values.flatten()
                        .map { if (it == Joker) strongCard else it }
                        .groupBy { it }
                }

        override operator fun compareTo(other: Hand): Int {
            if (kind == other.kind) return hand.compare(other.hand)
            return if (kind > other.kind) 1 else -1
        }

        private fun List<Card>.compare(other: List<Card>) =
            zip(other).firstOrNull { cards -> cards.compare() != 0 }?.compare() ?: 0

        private fun Pair<Card, Card>.compare() =
            if (first == second) 0
            else if (first.value < second.value) 1
            else -1
    }

    interface Card {
        val value: Int
    }

    enum class CardRegular(val char: Char) : Card {
        As('A'),
        King('K'),
        Queen('Q'),
        Jack('J'),
        Ten('T'),
        Nine('9'),
        Eight('8'),
        Seven('7'),
        Six('6'),
        Five('5'),
        Four('4'),
        Three('3'),
        Two('2');

        override val value: Int
            get() = ordinal

        companion object {
            fun fromString(c: Char) = CardRegular.values().first { it.char == c }
        }
    }

    enum class CardAlternate(val char: Char) : Card {
        As('A'),
        King('K'),
        Queen('Q'),
        Ten('T'),
        Nine('9'),
        Eight('8'),
        Seven('7'),
        Six('6'),
        Five('5'),
        Four('4'),
        Three('3'),
        Two('2'),
        Joker('J');

        override val value: Int
            get() = ordinal

        companion object {
            fun fromString(c: Char) = CardAlternate.values().first { it.char == c }
        }
    }

    enum class HandType {
        None,
        HighCard,
        OnePair,
        TwoPair,
        ThreeOfKind,
        FullHouse,
        FourOfKind,
        FiveOfKind;
    }

}


