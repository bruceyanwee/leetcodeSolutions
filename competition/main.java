public class main{
    public static void main(String[] args) {
        int[][] arr = {{1,3},{-2,2}}; 
        int K =1;
        int[][] rst = new int[K][2];
        Solution sl = new Solution();
        rst = sl.kClosest(arr,K);
        for (int i =0;i<rst.length ;i++ ) {
            System.out.println("["+rst[i][0]+"],"+"["+rst[i][1]+"]");
        }
    }
}
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        int[][] val = new int[n][2];
        int[][] res = new int[K][2];
        for(int i =0;i<n;i++){
            val[i][0] = i;
            val[i][1] = points[i][0]*points[i][0] + points[i][1]*points[i][1];
        }
        int t =K;
        int head = 1;
        while(K > 0){
            for(int i=n-1;i >= head ;i--){
                if(val[i][1] < val[i-1][1]){
                    int temp_val = val[i][1];
                    int temp_index = val[i][1];
                    val[i][1] = val[i-1][1];
                    val[i][0] = val[i-1][0];
                    val[i-1][1] = temp_val;
                    val[i-1][0] = temp_index;
                }
            }
            t--;
            head++;
        }
        for(int i =0;i<K-1;i++){
            int index = val[i][0];
            res[i][0] = points[index][0];
            res[i][1] = points[index][1];
        }
        return res;
    }
    
}