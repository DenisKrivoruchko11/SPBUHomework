package homework3.hw3_task1

fun <K : Comparable<K>, T> addItem(addsItem: AvlTreeItem<K, T>, itemForAdd: AvlTreeItem<K, T>, tree: AvlTree<K, T>) {
    if (itemForAdd.key < addsItem.key) {
        if (itemForAdd.rightChild == null) {
            itemForAdd.rightChild = addsItem
            addsItem.parent = itemForAdd
        } else {
            addItem(addsItem, itemForAdd.rightChild!!, tree)
        }
    } else if (itemForAdd.key > addsItem.key) {
        if (itemForAdd.leftChild == null) {
            itemForAdd.leftChild = addsItem
            addsItem.parent = itemForAdd
        } else {
            addItem(addsItem, itemForAdd.leftChild!!, tree)
        }
    }
    itemForAdd.entries.plus(Pair(addsItem.key, addsItem.value))
    itemForAdd.values.plus(addsItem.value)
    itemForAdd.keys.plus(addsItem.key)
    itemForAdd.size = maxOf(itemForAdd.rightChild?.size ?: 0, itemForAdd.leftChild?.size ?: 0) + 1
    itemForAdd.balanceCoefficient = (itemForAdd.rightChild?.size ?: 0) - (itemForAdd.leftChild?.size ?: 0)
    itemForAdd.balance(tree)
}

private fun <K : Comparable<K>, T> deleteItem(key: K, item: AvlTreeItem<K, T>, tree: AvlTree<K, T>) {
    if (item.key < key) {
        deleteItem(key, item.rightChild!!, tree)
    } else if (item.key > key) {
        deleteItem(key, item.leftChild!!, tree)
    } else {
        if (item.leftChild == null && item.rightChild == null) {
            if (item.parent!!.key < item.key) {
                item.parent!!.balanceCoefficient--
                item.parent!!.rightChild = null
                item.parent = null
            } else {
                item.parent!!.balanceCoefficient++
                item.parent!!.leftChild = null
                item.parent = null
            }
        } else if (item.leftChild == null || item.rightChild == null) {
            if (item.parent!!.key < item.key) {
                item.parent!!.balanceCoefficient--
                item.parent!!.rightChild = (if (item.rightChild == null) item.leftChild else item.rightChild)
                item.parent = null
            } else {
                item.parent!!.balanceCoefficient++
                item.parent!!.leftChild = (if (item.rightChild == null) item.leftChild else item.rightChild)
                item.parent = null
            }
        } else {

        }
    }
    item.balance(tree)
}

class AvlTree<K : Comparable<K>, T>(var head: AvlTreeItem<K, T>) {
    fun plus(addsItem: AvlTreeItem<K, T>) {
        if (head.containsKey(addsItem.key)) throw Exception("Key exists")
        addItem(addsItem, head, this)
    }

    fun minus(key: K) {
        deleteItem(key, head, this)
    }
}