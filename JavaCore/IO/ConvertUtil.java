package IO;

/*做一个功能验证，要用到inputStream与outputStream的转换，本以为很简单的东东
搞了蛮久，从"程序员 闫帆"处取得一段代码*/

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ConvertUtil {
    //inputStream转outputStream
    public ByteArrayOutputStream parse(InputStream in) throws Exception {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
            swapStream.write(ch);
        }
        return swapStream;
    }

    //outputStream转inputStream
    public ByteArrayInputStream parse(OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    //inputStream转String
    public String parse_String(InputStream in) throws Exception {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int ch;
        while ((ch = in.read(buffer)) != -1) {
            swapStream.write(ch);
        }
        return swapStream.toString();
    }

    //OutputStream 转String
    public String parse_String(OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream.toString();
    }

    //String转inputStream
    public ByteArrayInputStream parse_inputStream(String in) throws Exception {
        ByteArrayInputStream input = new ByteArrayInputStream(in.getBytes());
        return input;
    }

    //String 转outputStream
    public ByteArrayOutputStream parse_outputStream(String in) throws Exception {
        return parse(parse_inputStream(in));
    }

    static class Test_InputStream {

        /**
         * 获取字节流
         *
         * @param url
         * @return
         */
        private String getStream(String url) {
            //获取字节流
            InputStream in = null;
            String result = "";
            try {
                in = new URL(url).openStream();
                int tmp;
                while ((tmp = in.read()) != -1) {
                    result += tmp;
                }
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //输出字节流
            return result;
        }

        public static void main(String[] args) {

            String URL = "http://www.baidu.com";
            Test_InputStream test = new Test_InputStream();
            System.out.println(test.getStream(URL));

        }
    }

    static class Test_InputStreamReader {

        /*
         * 得到字符流前需先有字节流
         */
        private String getStream(String url) {
            try {
                //得到字节流
                InputStream in = new URL(url).openStream();
                //将字节流转化成字符流，并指定字符集
                InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                String results = "";
                int tmp;
                while ((tmp = isr.read()) != -1) {
                    results += (char) tmp;
                }
                return results;

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        /**
         * @param args
         */
        public static void main(String[] args) {
            // TODO Auto-generated method stub
            String URL = "http://www.baidu.com";
            Test_InputStreamReader test = new Test_InputStreamReader();
            System.out.println(test.getStream(URL));
        }

    }

    static class Test_BufferedReader {

        /*
         * 字节流——字符流——缓存输出的字符流
         */
        private String getStream(String url) {
            try {
                //得到字节流
                InputStream in = new URL(url).openStream();
                //将字节流转化成字符流，并指定字符集
                InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                //将字符流以缓存的形式一行一行输出
                BufferedReader bf = new BufferedReader(isr);
                String results = "";
                String newLine = "";
                while ((newLine = bf.readLine()) != null) {
                    results += newLine + "\n";
                }
                return results;

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        /**
         * @param args
         */
        public static void main(String[] args) {
            // TODO Auto-generated method stub
            String URL = "http://www.baidu.com";
            Test_BufferedReader test = new Test_BufferedReader();
            System.out.println(test.getStream(URL));
        }
    }
}