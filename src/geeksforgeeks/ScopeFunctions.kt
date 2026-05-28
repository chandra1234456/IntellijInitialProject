package geeksforgeeks

class Person() {
    var name: String = "Abcd"
    var contactNumber: String = "1234567890"
    var address: String = "xyz"
    fun displayInfo()  = print("\n Name: $name\n " +
            "Contact Number: $contactNumber\n " +
            "Address: $address")

}

fun main(){
    val person = Person().let {
        return@let "Hello My Name is ${it.name}"
    }
    println(person)
    val person2 = Person().let {
         it.name = "BALA"
    }
    println(person2)
    val person3 = Person().let { person ->
        person.name = "BALA"
    }
    println(person3)
}