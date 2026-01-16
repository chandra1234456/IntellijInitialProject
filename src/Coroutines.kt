

fun main() {
    println("Hello Coroutines")
    val list = mutableListOf("Hello", "Hiii", 2, 4, 1.05, 4.050, 4.60)
    val listTwo = mutableListOf<Any>("Hello", "Hiii", 2, 4, 1.05, 4.050, 4.60)
    listItem(list)
    listItemTwo(listTwo)
    performTask(
        onComplete = { receivedResult ->
            println("Callback received receivedResult: $receivedResult")

        }, onReceived = { receivedResult ->
            println("Callback received receivedResult: $receivedResult")

        }
    )
    val c1 = 'a' + 1
    val c2 = 'a' + 25
    val c3 = 'E' - 2
    println("c1 $c1")
    println("c2 $c2")
    println("c3 $c3")
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









