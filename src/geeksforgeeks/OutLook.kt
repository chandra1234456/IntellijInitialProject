package geeksforgeeks


//https://neetcode.io/courses/ood-interview/0
fun main(){
    reverseAString("Hello")
    reverseWordSentence("I love learning programming")
    palenDrome("madamto")
    checkAnagramStrings("listen","silent")
    findFirstNonRepeatedCharacter("HelloHeo")
    findDuplicateCharacters("HelloHe")
    countVowelsString("Hellaiu")
    findMaximumElement(arrayListOf(10,2, 4, 6, 8, 1))
    findSecondLargestElement(arrayListOf(10,2, 4, 6, 8, 1))
    findThirdMin(arrayListOf(10,2, 4, 6, 8, 1))
    findMissingCharacterInString("Abcdefghijklmnopqrstuvwxyz")
    findCommonElementsTwoArrays(arrayListOf(1, 2, 3, 4, 5) ,arrayListOf(3, 4, 5, 6, 7))
    findMaximumProductTwoIntegers(arrayListOf(-10, -5, 2, 8, 3))
    firstNonRepeatingInAnArray(arrayListOf(1, 2, 1, 3,3, 2))
    mostFrequentInArray(arrayListOf(1, 2, 2, 2, 4, 1))
}
fun mostFrequentInArray(list: ArrayList<Int>){
    var maxCount = 0
    var result = 0
    for(i in list.indices){
        var count = 0
        for(j in list.indices){
            if (list[i]==list[j]){
                count++
            }
        }
        if (count>maxCount ||(count == maxCount && list[i]>result)){
            maxCount =count
            result =list[i]
        }
    }
    println(result)
}
fun firstNonRepeatingInAnArray(list: ArrayList<Int>) {
    for(i in list.indices){
        var isSingleCharFind = false
        for(j in list.indices){
            if (i != j && list[i]==list[j]){
                isSingleCharFind = true
                break
            }
        }
        if (!isSingleCharFind){
            println("Non-Repeating ${list[i]}")
            return
        }
    }
    println("No non-repeating element found")
}

fun findMaximumProductTwoIntegers(list: ArrayList<Int>){
    var maxProductInteger = 0
    for (i in list.indices){
        for(j in i+1 until list.size){
            if (maxProductInteger < list[i]*list[j]){
                maxProductInteger = list[i]*list[j]
            }
        }
    }
    println(maxProductInteger)
}
fun findCommonElementsTwoArrays(list1: ArrayList<Int>,list2: ArrayList<Int>){
    val commonElements = ArrayList<Int>()
    for (i in list1){
        if (i in list2){
            commonElements.add(i)
        }
    }
    println(commonElements)
}
fun findMissingCharacterInString(input :String){
    var missingChars = ""
    val totalChars = "abcdefghijklmnopqrstuvwxyz"
    val lowercaseInput = input.lowercase()
    for (i in totalChars){
        if (i !in lowercaseInput){
            missingChars +=i
        }
    }
    println(missingChars)
}
fun findThirdMin(arr: ArrayList<Int>): Int {
    var first = Int.MAX_VALUE
    var second = Int.MAX_VALUE
    var third = Int.MAX_VALUE

    for (num in arr) {
        if (num < first) {
            third = second
            second = first
            first = num
        } else if (num < second && num != first) {
            third = second
            second = num
        } else if (num < third && num != second && num != first) {
            third = num
        }
    }

    return if (third == Int.MAX_VALUE) -1 else third
}
fun findSecondLargestElement(arr: ArrayList<Int>): Int {
    var largest = Int.MIN_VALUE
    var secondLargest = Int.MIN_VALUE

    for (num in arr) {
        if (num > largest) {
            secondLargest = largest
            largest = num
        } else if (num > secondLargest && num < largest) {
            secondLargest = num
        }
    }

    return secondLargest
}
fun findMaximumElement(list : ArrayList<Int>){
    var maxElement = list[0]
    for (i in 1 until list.size){
        if (maxElement>list[i]){
            maxElement=list[i]
        }
    }
    println(maxElement)
}

fun countVowelsString(input : String){
    val vowels = "aeiou"
    var countVowels = 0
    for (i in input){
        if (i in vowels){
            countVowels++
        }
    }
    println(countVowels)
}
fun findDuplicateCharacters(input : String){
    var duplicates = ""
    var uniques = ""
    for (i in input){
      if (i !in uniques){
          uniques+=i
      }else{
          duplicates+=i
      }
    }
    println(duplicates)
    println(uniques)
}
fun findFirstNonRepeatedCharacter(input1: String){
    for (i in input1.indices){
        var isSingleCharFind = false
        for (j in input1.indices){
            if (i != j && input1[i]==input1[j]){
                isSingleCharFind = true
                break
            }
        }
        if (!isSingleCharFind){
            println("Non-Repeated Character ${input1[i]}")
            return
        }
    }
    println("Non-Repeated Character 0")

}
fun checkAnagramStrings(input1: String,input2: String){
    sortTheAlphabets(input1)
    sortTheAlphabets(input2)
    if (sortTheAlphabets(input1)==sortTheAlphabets(input2)) println("Anagram")else println("Not Anagram")
}
fun sortTheAlphabets(input: String): String {
    val listString = input.toCharArray()
    for (i in listString.indices){
         for (j in 0 until listString.size-1){
              if (listString[i]<listString[j]){
                  val temp = listString[j]
                  listString[j] = listString[i]
                  listString[i] = temp
              }
         }
    }
    return String(listString)
}
fun palenDrome(input: String){
    var reverse = ""
    for (i in input.length-1 downTo 0){
        reverse +=input[i]
    }
    if (reverse ==input)println("Is Palendrome") else println("Is Not Palendrome")
}
fun reverseWordSentence(inPut: String){
    var reverse = ""
    val data = inPut.split(' ')
    for (i in data.size-1 downTo 0){
        reverse += data[i] + " "
    }
    println(data)
    println(reverse)
}
fun reverseAString(input:String){
    var reverse = ""
    for (i in input.length-1 downTo 0){
        reverse+=input[i]
    }
    println(reverse)
}