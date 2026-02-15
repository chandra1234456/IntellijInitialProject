

//https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/
//https://kotlinlang.org/docs/collection-operations.html#common-operations

fun main() {
    val stringList = listOf("one", "two", "one")
    printAll(stringList)

    val stringSet = setOf("one", "two", "three")
    printAll(stringSet)
}

fun printAll(strings: Collection<String>) {
    for(s in strings) print(s)
    println()
}