import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

/*
suspend fun main() = supervisorScope {
    val job1 = async {
        delay(1000)
        println("API 1 Done")
        "Result 1"
    }

    val job2 = async {
        delay(500)
        throw RuntimeException("API 2 Failed")
    }

    val result1 = job1.await()
    val result2 = job2.await()

    println(result1)
   // println(result2)
}*/

fun simpleTask() = runBlocking {
    println("Starting Task")
    delay(1000)
    println("Task Completed")
}

fun main() {
    // simpleTask()
    //  multipleTasks()
    // controlledExecution()
    // fetchUserData()
    // fetchProductDetails()
    // safeDataFetch()
    //dispatcherDemo()
   // cancellableTask()
    millionThread()
}

fun cancellableTask() = runBlocking {
    val scope = CoroutineScope(Dispatchers.Default)

    val job = scope.launch {
        println("Task started")

        for (i in 1..10) {
            ensureActive() // Check for cancellation
            println("Processing item $i")
            delay(500)
        }
        println("Task completed") // This won't print if cancelled
    }
    println("Delay Started")
    delay(1200)
    println("Cancelling task...")
    job.cancel()
    delay(1000)
    println("Main function ending")
}
fun dispatcherDemo() = runBlocking {
    val oneDeferred = async { coroutineOne() }
    val twoDeferred = async { coroutineTwo() }
    val threeDeferred = async { coroutineThree() }
    val one = oneDeferred.await()
    val two = twoDeferred.await()
    val three = threeDeferred.await()
    println("All tasks completed")

}

suspend fun coroutineOne(): String = withContext(Dispatchers.IO) {
    println(Thread.currentThread().name)
    delay(1000)
    "File data loaded"

}

suspend fun coroutineTwo() = withContext(Dispatchers.Default) {
    println(Thread.currentThread().name)
    delay(1000)
    "Computation result: 42"

}

suspend fun coroutineThree(): String = withContext(Dispatchers.Unconfined) {
    println(Thread.currentThread().name)
    delay(1000)
    "UI updated"
}

fun safeDataFetch() = runBlocking {
    val dataDeferred = async { fetchNetworkData() }
    val cacheDeferred = async { fetchCachedData() }
    val finalData = try {
        dataDeferred.await()   // this will throw exception
    } catch (e: Exception) {
        println("Primary fetch failed, using cache...")
        cacheDeferred.await() // fallback to cache
    }
    println("Final data: $finalData")
}

suspend fun fetchNetworkData() {
    delay(1000)
    throw IllegalStateException("Network error")
}

suspend fun fetchCachedData(): String {
    delay(500)
    return "Cached data"
}

fun fetchProductDetails() = runBlocking {
    val start = System.currentTimeMillis()
    val productNameDeferred = async { fetchProductName() }
    val productPriceDeferred = async { fetchProductPrice() }
    val productRatingDeferred = async { fetchProductRating() }

    val price = productPriceDeferred.await()
    val name = productNameDeferred.await()
    val rating = productRatingDeferred.await()
    val end = System.currentTimeMillis()
    println("Total time: ${end - start} ms")
    println("Price: ${price}, Name: $name, Rating: $rating")

}

suspend fun fetchProductName(): String {
    delay(800)
    return "John Doe"
}

suspend fun fetchProductPrice(): Double {
    delay(1200)
    return 999.99
}

suspend fun fetchProductRating(): Double {
    delay(500)
    return 4.5
}

fun fetchUserData() = runBlocking {
    val start = System.currentTimeMillis()
    val userNameDeferred = async { fetchUserName() }.await()
    val userAgeDeferred = async { fetchUserAge() }.await()
    //val userName = userNameDeferred.await()  // Both running concurrently
    //val userAge = userAgeDeferred.await()    // Total: ~1500ms ✅
    val end = System.currentTimeMillis()
    println("Total time: ${end - start} ms")
    println("User: ${userNameDeferred}, Age: $userAgeDeferred")
}

suspend fun fetchUserName(): String {
    delay(1000)
    return "John Doe"
}

suspend fun fetchUserAge(): Int {
    delay(1500)
    return 25
}

fun controlledExecution() = runBlocking {
    val coroutineA = launch {
        println("Coroutine A: Starting long operation")
        delay(3000)
        println("Coroutine A: Completed")
    }
    val coroutineB = launch {
        println("Coroutine B: Starting short operation")
        delay(1000)
        println("Coroutine B: Completed")
    }
    coroutineA.join()
    println("Coroutine A has finished, now continuing...")
}


fun multipleTasks() = runBlocking {

    launch {
        println("Coroutine 1 starting")
        delay(1000)
        println("Coroutine 1: delay 1000ms")
        println("Coroutine 1 completed")
    }

    launch {
        println("Coroutine 2 starting")
        delay(2000)
        println("Coroutine 2: delay 2000ms")
        println("Coroutine 2 completed")
    }

    launch {
        println("Coroutine 3 starting")
        delay(1500)
        println("Coroutine 3: delay 1500ms")
        println("Coroutine 3 completed")
    }

    println("All coroutines launched!")
}

fun millionThread() = runBlocking{
     (1..1000000).forEach {
         thread(start = true) {
             print(it)
             Thread.sleep(1000L)
         }
     }
 }


