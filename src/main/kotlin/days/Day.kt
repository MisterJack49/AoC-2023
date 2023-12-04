package days

import util.InputReader

abstract class Day(dayNumber: Int, alternate: Boolean) {

    // lazy delegate ensures the property gets computed only on first access
    protected val inputList: List<String> by lazy { InputReader.getInputAsList(dayNumber) }
    protected val alternateInputList: List<String> by lazy { InputReader.getInputAsList(dayNumber, alternate = true) }
    protected val inputString: String by lazy { InputReader.getInputAsString(dayNumber) }
    
    protected val list : List<String> by lazy { if(alternate) alternateInputList else inputList }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any
}
