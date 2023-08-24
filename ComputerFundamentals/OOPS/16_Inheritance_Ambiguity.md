# Inheritance Ambiguity

- Ambiguity in inheritance occurs when a derived class inherits from multiple base classes, and these base classes have members or methods with the same name. This can lead to confusion for the compiler when resolving which member or method to use.

## Example

```cpp
class BaseA {
public:
    void show() {
        cout << "BaseA" << std::endl;
    }
};

class BaseB {
public:
    void show() {
        cout << "BaseB" << std::endl;
    }
};

// Derived class inheriting from both BaseA and BaseB
class Derived : public BaseA, public BaseB {
public:
    // No show method defined in Derived
};

int main() {
    Derived d;
    
    // Ambiguity: Which show method to call?
    d.show(); // This line will cause a compilation error due to ambiguity
    
}
```

## Fix 1

- Use scope resolution operator

```cpp
d.BaseA::show(); // Call show() from BaseA
d.BaseB::show(); // Call show() from BaseB
```

## Fix 2