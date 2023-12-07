package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day7Test {

    @Test
    fun testPartOne() {
        assertThat(Day7().partOne(), `is`(6440))
    }

    @Test
    fun testPartTwo() {
        assertThat(Day7().partTwo(), `is`(5905))
    }
}
