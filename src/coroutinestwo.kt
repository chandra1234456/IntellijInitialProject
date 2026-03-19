import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope


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
}