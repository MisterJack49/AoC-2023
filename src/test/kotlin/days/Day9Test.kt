package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun testPartOne() {
        assertThat(Day9().partOne(), `is`(114))
    }

    @Test
    fun testPartTwo() {
        assertThat(Day9().partTwo(), `is`(""))
    }
}
