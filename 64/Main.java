package com.company;

public class Main{
    public static void main(String[] args) {
        int rst;
        int[][] grid = {{1,2,3},{1,1,4},{2,4,1}};
        Solution sl = new Solution();
        rst = sl.minPathSum(grid);
        System.out.println(rst);
    }
}
class Solution {
    public int[][] st_grid;
    public int[][] min_map;
    public int count;
    public int minPathSum(int[][] grid) {
        int res =0;
        int m = grid.length;
        int n = grid[0].length;
        st_grid = new int[m][n];
        min_map = new int[m][n];
        for (int i=0;i<m;i++ ) {
            for (int j =0;j< n;j++ ) {
                st_grid[i][j] = grid[i][j];
                min_map[i][j] = -1;
            }
        }
        count =0;
        res = min2(m,n);
        //res = min(m-1,n-1);
        //System.out.println("count: "+count);
        return res;

    }
    //自顶向下，递归的
    private int min(int r,int c){//
        count++;
        if (r != 0 && c != 0) {
            int a=0;
            int b=0;
            if (min_map[r-1][c] != -1)//计算过的
                a = min_map[r-1][c]+st_grid[r][c];
            else a = min(r-1,c)+st_grid[r][c];
            if (min_map[r][c-1] != -1)//计算过的
                b = min_map[r][c-1]+st_grid[r][c];
            else b = min(r,c-1)+st_grid[r][c];
            if (a<b) {
                min_map[r][c] = a;
                return a;
            }else{
                min_map[r][c] = b;
                return b;
            }
        }else{
            //起点，递归终点
            if (r == 0 && c == 0) {
                min_map[0][0] = st_grid[0][0];
                return min_map[0][0];
            }
            //上边界
            else if (r == 0) {//r==0
                if (min_map[r][c-1] != -1)
                    return min_map[r][c-1] + st_grid[r][c];
                else {
                    min_map[r][c] = min(r, c - 1) + st_grid[r][c];
                    return min_map[r][c];
                }
            }
            //左边界
            else{//c==0
                if (min_map[r-1][c] != -1)
                    return min_map[r-1][c] + st_grid[r][c];
                else {
                    min_map[r][c] = min(r-1, c) + st_grid[r][c];
                    return min_map[r][c];
                }
            }
        }
    }
    //自下而上解决，类似斐波那契数列一样，构造一个查询表；
    //1：从左上角出发，用到达该格子的最小路径值填满格子；
    private int min2(int r,int c){
        for (int i =0;i<r;i++){
            for (int j=0;j<c;j++){
                //最上面一行
                if (i == 0){
                    if (j==0) {
                        min_map[i][j] = st_grid[0][0];
                    }else {
                        min_map[i][j] = min_map[i][j-1]+st_grid[i][j];
                    }
                }
                //左边一列
                else if (j == 0){
                    min_map[i][j] = min_map[i-1][j] + st_grid[i][j];
                }
                //中间的那些点（i ！= 0 && j ！= 0）
                else {
                    int up = min_map[i-1][j];
                    int left = min_map[i][j-1];
                    if (up<left){
                        min_map[i][j] = up + st_grid[i][j];
                    }else {
                        min_map[i][j] = left + st_grid[i][j];
                    }
                }
            }
        }
        return min_map[r-1][c-1];
    }
}