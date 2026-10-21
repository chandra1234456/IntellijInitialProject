package NewJourney

import kotlin.collections.set


fun main(){
    println(findKLargest(arrayListOf(10, 5, 8, 10, 3),2))
    println(findKLargest(arrayListOf(-5, -2, -9),2))
    println(findKLargest(arrayListOf(4, 4, 4),2))
    println(findKLargest(arrayListOf(1, 2, 3),2))
    println(findKLargest(arrayListOf(7),2))
    println(findFirstRepeatedNumberNew(listOf(4, 2, 7, 2, 4)))
}
fun findKLargest(numbers: ArrayList<Int>, targetPosition: Int): Int? {
    if (numbers.isEmpty() || targetPosition <= 0) return null

    for (i in 0 until numbers.size) {
        for (j in i + 1 until numbers.size) {
            if (numbers[i] < numbers[j]) {
                val temp = numbers[i]
                numbers[i] = numbers[j]
                numbers[j] = temp
            }
        }
    }

    val uniqueList = mutableListOf<Int>()
    for (num in numbers) {
        if (num !in uniqueList) {
            uniqueList.add(num)
        }
    }

    if (targetPosition > uniqueList.size) return null

    return uniqueList[targetPosition - 1]
}

fun findFirstRepeatedNumberNew(numbers: List<Int>): Int?{
    val frequency = HashMap<Int, Int>()
    for (number in numbers){
        if (frequency.contains(number)){
            frequency[number] = frequency[number]!! + 1
        }else{
            frequency[number] = 1
        }
    }
    var count = 0
    for (number in numbers) {
        if (frequency[number] != 1) {
            count++
        }
    }
    return count
}