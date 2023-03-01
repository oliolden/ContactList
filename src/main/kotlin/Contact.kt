class Contact(var firstName: String, var lastName: String) {
    // Create maps with first phone number and email address.
    var phoneNumbers = mutableMapOf<String, String>()
    var emailAdresses = mutableMapOf<String, String>()

    fun name(): String {
        return "$firstName $lastName"
    }

    fun show() {
        println(name())
        showNumbers()
        showEmails()
    }

    fun showNumbers() {
        for (number in phoneNumbers) {
            println("${number.key}: ${number.value}")
        }
    }

    fun showEmails() {
        for (email in emailAdresses) {
            println("${email.key}: ${email.value}")
        }
    }

    fun addPhone(name: String, phoneNumber: String) {
        phoneNumbers[name] = phoneNumber
    }

    fun addEmail(name: String, emailAddress: String) {
        emailAdresses[name] = emailAddress
    }

    override fun toString(): String {
        return "$firstName\n$lastName\n${phoneNumbers.entries.joinToString(",")}\n${emailAdresses.entries.joinToString(",")}"
    }

}
