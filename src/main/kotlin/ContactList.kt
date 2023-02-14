class ContactList {
    var contacts = mutableListOf<Contact>()

    fun add(contact: Contact) {
        contacts.add(contact)
        sort()
    }

    private fun sort() {
        contacts.sortBy { it.lastName }
    }

    fun printAll() {
        println(contacts.joinToString("\n"))
    }

    fun find(search: String): MutableList<Contact> {
        var resultList = mutableListOf<Contact>()
        for (contact in contacts) {
            if (contact.name().lowercase().contains(search)) resultList.add(contact)
        }
        return resultList
    }

    fun menu() {
        while (true) {
            println("---------------------")
            println("  Contact Menu 3000")
            println("---------------------")
            println("Commands: ")
            println("list")
            println("search")
            println("add")
            println("edit")
            println("delete")
            println("exit")

            when (readln()) {
                "list" -> {
                    printAll()
                    println("Press enter to continue...")
                    readln()
                }
                "search" -> {
                    print("Search: ")
                    var result = find(readln())
                    if (result.count() > 0) { println(result.joinToString("\n")) }
                    else println("No matches found.")
                    println("Press enter to continue...")
                    readln()
                }
                "add" -> {
                    print("First name: ")
                    var firstName = readln();
                    print("Last name: ")
                    var lastName = readln();
                    print("Phone: ")
                    var phone = readln();
                    print("Email: ")
                    var email = readln();
                    var new = Contact(firstName, lastName, phone, email)
                    add(new)
                    println("$new was successfully added to your contacts.")
                    println("Press enter to continue...")
                    readln()
                }
                "edit" -> continue
                "delete" -> continue
                "exit" -> return
                else -> println("Invalid input.")
            }
        }
    }
}