package demo

import java.io.File
import java.io.FileInputStream
import java.io.OutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * zip 工具类
 */
object ZipUtils {

    private const val BUFFER_SIZE = 2 * 1024

    fun zip(srcFile: File, out: OutputStream) {
        val zos = ZipOutputStream(out)
        compress(srcFile, srcFile.name, zos)
        zos.flush()
        zos.close()
    }

    private fun compress(srcFile: File, name: String, zos: ZipOutputStream) {
        val buf = ByteArray(BUFFER_SIZE)
        if (srcFile.isFile) {
            zos.putNextEntry(ZipEntry(name))
            val `in` = FileInputStream(srcFile)
            var len: Int
            do {
                len = `in`.read(buf)
                if(len!= -1){
                    zos.write(buf, 0, len)
                }
            } while (len != -1)
            zos.closeEntry()
            `in`.close()
        } else {
            val childFiles = srcFile.listFiles()
            for (file in childFiles) {
                compress(file, name + File.separator + file.name, zos)
            }
        }
    }
}