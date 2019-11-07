import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String[] s1 = scanner.nextLine().split(" ");
            Arrays.sort(s1);
            for(int i=0;i<s1.length;i++) {
                if (i == s1.length -1) {
                    System.out.println(s1[i]);
                } else {
                    System.out.print(s1[i] + " ");
                }
            }
        }
    }
}