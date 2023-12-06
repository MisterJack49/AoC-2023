package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun testPartOne() {
        assertThat(Day6().partOne(), `is`(288))
    }

    @Test
    fun testPartTwo() {
        assertThat(Day6().partTwo(), `is`(71503))
    }
}
