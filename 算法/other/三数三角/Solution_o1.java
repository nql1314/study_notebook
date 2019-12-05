import java.util.Random;

public class Solution_o1 {
    int nums[];

    public int getPeak(int[] nums) {
        this.nums = nums;
        if (nums == null || nums.length == 0) return -1;
        int len = nums.length;
        return G(len, 0);
    }

    private int G(int height, int pos) {
        if (height == 1) return nums[pos];
        else if (height <= 3) {
            return findAnother(G(height - 1, pos), G(height - 1, pos + 1));
        } else {
            int m = 1;
            while (m * 3 < height) m *= 3;
            return findAnother(G(height - m, pos), G(height - m, pos + m));
        }
    }

    private static int findAnother(int a, int b) {
        return a == b ? a : 3 - a - b;
    }

    public static void main(String[] args) {
        prove();
        int[] nums = new int[1024 * 1024];
//        int[] nums = {0,2,1,2,0,1,0,1,2,0};
        for (int i = 0; i < nums.length; i++) {
            nums[i] = new Random().nextInt(3);
        }
        long start = System.currentTimeMillis();
        System.out.println("result:" + new Solution_o1().getPeak(nums) + "       time:" + (System.currentTimeMillis() - start));
    }

    // 证明4个数字的顶角等于边上两个数字的解
    private static void prove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int m = 0; m < 3; m++) {
                        int G[][] = new int[4][4];
                        G[0][0] = i;
                        G[0][1] = j;
                        G[0][2] = k;
                        G[0][3] = m;
                        for (int x = 1; x < 4; x++) {
                            for (int y = 0; y < 4 - x; y++) {
                                G[x][y] = findAnother(G[x - 1][y], G[x - 1][y + 1]);
                            }
                        }
                        if (G[3][0] != findAnother(G[0][0], G[0][3])) {
                            System.out.println("not proved");
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("proved");
    }
}
