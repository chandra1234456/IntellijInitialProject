import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

//Coroutines provide a lightweight way to handle async tasks using suspend functions.
// They follow structured concurrency, support cancellation, and integrate well with Android lifecycle using viewModelScope and lifecycleScope.
//1️⃣3️⃣ Common Interview Questions (With Answers)
//❓ Why coroutines better than threads?
//✔ Lightweight, structured, non-blocking
//❓ launch vs async?
//✔ launch → no result
//✔ async → result with await()
//❓ Why GlobalScope is bad?
//✔ Lives forever → memory leaks
//❓ What happens if ViewModel cleared?
//✔ viewModelScope cancels coroutines

//  Builder        use case                   return
//runBlocking ---- Use Case(Testing / main) -- returns (Blocks thread)
//async ----Get result ------Deferred<T> using await
//launch ---  Fire & forget ------Job

//runBlocking blocks the main thread until all coroutines inside it finish.
/*suspend fun main(){ //= runBlocking {
    //launch is not a top-level function
    //It must be called inside a CoroutineScope
    //main() finishes immediately → program exits → coroutine never runs
   // launch is an extension function of CoroutineScope.
    //Without a scope, Kotlin doesn’t know who controls the coroutine lifecycle.

    //Why println("Main thread") prints but coroutine doesn’t?
    //main() finishes immediately
    //JVM exits
    //Coroutine never gets CPU time
    /*launch {
        delay(1000)
        println("Hello from coroutine ${Thread.currentThread().name}")
    }*/
    //println("Main thread")
   // Why not use GlobalScope?
   // GlobalScope.launch { ... } // ❌
    //✔ Lives forever
    //✔ Causes memory leaks
    //✔ Not lifecycle aware

    //val result = fetchData()
    //println(result)
   //val string = loadData()
  //  println(string)
    data()
    //println("Hello"+20+30)
    //println(""+20+"Hello")
    //testCoroutineScope()
    testSupervisorScope()


}*/
//"End" is not printed because GlobalScope.launch is asynchronous and the parent function finishes before the delay completes, so the coroutine is cancelled when the process ends.
/*@OptIn(DelicateCoroutinesApi::class)
suspend fun data() {
    GlobalScope.launch(Dispatchers.IO) {
        println("Start")
        delay(2000)
        println("End")
    }
}*/
suspend fun data() = coroutineScope {
    launch(Dispatchers.IO) {
        println("Start")
        delay(2000)
        println("End")
    }
}
//Core difference (one line)
//supervisorScope → If one child fails, others continue(
//Follows structured concurrency
//Failure is propagated
//Parent cancels all child coroutines if any one fails)

//coroutineScope → If one child fails, all children are cancelled
//coroutineScope waits for all child coroutines to finish(
// Uses SupervisorJob
//Failure is isolated
//One child failing does NOT cancel others)
//===TABLE
//| Feature                | coroutineScope                 | supervisorScope            |
//| ---------------------- | ------------------------------ | -------------------------- |
//| Failure handling       | Cancels all children           | Only failing child cancels |
//| Job type               | Job                            | SupervisorJob              |
//| Structured concurrency | Yes                            | Yes                        |
//| Use case               | All tasks depend on each other | Tasks are independent      |
//| Android usage          | Default choice                 | When partial failure is OK |

//When to use what? 🤔
//Use coroutineScope when:
//All tasks are related
//One failure should cancel everything
//📌 Example: Loading screen data
//
//Use supervisorScope when:
//Tasks are independent
//One failure should not affect others
//📌 Example: Multiple API calls where partial data is acceptable
//coroutineScope cancels all child coroutines if any one fails, while supervisorScope isolates failures so other children continue executing.
suspend fun testCoroutineScope() = coroutineScope {
    launch {
        delay(1000)
        println("Child 1 done")
    }

    launch {
        delay(500)
        throw Exception("Child 2 failed")
    }
}
suspend fun testSupervisorScope() = supervisorScope {
    launch {
        delay(1000)
        println("Child 1 done")
    }

    launch {
        delay(500)
        throw Exception("Child 2 failed")
    }
}

suspend fun loadData(): String =
    withContext(Dispatchers.IO) {
        delay(500)
        "Data Loaded"
    }

//withContext is very important for interviews because it shows you understand thread switching without launching a new coroutine.
//withContext changes the coroutine context (dispatcher) and suspends the current coroutine until the block completes, then returns the result.
//✔ Does NOT create a new coroutine
//✔ Used for thread switching
//✔ Structured concurrency friendly
//✅ Correct
/*withContext(Dispatchers.Default) {
    heavyWork()
}*/
/*viewModelScope.launch {

    val data = withContext(Dispatchers.IO) {
        loadFromDb()
    }

    val processed = withContext(Dispatchers.Default) {
        processData(data)
    }

    withContext(Dispatchers.Main) {
        showData(processed)
    }
}
Main → IO → Default → Main
*/
/*❌ Wrong
launch(Dispatchers.Default) {
    heavyWork()
}*/
fun calculateSum(): Int {
    var sum = 0
    for (i in 1..1_000_000) {
        sum += i
    }
    return sum
}


//suspend functions can pause and resume without blocking the thread.
suspend fun fetchData(): String {
    delay(1000)
    return "Data Loaded"
}

fun listItemTwo(listTwo: MutableList<Any>) {
    println(listTwo)
}

fun <T> listItem(list: List<T>) {
    for (i in list) {
        println(i)
    }
}

fun performTask(onComplete: (result: String) -> Unit, onReceived: (receivedResult: String) -> Unit) {
    // Perform some long-running or asynchronous work
    println("onComplete Task started...")
    // ... when finished ...
    val result = "Task finished successfully!"
    println("onReceived Task started...")
    val received = "Received Task finished successfully!"
    onComplete(result) // Invoke the callback
    onReceived(received)
}









