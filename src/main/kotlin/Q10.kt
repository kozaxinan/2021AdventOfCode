import java.lang.IllegalArgumentException
import java.util.*

fun main() {

    input
        .asSequence()
        .map { it.toCharArray() }
        .map { chars ->
            val stack = Stack<Char>()
            chars.forEach {
                when (it) {
                    ']' -> stack.checks(chars, '[')
                    ')' -> stack.checks(chars, '(')
                    '}' -> stack.checks(chars, '{')
                    '>' -> stack.checks(chars, '<')
                    else -> stack.push(it)
                }
            }
            chars to stack.reversed()
        }
        .filterNot { illegals.contains(it.first) }
        .map {
            it
                .second
                .fold(0) { acc: Long, i: Char ->
                    acc * 5 + when (i) {
                        '(' -> 1L
                        '[' -> 2L
                        '{' -> 3L
                        '<' -> 4L
                        else -> throw IllegalArgumentException()
                    }
                }
        }
        .sorted()
        .toList()
        .let { it[it.size / 2] }
        .also(::println)
}

private val illegals = mutableMapOf<CharArray, Char>()

private fun Stack<Char>.checks(chars: CharArray, char: Char) {
    val peek = peek()
    if (peek == char) {
        pop()
    } else {
        illegals.putIfAbsent(chars, char)
    }
}




