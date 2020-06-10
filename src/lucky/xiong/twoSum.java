package lucky.xiong;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *  
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 题目来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 解题思路： https://sqhl.xyz/write
 */
public class twoSum {

    public static void main(String[] args) {
        int[] nums = randomIntArray(5);
        System.out.println(Arrays.toString(nums));
        int target = randomTarget(nums);

        // 暴力法
//        System.out.println(Arrays.toString(twoSum2Power(nums,target)));

        // 暴力法V2
        System.out.println(Arrays.toString(twoSum2PowerV2(nums,target)));


    }

    /**
     * 暴力法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2Power(int[] nums, int target) {
        //获取开始时间
        long startTime=System.nanoTime();
        for(int i = 0;i<nums.length;i++){
            for (int j=nums.length-1;j>i;j--){
                if (nums[i]+nums[j]==target){
                    //获取结束时间
                    long endTime=System.nanoTime();
                    System.out.println("程序运行时间： "+(endTime-startTime)+"纳秒");
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    /**
     * 暴力法 - 优化版本
     * 对元素有序排序，采用2分法查找根据目标值减去当前值，取对应的可能下标位置
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2PowerV2(int[] nums, int target) {

        // 排序
        // 下标映射
        int[] tmp = nums.clone();
        Arrays.sort(nums);
        System.out.println(
          "系统排序："+      Arrays.toString(nums)
        );

        twoSum2Hash(tmp,target);
        System.out.println(
                "快速排序："+      Arrays.toString(tmp)
        );
//        for (int i = 0 ;i<nums.length;i++){
//            // 获取需要的值
//            int diff = target-nums[i];
//            // 二分查找
//            int start = i;
//            int end = nums.length-1;
//            int mid = 0;
//
//        }

       // throw new IllegalArgumentException("No two sum solution");
        return new int[1];
    }

    /**
     * 两遍哈希表
     * @param nums
     * @param target
     * @return
     */
    public static void twoSum2Hash(int[] nums, int target) {

        // 排序
        Map<Integer,Integer> map =  arraySort(nums);
        // 测试下标是否正确
        for (Map.Entry<Integer,Integer> value : map.entrySet()){
            System.out.println("原下标:"+value.getKey() + " 排序后的下标："+value.getValue());
        }
//        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * 生成随机整数数组 ，包括负数， 不限重复
     * @param length 指定长度
     * @return
     */
    public static int[] randomIntArray(int length){
        Random random = new Random();
        int[] res = new int[length];
        for (int i=0 ;i<length-1;i++){
            res[i] = random.nextInt(10);
        }
        return res;
    }

    /**
     * 随机获取数组两个数之和 作为target
     * @param res
     * @return
     */
    public static int randomTarget(int[] res){
        int len = res.length;
        Random random = new Random();
        int i = random.nextInt(len);
        int j = random.nextInt(len);
        while (i==j){
            j = random.nextInt(len);
        }
        return res[i]+res[j];
    }


    /**
     * 暴力方法V2之 排序并对原数组下标排序
     * 获得两个数组
     * 例如 [0,3,1,2]
     * 排序后： [0,1,2,3]
     * 下标： [0,2,3,1]
     */
    public static Map<Integer,Integer> arraySort(int[] arr){
        int v = (new Double(arr.length * 1.4)).intValue();
        Map<Integer,Integer> map = new HashMap<>(v);
        // -1 保存为排序后的内容
        //map.put(-1,);
        // 开始排序
        sort(arr,0,arr.length-1,map);
        return map;
    }
    /**
     * 快排核心算法，递归实现
     * @param array
     * @param left
     * @param right
     */
    public static void sort(int[] array, int left, int right,Map<Integer,Integer> map) {
        if(left > right) {
            return ;
        }

        // base中存放基准数
        int base = array[left];
        int i = left, j = right;

        while(i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while(array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while(array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if(i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;

                map.put(i,j);
                map.put(j,i);
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // TODO:这里如何变更map的值？

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1,map);
        sort(array, i + 1, right,map);

    }
}

