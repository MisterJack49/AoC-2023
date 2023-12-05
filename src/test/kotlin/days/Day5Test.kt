package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun testPartOne() {
        assertThat(Day5().partOne(), `is`(35L))
    }

    @Test
    fun testPartTwo() {
        assertThat(Day5().partTwo(), `is`(46L))
    }
}
