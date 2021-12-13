fun main() {
    val length = input.first().length

    var most = input
    for (i in 0 until length) {
        most = addAllCoordinates(i, most, true)
    }

    var least = input
    for (i in 0 until length) {
        least = addAllCoordinates(i, least, false)
    }

    println(most.joinToString(separator = "").toInt(2) * least.joinToString(separator = "").toInt(2))
}

private fun addAllCoordinates(index: Int, list: List<String>, most: Boolean): List<String> {
    if (list.size == 1) return list
    val i1: Int =
        list
            .map { it.toCharArray().map { if (it == '1') 1 else -1 } }
            .reduce { acc: List<Int>, ints: List<Int> ->
                acc.mapIndexed { index, i -> ints[index] + i }
            }[index]
            .let {
                if (most) if (it >= 0) 1 else 0
                else if (it < 0) 1 else 0
            }

    return list.filter { it[index].digitToInt() == i1 }.also { println("$index : $it") }
}
