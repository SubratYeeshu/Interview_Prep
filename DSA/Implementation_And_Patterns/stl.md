# Containers

## Vectors 


## Map


## Unordered Map


## Multimap


## Set


## Multiset


## Priority Queue


## Queue


## Stack


## Dequeue


## List


# Algorithms

## Sorting


## Reverse


## Max / Min Element


## String


## Binary search

```cpp

int main()
{
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
    // If element found returs the iterator of first occurence
    cout << *lower_bound(temp.begin(), temp.end(), 12) << endl;
    cout << *lower_bound(temp.begin(), temp.end(), 50) << endl;
    cout << lower_bound(temp.begin(), temp.end(), 50) - temp.begin() << endl;
    cout << "The count of 50 is : " << upper_bound(temp.begin(), temp.end(), 50) - lower_bound(temp.begin(), temp.end(), 50);
}
```


## Next permutation