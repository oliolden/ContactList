fun main() {
    var contacts = ContactList()
    contacts.add(Contact("Oliver", "Oldenstam", "12413535", "foo@bar.com"))
    contacts.add(Contact("Olle", "Någård", "32561361", "bar@foo.net"))
    contacts.add(Contact("Benjamin", "Stålberg", "095678236", "lorem@ipsum.org"))
    contacts.menu()
}