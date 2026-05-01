package collections

/*fun main() {
 //   collectionBasics()
    listTransformations()
    val map = buildMap { // this is MutableMap<String, Int>, types of key and value are inferred from the `put()` calls below
        put("a", 1)
        put("b", 0)
        put("c", 4)
    }

    println(map) // {a=1, b=0, c=4}
}*/
fun listTransformations(){
    val list = listOf("apple", "banana", "cherry", "date", "elderberry")
    println("Keep only fruits with length > 5 characters  ${list.filter { it.length>5 }}")
    println("Convert each fruit to uppercase  ${list.map { it.uppercase() }}")
    println("shuffle() ${list.shuffled()}")
    println("Create a map where key = fruit name, value = length of fruit ${list.map { it.groupBy {it } }}")
    println("Group fruits by their first letter ${list.groupBy { it.first() }}}")
    println("Group fruits by their last letter ${list.groupBy { it.last() }}}")
    println("For GroupBy, ${list.groupBy { it }}}")
    println("For GroupBy, print each group like: ${list.groupBy { "Group ${it.first()} : $it" }}}")
   // println("getShortWordsTo ${list.filterTo()}")
    //println("Convert each fruit to a list of its characters (flattened)  ${list.filter { it }.flatMap { it }}")
}

fun collectionBasics() {
    val list = listOf( 5, 2, 8, 1, 9, 3, 7, 4, 6)
    println("Get all numbers greater than 5  ${list.filter { it>5 }}")
    println("Square each number (multiply by itself) ${list.map { it*it }}")
    println("Sort the list in ascending order ${list.sorted()}")
    println("Sort the list in descending order ${list.sortedDescending()}")
    println("Print first and last element ${list.first()}")
    println("Print first and last element ${list.last()}")
    println("Calculate sum of all numbers ${list.sum()}")
    println("Calculate average (as Double) ${list.average()}")


    val mutableList = mutableListOf( 5, 2, 8, 1, 9, 3, 7, 4, 6)
    println("Add the number 10 to it ${mutableList.add(10)}")
    println("Remove the number 3 from it ${mutableList.remove(3)}")
    println("Print the final mutable list $mutableList")


}


data class Course(val name: String)

class OddList<T>(val list: List<T>) {
    fun oddItems(): List<T> {
        return list.filterIndexed { index, _  -> index % 2 == 1 }
    }
}

fun main() {
    val listOfStrings = listOf("Kotlin", "Java", "C#")
    val resultOfStrings: OddList<String> = OddList(listOfStrings)
    println(resultOfStrings.oddItems())

    val listOfInts = listOf(1, 7, 8, 9, 12, 45)
    val resultOfInts = OddList(listOfInts)
    println(resultOfInts.oddItems())

    val courses = listOf(
        Course("Kotlin"),
        Course("Java"),
        Course("C#"),
        Course("PHP"),
        Course("C++")
    )
    val resultCourses = OddList(courses).oddItems()
    println(resultCourses)
    listIterator()
}

fun listIterator(){
    val numbers = listOf("one", "two", "three", "four")
    val listIterator = numbers.listIterator()
    while (listIterator.hasNext()) listIterator.next()
    println("Iterating backwards:")
// Iterating backwards:
    while (listIterator.hasPrevious()) {
       // print("Index: ${listIterator.previousIndex()}")
        print(listIterator.previous())
        // Index: 3, value: four
        // Index: 2, value: three
        // Index: 1, value: two
        // Index: 0, value: one
    }
}