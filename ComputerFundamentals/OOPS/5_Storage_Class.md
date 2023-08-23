# Storage Class

## There are five storage class 
- Extern 
- Auto 
- Static
- Register
- Mutable

## Extern 

```cpp
// Extern class is used for declaring a variable in one file and accessing it globally in any other file
int a = 10;
extern int a;  // Can be used in different file

void show(){
	int a = 5;
	cout << a << endl;
}
void show2(){
	cout << a << endl;
}

int main(){
	show();
	show2();

}
```

## Auto 

```cpp
// Automatically detects data type of variable using initialized value
int main(){
	auto name = "Subrat";
	auto age = 23;
	auto height = 5.9;

	cout << name << " " << age << " " << height << endl;
}
```

## Static

```cpp
// Fixed memory for every instance / for the whole program one time allocation
void demo(){
	static int a = 10;
	cout << a << " " << endl;
	a++;
}

int main(){
    demo();  // 10
    demo();  // 11
    demo();  // 12
}
```

## Register

```cpp
// Stores on register if memory is available - much faster 
int main(){
	register int a = 10;
	cout << a << endl;
}
```

## Mutable

```cpp
// We can change data member of a object even if it is constant but the storage class must be mutable
class Student{
public:
	string firstName;
	mutable string lastName;

	Student(){
		firstName = "Subrat";
		lastName = "Yeeshu";
	}
};

int main(){
	const Student s1;
	// s1.firstName = "Subrat";
	s1.lastName = "Hero";  

	cout << s1.firstName << " " << s1.lastName << endl;
}
```