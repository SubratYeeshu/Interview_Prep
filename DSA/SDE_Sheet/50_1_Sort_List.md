# Sort List

- Given the head of a linked list, return the list after sorting it in ascending order.

## Approach 1 : Merge Sort

Time complexity : O(NLogN)  
Space complexity : O(LogN)

```cpp
ListNode* findMid(ListNode* head){
    ListNode* fast = head->next;
    ListNode* slow = head;
    
    while(fast != NULL && fast -> next != NULL){
        slow = slow -> next;
        fast = fast -> next -> next;
    }
    return slow;
}

ListNode* merge(ListNode* left, ListNode* right){
    if(left == NULL){
        return right;
    }
    if(right == NULL){
        return left;
    }
    
    ListNode* ans = new ListNode(-1);
    ListNode* temp = ans;
    
    //Merge
    while(left!=NULL && right !=NULL){
        if(left -> val < right -> val){
            temp -> next = left;
            temp = left;
            left = left -> next;
        }else{
            temp -> next = right;
            temp = right;
            right = right -> next;
        }
    }
    
    while(right!=NULL){
        temp -> next = right;
        temp = right;
        right = right -> next;
    }
    while(left!=NULL){
        temp -> next = left;
        temp = left;
        left = left -> next;
    }
    //dummy node
    ans = ans -> next;
    return ans;
}

ListNode* sortList(ListNode* head) {
    //base case
    if(head == NULL || head -> next == NULL){
        return head;
    }
    //break LL in two halves 
    ListNode* mid = findMid(head);
    ListNode* left = head;
    ListNode* right = mid->next;
    mid -> next = NULL;
    //RR Calls
    left = sortList(left);
    right = sortList(right);
    //Merge
    ListNode* result = merge(left, right);
    return result;
}
```