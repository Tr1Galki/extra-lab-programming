fun main() {
    val data = mutableListOf(Pair(String.toString(), DataClass()))

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
            1 -> create(input, data)
            2 -> read(input, data)
            3 -> delete(input, data)
            4 -> readall(data)
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

class DataClass(){
    var color: String = ""
    var age: Float = 0F
    var weight: Float = 0.0F
    constructor(_color: String, _age: Float, _weight: Float) : this() {
        color = _color
        age = _age
        weight = _weight
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
            return if (wordList[0] == "create" && floatCheck(wordList[3]) && floatCheck(wordList[4])){
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

fun floatCheck(s: String):Boolean{
    var tmpCount = 0
    var pointCount = 0
    if (s[tmpCount] !in arrayOf('-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9')){
        return false
    }
    tmpCount++
    while (tmpCount < s.length){
        if (s[tmpCount] !in arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.')){
            return false
        }
        if (s[tmpCount] == '.'){
            pointCount++
        }
        tmpCount++
    }
    if (pointCount > 1){
        return false
    }
    return true
}

fun create(s: String, data: MutableList<Pair<String, DataClass>>){
    val wordList = s.trim().split(" ").filter {  it != ""  }
    for (elem in data){
        if (elem.first == wordList[1]){
            println("Create: already exists")
            return
        }
    }
    data.add(Pair(wordList[1], DataClass(wordList[2], wordList[3].toFloat(), wordList[4].toFloat())))
    println("by value ${wordList[1]} making characteristics:\n-color: ${wordList[2]}\n-age: ${wordList[3].toFloat()}\n" +
            "-weight: ${wordList[4].toFloat()}")
}

fun read(s: String, data: MutableList<Pair<String, DataClass>>){
    val wordList = s.trim().split(" ").filter {  it != ""  }
    var tmpCurr = 1
    while (tmpCurr < data.size){
        if (data[tmpCurr].first == wordList[1]){
            println("characteristics of ${data[tmpCurr].first}:\n-color: ${data[tmpCurr].second.color}\n" +
                    "-age: ${data[tmpCurr].second.age}\n" + "-weight: ${data[tmpCurr].second.weight}")
            return
        }
        tmpCurr++
    }
    println("Read: not found")
    return
}

fun delete(s: String, data: MutableList<Pair<String, DataClass>>){
    val wordList = s.trim().split(" ").filter {  it != ""  }
    var tmpCurr = 1
    while (tmpCurr < data.size){
        if (data[tmpCurr].first == wordList[1]){
            data.removeAt(tmpCurr)
            println("OK")
            return
        }
        tmpCurr++
    }
    println("Delete: not found")
}

fun readall(data: MutableList<Pair<String, DataClass>>){
    var tmpCurr = 1
    while (tmpCurr < data.size){
        println("characteristics of ${data[tmpCurr].first}:\n-color: ${data[tmpCurr].second.color}\n" +
                "-age: ${data[tmpCurr].second.age}\n" + "-weight: ${data[tmpCurr].second.weight}")
        tmpCurr++
    }
}