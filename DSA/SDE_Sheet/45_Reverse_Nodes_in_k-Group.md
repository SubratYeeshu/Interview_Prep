# Reverse Nodes in k-Group

## Problem statement

- Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list, k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is. You may not alter the values in the list's nodes, only nodes themselves may be changed.

## Approach 1 : Recursion

Time complexity : O(N)
Auxiliary space : O(N / K)

```cpp
void reverse(ListNode* start, ListNode* end){
    ListNode* prev = NULL;
    ListNode* curr = start;
    ListNode* forward = curr;
    
    while(prev != end){
        forward = curr -> next;
        curr -> next = prev;
        prev = curr;
        curr = forward;
    }
    
}

ListNode* reverseKGroup(ListNode* head, int k) {
    if(!head || !head -> next || k == 1)return head;
    
    ListNode* start = head;
    ListNode* end = head;
    
    int inc = k - 1;
    while(inc--){
        end = end -> next;
        if(end == NULL)return head;  // Smaller than k len remaining (Can not reverse)
    }
    
    ListNode* reveresedKGroupList = reverseKGroup(end -> next, k);
    reverse(start, end);
    start -> next = reveresedKGroupList;
    return end;
}
```

## Approach 2 : Iterative

Time complexity : O(N)
Auxiliary space : O(1)

```cpp
void reverse(ListNode* start, ListNode* end){
    ListNode* prev = NULL;
    ListNode* curr = start;
    ListNode* forward = curr;
    
    while(prev != end){
        forward = curr -> next;
        curr -> next = prev;
        prev = curr;
        curr = forward;
    }
    
}

/*

    -> We want to maintain 4 pointers, beforeStart, start = beforeStart -> next, end, afterEnd(temp) = end -> next
    -> These pointers are used to reverse and connect back the linked list

*/
ListNode* reverseKGroup(ListNode* head, int k) {
    if(!head || !head -> next || k == 1)return head;
    
    ListNode* dummyNode = new ListNode(0);
    dummyNode -> next = head;
    
    ListNode* beforeStart = dummyNode;
    ListNode* end = head;
    int i = 0;
    
    while(end != NULL){
        i++;
        if(i % k == 0){
            // Setting up the pointers for future connection
            ListNode* start = beforeStart -> next;
            ListNode* temp = end -> next;
            
            
            // Reversing the k grouped elements
            reverse(start, end);
            
            // Connecting back
            beforeStart -> next = end;
            beforeStart = start;
            start -> next = temp;
            end = temp;
        }else end = end -> next;
    }
    
    return dummyNode -> next;
}
```