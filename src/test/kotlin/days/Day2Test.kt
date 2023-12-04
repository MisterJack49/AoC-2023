package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun testPartOne() {
        assertThat(Day2().partOne(), `is`(8))
    }

    @Test
    fun testPartTwo() {
        assertThat(Day2().partTwo(), `is`(2286))
    }
}
