package NewJourney

fun main(){
    println(reverseStrings("Hello Kotlin"))
    println(palendromString("racecar"))
    println(anagram("geeks","kseeg"))
    secondLargest(listOf(1,2,3,4,5))
    findDuplicate(listOf(1,2,3,4,5,4,5))
    removeDuplicateCharactersFromAString("HelleHoo")
    sortListStringsByLength(arrayListOf("He","two","thre","a"))
    findTwoSum(listOf(2,11,15,7),9)
    findTwoSum(listOf(3,2,4),6)
    findTwoSum(listOf(3,3),6)
}

fun findTwoSum(list : List<Int>,target :Int){
   for(i in list.indices){
       for(j in i+1 until list.size) {
           if (list[i]+list[j]==target){
               println("Two sum Value Positions $i $j ")
           }
       }
   }
}

fun sortListStringsByLength(list : ArrayList<String>){
    for (i in list.indices){
        for (j in i+1 until list.size){
            if (list[i].length>list[j].length){
                val temp = list[i]
                list[i] = list[j]
                list[j] = temp
            }
        }
    }
    println(list.joinToString())
}
fun removeDuplicateCharactersFromAString(input : String){
    val arr = input.toCharArray()
    var unquieString = ""
    var duplicateString = ""
    for (char in arr){
        if (!unquieString.contains(char)){
            unquieString+=char
        }else{
            duplicateString+=char
        }
    }
    println(unquieString)
    println(duplicateString)
}

fun findDuplicate(list : List<Int>){
    val arr = list.toMutableList()
    val duplicates = mutableListOf<Int>()
    val unique = mutableListOf<Int>()
    for(item in arr){
        if (!unique.contains(item)){
            unique.add(item)
        }else{
            duplicates.add(item)
        }
    }
    println(duplicates.joinToString())
}

fun secondLargest(list : List<Int>){
    val arr = list.toMutableList()
    for (i in arr.indices){
        for (j in i+1 until arr.size){
            if (arr[i]>arr[j]){
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }
    }
    println(arr.joinToString())
    println(arr[1])
}
fun reverseStrings(input :String): String{
    var reverseString = ""
    for (i in input.lastIndex downTo 0){
        reverseString +=input[i]
    }
    return reverseString
}
fun palendromString(input :String): Boolean{
    var reversedValue = ""
    for(i in input.lastIndex downTo 0){
        reversedValue+=input[i]
    }
    return reversedValue ==input
}
fun anagram(input1: String,input2 : String): Boolean{
    if (input1.length != input2.length)return false
    val order0 = sortTheStringAlphabeticalOrder(input1)
    val order1 = sortTheStringAlphabeticalOrder(input2)
    println("Order0 $order0 and Order1 $order1")
    return order0 == order1
}
fun sortTheStringAlphabeticalOrder(input: String): String{
    val chars = input.toCharArray()
    for (i in chars.indices){
        for (j in i+1 until chars.size){
            if (chars[i]>chars[j]){
                val temp = chars[i]
                chars[i] = chars[j]
                chars[j] = temp
            }
        }
    }
    return chars.concatToString()
}

