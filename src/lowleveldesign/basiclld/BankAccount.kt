package lowleveldesign.basiclld


fun main() {
    val notification1 =
        NotificationFactory.createNotification("EMAIL")

    val notification2 =
        NotificationFactory.createNotification("SMS")

    val notification3 =
        NotificationFactory.createNotification("PUSH")

    notification1.send("Welcome!")
    notification2.send("OTP: 1234")
    notification3.send("New message received")

}

object NotificationFactory{
    fun createNotification(type:String): Notification{
        return when (type.uppercase()) {
            "EMAIL" -> EmailNotification()
            "SMS" -> SmsNotification()
            "PUSH" -> PushNotification()
            else -> throw IllegalArgumentException("Invalid payment type")
        }
    }
}


interface Notification {
    fun send(message: String)
}

class EmailNotification : Notification {
    override fun send(message: String) {
        println("Email notification: $message")
    }
}

class SmsNotification : Notification {
    override fun send(message: String) {
        println("SMS notification: $message")
    }
}

class PushNotification : Notification {
    override fun send(message: String) {
        println("Push notification: $message")
    }
}




