# Intersection of Two Linked Lists

## Problem statement 

- Given the heads of two singly linked-lists headA and headB, return the ListNode at which the two lists intersect. If the two linked lists have no intersection at all, return null.

## Approach 1 : 

Time complexity : O(M * N) 
Space complexity : O(1)

```cpp
ListNode* intersectionPresent(ListNode* head1, ListNode* head2) {
    while(head2 != NULL){
        ListNode* temp = head1;
        while(temp != NULL) {
            if(temp == head2) return head2;
            temp = temp -> next;
        }
        head2 = head2->next;
    }
    return NULL;
}
```

## Approach 2 : Hashing

Time complexity : O(N + M)
Space complexity : O(N)

```cpp
ListNode* intersectionPresent(ListNode* head1,ListNode* head2) {
    unordered_set<ListNode*> st;
    while(head1 != NULL) {
        st.insert(head1);
        head1 = head1 -> next;
    }
    while(head2 != NULL) {
        if(st.find(head2) != st.end()) return head2;
        head2 = head2->next;
    }
    return NULL;
}
```

## Approach 3 : Optimal 

Time complexity : O(N + M)
Space complexity : O(1)

```cpp
ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
    ListNode* d1 = headA;
    ListNode* d2 = headB;
    
    // They must be at same position (aligned)or they must intersect at some time, either it can intersect 
    // at the answer or at the end of ll if ans is not found
    while(d1 != d2){
        if(d1 != NULL)d1 = d1 -> next;
        else d1 = headB;
        
        if(d2 != NULL)d2 = d2 -> next;
        else d2 = headA;
    }
    
    return d1;
}
```