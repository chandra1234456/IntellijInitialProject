package GFG60Problems

fun main(){
    /*secondLargest(arrayListOf(12, 35, 1, 10, 34, 1))
    secondLargest(arrayListOf(10, 5, 20, 8, 15))
    secondLargest(arrayListOf(5, 10, 15))
    secondLargest(arrayListOf(10, 10, 5))
    secondLargest(arrayListOf(5, 5, 5))
    secondLargest(arrayListOf(-5, -10, -2))*/
    moveAllZerosEnd(arrayListOf(1, 2, 0, 4, 3, 0, 5, 0))
    moveAllZerosEnd(arrayListOf(10, 20, 30))
    moveAllZerosEnd(arrayListOf(0, 0))
}

fun moveAllZerosEnd(list: ArrayList<Int>){
    var count = 0
    for(num in list){
        if (num !=0){
            list[count] = num
            count++
        }
    }
    while (count<list.size){
        list[count]=0
        count++
    }
    println(list)
    println(count)
}

fun secondLargest(list: ArrayList<Int>) {
    var firstLargest = Int.MIN_VALUE
    var secondLargest = Int.MIN_VALUE

    for (num in list) {
        if (num > firstLargest) {
            secondLargest = firstLargest
            firstLargest = num
        } else if (num < firstLargest && num > secondLargest) {
            secondLargest = num
        }
    }

    if (secondLargest == Int.MIN_VALUE) {
        println("No second distinct largest element")
    } else {
        println("Largest $firstLargest SecondLargest $secondLargest")
    }
}
