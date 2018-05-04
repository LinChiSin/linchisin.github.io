import java.util.Scanner;

public class SeachInRotatedSortedArray81 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            int n=scanner.nextInt();
            int[]nums=new int[n];
            for (int i = 0; i < n; i++) {
                nums[i]=scanner.nextInt();
            }
            int target=scanner.nextInt();
            System.out.println(search(nums,target));
        }

    }


    /*
    描述：在O(lgn）时间复杂度内，针对升序数组旋转后的数组，判断能否找到目标值
    思路：找出机制
     */
    public static boolean search(int[] nums, int target) {



        return false;
    }
}
