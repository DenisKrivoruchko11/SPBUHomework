package interfaces

interface IBorTree {
    fun add(element: String): Boolean
    fun contains(element: String): Boolean
    fun remove(element: String): Boolean
    fun size(): Int
    fun howManyStartWithPrefix(prefix: String): Int
}