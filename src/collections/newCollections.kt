package collections

fun main(){
    //one()
    two()
    three()

}
fun three(){
    val numbers = listOf(1, 2, 2, 3, 3, 3, 4)
    val numbersWords = numbers.groupBy { it.countOneBits() }
    println(numbersWords)

}
fun two(){
    val words = listOf("apple", "banana", "kiwi", "avocado", "cherry")
    val newWords = words.groupBy { it.first() }
    //val result = words.associateBy { it.first() }Map first letter → first matching word
    println(newWords)
}

fun one(){
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    //val newList = numbers.filter { it%2 == 0 } .map { it * 2 }
    val newList = numbers.mapNotNull {
        if (it % 2 == 0) it * it else null
    }
    val doubled = numbers.map { it * 2 }
    println(newList)
    println(doubled)
}