package opps

open class Car {
    open fun driving() {
        println("Car is driving")
    }
}

class Men : Car() {
    override fun driving() {
        super.driving()
        println("Men Driving")
    }

}

fun main() {
    val men = Men()
    men.driving()
}