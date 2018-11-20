package baseObj;

public class ListNode
{
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public ListNode add(int value) {
        ListNode newNode = new ListNode(value);
        this.next = newNode;

        return newNode;
    }

    public ListNode addNode(ListNode newNode) {
        this.next = newNode;
        return newNode;
    }

    @Override
    public String toString() {
        ListNode head = this;
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }
        sb.append("null");

        return sb.toString();
    }

}
