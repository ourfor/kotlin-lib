package top.ourfor

import top.ourfor.io.write
import org.junit.Test
import top.ourfor.net.http.download
import top.ourfor.net.http.get
import java.io.File

class MyTest {

    val data: String = "/Users/catalina/Desktop/樱花粉的浪漫.flac"
    val url: String = "https://api.ourfor.top/blog/essays"


    @Test
    fun testFile(): Unit {
        println(get(url))
    }

}
