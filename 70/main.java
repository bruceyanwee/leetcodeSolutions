public class main{
    public static void main(String[] args) {
        int n =5;
        int rst;
        Solution sl = new Solution();
        rst = sl.climbStairs(n);
        System.out.println(rst);
    }
}
class Solution {
    public int[] memo;
    public int climbStairs(int n) {
        memo = new int[n+1];
        for (int i=0;i<n+1 ;i++ ) {
            memo[i]=-1;
        }
        memo[0]=1;
        memo[1]=1;
        //memo[2]=2;
        return f2(n);
    }
    //直接递归，复杂度太高
    private int f1(int n ){
        if (n == 1 || n == 0) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return f1(n-1) + f1(n-2);
    }
    //动态规划 自顶向下
    private int f2(int n){
        if (n == 0 || n == 1) {
            return 1;
        }
        if (memo[n] != -1) {
            return memo[n-1]+memo[n-2];
        }else{
            memo[n] = f2(n-2)+f2(n-1);
            return memo[n];
        }
    }
    //动态规划，自下向上
    private int f3(int n) {
        int[] local_memo = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            if (i == 0 || i == 1) {
                local_memo[i] = 1;
            }else{
                local_memo[i] = local_memo[i - 1] + local_memo[i - 2];
            }
        }
        return local_memo[n];
    }

}