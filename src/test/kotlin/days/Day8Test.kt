package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun testPartOne() {
        assertThat(Day8().partOne(), `is`(2))
    }

    @Test
    fun testPartTwo() {
        assertThat(Day8(true).partTwo(), `is`(6L))
    }
}
