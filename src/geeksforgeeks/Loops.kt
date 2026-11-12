package geeksforgeeks


fun main() {
    removeDuplicatesFromSorted(arrayListOf(1, 2, 2, 3, 4, 4, 4, 5, 5))
    arrayReverse(arrayListOf(1, 2, 2, 3, 4, 4, 4, 5, 5))
    moveAllZerostoEndofArray(arrayListOf(1, 0, 3, 0, 5, 2))
    moveAllZerostoEndofArrayNew(arrayListOf(1, 0, 3, 0, 5, 2))
    removeDuplicateCharactersFromAString("Heelloo")
}
fun removeDuplicateCharactersFromAString(input : String){
     val newCharArray = input.toCharArray()
     var uniqueString = ""
     for(i in newCharArray){
         if (i !in uniqueString){
             uniqueString+=i
         }
     }
    println(uniqueString)
}
fun moveAllZerosToEnd(newList: ArrayList<Int>) {
    var index = 0
    for (i in newList.indices) {
        if (newList[i] != 0) {
            val temp = newList[i]
            newList[i] = newList[index]
            newList[index] = temp
            index++
        }
    }

    println(newList.joinToString())
}
fun moveAllZerostoEndofArray(newList: ArrayList<Int>){
    for (i in newList.indices){
       for (j in i+1 until newList.size){
           if (newList[i]<newList[j]){
               val temp = newList[j]
               newList[j] = newList[i]
               newList[i] = temp
           }
       }
    }
    println(newList.joinToString())
}
fun moveAllZerostoEndofArrayNew(newList: ArrayList<Int>){
    val allZeroList = ArrayList<Int>()
    val nonZeroList = ArrayList<Int>()
    for (i in newList){
        if (i == 0){
            allZeroList.add(i)
        }else {
            nonZeroList.add(i)
        }

    }
    println(nonZeroList+allZeroList.joinToString())
}

fun arrayReverse(newList: ArrayList<Int>){
    val reverseList = ArrayList<Int>()
    for(i in newList.lastIndex downTo 0){
        reverseList.add(newList[i])
    }
    println(reverseList.joinToString())
}
fun removeDuplicatesFromSorted(newList: ArrayList<Int>){
    val distinctList = ArrayList<Int>()
    for (i in newList){
        if (i !in distinctList){
            distinctList.add(i)
        }
    }
   println(distinctList.joinToString())
}
