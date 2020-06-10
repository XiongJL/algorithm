package lucky.xiong;
import java.math.*;
import java.util.*;
public class SortedIndex {
    public static int maxn=10000+10;
    public static int a[]=new int[maxn];
    public static int index[]=new int[maxn];

    //java中使用快排对数组下标进行排序
    public static void sort(int[] index,int low,int height){
        int i=low;
        int j=height;
        if (i>j) {    //放在k之前，防止下标越界
            return;
        }
        int k=a[index[i]];

        while (i<j) {
            while (i<j&&a[index[j]]>k) {    //找出小的数
                j--;
            }
            while (i<j&&a[index[i]]<=k) {  //找出大的数
                i++;
            }
            if(i<j){   //交换
                int swap=index[i];
                index[i]=index[j];
                index[j]=swap;
            }

        }
        //交换K
        int temp=index[i];
        index[i]=index[low];
        index[low]=temp;
        //k=a[i];
        //a[i]=a[low];
        //a[low]=k;

        //对左边进行排序,递归算法
        sort(index, low, i-1);
        //对右边进行排序
        sort(index,i+1,height);
    }

    public static void main(String []args) {
        Scanner input=new Scanner(System.in);
        while(input.hasNext()) {
            int n=input.nextInt();
            for(int i=1;i<=n;i++) {
                a[i]=input.nextInt();
                index[i]=i;
            }
            sort(index,1,n);
            for(int i=1;i<=n;i++) {
                System.out.println(index[i]);
            }
        }

    }
}