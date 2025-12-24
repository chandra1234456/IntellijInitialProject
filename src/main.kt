


fun main(){
    val emotions = mutableListOf("Happy","Hello","Bhai","Nikhil","Loude","Happy","Hello")
    //emotions.forEach(::println)
    emotions.add("last Item")
    //emotions.removeAt(10)
    val hasDuplicates = emotions.size != emotions.distinct().size
    println(hasDuplicates) // true
    println(emotions.distinct())
    println(emotions.toSet())
    /*for (i in emotions){
        println("item $i")
    }*/

}