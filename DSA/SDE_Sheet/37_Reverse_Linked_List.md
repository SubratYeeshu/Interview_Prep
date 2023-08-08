# Reverse Linked List

## Problem statement 

- Given the head of a singly linked list, reverse the list, and return the reversed list.

## Approach 1 : Iterative

Time complexity : O(N)  
Auxiliary space : O(1)

```cpp
ListNode* reverseIterative(ListNode* head){
    if(!head)return NULL;
    ListNode* prev = NULL;
    ListNode* curr = head;
    ListNode* forward = head;
    
    while(curr != NULL){
        forward = curr -> next;
        curr -> next = prev;
        prev = curr;
        curr = forward;
    }

    return prev;
}
```

## Approach 2 : Recursive

Time complexity : O(N)  
Auxiliary space : O(N) recursive stack space

```cpp
ListNode* reverseRecursive(ListNode* head){
    if(!head || !head -> next)return head;
    
    ListNode* reverseListHead = reverseRecursive(head -> next);
    head -> next -> next = head;
    head -> next = NULL;
    
    return reverseListHead;
}
```