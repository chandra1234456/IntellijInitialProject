package loops

fun main() {
   // val reversString = reverseStringValue("Hello")
    val reversArray = arrayOf(1,2,3,4,5,6,7,8,9,10)
    reverseArrayValues(reversArray)
    // print("Reverse String $reversString")
    val a = "abc"
    val b = "abc"
    //Both "abc" literals are stored in the String Pool
    //a and b refer to the same memory object
    // String Pool is a special memory area where JVM stores string literals so that identical strings share the same memory location.
    // print(a==b)
    //print(a===b)
    //String is a built-in immutable class in Kotlin used to represent text.
    //Strings is not a Kotlin data type and does not exist unless defined by the developer.
   // One text = String
     //Many texts = List<String>
}

fun reverseArrayValues(reversArray: Array<Int>) {
    //i Is Position (item Position)
    // reversArray[i] -> represents value of Respective Position
    for (i in reversArray.indices) {
        println("I Value $i")
        println("list ${reversArray[i]}")

    }
}

fun reverseStringValue(value: String): String {
    if (!value.isNullOrBlank()) {
        // length → count
        //  lastIndex → position
        val length = value.length
        for (i in value.lastIndex downTo 0)
            println(" ${value[i]}")
        for (i in value.indices) {
            println(value[i])
        }
    } else {
        return "String Should Not Empty or null"
    }
    return ""
}
