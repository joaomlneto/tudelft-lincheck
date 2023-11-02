import org.jetbrains.kotlinx.lincheck.*
import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.*
import org.jetbrains.kotlinx.lincheck.strategy.stress.*
import org.junit.*

// TODO: Run the test!
class BoundedQueueGPTLincheckTest {
    private val queue = BoundedQueueGPT<Int>(capacity = 1)

    @Operation
    fun add(item: Int) = queue.add(item)

    @Operation
    fun poll() = queue.poll()

    @Test
    fun stressTest() = StressOptions().check(this::class)

    @Test
    fun modelCheckingTest() = ModelCheckingOptions().check(this::class)
}
