fun main() {
    val numbers = input.first().split(",")
    var strings = input
        .drop(1)
        .filter { it.isNotBlank() }
        .map {
            it
                .trim()
                .replace("  ", " ")
                .split(' ')
        }
        .windowed(5, 5)

    val winners = mutableSetOf<Int>()
    numbers.forEach { number ->
        strings = strings.map { matrix ->
            matrix.map { row -> row.map { if (it == number) "-$it" else it } }
        }

        strings.forEachIndexed { which, matrix ->
            matrix.forEachIndexed { index, row ->
                if (row.all { it.contains("-") }) {
                    winners += which
                } else {
                    val column = matrix.map { it[index] }
                    if (column.all { it.contains("-") }) {
                        winners += which
                    }
                }

            }
        }

        if (winners.size == strings.size) {
            val matrix = strings[winners.last()]
            val sumOf = matrix.flatten().filterNot { it.contains("-") }.sumOf { it.toInt() }
            throw Exception("${sumOf * number.toInt()}")
        }
    }


    println(strings)
}
