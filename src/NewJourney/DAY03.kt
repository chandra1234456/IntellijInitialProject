package NewJourney

fun main(){
    rightAngledTriangle()
    invertedRightAngledTriangle()
    rightAlignedTriangle()
    countGreaterThanTen(arrayListOf(5, 12, 7, 20, 15, 3, 25))
    duplicateNumbers(arrayListOf(10, 10, 10, 20, 20, 30))
    reverse("HelloKotlin")
    reverseList(arrayListOf(10, 10, 10, 20, 20, 30))
}
fun reverseList(list: ArrayList<Int>){
    var reverseValue = ArrayList<Int>()
    for (i in list.size-1 downTo 0){
        reverseValue.add(list[i])
    }
    println(reverseValue.joinToString())
}

fun reverse(intPut: String){
    var reverseValue = ""
    for (i in intPut.length-1 downTo 0){
        reverseValue += intPut[i].toString()
    }
    println(reverseValue)
}
fun duplicateNumbers(list: ArrayList<Int>){
    val newList = ArrayList<Int>()
    val duplicates = ArrayList<Int>()
    for (num  in list){
        if (num !in newList) {
            newList.add(num)
        }else{
            if(num !in duplicates) {
                duplicates.add(num)
            }
        }
    }
    print(duplicates.joinToString())
}
fun countGreaterThanTen(list: ArrayList<Int>){
    var count = 0
    for (i in list.indices){
        if (list[i]>10){
            count++
        }
    }
    print(count)
}
fun rightAngledTriangle(){
    print("=== Right Angled Triangle ===")
    println()
    val rows = 5
    for(i in 1..rows){
        for(j in 1..i){
            print("*")
        }
        println()
    }
}
fun invertedRightAngledTriangle(){
    print("==== Inverted RightAngled Triangle ===")
    println()
    val rows = 5
    for(i in 1..rows){
        for (j in i..rows){
            print("*")
        }
        println()
    }
}
fun rightAlignedTriangle(){
    print("==== Right Aligned Triangle ===")
    println()
    val rows = 5
    for(i in 1..rows){
       //for(j in rows-i..1){
           println("*")
      // }
        //print("")
    }
}