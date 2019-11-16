package top.ourfor.io

import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.BufferedWriter
import java.io.IOException
import java.io.File


/**
 * @param file the file that you want write data to
 * @param content data you want to save
 * @param encoding save the file with encoding
 * @return void
 * @author author
 */
@Throws(IOException::class)
fun write(file: File, content: String, encoding: String) {
    file.delete()
    file.createNewFile()
    val writer = BufferedWriter(OutputStreamWriter(
            FileOutputStream(file), encoding))
    writer.write(content)
    writer.close()
}

/**
 * @param file the file that you want write data to, encoding is utf-8
 * @param content the data you want to save
 * @return void
 * @author ourfor
 */
@Throws(IOException::class)
fun write(file: File, content: String) {
    write(file, content, "UTF-8")
}
