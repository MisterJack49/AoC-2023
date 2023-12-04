package util

import java.io.File

object InputReader {

    fun getInputAsString(day: Int): String {
        return fromResources(day, alternate = false).readText()
    }

    fun getInputAsList(day: Int, alternate: Boolean = false): List<String> {
        return fromResources(day, alternate).readLines()
    }

    private fun fromResources(day: Int, alternate: Boolean): File {
        return File(
            javaClass.classLoader.getResource("input_day_$day${if (alternate) "_alternate" else ""}.txt").toURI()
        )
    }
}
