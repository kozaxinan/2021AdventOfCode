import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    var ints: List<Int> = input.first().split(",").map { it.toInt() }

    var counts = mapOf<Int, Int>(
        8 to ints.count { it == 8 },
        7 to ints.count { it == 7 },
        6 to ints.count { it == 6 },
        5 to ints.count { it == 5 },
        4 to ints.count { it == 4 },
        3 to ints.count { it == 3 },
        2 to ints.count { it == 2 },
        1 to ints.count { it == 1 },
        0 to ints.count { it == 0 },
    ).mapValues { it.value.toLong() }

    repeat(256) {
        println(it)
        counts = ints(counts)
        println(counts)
    }

    println(counts.values.sum())
}

private fun ints(ints: Map<Int, Long>): Map<Int, Long> {
    val mothers: Long = ints.getValue(0)
    return ints.mapValues { (key, value) ->
        when (key) {
            8 -> mothers
            6 -> ints.getValue(key + 1) + mothers
            else -> ints.getValue(key + 1)
        }
    }
}

