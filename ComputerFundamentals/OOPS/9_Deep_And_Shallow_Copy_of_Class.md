# Deep And Shallow Copy of Class

- Shallow Copy and Deep Copy are concepts related to copying objects, especially when objects contain pointers to dynamically allocated memory. 

## Deep copy of class

- Deep Copy: A deep copy, on the other hand, involves creating a completely independent copy of the object, including dynamically allocated memory pointed to by its pointers. This ensures that changes in one object do not affect the other, as they have separate memory allocations.

## Code Snippet

```cpp

```

## Shallow copy of class

- Shallow Copy: A shallow copy of an object involves copying the object and its members, but not the dynamic memory pointed to by its pointers. As a result, both the original object and the copied object will point to the same memory locations, which can lead to unintended consequences if modifications are made.


## Code Snippet

```cpp

```