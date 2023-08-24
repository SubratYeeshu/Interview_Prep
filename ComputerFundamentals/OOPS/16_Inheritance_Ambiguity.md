# Inheritance Ambiguity

- Ambiguity in inheritance occurs when a derived class inherits from multiple base classes, and these base classes have members or methods with the same name. This can lead to confusion for the compiler when resolving which member or method to use.

## Ambiguity 1

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

- Explicilty mention which function to call 

```cpp
class Derived : public BaseA, public BaseB {
public:
	void show(){
		BaseB::show();
	}
};
```

## Ambiguity 2 

- No need of fix automatically resolved

```cpp
class A{
public:
	int x;
	void show(){
		cout << "Hello World";
	}
};

class B:public A{
public:
	int y;

	// Uncomment below to check : The function in the child class overrides the function in the parent class
	// void show(){
	// 	cout << "Hello Moon";
	// }
};


int main() {
    B obj;
    obj.show(); 
}
```