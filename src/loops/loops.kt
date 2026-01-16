package loops
  //Arrays
fun main(){
    //joinToString() is used to convert an array (or collection) into a readable String.
   // If you print an array directly, you don’t get its elements:
   // That’s the memory reference, not the values.
    //So joinToString() joins all elements into one string, separated by commas by default.
    val array = arrayOf(1,2,3,4,5,11,6,7,8,9,10)
      //Sorts the array in descending order (largest → smallest),Uses sorting algorithm
      //Order depends on values, not position
      //sortedArrayDescending() rearranges elements based on value order, while reversedArray() only reverses their existing positions.
    println(array.sortedArrayDescending().joinToString())
    //.sortedArray() is used to sort an array in ascending order (smallest → largest) and return a new array.
    println(array.sortedArray().joinToString())
      //Reverses the current order of elements
      //Keeps relative order, just flipped
    println(array.reversedArray().joinToString())
    firstArray(array)
      println("With Loop: ${reverseArray(array).joinToString()}")
      val newArray = arrayOf(1,2,3,11,4,5)
      reverseArrayWithoutLoop(newArray)   // Reverses the array in place
      println("Without Loop: ${newArray.joinToString()}")
      println(3.isOdd())
     val finalArray = twoSumArray(array,13)
      println("Two Sum ${finalArray.joinToString()}")
      val duplicates = arrayOf(1,2,3,4,5,11,6,7,1,3,4,8,9,10)
      val noDuplicates = removeDuplicates(duplicates)
      println("Removed Duplicates ${noDuplicates.joinToString()}")
      println("Removed Duplicates HASH ${removeDuplicatesHashSet(array).joinToString()}")
}
fun removeDuplicatesHashSet(arr: Array<Int>): IntArray {
    val set = HashSet<Int>()
    for (num in arr) {
        set.add(num)
    }

    return set.toIntArray()
}
fun removeDuplicatesNew(arr: Array<Int>): IntArray {
    val seen = HashSet<Int>()
    val result = ArrayList<Int>()

    for (num in arr) {
        if (!seen.contains(num)) {
            seen.add(num)
            result.add(num)
        }
    }
    return result.toIntArray()
}
fun removeDuplicatesOne(arr: Array<Int>): IntArray {
    val result = ArrayList<Int>()
    for (i in arr.indices) {
        var isDuplicate = false
        //0 until i Previous elements Remove duplicates / keep unique

        for (j in 0 until i) {
            if (arr[i] == arr[j]) {
                isDuplicate = true
                break
            }
        }
        //We only want to add an element once, the first time it appears.
        if (!isDuplicate) {
            result.add(arr[i])
        }
    }

    return result.toIntArray()
}




fun removeDuplicates(duplicates: Array<Int>): IntArray {
    val result = ArrayList<Int>()
    for (i in duplicates.indices){
       // i + 1 until size Next elements Find duplicates
       for (j in i + 1 until duplicates.size){
            if (duplicates[i]==duplicates[j]){
                println("Duplicate Element ${duplicates[i]}")
            }
       }
    }
    return intArrayOf()//No duplicates
}

fun twoSumArray(array: Array<Int>, target: Int): IntArray {
    val hashMap = HashMap<Int,Int>()

    for(i in array.indices){
        val completedValue = target -array[i]
        if (hashMap.containsKey(completedValue)){
           return intArrayOf(hashMap[completedValue]!!,i)
        }
        hashMap[array[i]] = i
    }
  return intArrayOf()
}

fun Int.isOdd(): Boolean {
     return this % 2 == 0
}

fun reverseArray(array: Array<Int>): Array<Int> {
    val result = Array(array.size) { 0 }

    for ((index, value) in array.withIndex()) {
        result[array.size - 1 - index] = value
    }
    return result
}
fun reverseArrayWithoutLoop(array: Array<Int>){
    var startPosition = 0
    var lastPosition = array.size-1
     while(startPosition<lastPosition){
         val tempArray = array[startPosition]
         array[startPosition] = array[lastPosition]
         array[lastPosition] = tempArray
         startPosition ++
         lastPosition --
     }
}

fun firstArray(array: Array<Int>) {
    //indices means the valid index range of an array or collection.
    //indices returns all valid index numbers of an array(from 0 to size - 1)
   // indices is a Kotlin property that returns the valid index range of a collection, helping prevent out-of-bounds errors.
    for (i in array.indices){
       // println(i)
     //   println("index $i , respective Value ${array[i]}")
    }
    //In Kotlin, withIndex() is a function used to iterate over a collection while getting both the index and the value at the same time.
    //withIndex() returns pairs of (index, value) for each element in a collection.
    //Prevents ArrayIndexOutOfBoundsException
    //withIndex() is used to iterate over a collection while accessing both index and value together.
    for ((index, value) in array.withIndex()) {
     //   println("index $index value $value")
    }
}
