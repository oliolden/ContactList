import java.io.File

class ContactList {
    private var contacts = mutableListOf<Contact>()

    fun save() {
        File("./contacts.txt").writeText(contacts.joinToString("\n"))
    }

    fun load() {
        var lines = File("./contacts.txt").readLines()
        var i = 0
        while (i < lines.count()) {
            var contact = Contact(lines[i], lines[i + 1])
            if (lines[i + 2].contains('=')) {
                var phones = lines[i + 2].split(",")
                for (phone in phones) {
                    var data = phone.split("=")
                    contact.addPhone(data[0], data[1])
                }
            }
            if (lines[i + 3].contains('=')) {
                var emails = lines[i + 3].split(",")
                for (email in emails) {
                    var data = email.split("=")
                    contact.addEmail(data[0], data[1])
                }
            }
            contacts.add(contact)
            i += 4
        }
        sort()
    }

    fun add(contact: Contact) {
        contacts.add(contact)
        sort()
        save()
    }

    private fun sort() {
        contacts.sortBy { it.lastName }
    }

    fun printAll() {
        for (contact in contacts) {
            println("${contacts.indexOf(contact)}: ${contact.name()}")
        }
    }

    fun find(search: String): MutableList<Contact> {
        var resultList = mutableListOf<Contact>()
        for (contact in contacts) {
            if (contact.name().lowercase().contains(search.lowercase())) resultList.add(contact)
        }
        return resultList
    }

    fun search() {
        print("Search: ")
        var result = find(readln())
        if (result.count() > 0) {
            for (contact in result) {
                println("${contacts.indexOf(contact)}: ${contact.name()}")
            }
        }
        else println("No matches found.")
    }

    fun show() {
        print("Id: ")
        var id = readln().toInt()
        contacts[id].show()
    }

    fun addMenu() {
        print("First name: ")
        var firstName = readln()
        print("Last name: ")
        var lastName = readln()
        var new = Contact(firstName, lastName)
        add(new)
        println("${new.name()} was successfully added to your contacts.")
    }

    fun delete() {
        print("Id: ")
        var id = readln().toInt()
        var name = contacts[id].name()
        println("Are you sure you want to delete ${name}? (y/n)")
        while (true) {
            when (readln()) {
                "y" -> {
                    contacts.removeAt(id)
                    println("$name was deleted.")
                    return
                }
                "n" -> {
                    println("No changes were made.")
                    return
                }
                else -> println("Invalid input")
            }
        }
    }

    fun edit() {
        print("Contact Id: ")
        var contact = contacts[readln().toInt()]
        println("Editing ${contact.name()}")
        println("What do you want to edit?")
        println("1. First Name, 2. Last Name, 3. Phones, 4. Emails")
        println("Type 'cancel' to cancel")
        while (true) {
            when (readln().lowercase().trim()) {
                "1" -> {
                    print("New first name: ")
                    contact.firstName = readln()
                    return
                }
                "2" -> {
                    print("New last name: ")
                    contact.lastName = readln()
                    return
                }
                "3" -> {
                    contact.showNumbers()
                    println("Commands: add, remove, cancel")
                    while (true) {
                        when (readln().lowercase().trim()) {
                            "add" -> {
                                print("Phone name: ")
                                var name = readln()
                                print("Number: ")
                                var number = readln()
                                contact.addPhone(name, number)
                                println("Number was added.")
                                return
                            }
                            "remove" -> {
                                print("Phone name: ")
                                var name = readln()
                                if (contact.phoneNumbers.containsKey(name)) {
                                    contact.phoneNumbers.remove(name)
                                    println("$name was removed.")
                                } else {
                                    println("Not found.")
                                }
                                return
                            }
                        }
                    }
                }
                "4" -> {
                    contact.showEmails()
                    println("Commands: add, remove, cancel")
                    while (true) {
                        when (readln().lowercase().trim()) {
                            "add" -> {
                                print("Email name: ")
                                var name = readln()
                                print("Address: ")
                                var address = readln()
                                contact.addEmail(name, address)
                                println("Address was added.")
                                return
                            }
                            "remove" -> {
                                print("Email name: ")
                                var name = readln()
                                if (contact.emailAdresses.containsKey(name)) {
                                    contact.emailAdresses.remove(name)
                                    println("$name was removed.")
                                } else {
                                    println("Not found.")
                                }
                                return
                            }
                        }
                    }
                }
                "cancel" -> return
                else -> println("Invalid.")
            }
        }
    }

    fun menu() {
        println("---------------------")
        println("  Contact Menu 3000")
        println("---------------------")
        println("Commands: ")
        println("list, search, show, add, edit, delete, exit")
        print(">")
        while (true) {
            when (readln().lowercase().trim()) {
                "list" -> printAll()
                "search" -> search()
                "show" -> show()
                "add" -> {
                    addMenu()
                    save()
                }
                "edit" -> {
                    edit()
                    save()
                }
                "delete" -> {
                    delete()
                    save()
                }
                "exit" -> return
                else -> println("Command not found.")
            }
            println("\nCommands: ")
            println("list, search, show, add, edit, delete, exit")
            print(">")
        }
    }
}