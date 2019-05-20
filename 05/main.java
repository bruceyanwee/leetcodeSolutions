/*给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"*/
public class main{
	public static void main(String[] args) {
		String s = "a";
		String rst;
		Solution sl = new Solution();
		rst = sl.longestPalindrome_3(s);
		System.out.println(rst);
	}
}
//思路：1:列举子序列 2:判断是否回文
class Solution {
    public String longestPalindrome(String s) {
        String str_longest =new String();
        int max_length_temp = 0;
        int length =0;
        for (int i =0;i< s.length();i++) {
        	for (int j = i+1;j<=s.length();j++) {//s[i,j]
        		//substring(0,1)就是第一个字母
        		String str_index = s.substring(i,j);
        		length = j - i;
        		if (isPalindrome(str_index)) {        	
        			if (length > max_length_temp) {
        				//需要更新最长
        				str_longest = str_index;
        				max_length_temp = length;
        			}
        		}        		
        	}
        }
        return str_longest;
    }
    private boolean isPalindrome(String s){
    	int head = 0;
    	int tail = s.length()-1;
    	boolean flag = true;
    	while(tail > head){
    		if (s.charAt(head) != s.charAt(tail)){
    			flag = false;
    			break;
    		}
    		tail--;
    		head++;
    	}
    	return flag;
    }

    //中心扩展法，该方法思路很简单，遍历回文序列的轴
    //相当于在构造子序列的时候就判断了是否回文，所以比上次暴力法少一个n的数量级  
    public String longestPalindrome_2(String s) {
    	//分成奇偶两种情况
        String str_longest =new String();
        int max_length_temp = 0;
        int length_o =0,length_j=1;//length_j表示"aba"则length_j=2，
        int index_o=0,index_j=0;
        if (s.length()==0){
            return "";
        }
        //第一次遍历，中间数作为轴，奇数个char，初始长度为1 ，
        for (int i = 0;i<s.length();i++ ) {
            int head = i;
            int tail =i;
            length_j = 0;
            while( head >= 0 && tail <= s.length()-1){
                if (s.charAt(head) != s.charAt(tail)) {
                    break;
                }
                length_j++;
                head--;
                tail++;
            }
            if (length_j > max_length_temp) {
                index_j  = i;
                max_length_temp = length_j;
            }
        }
        length_j = max_length_temp;
        max_length_temp =0;
        //System.out.println("index_j: "+ index_j + "length_j: "+ length_j);
        //第二次遍历，两边是对称的，偶数个char，
        for (int i = 0;i<s.length()-1;i++ ) {
            int head = i;
            int tail =i+1;//head的右边为中线
            length_o = 0;
            while( head >= 0 && tail <= s.length()-1){
                if (s.charAt(head) != s.charAt(tail)) {
                    break;
                }
                length_o++;
                head--;
                tail++;
            }
            if (length_o > max_length_temp) {
                index_o  = i;
                max_length_temp = length_o;
            }
        }
        length_o = max_length_temp;
        
        if (2*length_o > 2*length_j - 1) {//字串总长度
            return s.substring(index_o - length_o + 1,index_o + length_o + 1);
        }else{
            return s.substring(index_j - length_j + 1,index_j + length_j);
        }
    }
    public String longestPalindrome_3(String s) {
    	int n = s.length();
    	String res = "";
    	boolean[][] is_P = new boolean[n][n];
		for (int i=0;i<n; i++) {
		    for (int j=i; j>=0; j--) {
		    	if (s.charAt(i)==s.charAt(j) && (i-j <= 1 || is_P[i-1][j+1])){
		    		is_P[i][j] = true;
		    	}
		    	if (is_P[i][j] && (i-j+1 > res.length())){
		    		res = s.substring(j,i+1);
		    	}
		    	
		    }
		} 
		return res;   
	}
}
//中心扩展法，该方法思路很简单，遍历回文序列的轴




