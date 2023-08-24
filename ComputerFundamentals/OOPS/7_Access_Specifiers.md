# Access Specifiers

## There are three types of access specifiers
- Public - All the class members declared under public will be available to everyone. The data members and member functions declared public can be accessed by other classes too. The public members of a class can be accessed from anywhere in the program using the direct member access operator (.) with the object of that class.

- Protected - Protected access modifier is similar to that of private access modifiers, the difference is that the class member declared as Protected are inaccessible outside the class but they can be accessed by any subclass(derived class) of that class.

- Private - Used to specify that a class member can only be accessed from within the class. This means that any function or object outside the class cannot access the private members of the class.

## Code Snippet

```cpp
class Student{
private:
	string password;
	string masterPassword = "iamdon";
protected:
	string number;
public:
	string firstName;
	string lastName;

	void setName(string x, string y){
		this -> firstName = x;
		this -> lastName = y;
	}

	void setPassword(string x){
		this -> password = x;
	}

	void getPassword(string x){
		if(this -> masterPassword == x)cout << "Your password is : " << this -> password << endl;
		else cout << "Wrond masterPassword" << endl;
	}

	void setNumber(string num){
		this -> number = num;
	}

	void getName(){
		cout << this -> firstName << " " << this -> lastName << endl;
	}

};

int main(){
	Student st1;
	st1.setName("Subrat", "Yeeshu");
	st1.setPassword("mario1718");
	st1.setNumber("94546712");
	
	// Accessing public data members
	cout << st1.firstName << " " << st1.lastName << endl;
	st1.getName();

	// Accessing private data members
	// cout << st1.password; // Error
	cout << "Enter the master password to get your passowrd : " << endl;
	string masterPassword;
	cin >> masterPassword;
	st1.getPassword(masterPassword);
}
```

