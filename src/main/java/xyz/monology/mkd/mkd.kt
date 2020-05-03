package xyz.monology.mkd

import java.io.File
import java.io.FileWriter

class MKD {
    private val strings = ArrayList<String>()

    private fun Int.clamp(min: Int, max: Int): Int {
        if (this > max) return max
        if (this < min) return min

        return this
    }

    fun h(level: Int, message: String) {
        strings.add("${"#".repeat(level.clamp(1, 6))} $message")
    }

    fun h1(message: String) = h(1, message)
    fun h2(message: String) = h(2, message)
    fun h3(message: String) = h(3, message)
    fun h4(message: String) = h(4, message)
    fun h5(message: String) = h(5, message)
    fun h6(message: String) = h(6, message)

    operator fun String.unaryPlus() {
        strings.add(this)
    }

    fun code(message: String) = "`$message`"

    fun blockCode(language: String = "", vararg lines: String) {
        strings.add("""
            ```$language
            ${lines.joinToString("\n")}
            ```
        """.trimIndent())
    }

    fun bq(message: String) = "> $message"

    fun st(message: String) = "~~$message~~"

    fun b(message: String) = "**$message**"

    fun i(message: String) = "*$message*"

    fun image(url: String, altText: String = "") {
        strings.add("![$altText]($url)")
    }

    fun link(url: String, name: String) {
        strings.add("[$name]($url)")
    }

    class Table(columns: kotlin.collections.List<String>) {
        private val values = ArrayList<String>()

        fun row(vararg items: String) {
            values.add(items.joinToString(" | "))
        }

        init {
            values.add(columns.joinToString(" | "))
            values.add(columns.joinToString(" | ") {
                "-".repeat(it.length)
            })
        }

        override fun toString() = values.joinToString("\n")
    }

    class List(private val ordered: Boolean, private val isTaskList: Boolean) {
        private val values = ArrayList<String>()
        private var subbed = 0
        private val subbing
            get() = "   ".repeat(subbed)

        operator fun String.unaryPlus() = c(this, false)

        fun c(message: String, complete: Boolean = true) {
            values.add("${if (ordered) "1." else "-"}${if (isTaskList) " [${if (complete) "x" else " "}]" else ""} $message")
        }

        operator fun plus(list: List) {
            list.subbed = subbed + 1
            for (value in list.values) {
                values.add("$subbing${if (ordered) "$1." else "-"}${if (isTaskList) " [ ]" else ""} $value")
            }
        }

        override fun toString() = values.joinToString("\n")

    }

    fun blank() {
        strings.add("\n\n")
    }

    fun list(ordered: Boolean, isTaskList: Boolean, function: List.() -> Unit) {
        strings.add(List(ordered, isTaskList).apply(function).toString())
    }

    fun tlist(function: List.() -> Unit) = list(ordered = true, isTaskList = true, function = function)

    fun ulist(function: List.() -> Unit) = list(ordered = false, isTaskList = false, function = function)

    fun olist(function: List.() -> Unit) = list(ordered = true, isTaskList = false, function = function)

    fun table(vararg columns: String, function: Table.() -> Unit) {
        strings.add(Table(columns.toList()).apply(function).toString())
    }

    fun toMarkdown() = strings.joinToString("\n\n")

    fun save(file: File) = FileWriter(file).apply {
        write(toMarkdown())
        flush()
        close()
    }
}

fun markdown(function: MKD.() -> Unit): MKD {
    return MKD().apply(function)
}