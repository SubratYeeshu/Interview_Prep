# This pointer

- The this pointer in C++ is a special keyword that represents a pointer to the current instance of the class. 

## Some points 

- The this pointer is not available in static member functions because static functions do not have a specific instance to refer to.
- When you call a member function on an object, the this pointer is automatically passed as an invisible argument to the function.
- It's often used to avoid naming conflicts between local variables and member variables with the same name.
- Using the this pointer is optional, as you can access member variables directly without it. However, it can make your code more self-explanatory in cases where there might be ambiguity.

## Code snippet

```cpp
class MyClass {
private:
    int value;

public:
    void setValue(int val) {
        this->value = val; // Using the this pointer to access the member variable
    }

    int getValue() {
        return this->value; // Using the this pointer to return the member variable
    }

    void printAddress() {
        cout << "Address of the current object: " << this << endl;
    }
};

int main() {
    MyClass obj1;
    obj1.setValue(42);

    MyClass obj2;
    obj2.setValue(100);

    cout << "Value of obj1: " << obj1.getValue() << endl;
    cout << "Value of obj2: " << obj2.getValue() << endl;

    obj1.printAddress();
    obj2.printAddress();

    return 0;
}
```