# Containers

## Vectors 

```cpp
int main(){
    vector<int> temp;
    
    // Resize 
    temp.resize(10, -1);
    for(auto i : temp)cout << i << " ";
    cout << endl;
    
    // Some functions
    cout << temp.size() << endl;
    cout << "Is the vector empty : " << temp.empty() << endl;
    
    for(int i = 0 ; i < temp.size() ; i++)temp[i] = i;
    
    // Iterators
    auto it = temp.begin();
    cout << *it << endl;
    
    auto it2 = temp.end();
    cout << *(--it2) << endl;
    
    vector<int> ::iterator it3;
    it3 = temp.begin() + 2;
    cout << *it3 << endl;
    
    
    // Some more funcions
    cout << temp.back() << endl;
    temp.pop_back();
    cout << temp.back() << endl;
    cout << temp.size() << endl;
    
    temp.push_back(100);
    cout << temp.back() << endl;
    
    
    temp.insert(temp.begin() + 2, 500);
    temp.insert(temp.begin() + 4, 3, 0);
    
    for(auto i : temp)cout << i << " ";
    cout << endl;
    
    temp.erase(temp.begin() + 2);  
    temp.erase(temp.begin() + 1, temp.begin() + 3); 
    
    
    temp.emplace_back(1000);  // Constructs the number directly in-place
    
    
    vector<string> temp2 (10, string(15, '.'));
    
    for(auto i : temp2){
        for(auto ch : i){
            cout << ch << " ";
        }
        cout << endl;
    }
    
    vector<string> temp3;
    
    for(int i = 0 ; i < 10 ; i++){
        string str = "";
        for(int j = 0 ; j < 10 ; j++){
            str.push_back('.');
        }
        temp3.push_back(str);
    }
    
    for(auto i : temp3){
        for(auto ch : i){
            cout << ch << " ";
        }
        cout << endl;
    }
    

    vector<int> vec {1, 100, 50, 20, 30};
    
    reverse(vec.begin(), vec.end());
    
    for(auto i : vec)cout << i << " ";
    cout << endl;
    
    reverse(vec.begin() + 1, vec.end() - 1);
    
    for(auto i : vec)cout << i << " ";
    cout << endl;
}
```

## Map

```cpp
int main() {
    // Create a map of strings and integers
    map<string, int> myMap;

    // Insert elements into the map
    myMap["Math"] = 90;
    myMap["English"] = 85;
    myMap["Math"] = 95; // Overwrites previous value
    myMap["Science"] = 88;

    // Print the elements in the map
    cout << "Map elements:" << endl;
    for (const auto& pair : myMap) {
        cout << pair.first << " -> " << pair.second << endl;
    }

    // Find the score for a specific subject
    string subjectToFind = "Math";
    if (myMap.count(subjectToFind) > 0) {
        cout << "Score for " << subjectToFind << ": " << myMap[subjectToFind] << endl;
    } else {
        cout << "Subject " << subjectToFind << " not found." << endl;
    }

    return 0;
}
```

## Unordered Map

```cpp
int main() {
    // Create an unordered map of strings and integers
    unordered_map<string, int> myUnorderedMap;

    // Insert elements into the unordered map
    myUnorderedMap["Math"] = 90;
    myUnorderedMap["English"] = 85;
    myUnorderedMap["Math"] = 95; // Overwrites previous value
    myUnorderedMap["Science"] = 88;

    // Print the elements in the unordered map
    cout << "Unordered map elements:" << endl;
    for (const auto& pair : myUnorderedMap) {
        cout << pair.first << " -> " << pair.second << endl;
    }

    // Find the score for a specific subject
    string subjectToFind = "Math";
    if (myUnorderedMap.count(subjectToFind) > 0) {
        cout << "Score for " << subjectToFind << ": " << myUnorderedMap[subjectToFind] << endl;
    } else {
        cout << "Subject " << subjectToFind << " not found." << endl;
    }

    return 0;
}
```

