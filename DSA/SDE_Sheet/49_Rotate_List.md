# Rotate List

## Problem statement

- Given the head of a linked list, rotate the list to the right by k places.

## Approach 1 : 

Time complexity : O(N) 
Auxiliary space : O(1)

```cpp
ListNode* rotateRight(ListNode* head, int k) {
    if(head == NULL || head->next == NULL || k == 0) return head;
    
    ListNode* curr = head;
    int len = 1;
    
    while(curr -> next != NULL){
        curr = curr -> next;
        len++;
    }
    
    k %= len;
    k = len - k;
    curr -> next = head;
    
    while(k--)curr = curr -> next;
    head = curr -> next;
    curr -> next = NULL;
    
    return head;
}
```