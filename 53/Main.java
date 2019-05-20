public class Main{
    public static void main(String[] args) {
        int rst;
        int[][] grid = {{1,2,3},{1,1,4},{2,4,1}};
        Solution sl = new Solution();
        rst = sl.maxSubArray(grid);
        System.out.println(rst);
    }
}
class Solution {
    public int maxSubArray(int[] nums) {
        return f1(nums);
    }
    //暴力求解法--枚举 O(n2)
    private int f1(int[] nums){
        int max_sum = nums[0];
        for (int i =0;i<nums.length;i++){
            int sum = 0;
            for (int j =i;j<nums.length;j++){
                sum += nums[j];
                if (sum > max_sum){
                    max_sum = sum;
                }
            }
        }
        return max_sum;
    }
    //动态规划，DP这类题目都比较巧妙，一开始都是很难下手，
    //1：是否可以分成子问题
    //2：能分成子问题，自顶向下--递归，思考方便 eg：求maxSubArray(m,n)，那么maxSubArray（m,n-1）,再去看nums[n]；
    //3：自底向上 -- 编程实现方便；
    private int f2(int[] nums){
        int[] sum = new int[nums.length];//sum[i]表示从0...i 的最大子序和；
        int max_sum = nums[0];
        sum[0] = nums[0];
        for (int i =1;i<nums.length;i++){
            sum[i] = max(sum[i-1]+nums[i],nums[i]);
            if (sum[i]>max_sum)
                max_sum = sum[i];
        }
        return max_sum;
    }
    private int max(int a,int b){
        if (a>b) return a;else return b;
    }
}