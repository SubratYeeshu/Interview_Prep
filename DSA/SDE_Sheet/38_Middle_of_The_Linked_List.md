# Middle of the Linked List

## Problem statement

- Given the head of a singly linked list, return the middle node of the linked list. If there are two middle nodes, return the second middle node.

## Approach 1 : Finding the length

Time complexity : O(2N) Two pass
Auxiliary space : O(N)

```cpp
int getLen(ListNode* &head){
    ListNode* temp = head;
    int len = 0;
    
    while(temp != NULL){
        temp = temp -> next;
        len++;
    }
    
    return len;
}

ListNode* middleNode(ListNode* head) {
    int len = getLen(head);
    for(int i = 0 ; i < len / 2 ; i++)head = head -> next;
    
    return head;
}
```

## Approach 2 : Using fast and slow pointer

Time complexity : O(N)
Auxiliary space : O(N)

```cpp
ListNode* middleNode(ListNode* head) {
    ListNode* slow = head;
    ListNode* fast = head;  
    
    // Finish line or outside
    while(fast != NULL && fast -> next != NULL){
        fast = fast -> next -> next;
        slow = slow -> next;
    }
    
    return slow;
}
```