package top.ourfor

import top.ourfor.io.write
import org.junit.Test
import java.io.File

class MyTest {

    val data: String = "Hello World"
    val path: String = "/Users/catalina/Desktop/test.json"


    @Test
    fun testFile() {
        write(File(path),data)
    }
}
