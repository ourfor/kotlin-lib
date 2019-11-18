package top.ourfor.io

import java.io.*


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

/**
 * @param file the file you want to read
 * @param encoding the text file encoding, or open this file with this encoding
 * @return the content of text file
 */
@Throws(IOException::class)
fun read(file: File,encoding: String): String {
    val reader = BufferedReader(InputStreamReader(FileInputStream(file),encoding))
    return reader.readText()
}

/**
 * @param file the file you want to read, open file with default encoding utf-8
 * @return the content of text file
 */
@Throws(IOException::class)
fun read(file: File): String {
    return read(file,"UTF-8")
}
