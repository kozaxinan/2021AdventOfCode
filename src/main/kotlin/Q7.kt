import kotlin.math.*

fun main() {
    val ints: List<Long> = input
        .first()
        .split(",")
        .map(String::toLong)
        .sorted()

    val mid1 = floor(ints.average()).roundToLong()
    val mid2 = ceil(ints.average()).roundToLong()

    val minOf1 = ints.sumOf { abs(it - mid1).burn() }
    val minOf2 = ints.sumOf { abs(it - mid2).burn() }

    println(min(minOf1, minOf2))
}

private fun Long.burn(): Long {
    return when {
        this == 0L -> 0
        else -> this + (this - 1).burn()
    }
}