## Multimap

```cpp
int main() {
    // Create a multimap of strings and integers
    multimap<string, int> myMultimap;

    // Insert elements into the multimap
    myMultimap.insert({"Math", 90});
    myMultimap.insert({"English", 85});
    myMultimap.insert({"Math", 95});
    myMultimap.insert({"Science", 88});

    // Print the elements in the multimap
    cout << "Multimap elements:" << endl;
    for (const auto& pair : myMultimap) {
        cout << pair.first << " -> " << pair.second << endl;
    }

    // Find all subjects for a specific score
    int scoreToFind = 90;
    cout << "Subjects with score " << scoreToFind << ":" << endl;
    for (const auto& pair : myMultimap) {
        if (pair.second == scoreToFind) {
            cout << pair.first << endl;
        }
    }

    return 0;
}
```

## Set

```cpp
int main() {
    // Create a set of integers
    set<int> mySet;

    // Insert elements into the set
    mySet.insert(10);
    mySet.insert(20);
    mySet.insert(10); // Duplicates are not allowed
    mySet.insert(30);

    // Print the elements in the set
    cout << "Set elements: ";
    for (int num : mySet) {
        cout << num << " ";
    }
    cout << endl;

    // Find if an element exists in the set
    int elementToFind = 20;
    bool exists = mySet.count(elementToFind) > 0;
    cout << "Element " << elementToFind << " exists: " << (exists ? "Yes" : "No") << endl;

    return 0;
}
```

## Multiset

```cpp
int main() {
    // Create a multiset of integers
    multiset<int> myMultiset;

    // Insert elements into the multiset
    myMultiset.insert(10);
    myMultiset.insert(20);
    myMultiset.insert(10); // Allows duplicate elements
    myMultiset.insert(30);

    // Print the elements in the multiset
    cout << "Multiset elements: ";
    for (int num : myMultiset) {
        cout << num << " ";
    }
    cout << endl;

    // Find the count of a specific element
    int elementToFind = 10;
    int count = myMultiset.count(elementToFind);
    cout << "Count of " << elementToFind << ": " << count << endl;

    return 0;
}
```

## Priority Queue

```cpp
int main() {
    // Create a min-heap (default behavior)
    priority_queue<int, vector<int>, greater<int>> minHeap;

    // Enqueue elements into the min-heap
    minHeap.push(30);
    minHeap.push(10);
    minHeap.push(20);

    // Print the top element of the min-heap (smallest element)
    cout << "Top element (min-heap): " << minHeap.top() << endl;

    // Dequeue elements from the min-heap
    minHeap.pop();

    // Print the size of the min-heap
    cout << "Min-heap size: " << minHeap.size() << endl;

    // Create a max-heap
    priority_queue<int> maxHeap;

    // Enqueue elements into the max-heap
    maxHeap.push(10);
    maxHeap.push(30);
    maxHeap.push(20);

    // Print the top element of the max-heap (largest element)
    cout << "Top element (max-heap): " << maxHeap.top() << endl;

    // Dequeue elements from the max-heap
    maxHeap.pop();

    // Print the size of the max-heap
    cout << "Max-heap size: " << maxHeap.size() << endl;

    return 0;
}
```

## Queue

```cpp

int main() {
    // Create a queue of integers
    queue<int> myQueue;

    // Enqueue elements into the queue
    myQueue.push(10);
    myQueue.push(20);
    myQueue.push(30);

    // Print the front element of the queue
    cout << "Front element: " << myQueue.front() << endl;

    // Dequeue elements from the queue
    myQueue.pop();

    // Print the size of the queue
    cout << "Queue size: " << myQueue.size() << endl;

    return 0;
}
```

## Stack

