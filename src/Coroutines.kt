import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
fun main() = runBlocking {
    //launch is not a top-level function
    //It must be called inside a CoroutineScope
    //main() finishes immediately → program exits → coroutine never runs
   // launch is an extension function of CoroutineScope.
    //Without a scope, Kotlin doesn’t know who controls the coroutine lifecycle.

    //Why println("Main thread") prints but coroutine doesn’t?
    //main() finishes immediately
    //JVM exits
    //Coroutine never gets CPU time
    launch {
        delay(1000)
        println("Hello from coroutine ${Thread.currentThread().name}")
    }
    println("Main thread")
   // Why not use GlobalScope?
   // GlobalScope.launch { ... } // ❌
    //✔ Lives forever
    //✔ Causes memory leaks
    //✔ Not lifecycle aware

    val result = fetchData()
    println(result)
   val string = loadData()
    println(string)
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
/*fun cubes(nums :Int)= sequence {
    repeat(nums){
        yield(it*it*it)
    }
}
fun main(){
    cubes(10).forEach{
        println(it)
    }
}*/


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









