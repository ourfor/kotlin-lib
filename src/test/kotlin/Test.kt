package top.ourfor

import org.junit.Test
import top.ourfor.io.read
import java.io.File

class MyTest {

    val path = "/Users/catalina/Desktop/json/UI设计师.json"


    @Test
    fun testFile(): Unit {
        println(read(File(path)))
    }

}