```cpp
int main() {
    // Create a stack of integers
    stack<int> myStack;

    // Push elements onto the stack
    myStack.push(10);
    myStack.push(20);
    myStack.push(30);

    // Print the top element of the stack
    cout << "Top element: " << myStack.top() << endl;

    // Pop elements from the stack
    myStack.pop();
    myStack.pop();

    // Print the size of the stack
    cout << "Stack size: " << myStack.size() << endl;

    return 0;
}
```

## Dequeue

```cpp
int main() {
    // Create a deque of integers
    deque<int> myDeque;

    // Add elements to the back of the deque
    myDeque.push_back(10);
    myDeque.push_back(20);
    myDeque.push_back(30);

    // Add elements to the front of the deque
    myDeque.push_front(5);
    myDeque.push_front(2);

    // Print the elements in the deque
    cout << "Deque elements: ";
    for (int num : myDeque) {
        cout << num << " ";
    }
    cout << endl;

    // Remove elements from the back and front of the deque
    myDeque.pop_back();
    myDeque.pop_front();

    // Print the updated deque
    cout << "Updated elements: ";
    for (int num : myDeque) {
        cout << num << " ";
    }
    cout << endl;

    return 0;
}
```

## List

```cpp
int main() {
    // Create a list of integers
    list<int> myList;

    // Add elements to the list
    myList.push_back(10);
    myList.push_back(20);
    myList.push_back(30);

    // Add elements to the front of the list
    myList.push_front(5);
    myList.push_front(2);

    // Print the elements in the list
    cout << "List elements: ";
    for (int num : myList) {
        cout << num << " ";
    }
    cout << endl;

    // Remove elements from the list
    myList.pop_back();
    myList.pop_front();

    // Print the updated list
    cout << "Updated elements: ";
    for (int num : myList) {
        cout << num << " ";
    }
    cout << endl;

    return 0;
}
```

# Algorithms

## Sorting

```cpp
bool cmp(pair<int, int> &p1, pair<int, int> &p2){
    if(p1.first == p2.first)return p2.second < p2.first;
    return p1.first < p2.first;
}

int main(){
   vector<pair<int, int>> vec;
   vec.push_back({5, 10});
   vec.push_back({7, 8});
   vec.push_back({7, 100});
   vec.push_back({2, 100});
   vec.push_back({3, 0});
   vec.push_back({3, 100});
   
   
   for(auto i : vec)cout << i.first << " : " << i.second << endl;
   cout << endl;
   
   cout << "Sorting based on first value asc" << endl;
   sort(vec.begin(), vec.end());
   for(auto i : vec)cout << i.first << " : " << i.second << endl;
   cout << endl;
   
   cout << "Sorting based on first value des" << endl;
   sort(vec.begin(), vec.end(), greater<pair<int, int>>());
   for(auto i : vec)cout << i.first << " : " << i.second << endl;
   cout << endl;
   
   cout << "Sorting based on custom comparator function" << endl;
   sort(vec.begin(), vec.end(), cmp);
   for(auto i : vec)cout << i.first << " : " << i.second << endl;
   cout << endl;
   
   cout << "Sorting based on custom comparator shorthand with additional variable" << endl;
   int k = 3;
   sort(vec.begin(), vec.end(), [k](pair<int,int> p1, pair<int, int> p2){
       if(p1.first <= k){
           if(p1.first == p2.first)return p1.second < p2.second;
           return p1.first < p2.first;
       }else{
           if(p1.first == p2.first)return p1.second > p2.second;
           return p1.first < p2.first;
       }
   });
   for(auto i : vec)cout << i.first << " : " << i.second << endl;
   cout << endl;
}
```

## Max / Min / Accumulate

```cpp
int main(){
    vector<int> temp {1, 100, 50, 20, 30};
    
    cout << *min_element(temp.begin(), temp.end()) << endl;
    cout << *max_element(temp.begin(), temp.end()) << endl;
    cout << accumulate(temp.begin(), temp.end(), 0);

}
```


## String

