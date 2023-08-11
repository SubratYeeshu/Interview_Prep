
# Merge Two Sorted List

## Problem statement

- You are given the heads of two sorted linked lists list1 and list2. Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists. Return the head of the merged linked list. 

## Approach 1 : Using extra space

Time complexity : O(N + M) 
Space complexity : O(N + M)

```cpp
ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
    ListNode* ans = new ListNode(-1);
    ListNode* head = ans;
    
    ListNode* d1 = list1;
    ListNode* d2 = list2;
    
    while(d1 != NULL && d2 != NULL){
        if(d1 -> val <= d2 -> val){
            ans -> next  = new ListNode(d1 -> val);
            d1 = d1 -> next;
        }else{
            ans -> next = new ListNode(d2 -> val);
            d2 = d2 -> next;
        }
        
        ans = ans -> next;
    }
    
    while(d1 != NULL)ans -> next = new ListNode(d1 -> val), d1 = d1 -> next, ans = ans -> next;
    while(d2 != NULL)ans -> next = new ListNode(d2 -> val), d2 = d2 -> next, ans = ans -> next;
    
    return head -> next;
}
```

## Approach 2 : Without using extra space

Time complexity : O(N + M) 
Space complexity : O(1) 

```cpp
ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
    if(!list1)return list2;
    if(!list2)return list1;
    
    // List1 will always be smaller
    if(list1 -> val > list2 -> val)swap(list1, list2);
    
    ListNode* res = list1;
    
    while(list1 != NULL && list2 != NULL){
        ListNode* temp = list1;
        
        while(list1 != NULL && list1 -> val <= list2 -> val){
            temp = list1;
            list1 = list1 -> next;
        }
        
        temp -> next = list2;
        swap(list1, list2);
    }
    
    return res;
}
```

## Approach 3 : Without using extra space (More Readable)

Time complexity : O(N + M) 
Space complexity : O(1) 


```cpp
// Updating next pointers of node using curr pointer
ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
    if(!list1)return list2;
    if(!list2)return list1;
    
    // Dummy node for easier operation
    ListNode* dummy = new ListNode();
    ListNode* curr = dummy;
    
    while(list1 && list2){
        if(list1 -> val < list2 -> val){
            curr -> next = list1;
            list1 = list1 -> next;
        }else{
            curr -> next = list2;
            list2 = list2 -> next;
        }
        curr = curr -> next;
    }
    
    if(list1)curr -> next = list1;
    if(list2)curr -> next = list2;
    
    return dummy -> next;
}
```

## Approach 4 : Recursion

Time complexity : O(N + M) 
Space complexity : O(N + M) Recursion stack space

```cpp
ListNode* merge(ListNode* l1, ListNode* l2){
    if(l1 == NULL)return l2;
    if(l2 == NULL)return l1;
    
    if(l1 -> val < l2 -> val){
        // Use from l1
        l1 -> next = merge(l1 -> next, l2);
        return l1;
    }else{
        // Use from l2
        l2 -> next = merge(l1, l2 -> next);
        return l2;
    }
}

ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
    return merge(list1, list2);
}
```
