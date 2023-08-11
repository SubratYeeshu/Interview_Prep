# Linked List Cycle II

## Problem statement

- Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null. There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter. Do not modify the linked list.

## Approach 1 : Floyd's detection 

Time complexity : O(N)
Space complexity : O(1)

```cpp
/*

-> Proof

   ptr1
    |    m      
    |<-------><------------  k
    V___________________   |
           -->|        |   |
           |  |   n    |   V
           |  | rounds | <--- ptr2
           |  |________|   ^
           |_______________|
          d
       
        Distance travelled by slow pointer = m + k + n*x1
        Distance travelled by fast pointer = m + k + n*x2
        Equation 1 : 2(m + k + n*x1) = m + k + n*x2
        resolving : n(x1 - 2x2) - m = k
        ny - k = m
        n(y - 1) + (n - k) = m
        n(y - 1) + d - m
        nz + d = m
        
        nz + d distance travelled by ptr2 and m distance travelled by ptr1
        
        Alternate Prooef : 
        
        slow = L1 + L2 (m + k)
        fast = L1 + L2 + nC (n rounds with c distance)
        
        we can write 2slow = fast
        L1 = nC - L2 (nc - l2 is d distance taken by slow pointer)

*/
ListNode *detectCycle(ListNode *head) {
    if(head == NULL || head->next == NULL)return NULL;
    ListNode* slow = head;
    ListNode* fast = head;
    
    while(fast != NULL && fast -> next != NULL){
        slow = slow -> next;
        fast = fast -> next -> next;
        if(slow == fast)break;
    }
    
    // No cycle
    if(fast == NULL || fast -> next == NULL)return NULL;
    
    ListNode* ptr1 = head;
    ListNode* ptr2 = slow;
    
    while(ptr1 != ptr2){
        ptr1 = ptr1 -> next;
        ptr2 = ptr2 -> next;
    }
    return ptr1;
    
}
```