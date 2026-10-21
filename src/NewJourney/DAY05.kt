package NewJourney

fun main() {
    println(countUniqueNumbers(listOf(2, 4, 2, 6, 4, 8)))
    println(findFirstRepeatedNumber(listOf(5, 3, 8, 3, 5)))
    println(findDuplicates(listOf(1, 2, 3, 2, 4, 1, 2, 5)))
    println(frequencyCounting(listOf(2, 3, 2, 4, 3, 2)))
    println(firstNonRepeatingNumber(listOf(4, 5, 4, 6, 6,5, 7,7)))
    println(mostFrequentNumber(listOf(2,3,3, 2, 4, 3, 2)))
}
fun mostFrequentNumber(list: List<Int>): Int? {
    val frequency = HashMap<Int, Int>()
    if (list.isEmpty()) return null
    if (list.size == 1) return list[0]

    for (number in list) {
        frequency[number] = frequency.getOrDefault(number, 0) + 1
    }

    var mostFrequent: Int? = null
    var maxCount = 0

    for (number in list) {
        val count = frequency[number]!!
        if (count > maxCount) {
            maxCount = count
            mostFrequent = number
        }
    }
    return mostFrequent
}
fun firstNonRepeatingNumber(list : List<Int>):Int?{
    val frequency = HashMap<Int, Int>()
     if (list.isEmpty()) return null
     if (list.size ==1) return list[0]
     for (number in list){
         if (frequency.contains(number)){
             frequency[number] = frequency[number]!! + 1
         }else{
             frequency[number] = 1
         }
     }
    for (number in list) {
        if (frequency[number] == 1) {
            return number
        }
    }
     return null
}

fun frequencyCounting(list : List<Int>):Map<Int, Int>{
    val frequency = HashMap<Int, Int>()
    for (number in list) {
        if (frequency.containsKey(number)) {
            frequency[number] = frequency[number]!! + 1
        } else {
            frequency[number] = 1
        }
    }
    return frequency
}


fun findDuplicates(list: List<Int>): Set<Int> {
    val seen = mutableSetOf<Int>()
    val duplicates = mutableSetOf<Int>()
    for (n in list) {
        if (!seen.add(n)) duplicates.add(n)
    }
    return duplicates
}

fun countUniqueNumbers(list : List<Int>): Int{
    val hashList = mutableSetOf<Int>()
    for (i in list.indices){
      hashList.add(list[i])
    }
    return hashList.size
}

fun findFirstRepeatedNumber(list: List<Int>): Int? {
    val seen = mutableSetOf<Int>()
    for (n in list) {
        if (!seen.add(n)) return n
    }
    return null
}

