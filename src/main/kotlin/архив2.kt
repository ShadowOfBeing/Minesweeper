//import java.io.IOException
//import kotlin.random.Random
//
//class Field(val minesCount: Int) {
//    var cellsCount = 81
//    val mines = MutableList(81) { 81 - cellsCount-- }
//    val objMines = MutableList<MutableList<Mine>>(9) { mutableListOf() }
//    val minefield = MutableList(9) { MutableList(9) { '.' }}
//    var win = false
//
//    init {
//        repeat(cellsCount) { mines.add(81 - cellsCount--) }
//        repeat(minesCount) {
//            val index = Random.nextInt(0, mines.lastIndex + 1)
//            val mineCoordinate = mines.removeAt(index)
//            minefield[mineCoordinate / 9][mineCoordinate % 9] = 'X'
//        }
//        for (row in 0..8) {
//            for (column in 0..8) {
//                objMines[row].add(Mine(minefield, row, column))
//            }
//        }
//        this.updateMap()
//        this.printMinefield()
//    }
//
//    fun updateMap() {
//        for (row in 0..8) {
//            for (column in 0..8) {
//                minefield[row][column] = objMines[row][column].viewValue
//            }
//        }
//    }
//
//    fun printMinefield() {
//        println(" |123456789|")
//        //println("—|—————————|")
//        println("—|—————————|")
//        for ((i, row) in minefield.withIndex()) {
//            println("${i + 1}|${row.joinToString("")}|")
//        }
//        println("—|—————————|")
//    }
//
//    fun markCell(x: Int, y: Int) {
//        if (objMines[x][y].state == "number") {
//            println("There is a number here!")
//        } else {
//            if (objMines[x][y].state == "close") {
//                objMines[x][y].state = "mark"
//                objMines[x][y].viewValue = '*'
//            } else {
//                objMines[x][y].state = "close"
//                objMines[x][y].viewValue = '.'
//            }
//            this.updateMap()
//            this.printMinefield()
//        }
//        if (this.allCellsMarkedTrue()) {
//            println("Congratulations! You found all the mines!")
//            this.win = true
//        }
//    }
//
//    fun allCellsMarkedTrue(): Boolean {
//        for (row in 0..8) {
//            for (column in 0..8) {
//                if (objMines[row][column].isMine && objMines[row][column].state != "mark" ||
//                    !objMines[row][column].isMine && objMines[row][column].state == "mark") return false
//            }
//        }
//        return true
//    }
//}
//
//class Mine(val minefield: MutableList<MutableList<Char>>, val rowIndex: Int, val columnIndex: Int) {
//    var cellValue = minefield[rowIndex][columnIndex]
//    var viewValue = '.'
//    var cellOne = mutableMapOf<String, Int>("rowIndex" to rowIndex - 1, "columnIndex" to columnIndex - 1)
//    var cellTwo = mutableMapOf<String, Int>("rowIndex" to rowIndex - 1, "columnIndex" to columnIndex)
//    var cellThree = mutableMapOf<String, Int>("rowIndex" to rowIndex - 1, "columnIndex" to columnIndex + 1)
//    var cellFour = mutableMapOf<String, Int>("rowIndex" to rowIndex, "columnIndex" to columnIndex - 1)
//    var cellFive = mutableMapOf<String, Int>("rowIndex" to rowIndex, "columnIndex" to columnIndex + 1)
//    var cellSix = mutableMapOf<String, Int>("rowIndex" to rowIndex + 1, "columnIndex" to columnIndex - 1)
//    var cellSeven = mutableMapOf<String, Int>("rowIndex" to rowIndex + 1, "columnIndex" to columnIndex)
//    var cellEight = mutableMapOf<String, Int>("rowIndex" to rowIndex + 1, "columnIndex" to columnIndex + 1)
//    var targetCells = mutableListOf(cellOne, cellTwo, cellThree, cellFour, cellFive, cellSix, cellSeven, cellEight)
//    var isMine: Boolean = if (cellValue == 'X') true else false
//    var mineCount = 0
//    var state = "close"
//
//    init {
//        if (!isMine) {
//            when (rowIndex) {
//                0 -> when (columnIndex) {
//                    0 -> targetCells = mutableListOf(cellFive, cellSeven, cellEight)
//                    8 -> targetCells = mutableListOf(cellFour, cellSix, cellSeven)
//                    else -> targetCells = mutableListOf(cellFour, cellFive, cellSix, cellSeven, cellEight)
//                }
//                8 -> when (columnIndex) {
//                    0 -> targetCells = mutableListOf(cellTwo, cellThree, cellFive)
//                    8 -> targetCells = mutableListOf(cellOne, cellTwo, cellFour)
//                    else -> targetCells = mutableListOf(cellOne, cellTwo, cellThree, cellFour, cellFive)
//                }
//                else -> if (columnIndex == 0) {
//                    targetCells = mutableListOf(cellTwo, cellThree, cellFive, cellSeven, cellEight)
//                } else if (columnIndex == 8) {
//                    targetCells = mutableListOf(cellOne, cellTwo, cellFour, cellSix, cellSeven)
//                }
//            }
//            for (i in targetCells) {
//                if (minefield[i["rowIndex"]!!][i["columnIndex"]!!] == 'X') {
//                    mineCount++
//                }
//            }
//            if (mineCount != 0) {
//                cellValue = Character.forDigit(mineCount, 10)
//                viewValue = cellValue
//                state = "number"
//            }
//        }
//    }
//}
//
//fun main() {
//    println("How many mines do you want on the field?")
//    val minesCount = readln().toInt()
//    if (minesCount > 0) {
//        val newMinefield = Field(minesCount)
//        while(!newMinefield.win) {
//            print("Set/delete mine marks (x and y coordinates):")
//            val (y, x) = readln().split(" ").map { it.toInt() }
//            newMinefield.markCell(x - 1, y - 1)
//        }
//    }
//}
