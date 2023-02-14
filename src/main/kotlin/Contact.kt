class Contact(var firstName: String, var lastName: String, phoneNumber: String, emailAddress: String) {
    // Create maps with first phone number and email address.
    var phoneNumbers = mutableMapOf(Pair("Phone", phoneNumber))
    var emailAdresses = mutableMapOf(Pair("Email", emailAddress))

    fun name(): String {
        return "$firstName $lastName"
    }

    fun addEmail(name: String, emailAddress: String) {
        emailAdresses[name] = emailAddress
    }

    fun addPhone(name: String, phoneNumber: String) {
        phoneNumbers[name] = phoneNumber
    }

    override fun toString(): String = name()
}