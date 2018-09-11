package IO.NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class DEMO {
    static class test1 {
        public static void main(String[] args) throws IOException {
            RandomAccessFile aFile = new RandomAccessFile(DEMO.class.getClassLoader().getResource("nio/nio-data.txt").getPath(), "rw");
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf); //读取48个字符
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip(); //flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }

                buf.clear();  //清空
                bytesRead = inChannel.read(buf); //重新读取，然后循环
            }
            aFile.close();
        }
    }

    static class test2 {
        public static void main(String[] args) throws IOException {
            SocketChannel channel = SocketChannel.open();
            channel.connect(new InetSocketAddress("http://jenkov.com", 80));
            Selector selector = Selector.open();
            channel.configureBlocking(false);
            SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
            while(true) {
                int readyChannels = selector.select();
                if(readyChannels == 0) continue;
                Set selectedKeys = selector.selectedKeys();
                Iterator keyIterator = selectedKeys.iterator();
                while(keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if(key.isAcceptable()) {
                        // a connection was accepted by a ServerSocketChannel.
                    } else if (key.isConnectable()) {
                        // a connection was established with a remote server.
                    } else if (key.isReadable()) {
                        // a channel is ready for reading
                    } else if (key.isWritable()) {
                        // a channel is ready for writing
                    }
                    keyIterator.remove();
                }
            }
        }
    }
}
