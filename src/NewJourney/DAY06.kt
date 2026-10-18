package NewJourney


fun main(){
    /*Find the largest and second-largest elements in an array.
    Count each character in a string.
    Remove duplicate elements from an array.
    Check whether a number is prime.
    Print the Fibonacci series.
    Find missing numbers in a sequence.
    Find the first non-repeating character.
    Reverse a string without using built-in reverse methods.
    Check whether two arrays are equal.
    Find the frequency of each number in an array.*/
    //print(findLargestAndSecondLargesElementInArray(arrayListOf(1,10,20,5,6,30,2,3)))
    //reverseString("madam")
   // checkWhetherTwoArraysAreEqual(arrayListOf(1,10,20,5,6,30,2,3),arrayListOf(1,10,20,5,6,30,2,3))
    //checkWhetherTwoArraysAreEqual(arrayOf(1,10,20,6,6,30,2,3),arrayOf(1,10,20,5,6,30,2,3))
    //characterCount("swiss")
    //println(hashPattern(listOf(4, 7, 2, 7, 9)))
    //println(setPattern(listOf(2, 4, 2, 6, 4, 8)))
   // twoSum(listOf(11, 15,7,2),9)
    //firstUniqueCharacterInString("loveleetcode")
    //Contains Duplicate II
    //Longest Consecutive Sequence
    IntersectionTwoArrays(listOf(4, 9, 5),listOf(9, 4, 9, 8, 4))

}
fun IntersectionTwoArrays(list1 :List<Int>,list2 :List<Int>){
    val intersectionList = mutableListOf<Int>()
    for(i in list1.indices){
        for (j in list2.indices){
            if (list1[i]==list2[j]){
                if (list2[j] !in intersectionList) {
                    intersectionList.add(list2[j])
                }
            }
        }
    }
    print(intersectionList.joinToString())
}
fun firstUniqueCharacterInString(inputString: String){
    for (i in inputString.indices){
    var count = 0
        for (j in  inputString.indices){
            if (inputString[i]==inputString[j]) {
                count++
            }
        }
        if (count==1){
            print(inputString[i])
            return
        }
    }


}
fun twoSum(list : List<Int>,target:Int){
      for (i in list.indices){
          for (j in i+1 until list.size){
              if (list[i]+list[j]==target){
                  print("index $i & $j $target")
              }
          }
      }
}
fun setPattern(list :List<Int>): Int{
    val unique = mutableSetOf<Int>()
    val notPresent = mutableSetOf<Int>()
    for(i in list.iterator()){
        if(unique.contains(i)) {
            unique.add(i)
        }else{ notPresent.add(i) }
    }
    return notPresent.size
}
fun hashPattern(list :List<Int>): HashSet<Int>{
    val seen = HashSet<Int>()
    val duplicates = HashSet<Int>()
    for(i in list){
        if(!seen.contains(i)) {
            seen.add(i)
        }else{ duplicates.add(i) }
    }
    return duplicates
}
fun characterCount(inputString: String) {
    val charCountMap = HashMap<Char, Int>()

    for (c in inputString) {
        charCountMap[c] = charCountMap.getOrDefault(c, 0) + 1
    }

    for ((key, value) in charCountMap) {
        println("$key $value")
        return
    }
}
fun checkWhetherTwoArraysAreEqual(list1:ArrayList<Int>,list2 : ArrayList<Int>){
    if (list1.size!=list2.size)return
    if (list1==list2)print("Done")
    //list1.contentEquals()

}
fun checkWhetherTwoArraysAreEqual(list1:Array<Int>,list2 : Array<Int>){
    if (list1.size!=list2.size)return
    //if (list1==list2)print("Done")
    print(list1.contentEquals(list2))

}
fun reverseString(input :String){
    var reversedValue =""
    for (i in input.lastIndex downTo 0){
        reversedValue+=input[i]
    }
}

fun findLargestAndSecondLargesElementInArray(list : ArrayList<Int>): Pair<Int,Int>{
    for (i in list.indices){
        for (j in i+1 until list.size){
            if (list[i]<list[j]){
               val tempValue = list[i]
                list[i] = list[j]
                list[j]= tempValue
            }
        }
    }
    return list[0] to list[1]
}