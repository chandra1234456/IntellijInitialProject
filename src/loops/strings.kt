package loops
//https://coderpad.io/interview-questions/kotlin-interview-questions/
fun main() {
   // val reversString = reverseStringValue("Hello")
    val reversArray = arrayOf(1,2,3,4,5,6,7,8,9,10)
   // reverseArrayValues(reversArray)
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
    val sentence = "Kotlin is easy to learn"
    val words = sentence.split(" ")
    val countTheWords = reverseWords(sentence)
    println(sentence.filter { !it.isWhitespace() })
    compressAString("aaabbcc")
    removeDuplicateCharacters("aaabbcc")
}

fun reverseWord(string: String) {
    for (i in string.lastIndex downTo 0){
        print(string[i])
    }
}

fun reverseWords(sentence: String) {
    val eachWord = sentence.split(" ")
    val pleaseAddTheWords = ""
    for (i in eachWord.lastIndex downTo 0){
        println(eachWord[i])
        pleaseAddTheWords+i
    }
    println(pleaseAddTheWords)
}



fun reverseArrayValues(reversArray: Array<Int>) {
    //i Is Position (item Position)
    // reversArray[i] -> represents value of Respective Position
    for (i in reversArray.lastIndex downTo 0) {
        println(reversArray[i])
    }
}

fun reverseStringValue(value: String): String {
    if (value.isNotBlank()) {
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
fun compressAString(input: String) {
    if (input.isEmpty()) {
        println("Compressed String: ")
        return
    }

    var compressedString = ""
    var count = 1

    for (i in 1 until input.length) {
        if (input[i] == input[i - 1]) {
            count++
        } else {
            compressedString += input[i - 1].toString() + count
            count = 1
        }
    }

    // Add last character group
    compressedString += input.last().toString() + count

    println("Compressed String: $compressedString")
}
/*fun removeDuplicateCharacters(input: String) {
    val result = input.toSet().joinToString("")
    println("Removed Characters $result")
}
fun removeDuplicateCharacters(input: String) {
    println("Removed Characters ${input.distinct().joinToString("")}")
}*/

fun removeDuplicateCharacters(input: String) {
    val seen = mutableSetOf<Char>()
    val result = StringBuilder()

    for (char in input) {
        if (char !in seen) {
            seen.add(char)
            result.append(char)
        }
    }

    println("Removed Characters $result")
}