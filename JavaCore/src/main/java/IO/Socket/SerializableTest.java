package IO.Socket;

import java.io.*;


public class SerializableTest
{
    static class Person implements Serializable
    {
        int age;
        String address;
        double height;
        public Person(int age, String address, double height)
        {
            this.age = age;
            this.address = address;
            this.height = height;
        }
        //JAVA BEAN自定义的writeObject方法
        private void writeObject(ObjectOutputStream out) throws IOException
        {
            System.out.println("writeObejct ------");
            out.writeInt(age);
            out.writeObject(new StringBuffer(address).reverse());
            out.writeDouble(height);
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
        {
            System.out.println("readObject ------");
            this.age = in.readInt();
            this.address = ((StringBuffer)in.readObject()).reverse().toString();
            this.height = in.readDouble();
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                "d:/data.txt"));
        Person p = new Person(25,"China",180);
        oos.writeObject(p);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                "d:/data.txt"));
        Person p1 = (Person) ois.readObject();
        System.out.println("age=" + p1.age + ";address=" + p1.address
                + ";height=" + p1.height);
        ois.close();
    }
}