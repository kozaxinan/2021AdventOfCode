fun main() {

    val digits = input
        .map { it.toCharArray().map(Char::digitToInt) }

    digits
        .asSequence()
        .mapIndexed { x, ints ->
            ints
                .mapIndexed { y, digit ->
                    val visited = mutableSetOf("$x:$y")
                    checkPoint(digits, digit, x - 1, y, visited) +
                            checkPoint(digits, digit, x + 1, y, visited) +
                            checkPoint(digits, digit, x, y - 1, visited) +
                            checkPoint(digits, digit, x, y + 1, visited)

                    visited.size
                }
                .sortedDescending()
        }
        .flatten()
        .sortedDescending()
        .take(3)
        .fold(1) { acc, i -> acc * i }
        .also(::println)
}

fun checkPoint(digits: List<List<Int>>, digit: Int, x: Int, y: Int, visited: MutableSet<String>): Int {
    if (x < 0 || x > digits.lastIndex) return 0
    if (y < 0 || y > digits.first().lastIndex) return 0
    if (visited.contains("$x:$y")) return 0

    val nextDigit = digits[x][y]
    if (nextDigit == 9) return 0
    if (nextDigit <= digit) return 0
    visited.add("$x:$y")

    return checkPoint(digits, nextDigit, x - 1, y, visited) +
            checkPoint(digits, nextDigit, x + 1, y, visited) +
            checkPoint(digits, nextDigit, x, y - 1, visited) +
            checkPoint(digits, nextDigit, x, y + 1, visited)
}



