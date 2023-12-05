package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun testPartOne() {
        assertThat(Day4().partOne(), `is`(13))
    }

    @Test
    fun testPartTwo() {
        assertThat(Day4().partTwo(), `is`(30))
    }
}
