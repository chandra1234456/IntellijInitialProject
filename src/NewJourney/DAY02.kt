package NewJourney

import kotlin.math.sqrt


fun main(){
    println(primeNumberNew(7))
}
fun primeNumberNew(input :Int): Boolean{
    if (input < 2)return false
    for (i in 2..sqrt(input.toDouble()).toInt()){
        if (input % i==0){
            return false
        }
    }
    return true
}