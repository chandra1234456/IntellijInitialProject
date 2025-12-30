import java.io.Serializable
import kotlin.io.println


fun main() {
    println("Hello Coroutines")
    val list = mutableListOf("Hello", "Hiii",2,4,1.05,4.050,4.60)
    val listTwo = mutableListOf<Any>("Hello", "Hiii",2,4,1.05,4.050,4.60)
    listItem(list)
    listItemTwo(listTwo)
}

fun listItemTwo(listTwo: MutableList<Any>) {
    println(listTwo)
}

fun <T> listItem(list: List<T>) {
    for (i in list) {
        println(i)
    }
}

