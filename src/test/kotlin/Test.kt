package top.ourfor

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.Test
import top.ourfor.crypto.Md5
import top.ourfor.crypto.uuid
import top.ourfor.encoding.json.stringify

class MyTest {

    @Test
    fun testFile(): Unit {
        println("Test")
        val mapper = jacksonObjectMapper()
        println(mapper.writeValueAsString(Data(100,"test")))
        val id = Md5.md5HexBuff("8888888",null)?.uuid()
        println(id)
        println(stringify(Data(200,"good night")))
    }

}

private fun String.uuid() {
    println(this)
}


data class Data (
        val id: Int,
        val tag: String
)
