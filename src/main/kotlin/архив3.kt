import java.io.IOException
import kotlin.random.Random
import kotlin.system.exitProcess

// рабочий прототип, но изначально скрыты только мины и пустые ячейки, ячейки с цифрами показаны

//class Field(val _minesCount: Int) {
//    var cellsCount = 81
//    val minesCount = if (_minesCount >= cellsCount) (_minesCount - 1) else _minesCount
//    var mines = MutableList(81) { 81 - cellsCount-- }
//    var objMines = MutableList<MutableList<Mine>>(9) { mutableListOf() }
//    var minefield = MutableList(9) { MutableList(9) { '.' }}
//    var win = false
//    var isFirstOpen = true
//
//    init {
//        createRandomMap()
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
//        println("—|—————————|")
//        for ((i, row) in minefield.withIndex()) {
//            println("${i + 1}|${row.joinToString("")}|")
//        }
//        println("—|—————————|")
//    }
//
//    fun executeCommand(string: String) {
//        val (y, x, command) = string.split(" ")
//        if (command == "mine") {
//            markCell(x.toInt() - 1, y.toInt() - 1)
//        } else if (command == "free") {
//            openCell(x.toInt() - 1, y.toInt() - 1)
//        }
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
//    fun openCell(x: Int, y: Int) {
//        objMines[x][y].viewEqualValue()
//        if (isFirstOpen) {
//            isFirstOpen = false
//            if (objMines[x][y].cellValue == 'X') {
//                createRandomMap("firstOpen", x, y)
//            }
//            objMines[x][y].state = "open"
//            this.chainReaction(x, y)
//        } else {
//            if (objMines[x][y].isMine) {
//                this.openAllCells()
//                print("You stepped on a mine and failed!")
//                exitProcess(0)
//            } else {
//                objMines[x][y].state = "open"
//            }
//        }
//        this.updateMap()
//        this.printMinefield()
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
//
//    fun openAllCells() {
//        for (row in 0..8) {
//            for (column in 0..8) {
//                minefield[row][column] = objMines[row][column].cellValue
//            }
//        }
//        this.printMinefield()
//    }
//
//    fun chainReaction(x: Int, y: Int) {
//        if (objMines[x][y].cellValue != '/') {
//            for (cell in objMines[x][y].targetCells) {
//                if (cell["isMine"] == 0) {
//                    objMines[cell["rowIndex"]!!][cell["columnIndex"]!!].viewEqualValue()
//                    objMines[cell["rowIndex"]!!][cell["columnIndex"]!!].state = "open"
//                }
//            }
//        } else {
//            for (cell in objMines[x][y].targetCells) {
//                if (objMines[cell["rowIndex"]!!][cell["columnIndex"]!!].state != "open") {
//                    objMines[cell["rowIndex"]!!][cell["columnIndex"]!!].viewEqualValue()
//                    objMines[cell["rowIndex"]!!][cell["columnIndex"]!!].state = "open"
//                    chainReaction(cell["rowIndex"]!!, cell["columnIndex"]!!)
//                }
//            }
//        }
//    }
//
//    fun createRandomMap(flag: String = "default", x: Int = 0, y: Int = 0) {
//        var whileFlag = false
//        cellsCount = 81
//        mines = MutableList(81) { 81 - cellsCount-- }
//        objMines = MutableList<MutableList<Mine>>(9) { mutableListOf() }
//        minefield = MutableList(9) { MutableList(9) { '.' }}
//        while (whileFlag == false) {
//            repeat(minesCount) {
//                val index = Random.nextInt(0, mines.lastIndex + 1)
//                val mineCoordinate = mines.removeAt(index)
//                minefield[mineCoordinate / 9][mineCoordinate % 9] = 'X'
//            }
//            if (flag == "default") {
//                for (row in 0..8) {
//                    for (column in 0..8) {
//                        objMines[row].add(Mine(minefield, row, column))
//                    }
//                }
//                this.updateMap()
//                this.printMinefield()
//                whileFlag = true
//            } else {
//                if (minefield[x][y] != 'X') {
//                    for (row in 0..8) {
//                        for (column in 0..8) {
//                            objMines[row].add(Mine(minefield, row, column))
//                        }
//                    }
//                    whileFlag = true
//                }
//            }
//        }
//    }
//}
//
//class Mine(val minefield: MutableList<MutableList<Char>>, val rowIndex: Int, val columnIndex: Int) {
//    var cellValue = minefield[rowIndex][columnIndex]
//    var viewValue = '.'
//    var cellOne = mutableMapOf<String, Int>("rowIndex" to rowIndex - 1, "columnIndex" to columnIndex - 1, "isMine" to 0)
//    var cellTwo = mutableMapOf<String, Int>("rowIndex" to rowIndex - 1, "columnIndex" to columnIndex, "isMine" to 0)
//    var cellThree = mutableMapOf<String, Int>("rowIndex" to rowIndex - 1, "columnIndex" to columnIndex + 1, "isMine" to 0)
//    var cellFour = mutableMapOf<String, Int>("rowIndex" to rowIndex, "columnIndex" to columnIndex - 1, "isMine" to 0)
//    var cellFive = mutableMapOf<String, Int>("rowIndex" to rowIndex, "columnIndex" to columnIndex + 1, "isMine" to 0)
//    var cellSix = mutableMapOf<String, Int>("rowIndex" to rowIndex + 1, "columnIndex" to columnIndex - 1, "isMine" to 0)
//    var cellSeven = mutableMapOf<String, Int>("rowIndex" to rowIndex + 1, "columnIndex" to columnIndex, "isMine" to 0)
//    var cellEight = mutableMapOf<String, Int>("rowIndex" to rowIndex + 1, "columnIndex" to columnIndex + 1, "isMine" to 0)
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
//                    i["isMine"] = 1
//                }
//            }
//            if (mineCount != 0) {
//                cellValue = Character.forDigit(mineCount, 10)
//                viewValue = cellValue
//                state = "number"
//            } else {
//                cellValue = '/'
//            }
//        }
//    }
//
//    fun viewEqualValue() {
//        viewValue = cellValue
//    }
//}
//
//fun main() {
//    println("How many mines do you want on the field?")
//    val minesCount = readln().toInt()
//    if (minesCount > 0) {
//        val newMinefield = Field(minesCount)
//        while(!newMinefield.win) {
//            print("Set/unset mine marks or claim a cell as free:")
//            newMinefield.executeCommand(readln())
//        }
//    }
//}