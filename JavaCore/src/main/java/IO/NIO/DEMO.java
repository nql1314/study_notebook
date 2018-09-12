package IO.NIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.*;
/**
 * nio demos
 */
public class DEMO {
    /**
     * FileChannel
     */
    static class test1 {
        public static void main(String[] args) throws IOException {
            RandomAccessFile aFile = new RandomAccessFile("C:\\github\\java_study\\JavaCore\\src\\main\\resources\\nio\\nio-data.txt", "rw");

            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf); //从channel读取48个字符到buffer
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip(); //flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }

                buf.clear();  //清空
                bytesRead = inChannel.read(buf); //重新读取，然后循环
            }

            File file = new File("C:\\github\\java_study\\JavaCore\\src\\main\\resources\\nio\\nio-data.txt");
            FileOutputStream outputStream = new FileOutputStream(file,true);
            FileChannel channel = outputStream.getChannel();
            String newData = "New String to write to file..." + System.currentTimeMillis()+"\n";

            ByteBuffer buf2 = ByteBuffer.allocate(48);
            buf2.clear();
            buf2.put(newData.getBytes());

            buf2.flip();

            while(buf2.hasRemaining()) {
                channel.write(buf2);
            }
            channel.close();
            aFile.close();
            outputStream.close();
        }
    }

}
