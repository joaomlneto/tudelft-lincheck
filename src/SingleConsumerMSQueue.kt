import java.util.concurrent.atomic.*

class SingleConsumerMSQueue<T> {
    @Volatile
    private var head: Node<T>

    private val tail: AtomicReference<Node<T>>

    init {
        val dummy = Node<T>(null)
        head = dummy
        tail = AtomicReference(dummy)
    }

    fun add(item: T) {
        val newNode = Node(item)
        while (true) {
            val curTail = tail.get()
            if (curTail.next.compareAndSet(null, newNode)) {
                tail.compareAndSet(curTail, newNode)
                return
            } else {
                // TODO: help to adjust the `tail` pointer.
                tail.compareAndSet(curTail, curTail.next.get())
            }
        }
    }

    fun poll(): T? {
        val headNext = head.next.get() ?: return null
        head = headNext
        return headNext.element
        // TODO: clean the element field before finishing.
    }

    internal fun validateState() {
        check(head.element == null) { "`head.element` should be `null`" }
        check(tail.get().next.get() == null) { "`tail.next` should be `null`" }
    }
}

private class Node<T>(
    var element: T?
) {
    val next = AtomicReference<Node<T>?>(null)
}
