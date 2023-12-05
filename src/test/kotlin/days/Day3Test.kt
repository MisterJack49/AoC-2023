package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun testPartOne() {
        assertThat(Day3().partOne(), `is`(4361))
    }

    @Test
    fun testPartTwo() {
        assertThat(Day3().partTwo(), `is`(467835))
    }
}
