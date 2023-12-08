package days

class Day8(alternate: Boolean = false) : Day(8, alternate) {

    override fun partOne() =
        inputList.let {
            val path = it.first().map { if (it == 'L') Direction.L else Direction.R }
            val network = it.drop(2).parseNetwork()

            for ((loc, step) in stepper("AAA", path, network)) {
                if (loc == "ZZZ") return@let step
            }
            -1
        }


    override fun partTwo() =
        inputList.let { input ->
            val path = input.first().map { if (it == 'L') Direction.L else Direction.R }
            val network = input.drop(2).parseNetwork()

            network.map.keys.filter { it.last() == 'A' }.map {
                for ((loc, step) in stepper(it, path, network)) {
                    if (loc.last() == 'Z') {
                        return@map step.toLong()
                    }
                }
                -1L
            }.fold(1) { acc: Long, i: Long -> lcm(acc, i) }
        }


    private fun stepper(start: String, path: List<Direction>, network: Network): Sequence<Pair<String, Int>> =
        sequence {
            var i = 0
            var loc = start
            while (true) {
                val (left, right) = network[loc]!!
                val dir = path[i++ % path.size]
                loc = if (dir == Direction.L) left else right
                yield(Pair(loc, i))
            }
        }

    enum class Direction { L, R }
    data class Network(val map: Map<String, Pair<String, String>>) {
        operator fun get(location: String): Pair<String, String>? = map[location]
    }

    private fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
    private fun lcm(a: Long, b: Long): Long = if (a == 0L || b == 0L) 0L else (a * b) / gcd(a, b)

    private val networkRegex = Regex("(?<key>\\w{3}) = \\((?<left>\\w{3}), (?<right>\\w{3})\\)")
    private fun List<String>.parseNetwork() =
        Network(associate {
            val result = networkRegex.find(it)!!.groups
            result["key"]!!.value to Pair(result["left"]!!.value, result["right"]!!.value)
        })

}
