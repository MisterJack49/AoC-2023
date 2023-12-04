package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day1Test {

    private val day = Day1()

    @Test
    fun testPartOne() {
        assertThat(Day1().partOne(), `is`(142))
    }

    @Test
    fun testPartTwo() {
        assertThat(Day1(true).partTwo(), `is`(281))
    }
}
