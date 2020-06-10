package lucky.xiong;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。(XIong: 不进行该假设)
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * @author XiongJl
 * @date 2020/6/2 9:42
 * @url 个人博客 没了！艹，覆盖文章了
 */
public class addTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(0);
        ListNode l2 = new ListNode(3);

        ListNode res = addTwoNumbers(l1,l2);

        System.out.println(res);
    }

    public static ListNode addTwoNumbers(ListNode l1 , ListNode l2){

        // 返回结果 ,res 用于保存头指针，它的next才是实际值
        ListNode res = new ListNode(0);
        ListNode cur = res;
        // 进位值
        int putNext = 0;
        while(l1!=null || l2!=null) {
            // 加上上一循环的进位值
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + putNext;
            if (sum<10){
                putNext = 0 ;
            }else{
                // 超10 进一
                putNext = 1;
                // 和保留个位数
                sum = sum % 10;
            }
            // 更改当前链表值, 但注意实际上是res的next指针为第一个节点
            cur.next = new ListNode(sum);
            // 指向下一个node
            cur = cur.next;

            // 获取参与计算的下一个node
            if (l1!=null){
                l1 =l1.next;
            }
            if (l2!=null){
                l2 = l2.next;
            }

        }
        // 还有进位
        if (putNext>0){
            cur.next = new ListNode(putNext);
        }
        return res.next;
    }
    /**
     * 链表类
     */
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
}
