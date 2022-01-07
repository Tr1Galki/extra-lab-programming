fun main() {
    val id = arrayOfNulls<String>(1000)
    val characts = Array(1000) { DataClass() }
    var command: Int
    var input: String

    println("Start of execution\nUse commands:\n  create _name_ _color_ _age_ _weight_\n  read _name_\n  delete" +
            " _name_\n  readall\n  exit\nUse spaces to separate")

    while (true) {
        println("write command:")
        input = readLine().toString()

        command = check(input)

        when (command) {
            0 -> {
                println("incorrect input")
                continue
            }
            1 -> create(input, id, characts)
            2 -> read(input, id, characts)
            3 -> delete(input, id, characts)
            4 -> readall(id, characts)
            5 -> {
                println("program finished")
                break
            }
            else -> {
                print("error")
            }
        }
    }
}

class DataClass{
    var color: String = ""
    var age: Int = 0
    var weight: Int = 0
}

fun create(s: String, id: Array<String?>, characts: Array<DataClass>){
    val wordList = s.trim().split(" ").filter {  it != ""  }
    var tmpCurr = 0
    while (tmpCurr < 1000){
        if (id[tmpCurr] == null){
            id[tmpCurr] = wordList[1]
            characts[tmpCurr].color = wordList[2]
            characts[tmpCurr].age = wordList[3].toInt()
            characts[tmpCurr].weight = wordList[4].toInt()
            println("by value ${wordList[1]} making characteristics:\n-color: ${wordList[2]}\n-age: ${wordList[3]}\n" +
                    "-weight: ${wordList[4]}")
            return
        }
        tmpCurr++
    }
}

fun read(s: String, id: Array<String?>, characts: Array<DataClass>){
    val wordList = s.trim().split(" ").filter {  it != ""  }
    var tmpCurr = 0
    while (tmpCurr < 1000){
        if (id[tmpCurr] == wordList[1]){
            println("characteristics of ${wordList[1]}:\n-color: ${characts[tmpCurr].color}\n" +
                    "-age: ${characts[tmpCurr].age}\n" + "-weight: ${characts[tmpCurr].weight}")
            return
        }
        tmpCurr++
    }
}

fun delete(s: String, id: Array<String?>, characts: Array<DataClass>){
    val wordList = s.trim().split(" ").filter {  it != ""  }
    var tmpCurr = 0
    while (tmpCurr < 1000){
        if (id[tmpCurr] == wordList[1]){
            id[tmpCurr] = null
            characts[tmpCurr].color = "0"
            characts[tmpCurr].age= 0
            characts[tmpCurr].weight = 0
            println("OK")
            return
        }
        tmpCurr++
    }
}

fun readall(id: Array<String?>, characts: Array<DataClass>){
    var tmpCurr = 0
    while (tmpCurr < 1000){
        if (id[tmpCurr] != null){
            println("characteristics of ${id[tmpCurr]}:\n-color: ${characts[tmpCurr].color}\n" +
                    "-age: ${characts[tmpCurr].age}\n" + "-weight: ${characts[tmpCurr].weight}")
        }
        tmpCurr++
    }
}

fun check(s: String): Int{
    val wordList = s.trim().split(" ").filter {  it != ""  }

    when (wordList.size) {
        1 -> {
            return if (wordList[0] == "exit"){
                5
            } else if ((wordList[0] == "readall")){
                4
            } else{
                0
            }
        }
        2 -> {
            return if (wordList[0] == "read"){
                2
            } else if ((wordList[0] == "delete")){
                3
            } else{
                0
            }
        }
        5 -> {
            return if (wordList[0] == "create" && intCheck(wordList[3]) && intCheck(wordList[4])){
                1
            } else{
                0
            }
        }
        else -> {
            return 0
        }
    }
}

fun intCheck(s: String):Boolean{
    var tmpCount = 0
    if (s[tmpCount] !in arrayOf('-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9')){
        return false
    }
    tmpCount++
    while (tmpCount < s.length){
        if (s[tmpCount] !in arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')){
            return false
        }
        tmpCount++
    }
    return true
}
