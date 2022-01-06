import kotlin.math.abs

fun main() {
    val data = Array(1000){ mutableListOf(Pair(String.toString(), DataClass())) }

    var command: Int
    var input: String

    println("Start of execution\nUse commands:\n  create _name_ _color_ _age_ _weight_\n  update _name_ _color_ _age_" +
            " _weight_\n  read _name_\n  delete" + " _name_\n  readall\n  exit\nUse spaces to separate")

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
            6 -> update(input, data)
            7 -> where(input, data)
            else -> {
                print("error")
            }
        }
    }
}

class DataClass(){
    var color: String = ""
    var age: Float = 0.0F
    var weight: Float = 0.0F
    constructor(_color: String, _age: Float, _weight: Float) : this() {
        color = _color
        age = _age
        weight = _weight
    }
}

fun getHash(s: String, len: Int): Int{
    //Maybe bot best function, but I think that it's enough random and well
    var res = 0
    var multiply = 1
    val prime = 31
    for (char in s){
        res += char.code * multiply * prime
        multiply++
    }
    res %= len
    return res
}

fun check(s: String):Int {
    val wordList = s.trim().split(" ").filter { it != "" }

    when (wordList.size) {
        1 -> {
            return if (wordList[0] == "exit") {
                5
            } else if ((wordList[0] == "readall")) {
                4
            } else {
                0
            }
        }
        2 -> {
            return if (wordList[0] == "read") {
                2
            } else if (wordList[0] == "delete") {
                3
            } else if (wordList[0] == "where") {
                7
            } else {
                0
            }
        }
        3 -> {
            return if (wordList[0] == "where") {
                7
            } else{
                0
            }
        }
        4 -> {
            return if (wordList[0] == "where") {
                7
            } else{
                0
            }
        }
        5 -> {
            return if (wordList[0] == "create" && floatCheck(wordList[3]) && floatCheck(wordList[4])) {
                1
            } else if (wordList[0] == "update" && floatCheck(wordList[3]) && floatCheck(wordList[4])) {
                6
            } else {
                0
            }
        }
        else -> return 0
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

fun create(s: String, data: Array<MutableList<Pair<String, DataClass>>>){
    val wordList = s.trim().split(" ").filter {  it != ""  }
    val hashCode = getHash(wordList[1], data.size)
    var tmpCurr = 1
    while (tmpCurr < data[hashCode].size){
        if (data[hashCode][tmpCurr].first == wordList[1]){
            println("Create: already exists")
            return
        }
        tmpCurr++
    }
    data[hashCode].add(Pair(wordList[1], DataClass(wordList[2], wordList[3].toFloat(), wordList[4].toFloat())))
    println("by value ${wordList[1]} making characteristics:\n-color: ${wordList[2]}\n-age: ${wordList[3].toFloat()}\n" +
            "-weight: ${wordList[4].toFloat()}")
}

fun read(s: String, data: Array<MutableList<Pair<String, DataClass>>>){
    val wordList = s.trim().split(" ").filter {  it != ""  }
    val hashCode = getHash(wordList[1], data.size)
    var tmpCurr = 1
    while (tmpCurr < data[hashCode].size){
        if (data[hashCode][tmpCurr].first == wordList[1]){
            println("characteristics of ${data[hashCode][tmpCurr].first}:\n-color: " +
                    "${data[hashCode][tmpCurr].second.color}\n" +  "-age: ${data[hashCode][tmpCurr].second.age}\n" +
                    "-weight: ${data[hashCode][tmpCurr].second.weight}")
            return
        }
        tmpCurr++
    }
    println("Read: not found")
    return
}

fun delete(s: String, data: Array<MutableList<Pair<String, DataClass>>>){
    val wordList = s.trim().split(" ").filter {  it != ""  }
    val hashCode = getHash(wordList[1], data.size)
    var tmpCurr = 1
    while (tmpCurr < data[hashCode].size){
        if (data[hashCode][tmpCurr].first == wordList[1]){
            data[hashCode].removeAt(tmpCurr)
            println("OK")
            return
        }
        tmpCurr++
    }
    println("Delete: not found")
    return
}

fun readall(data: Array<MutableList<Pair<String, DataClass>>>){
    for (i in data){
        var tmpCurr = 1
        while (tmpCurr < i.size){
            println("characteristics of ${i[tmpCurr].first}:\n-color: ${i[tmpCurr].second.color}\n" +
                    "-age: ${i[tmpCurr].second.age}\n" + "-weight: ${i[tmpCurr].second.weight}")
            tmpCurr++
        }
    }
}

fun update(s: String, data: Array<MutableList<Pair<String, DataClass>>>){
    val wordList = s.trim().split(" ").filter {  it != ""  }
    val hashCode = getHash(wordList[1], data.size)
    var tmpCurr = 1
    while (tmpCurr < data[hashCode].size){
        if (data[hashCode][tmpCurr].first == wordList[1]){
            data[hashCode][tmpCurr].second.color = wordList[2]
            data[hashCode][tmpCurr].second.age = wordList[3].toFloat()
            data[hashCode][tmpCurr].second.weight = wordList[4].toFloat()

            println("new characteristics of ${data[hashCode][tmpCurr].first}:\n-color: ${data[hashCode][tmpCurr].second.color}\n" +
                    "-age: ${data[hashCode][tmpCurr].second.age}\n" + "-weight: ${data[hashCode][tmpCurr].second.weight}")
            return
        }
        tmpCurr++
    }
    println("Update: not found")
    return
}

fun where(s: String, data: Array<MutableList<Pair<String, DataClass>>>){
    val wordList = s.trim().split(" ", "=").filter {  it != ""  }
    if (wordList[1] != "weight"){
        println("incorrect input")
        return
    }
    var tmpCount = 0
    for (i in data){
        var tmpCurr = 1
        while (tmpCurr < i.size){
            val sum =wordList[2].toFloat() - i[tmpCurr].second.weight
            if (abs(sum) < 0.001){
                println("characteristics of ${i[tmpCurr].first}:\n-color: ${i[tmpCurr].second.color}\n" +
                        "-age: ${i[tmpCurr].second.age}\n" + "-weight: ${i[tmpCurr].second.weight}")
                tmpCount++
            }
            tmpCurr++
        }
    }
    if (tmpCount == 0){
        println("Weight: not found cat with this weight")
    }
}