# Four Pillars of OOPS

## Inheritance

- Inheritance is the mechanism where a new class can inherit properties (fields and methods) from an existing class. The existing class is called the base class or parent class, and the new class is called the derived class or child class.

## Encapsulation

- Encapsulation is the practice of bundling data (attributes) and methods (functions) that operate on the data into a single unit, known as a class. It restricts direct access to some of an object's components, providing better control over data integrity and reducing unintended side effects.

## Polymorphism

- Polymorphism is an important concept of object-oriented programming. It simply means more than one form. That is, the same entity (function or operator) behaves differently in different scenarios. 

- Polymorphism can be implemented using these methods
```cpp
/*
Function overloading
Operator overloading
Function overriding
Virtual functions
*/
```
- Function Overlading (Compile time Polymorphism) : We can use two functions having the same name if they have different parameters (either types or number of arguments). And, depending upon the number/type of arguments, different functions are called. 

```cpp
// Function with 2 int parameters
int sum(int num1, int num2) {
    return num1 + num2;
}

// Function with 2 double parameters
double sum(double num1, double num2) {
    return num1 + num2;
}

// Function with 3 int parameters
int sum(int num1, int num2, int num3) {
    return num1 + num2 + num3;
}

int main() {
    // Call function with 2 int parameters
    cout << "Sum 1 = " << sum(5, 6) << endl;

    // Call function with 2 double parameters
    cout << "Sum 2 = " << sum(5.5, 6.6) << endl;

    // Call function with 3 int parameters
    cout << "Sum 3 = " << sum(5, 6, 7) << endl;

    return 0;
}
```

- Operator Overloading (Compile time Polymorphism) : Operator overloading is a concept in programming languages that allows you to define custom behaviors for certain operators when used with objects of user-defined classes or data types. In other words, it lets you give special meaning to operators beyond their default functionalities.

```cpp
// C++ program to overload ++ when used as prefix

#include <iostream>
using namespace std;

class Count {
   private:
    int value;

   public:

    // Constructor to initialize count to 5
    Count() : value(5) {}

    // Overload ++ when used as prefix
    void operator ++() {
        value = value + 1;
    }

    void display() {
        cout << "Count: " << value << endl;
    }
};

int main() {
    Count count1;

    // Call the "void operator ++()" function
    ++count1;

    count1.display();
    return 0;
}
```

- Function overriding (Runtime Polymorphism) : It is the redefination of the base class function in its derived class with same signature (return type, and parameter).

```cpp
class Base {
   public:
    virtual void print() {
        cout << "Base Function" << endl;
    }
};

class Derived : public Base {
   public:
    void print() {
        cout << "Derived Function" << endl;
    }
};

int main() {
    Derived derived1;

    // Call print() function of Derived class
    derived1.print();

    return 0;
}
```

## Abstraction

-  It involves hiding complex implementation details and showing only the necessary features of an object to the outside world. Abstraction helps in simplifying the interaction with objects by focusing on what an object does rather than how it does it.

```cpp
class implementAbstraction {
private:
    int a, b;
 
public:
    // method to set values of
    // private members
    void set(int x, int y)
    {
        a = x;
        b = y;
    }
 
    void display()
    {
        cout << "a = " << a << endl;
        cout << "b = " << b << endl;
    }
};
 
int main()
{
    implementAbstraction obj;
    obj.set(10, 20);
    obj.display();
    return 0;
}
```

