import java.util.*

fun main() {

    input
        .sumOf { row ->
            var hor1 = ' '
            var hor2 = ' '
            var hor3 = ' '
            var start1 = ' '
            var start2 = ' '
            var end1 = ' '
            var end2 = ' '

            val split = row
                .substringBefore("|")
                .trim()
                .split(" ")

            val one = split.first { it.length == 2 }
            val seven = split.first { it.length == 3 }
            val four = split.first { it.length == 4 }

            val charsMap = mutableMapOf<Char, Int>()
            row
                .substringBefore("|")
                .replace(" ", "")
                .forEach {
                    charsMap[it] = charsMap.getOrDefault(it, 0) + 1
                }

            hor1 = seven.toSortedSet().apply { removeAll(one.toSortedSet()) }.first()

            start2 = charsMap.filterValues { it == 4 }.firstNotNullOf { it.key }
            start1 = charsMap.filterValues { it == 6 }.firstNotNullOf { it.key }
            end2 = charsMap.filterValues { it == 9 }.firstNotNullOf { it.key }
            end1 = charsMap.filterValues { it == 8 }.firstNotNullOf { if (it.key != hor1) it.key else null }

            hor2 = four
                .toHashSet()
                .apply {
                    remove(start1)
                    remove(end1)
                    remove(end2)
                }
                .first()

            hor3 = charsMap.filterValues { it == 7 }.firstNotNullOf { if (it.key != hor2) it.key else null }

            val numbers: Map<SortedSet<Char>, Int> = createNumberSet(hor1, hor2, hor3, start1, start2, end1, end2)

            row
                .substringAfter("|")
                .trim()
                .split(" ")
                .map { numbers.getValue(it.toSortedSet()) }
                .joinToString(separator = "")
                .toLong()
        }
        .run {
            println(this)
        }
}

private fun createNumberSet(
    hor1: Char,
    hor2: Char,
    hor3: Char,
    start1: Char,
    start2: Char,
    end1: Char,
    end2: Char
): Map<SortedSet<Char>, Int> = mapOf(
    sortedSetOf(hor1, hor3, start1, start2, end1, end2) to 0,
    sortedSetOf(end1, end2) to 1,
    sortedSetOf(hor1, hor2, hor3, start2, end1) to 2,
    sortedSetOf(hor1, hor2, hor3, end1, end2) to 3,
    sortedSetOf(hor2, start1, end1, end2) to 4,
    sortedSetOf(hor1, hor2, hor3, start1, end2) to 5,
    sortedSetOf(hor1, hor2, hor3, start1, start2, end2) to 6,
    sortedSetOf(hor1, end1, end2) to 7,
    sortedSetOf(hor1, hor2, hor3, start1, start2, end1, end2) to 8,
    sortedSetOf(hor1, hor2, hor3, start1, end1, end2) to 9,
)


//0:      1:      2:      3:      4:
// aaaa    ....    aaaa    aaaa    ....
//b    c  .    c  .    c  .    c  b    c
//b    c  .    c  .    c  .    c  b    c
// ....    ....    dddd    dddd    dddd
//e    f  .    f  e    .  .    f  .    f
//e    f  .    f  e    .  .    f  .    f
// gggg    ....    gggg    gggg    ....
//
//5:      6:      7:      8:      9:
// aaaa    aaaa    aaaa    aaaa    aaaa
//b    .  b    .  .    c  b    c  b    c
//b    .  b    .  .    c  b    c  b    c
// dddd    dddd    ....    dddd    dddd
//.    f  e    f  .    f  e    f  .    f
//.    f  e    f  .    f  e    f  .    f
// gggg    gggg    ....    gggg    gggg

// 4 e
// 6 b
// 9 f

// 7 d
// 7 g

// 8 a
// 8 c



