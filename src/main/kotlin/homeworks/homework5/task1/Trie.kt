package homeworks.homework5.task1

import homeworks.homework5.task1.interfaces.ITrie
import homeworks.homework5.task1.models.TrieItem
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.io.Serializable
import java.util.Scanner

class Trie : ITrie, Serializable {

    private val head = TrieItem("")

    private fun clear() {
        head.clear()
    }

    override fun add(element: String): Boolean {
        return head.plus(element)
    }

    override fun contains(element: String): Boolean {
        return head.isExist(element)
    }

    override fun remove(element: String): Boolean {
        return head.minus(element)
    }

    override fun size(): Int {
        return head.size()
    }

    override fun howManyStartWithPrefix(prefix: String): Int {
        return head.quantityWithThisPrefix(prefix)
    }

    fun serialize(output: OutputStream) {
        val words = head.getAllWords("")

        if (words.isEmpty()) throw IOException("Trie is empty.")

        output.write((words.joinToString(" ") { "'$it'" }).toByteArray())
        output.close()
    }

    fun deserialize(input: InputStream) {
        val inputString = input.bufferedReader().readLine() ?: throw KotlinNullPointerException("Input is null.")
        val scanner = Scanner(inputString)

        clear()

        while (scanner.hasNext()) {
            val word = scanner.next()

            if (word.length < 2 || word[0] != '\'' || word[word.lastIndex] != '\'') {
                throw IOException("String is not correct.")
            }

            if (word.length == 2) {
                add("")
                continue
            }

            add(word.substring(1, word.lastIndex))
        }

        input.close()
    }
}
