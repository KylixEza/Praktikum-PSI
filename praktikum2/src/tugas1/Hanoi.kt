package tugas1

fun Int.solveHanoiTower(
	fromRod: Char,
	toRod: Char, auxRod: Char
) {
	if (this == 0) return
	this.minus(1).solveHanoiTower(fromRod, auxRod, toRod)
	println("Memindahkan disk $this dari pilar $fromRod ke pilar $toRod")
	this.minus(1).solveHanoiTower(auxRod, toRod, fromRod)
}

fun main() {
	print("Masukkan jumlah disk: ")
	val disk = readLine()?.toInt() ?: 0
	disk.solveHanoiTower( 'A', 'C', 'B')
}