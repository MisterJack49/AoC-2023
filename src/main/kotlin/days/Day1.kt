package days

class Day1(alternate: Boolean = false) : Day(1, alternate) {
    override fun partOne(): Any {
        return inputList
            .map { "${it.findFirstDigit()}${it.findLastDigit()}" }
            .sumOf { it.toInt() }
    }

    override fun partTwo(): Any {
        return inputList
            .map {
                val first: Char = it.findFirstDigitComplex()
                val last: Char = it. findLastDigitComplex()
                
                "$first$last"
            }
            .sumOf { it.toInt() }
    }
}

private fun String.findFirstDigit() =
    first { it.isDigit() }

private fun String.findLastDigit() =
    last { it.isDigit() }

private fun String.firstDigitIndex() =
    indexOfFirst { it.isDigit() }

private fun String.lastDigitIndex() =
    indexOfLast { it.isDigit() }

private fun String.findFirstDigitComplex() =
    if (firstDigitIndex() == -1) Digit.fromString(findFirstSpelled(firstSpelledIndex()))
    else if (firstSpelledIndex() == -1) findFirstDigit()
    else if (firstDigitIndex() < firstSpelledIndex()) findFirstDigit()
    else Digit.fromString(findFirstSpelled(firstSpelledIndex()))

private fun String.findLastDigitComplex() =
    if (lastDigitIndex() == -1) Digit.fromString(findLastSpelled(lastSpelledIndex()))
    else if (lastSpelledIndex() == -1) findLastDigit()
    else if (lastDigitIndex() > lastSpelledIndex()) findLastDigit()
    else Digit.fromString(findLastSpelled(lastSpelledIndex()))

private fun String.firstSpelledIndex() =
    spelled.map { indexOf(it) }
        .filter { it > -1 }
        .run { 
            if(isEmpty())
                -1
            else
                min()
        }

private fun String.lastSpelledIndex() =
    spelled.map { lastIndexOf(it) }
        .run {
            if(isEmpty())
                -1
            else
                max()
        }

private fun String.findFirstSpelled(idx: Int) =
    spelled.first {
        this.startsWith(it, idx)
    }

private fun String.findLastSpelled(idx: Int) =
    spelled.last {
        this.startsWith(it, idx)
    }

private val spelled = Digit.values().map { it.spelling }

private enum class Digit(val spelling: String, val digit: Char) {
    One("one", '1'),
    Two("two", '2'),
    Three("three", '3'),
    Four("four", '4'),
    Five("five", '5'),
    Six("six", '6'),
    Seven("seven", '7'),
    Eight("eight", '8'),
    Nine("nine", '9');

    companion object {
        fun fromString(string: String) = Digit.values().first { it.spelling == string }.digit
    }
}
