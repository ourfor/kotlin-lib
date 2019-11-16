package top.ourfor.net.http

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

/**
 * @param url the url of resource (eg. https://file.example.com/test.mp3)
 * @param path the file path that you want to save file (eg. /example/audio/music.mp3)
 * @return void
 * @author ourfor
 */
fun download(url: String,path: String) {
    val file = File(path)
    try {
        val uri = URL(url)
        val conn: HttpsURLConnection = uri.openConnection() as HttpsURLConnection
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
        conn.connectTimeout = 5 * 1000

        val fis = conn.inputStream
        val fos = FileOutputStream(file)

        val bytes = ByteArray(1024)

        var position = fis.read(bytes)
        while (position != -1) {
            fos.write(bytes, 0, position)
            position = fis.read(bytes)
        }

        println("下载完成")

        fis.close()
        fos.close()
    } catch(e: MalformedURLException){
        println("发生异常: 请输入正确的网址")
    } catch(e: Exception){
        println(e)
    }
}

/**
 * @param url send the request to url
 * @return result is string or null
 * @author ourfor
 */
fun get(url: String): String? {
    val client = OkHttpClient()
    val req = Request.Builder().url(url).build()
    var result: String? = null
    try {
        val res = client.newCall(req).execute()
        result = res.body?.string()
    } catch (e: Exception) {
        println(e)
    } finally {
        return result
    }
}