import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution45 {
    public int jump(int[] nums) {
        int current = 0;
        int jump = 0;
        while (current < nums.length - 1) {
            int max = 0;
            int j=0;
            for (int i=1;i<=nums[current];i++) {
                if (i+current >= nums.length - 1) {
                    j = i;break;
                } else if (i+nums[i+current] > max) {
                    j = i;
                    max = i + nums[i+current];
                }
            }
            current = current + j;
            jump++;
        }
        return jump;
    }
}


 class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            int ret = new Solution45().jump(nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
