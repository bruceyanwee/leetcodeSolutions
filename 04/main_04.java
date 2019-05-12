import java.util.LinkedList;
import java.util.Queue;
import java.util.Comparator;
import java.util.PriorityQueue;
public class main_04{
	public static void main(String[] args) {
		int[] nums1 = new int[]{1,3};
		int[] nums2 = new int[]{2,4};
		Solution sl = new Solution();
		double median = sl.findMedianSortedArrays_2(nums1,nums2);
		System.out.println(median);
	}
}
//两个数组依次弹出元素
//即把两个数组封装成一个队列，依次弹出最小元素，弹到index = 中位数即可
//目前时间复杂度为O（m+n）,就是新建一个sort_queue的时间
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //第一种方式，解决问题！重新开辟的空间存储
    	int l1 = nums1.length;
    	int l2 = nums2.length;
    	int length = l1 + l2;
    	int index1=0,index2=0;
    	double m;
    	int[] sort_queue = new int[length];
    	for (int i =0;i<length;i++) {
    		//边界条件
    		 if (index1 == l1 || index2 == l2) {
    		 	break;
    		 }
			 if (nums1[index1] < nums2[index2]) {//把小的先入队列
			 	sort_queue[index1+index2] = nums1[index1];
			 	index1++;
			 }else{
			 	sort_queue[index1+index2] = nums2[index2];
			 	index2++;
			 }
			 
    	}

    	if (index1 == l1) {//说明l1 全部入队列了
    		for (int i=index1+index2;i<length;i++) {
    			sort_queue[i] = nums2[index2];
    			index2++;
    		}
    	}else if (index2 == l2) {
    		for (int i=index1+index2;i<length;i++) {
    			sort_queue[i] = nums1[index1];
    			index1++;
    		}
    	}
    	if (length % 2 == 0) {//偶数，那么中位数就是中间两个的平均 index_l = (l1+l2)/2 -1,index_r = index_l+1
    		m = (double)(sort_queue[length/2 -1] + sort_queue[length/2])/2;
    	}else{//奇数，中位数就是 index = (L-1)/2
    		m = sort_queue[(length-1)/2];
    	}
        return m;
    }
    //把内存再节省一点，不用移动数据，直接操作指针就行，index1 
    public double findMedianSortedArrays_2(int[] nums1, int[] nums2){
    	int l1 = nums1.length;
        int l2 = nums2.length;
        int length = l1 + l2;
        int index1=0,index2=0;
        double m;
        int[] sort_queue = new int[length/2+1];
        for (int i =0;i<length/2+1;i++) {
            //边界条件
            if (index1 == l1) { //nums1 全部已加入
                sort_queue[index2+index1] = nums2[index2];
                index2++;
            }else if (index2 == l2){ //nums2 全部已加入
                sort_queue[index1+index2] = nums1[index1];
                index1++;
            }else if (nums1[index1] < nums2[index2]) {//把小的先入队列
                sort_queue[index1+index2] = nums1[index1];
                index1++;
            }else {
                sort_queue[index1+index2] = nums2[index2];
                index2++;
            }

        }
        ;
        if (length % 2 == 0) {//偶数，那么中位数就是中间两个的平均 index_l = (l1+l2)/2 -1,index_r = index_l+1
            m = (sort_queue[length/2 -1] + sort_queue[length/2])/2.0;
        }else{//奇数，中位数就是 index = (L-1)/2
            m = sort_queue[(length-1)/2];
        }
        return m;
    }
    
}
/*There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run 

time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5*/