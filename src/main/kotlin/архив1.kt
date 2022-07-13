//import kotlin.random.Random
//
//class Mine(val minefield: MutableList<MutableList<Char>>, val rowIndex: Int, val columnIndex: Int) {
//    var value = minefield[rowIndex][columnIndex]
//    var cellOne = mutableMapOf<String, Int>("rowIndex" to (rowIndex - 1), "columnIndex" to columnIndex - 1)
//    var cellTwo = mutableMapOf<String, Int>("rowIndex" to (rowIndex - 1), "columnIndex" to columnIndex)
//    var cellThree = mutableMapOf<String, Int>("rowIndex" to (rowIndex - 1), "columnIndex" to columnIndex + 1)
//    var cellFour = mutableMapOf<String, Int>("rowIndex" to rowIndex, "columnIndex" to columnIndex - 1)
//    var cellFive = mutableMapOf<String, Int>("rowIndex" to rowIndex, "columnIndex" to columnIndex + 1)
//    var cellSix = mutableMapOf<String, Int>("rowIndex" to (rowIndex + 1), "columnIndex" to columnIndex - 1)
//    var cellSeven = mutableMapOf<String, Int>("rowIndex" to (rowIndex + 1), "columnIndex" to columnIndex)
//    var cellEight = mutableMapOf<String, Int>("rowIndex" to (rowIndex + 1), "columnIndex" to columnIndex + 1)
//    var targetCells = mutableListOf(cellOne, cellTwo, cellThree, cellFour, cellFive, cellSix, cellSeven, cellEight)
//    var isMine: Boolean = if (value == 'X') true else false
//    var mineCount = 0
//    init {
//        if (!isMine) {
//            when (columnIndex) {
//                0 -> when (rowIndex) {
//                    0 -> targetCells = mutableListOf(cellFive, cellSeven, cellEight)
//                    8 -> targetCells = mutableListOf(cellTwo, cellThree, cellFive)
//                    else -> targetCells = mutableListOf(cellTwo, cellThree, cellFive, cellSeven, cellEight)
//                }
//                8 -> when (rowIndex) {
//                    0 -> targetCells = mutableListOf(cellFour, cellSix, cellSeven)
//                    8 -> targetCells = mutableListOf(cellOne, cellTwo, cellFour)
//                    else -> targetCells = mutableListOf(cellOne, cellTwo, cellFour, cellSix, cellSeven)
//                }
//                else -> if (rowIndex == 0) {
//                    targetCells = mutableListOf(cellFour, cellFive, cellSix, cellSeven, cellEight)
//                } else if (rowIndex == 8) {
//                    targetCells = mutableListOf(cellOne, cellTwo, cellThree, cellFour, cellFive)
//                }
//            }
//            for (i in targetCells) {
//                if (minefield[i["rowIndex"]!!][i["columnIndex"]!!] == 'X') {
//                    mineCount++
//                }
//            }
//            if (mineCount != 0) {
//                value = Character.forDigit(mineCount, 10)
//            }
//        }
//    }
//}
//
//fun main() {
//    print("How many mines do you want on the field?")
//    val minesCount = readln().toInt()
//    var cellsCount = 81
//    val mines = MutableList(81) { 81 - cellsCount-- }
//    val objMines = MutableList<MutableList<Mine>>(9) { mutableListOf() }
//    repeat(cellsCount) { mines.add(81 - cellsCount--) }
//    val minefield = MutableList(9) { MutableList(9) { '.' }}
//    repeat(minesCount) {
//        val index = Random.nextInt(0, mines.lastIndex + 1)
//        val mineCoordinate = mines.removeAt(index)
//        minefield[mineCoordinate / 9][mineCoordinate % 9] = 'X'
//    }
//    for (row in 0..8) {
//        for (column in 0..8) {
//            objMines[row].add(Mine(minefield, row, column))
//            if (objMines[row][column].value != '.' && objMines[row][column].value != 'X') {
//                minefield[row][column] = objMines[row][column].value
//            }
//        }
//    }
//    print(minefield.map { it.joinToString("") }.joinToString("\n"))
//}
