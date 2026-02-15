package loops


fun main() {
    val returnString = reverseBasicString("Kotlin")
    println("Reverse Value :: $returnString")

    val isPalindrome = checkGiveStringIsPalindrome("Madam")
    println("Palindrome Value :: $isPalindrome")

    val count = countVowelsGivenString("Hello World")
    println("Count Vowels :: $count")

    val countChars = countCharactersWithoutSpaces("Hello World")
    println("Count Chars :: $countChars")

    val lowerCase = convertToUpperCaseAndLowerCase("Hello World")
    println("Lower Case :: $lowerCase")

    val upperCase = convertToLowerCaseAndUpperCase("Hello World")
    println("Upper Case :: $upperCase")

    val nonRepeating = findFirstNonRepeatingCharacter("aabbcc")
    println("Non Repeating :: $nonRepeating")

    val removeAllWhitespaces = removeAllWhitespaces("Kotlin is fun")
    println("remove All Whitespaces :: $removeAllWhitespaces")

    val replaceCharacter = replaceACharacter("banana", 'A', 'o')
    println("replace Character :: $replaceCharacter")

    val countOccurrencesOfACharacter = countOccurrencesOfACharacter("banana", 'a')
    println("Occurrences Character :: $countOccurrencesOfACharacter")


    val checkAnagram = checkAnagram("listen", "sirment")
    println("check Anagram  :: $checkAnagram")

    println("Sort String Manually :: ${sortStringManually("bbbbaaaaa")}")


}

fun checkAnagram(string: String, string2: String): Boolean {
    if (checkAnagramLength(string, string2)) {
        val stringOne = string.lowercase()
        val stringTwo = string2.lowercase()
        val sortedOne = stringOne.toCharArray().sorted().joinToString("")
        val sortedTwo = stringTwo.toCharArray().sorted().joinToString("")
        return sortedOne == sortedTwo
    }
    return false
}

fun sortStringManually(str: String): String {
    // Convert string to a char array
    val charArray = str.toCharArray()

    // Bubble sort
    for (i in 0 until charArray.size - 1) {
        for (j in 0 until charArray.size - i - 1) {
            if (charArray[j] > charArray[j + 1]) {
                // Swap characters
                val temp = charArray[j]
                charArray[j] = charArray[j + 1]
                charArray[j + 1] = temp
            }
        }
    }

    // Convert back to string
    return String(charArray)
}



fun checkAnagramLength(string: String, checkAnagramString: String): Boolean {
    return string.length == checkAnagramString.length
}

fun countOccurrencesOfACharacter(string: String, input: Char): Int {
    var inputOccurrences = 0
    for (i in string.indices) {
        if (string[i] == input) {
            inputOccurrences++
        }
    }
    return inputOccurrences
}

fun replaceACharacter(string: String, replace: Char, replaceWith: Char): String {
    return string.replace(replace, replaceWith, true)

}

fun removeAllWhitespaces(string: String): String {
    var removedAllWhiteSpaces = ""
    for (i in string.indices) {
        if (!string[i].isWhitespace()) {
            removedAllWhiteSpaces += string[i]
        }
    }
    return removedAllWhiteSpaces

}

fun findFirstNonRepeatingCharacter(string: String): Char {
    for (i in string.indices) {
        println("Index i: $i, Value: ${string[i]}")
        var isFound = false

        for (j in string.indices) {
            println("   Comparing with Index j: $j, Value: ${string[j]}")

            if (i != j && string[i] == string[j]) {
                println("   Duplicate found for '${string[i]}' at index $j")
                isFound = true
                break
            }
        }

        if (!isFound) {
            println("First non-repeating character is '${string[i]}' at index $i")
            return string[i]
        }
    }
    return '$'
}


fun convertToUpperCaseAndLowerCase(value: String): String {
    return value.lowercase()
}

fun convertToLowerCaseAndUpperCase(value: String): String {
    return value.uppercase()
}

fun countCharactersWithoutSpacesNew(value: String): Int {
    return value.count { !it.isWhitespace() }
}

fun countCharactersWithoutSpaces(value: String): Int {
    var countChar = 0
    for (i in value.indices) {
        if (!value[i].isWhitespace()) {
            // if (!Character.isWhitespace(value[i])) {
            //  println("chars ${value[i]}")
            countChar++
        }
    }
    return countChar
}


fun countVowelsGivenStringNew(value: String): Int {
    return value.count { it.lowercaseChar() in "aeiou" }
}

fun countVowelsGivenString(value: String): Int {
    var countVowels = 0
    for (i in value.indices) {
        if (value[i].lowercaseChar() in listOf('a', 'e', 'i', 'o', 'u')) {
            // if (value[i]=='a'||value[i]=='e'||value[i]=='i'||value[i]=='o'||value[i]=='u'){
            //println("vowels ${value[i]}")
            countVowels++
        }
    }
    return countVowels
}

fun checkGiveStringIsPalindrome(actualValue: String): Boolean {
    var reverseValue = ""
    for (i in actualValue.lastIndex downTo 0) {
        reverseValue += actualValue[i]
    }
    return reverseValue == actualValue
}

fun reverseBasicString(string: String): String {
    var reverseValue = ""
    for (i in string.lastIndex downTo 0) {
        reverseValue += string[i]
    }
    return reverseValue
}