```cpp

int main(){
    string name = "HelloSubratYeeshu";
    
    cout << name.size() << endl;
    
    string firstName = name.substr(5, 6);
    cout << firstName << endl;
    
    string lastName = name.substr(11);
    cout << lastName << endl;
    
    // string stream;
    
    string id_no = "0053621400";
    int idNo = stoi(id_no);
    
    cout << idNo << endl;
    
    int idNo2 = 0;
    
    for(int i = 0 ; i < id_no.size() ; i++){
        idNo2 = idNo2 * 10 + (id_no[i] - '0');
    }
    
    cout << idNo2 << endl;
    
    int x = 25400;
    string xx = to_string(x);
    cout << xx << endl;
    
    xx.append("Hello");
    cout << xx << endl;
    
    string yy = "World";
    yy += xx;
    
    cout << yy << endl;
    
    string temp = "12";
    swap(temp[0], temp[1]);
    cout << temp << endl;
    
    string spacedinput = "";
    // cin >> spacedinput;
    
    // cout << spacedinput << endl;
    
    // getline(cin, spacedinput);
    
    // cout << spacedinput << endl;
    
    // String stream 
    
    // getline(stream, variable, separator)
    
    string temp2 = "SurbatYeeshu,English,Physics,Chemistry,Maths";
    string str = "";
    vector<string> vec;
    stringstream mystream(temp2);
    
    while(getline(mystream, str, ',')){
        vec.push_back(str);
    }
    
    for(auto i : vec)cout << i << " -> ";
    cout << endl;
    

    // stringstream ss(string) // to make the string work as stream
    // geline(stream, variable, separator)
}

```

## Binary search

```cpp

int main(){
    vector<int> temp {1, 2, 5, 8, 12, 20, 50, 50, 50, 99, 100};
    
    // Binary Search
    cout << "************************** Binary Search STL **************************" << endl;
    // Returns true or false
    cout << binary_search(temp.begin(), temp.end(), 50) << endl << endl;
    
    
    // Upper Bound
    cout << "************************** Upper Bound STL **************************" << endl;
    // If element not found upper bound returns iterator next bigger element if exists else returns end iterator
    cout << *upper_bound(temp.begin(), temp.end(), 12) << endl;
    cout << *upper_bound(temp.begin(), temp.end(), 50) << endl;
    auto it = upper_bound(temp.begin(), temp.end(), 100);
    if(it == temp.end())cout << "Element not found in the given vector" << endl;
    
    // Number of elements greater than any element or the index of any number
    cout << upper_bound(temp.begin(), temp.end(), 12) - temp.begin() << endl;
    
    // To find the last index of a element
    auto it2 = upper_bound(temp.begin(), temp.end(), 50);
    it2--;
    cout << "Element is present at index : " << it2 - temp.begin() << endl << endl;
    
    
    // Lower bound
    cout << "************************** Lower Bound STL **************************" << endl;

    // This returns the element iteself if present the first occurence 
    cout << *lower_bound(temp.begin(), temp.end(), 12) << endl;
    cout << *lower_bound(temp.begin(), temp.end(), 50) << endl;

    // If element found returns the iterator of first occurence
    cout << lower_bound(temp.begin(), temp.end(), 50) - temp.begin() << endl;
    cout << "The count of 50 is : " << upper_bound(temp.begin(), temp.end(), 50) - lower_bound(temp.begin(), temp.end(), 50);
}
```


## Next permutation

```cpp
int main(){
/*
    -> Takes 3 argurments / 1 Optional
    -> next_permutation(itr.begin(), itr.end(), custom_comp_fn)
    -> Return type bool
*/
    
    vector<int> v = {9, 6, 2, 6, 5, 6, 4};
    bool isPermPossible = next_permutation(v.begin(), v.end());
    cout << "Permutation possible = " << isPermPossible << " " << endl;
    
    for(auto i : v)cout << i << " ";


}
```