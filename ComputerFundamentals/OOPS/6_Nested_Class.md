# Nested Class

```cpp
class StudentInfo{
public:
	string firstName;
	string lastName;

	class ParentInfo{
	public:
		string parentFirstName;
		string parentLastName;

		static int count;

		ParentInfo(){
			count++;
			cout << "Parent Object Count -> " << count << endl;
		}
	};
};

int StudentInfo::ParentInfo::count = 0;

int main(){
	StudentInfo s1;
	s1.firstName = "Subrat";
	s1.lastName = "Yeeshu";

    // Nested class syntax
    // parentClass::childClass objectName
	StudentInfo::ParentInfo p1;
	p1.parentFirstName = "Jyoti";
	p1.parentLastName = "Kiran";

	cout << "Name of student : " << s1.firstName << " " << s1.lastName << "\nName of parent : " << p1.parentFirstName << " " << p1.parentLastName << endl;
}
```