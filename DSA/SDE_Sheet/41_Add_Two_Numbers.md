# Add two numbers

## Problem statement

- You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list. You may assume the two numbers do not contain any leading zero, except the number 0 itself.

## Approach 1 

Time complexity : O(N) 
Space complexity : O(N)

```cpp
ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
    ListNode* ans = new ListNode(-1);
    ListNode* temp = ans;
    
    int carry = 0;
    while(l1 || l2 || carry){
        int sum = (l1 != NULL ? l1 -> val : 0) + (l2 != NULL ? l2 -> val : 0) + carry;
        carry = sum / 10;
        sum %= 10;
        
        ListNode* newNode = new ListNode(sum);
        ans -> next = newNode;
        ans = ans -> next;
        
        if(l1)l1 = l1 -> next;
        if(l2)l2 = l2 -> next;
    }
    return temp -> next;
}
```