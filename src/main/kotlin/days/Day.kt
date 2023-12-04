package days

import util.InputReader

abstract class Day(dayNumber: Int, alternate: Boolean = false) {

    // lazy delegate ensures the property gets computed only on first access
    private val _inputList: List<String> by lazy { InputReader.getInputAsList(dayNumber) }
    private val _alternateInputList: List<String> by lazy { InputReader.getInputAsList(dayNumber, alternate = true) }
    protected val inputString: String by lazy { InputReader.getInputAsString(dayNumber) }
    
    protected val inputList : List<String> by lazy { if(alternate) _alternateInputList else _inputList }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any
}
