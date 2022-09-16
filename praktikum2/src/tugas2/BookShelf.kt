package tugas2

class BookShelf {
	private val books = arrayListOf<Book>()
	
	fun observe() {
		do {
			println(
				"""
					1. Masukkan buku
					2. Menampilkan buku
					3. Keluar
				""".trimIndent()
			)
			
			print("Pilihan   : ")
			val choice = readln().toCondition()
			println()
			when(choice) {
				Condition.ADD -> addBook()
				Condition.SHOW -> showBooks()
				Condition.EXIT -> println("Semoga harimu buruk :)")
				Condition.UNKNOWN -> println("Perintah tidak dapat ditemukan :(")
			}
		} while (choice != Condition.EXIT)
	}
	
	private fun String.toCondition() = when(this) {
		"1" -> Condition.ADD
		"2" -> Condition.SHOW
		"3" -> Condition.EXIT
		else -> Condition.UNKNOWN
	}
	
	private fun addBook() {
		print("Title   : ")
		val title = readln()
		print("Author  : ")
		val author = readln()
		print("Year    : ")
		val year = readln().toInt()
		
		val book = Book(
			randomId(),
			title,
			author,
			year
		)
		books.add(book)
		
		println()
	}
	
	private fun showBooks() {
		println("Daftar buku yang tersedia :")
		for (book in books) {
			println("${book.bookTitle} (${book.bookAuthor}, ${book.bookYear})")
		}
		
		println()
	}
	
	private fun randomId() : String {
		val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
		return (1..16)
			.map { charset.random() }
			.joinToString("")
	}
}

fun main() {
	BookShelf().observe()
}