# Palindrome Linked List

## Problem statement

- Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

## Approach 1 

Time complexity : O(N \* M)  
Space complexity : O(1)

```cpp
ListNode* reverse(ListNode* head){
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

void printList(ListNode* temp){
    while(temp != NULL){
        cout << temp -> val << " ";
        temp = temp -> next;
    }
    cout << endl;
}

bool isPalindrome(ListNode* head) {
    if(head == NULL || head -> next == NULL)return true;
    ListNode* slow = head;
    ListNode* fast = head;
    
    
    // Changed condition of while loop because we want one node behind middle to reverse the LL and correct the  LL after operations
    while(fast -> next != NULL && fast -> next -> next != NULL){
        fast = fast -> next -> next;
        slow = slow -> next;
    }
    
    slow -> next = reverse(slow -> next);
    
    ListNode* start = head;
    ListNode* mid = slow -> next;
    
    while(mid != NULL){
        if(start -> val != mid -> val)return false;
        else{
            start = start -> next;
            mid = mid -> next;
        }
    }
    
    // Setting back the linked list configuration
    printList(head);
    slow -> next = reverse(slow -> next);
    printList(head);
    
    return true;
}
```