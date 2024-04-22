import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import org.junit.*

class SingleConsumerMSQueueLincheckTest {
    private val queue = SingleConsumerMSQueue<Int>()

    // TODO 1:
    //  Add a `nonParallelGroup` option to the
    //  `@Operation(..)` annotation to forbid parallel
    //  `add(..)` calls.
    @Operation(nonParallelGroup = "consumers")
    fun poll() = queue.poll()

    @Operation
    fun add(item: Int) = queue.add(item)

    // TODO 3:
    //  Add a `validate()` function that checks the
    //  queue state at the end of each execution.
    //  Call `queue.validateState()` for this.

    @Operation
    fun validate() = queue.validateState()

    @Test
    fun modelCheckingTest() = ModelCheckingOptions()
        // TODO 2:
        //  Add an obstruction-freedom check
        //  by calling `checkObstructionFreedom()` on
        //  the constructed `ModelCheckingOptions`.
        //
        .checkObstructionFreedom()
        // TODO 4:
        //  Provide a sequential queue implementation
        //  for a more robust results verification.
        //  ----
        //  1. Implement the same `add(..)` and `poll()`
        //     functions in `SequentialQueueInt`.
        //  2. Provide the `SequentialQueueInt` class
        //     as the sequential specification
        //     by calling `sequentialSpecification(..)`
        //     on the constructed `ModelCheckingOptions`.
        .check(this::class)
}

class SequentialQueueInt {
    fun add(item: Int): Boolean = TODO("implement me")
    fun poll(): Int? = TODO("implement me")
}
