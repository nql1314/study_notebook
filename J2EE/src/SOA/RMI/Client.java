package SOA.RMI;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) {
        try {
            GreetService greetService = (GreetService) Naming.lookup("rmi://10.108.1.138:1098/GreetService");
            System.out.println(greetService.sayHello("Jobs"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}