import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val coordinates = input
        .map { row ->
            val split = row.split(" -> ")
            val (x1, y1) = split[0].split(",").map(String::toInt)
            val (x2, y2) = split[1].split(",").map(String::toInt)

            mutableListOf<String>().apply {
                if (y1 == y2) {
                    addAllCoordinates(x1, x2, y1, true)
                } else if (x1 == x2) {
                    addAllCoordinates(y1, y2, x1, false)
                } else if (abs(x2 - x1) == abs(y2 - y1)) {
                    addDiagonalCoordinates(x1, x2, y1, y2)
                }
            }
        }

    val checked = hashSetOf<String>()
    val intersect = hashSetOf<String>()
    coordinates.forEach { line ->
        for (coor in line) {
            if (!checked.add(coor)) {
                intersect.add(coor)
            }
        }
    }

    println(intersect)
    println(intersect.size)
}

private fun MutableList<String>.addAllCoordinates(x1: Int, x2: Int, y1: Int, reverse: Boolean) {
    for (i in min(x1, x2)..max(x1, x2)) {
        if (reverse) {
            add("$i,$y1")
        } else {
            add("$y1,$i")
        }
    }
}

private fun MutableList<String>.addDiagonalCoordinates(x1: Int, x2: Int, y1: Int, y2: Int) {
    var startY = y1
    var startX = x1
    for (i in 0 .. abs(x2 -x1)) {
        add("$startX,$startY")

        if (y1 < y2)
            startY++
        else
            startY--
        if (x1 < x2)
            startX++
        else
            startX--
    }
}
