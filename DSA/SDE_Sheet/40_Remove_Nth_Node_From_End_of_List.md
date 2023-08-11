# Remove Nth Node From End of List

## Problem statement

- Given the head of a linked list, remove the nth node from the end of the list and return its head.

## Approach 1 : Two Pass

Time complexity : O(2N) 
Auxiliary space : O(1)

```cpp
int getlen(ListNode* head){
    if(!head)return 0;
    int c = 0;
    while(head != NULL){
        c++;
        head = head -> next;
    }
    return c;
}
ListNode* removeNthFromEnd(ListNode* head, int n) {
    int len = getlen(head);
    ListNode* temp = head;
    
    for(int i = 0 ; i < len - n - 1 ; i++)temp = temp -> next;
    
    if(len == n){
        temp = temp -> next;
        return temp;
    }
    temp -> next = temp -> next -> next;
    
    return head;
}
```

## Approach 2.1 : Single Pass (Edge case handling 1)

Time complexity : O(N) 
Space complexity : O(1)

```cpp
// Fast travels n distance first loop, then len - n in second loop travelled by both fast and slow
// We can also maintain a dummy node in starting to correct the edge cases
ListNode* removeNthFromEnd(ListNode* head, int n) {
    ListNode* slow = head;
    ListNode* fast = head;
    
    while(n--)fast = fast -> next;
    
    // Edge case
    if(fast == NULL)return slow -> next;
    
    while(fast -> next != NULL){
        slow = slow -> next;
        fast = fast -> next;
    }
    slow -> next = slow -> next -> next;
    return head;
}
```

## Approach 2.1 : Single Pass (Edge case handling 2)

Time complexity : O(N) 
Space complexity : O(1)

```cpp
ListNode* removeNthFromEnd(ListNode* head, int n) {        
    ListNode* dummy = new ListNode();
    dummy -> next = head;
    ListNode* slow = dummy;
    ListNode* fast = dummy;
    
    for(int i = 1 ; i <= n ; i++){
        fast = fast -> next;
    }
    while(fast -> next != NULL){
        slow = slow -> next;
        fast = fast -> next;
    }
    
    slow -> next = slow -> next -> next;
    return dummy -> next;
    
}
```