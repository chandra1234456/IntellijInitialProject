package GFG60Problems

fun main() {
    secondLargest(arrayListOf(12, 35, 1, 10, 34, 1,35))
    /*secondLargest(arrayListOf(10, 5, 20, 8, 15))
    secondLargest(arrayListOf(5, 10, 15))
    secondLargest(arrayListOf(10, 10, 5))
    secondLargest(arrayListOf(5, 5, 5))
    secondLargest(arrayListOf(-5, -10, -2))*/
     moveAllZerosEnd(arrayListOf(1, 2, 0, 4, 3, 0, 5, 0))
    /*moveAllZerosEnd(arrayListOf(10, 20, 30))
    moveAllZerosEnd(arrayListOf(0, 0))*/
    reverseAnArray(arrayListOf(1, 2, 0, 4, 3, 0, 5, 0))
    reverseAnArrayNew(arrayListOf(1, 2, 0, 4, 0, 5, 0))
    rotateAnArray(arrayListOf(1, 2, 0,4),2)
}
fun rotateAnArray(list: ArrayList<Int>, rotateTime: Int) {
    if (list.isEmpty()) return
    //val rotations = rotateTime % list.size

    repeat(rotateTime) {
        val temp = list[0]
        for (i in 0 until list.size - 1) {
            list[i] = list[i + 1]
        }
        list[list.size - 1] = temp
    }

    println(list.joinToString())
}
fun reverseAnArrayNew(list: ArrayList<Int>) {
    var left = 0
    var right = list.size - 1

    while (left < right) {
        val temp = list[left]
        list[left] = list[right]
        list[right] = temp
        left++
        right--
    }

    println(list.joinToString())
}
fun reverseAnArray(list : ArrayList<Int>){
    val newArray = ArrayList<Int>()
    for (i in list.size-1  downTo 0){
        newArray.add(list[i])
    }
    println(newArray.joinToString())
}

fun moveAllZerosEnd(list : ArrayList<Int>){
    var count = 0
    for (num in list){
        if (num !=0){
            list[count]=num
            count++
        }
    }
    while (count<list.size){
        list[count] = 0
        count++
    }
 println(list.joinToString())
}


fun secondLargest(list : ArrayList<Int>){
    var largest = Int.MIN_VALUE
    var secondLargest = Int.MIN_VALUE

    for (num in list){
        if (num>largest){
            secondLargest = largest
            largest = num
        }else if(num < largest && num>secondLargest){
            secondLargest = num
        }
    }
    println(secondLargest)
    println(largest)
}

