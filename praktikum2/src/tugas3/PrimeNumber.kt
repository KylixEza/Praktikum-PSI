package tugas3

fun main() {
	val range = 1..100
	
	println("Bilangan Prima antara 1-100 adalah")
	print("[")
	for(value in range) {
		if(value == 1) continue
		val isPrime = checkPrimeNumber(value) { num, i ->
			(num % i != 0)
		}
		if (isPrime) {
			if(value >= 97) print("$value")
			else print("$value, ")
		}
	}
	print("]")
}

fun checkPrimeNumber(number: Int, block: (Int, Int) -> Boolean): Boolean {
	var isPrime = true
	
	for (i in 2..number / 2) {
		isPrime = block(number, i)
		if(!isPrime) break
	}
	
	return isPrime
}