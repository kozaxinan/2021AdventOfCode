import java.io.File

class Input

val input: List<String>
    get() = File(Input::class.java.getResource("/input").file).readLines()