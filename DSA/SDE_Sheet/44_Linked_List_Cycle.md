# Linked List Cycle

## Problem statement

- Given head, the head of a linked list, determine if the linked list has a cycle in it. There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter. Return true if there is a cycle in the linked list. Otherwise, return false.

## Approach 1 : Using map

Time complexity : O(N)
Space complexity : O(N)

```cpp
bool findLoopUsingMap(ListNode* head){
    if(head == NULL)return false;
    unordered_map<ListNode*, bool>visited;
    
    ListNode* temp = head;
    
    while(temp != NULL){
        if(visited[temp] == true){
            // cout << "The Loop is present on the element -> " << temp -> data << endl;
            return true;
        }
        
        visited[temp] = true;
        temp = temp -> next;
    }
    // cout << "The Loop is not present" << endl;
    return false;
}
```

## Approach 2 : Floyd's detection

Time complexity : O(N)
Space complexity : O(1)

```cpp
/*

    -> Proof

        m      
    <--------> <------------  k
    ____________________   |
              |        |   |
              |   n    |   V
              | rounds |  
              |________|

        Distance travelled by slow pointer = m + k + n*x1
        Distance travelled by fast pointer = m + k + n*x2
        Equation 1 : 2(m + k + n*x1) = m + k + n*x2
        resolving : n(x1 - 2x2) - m = k
        Putting m(n - 1) = k
        So for some x1 and x2 there exists a real k

*/
bool findLoopUsingFloydDetection(ListNode* head){
    if(head == NULL)return false;   
    ListNode* slow = head;
    ListNode* fast = head;
    
    while(fast != NULL && fast -> next != NULL && slow != NULL){
        fast = fast -> next -> next;
        slow = slow -> next;
        
        if(slow == fast)return true;
    }
    
    return false;
}
```