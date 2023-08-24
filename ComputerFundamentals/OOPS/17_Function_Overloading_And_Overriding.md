# Function Overloading And Overriding

## Function Overloading

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

## Function Overriding

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

## Theoretical difference

```cpp
/*

    Function Overloading                            Function Overriding
-> It is achieved at compile time               It is achieved at runtime
-> Overloading occurs without inheritance       Inheritance is needed for function overriding
-> Overloaded functions must differ in          Function signature must be same
signature(number of params, type of pararms)    
-> Overloaded function in same scope            Different scopes
-> Overloaded function behave differenlty       Overriding is needed when derived class function
depending on params                             need to have different jobs


*/
```