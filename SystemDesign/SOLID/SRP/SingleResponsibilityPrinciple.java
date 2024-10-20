// SRP Violation: User class is dependent on saveUserToDB and sendMailToUser 
class User {
    private String userName;
    private String userEmail;

    // Constructor
    public User(String userName, String userEmail){
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public void saveUserToDB(){/* Logic */};

    public void sendMailToUser(){/* Logic */};

    public String getUserName(){
        return userName;
    }

    public String getUserEmail(){
        return userEmail;
    }
}

class UserEntity {
    private String userName;
    private String userEmail;

    // Constructor
    public UserEntity(String userName, String userEmail){
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserEmail(){
        return userEmail;
    }
}

class UserRepository {
    public void saveUserToDB(UserEntity u1){
        System.out.println("Hi " + u1.getUserName() + " Good Morning!!");
    }
}

class EmailService {
    public void sendMailToUser(UserEntity u1){
        System.out.println("Email has been successfully sent to: " + u1.getUserEmail());
    }
}

public class SingleResponsibilityPrinciple {
    public static void main(String[] args){
        // SRP Violation
        User u = new User("Subrat", "subrat.yeeshu@gmail.com");

        // SRP 
        UserEntity u1 = new UserEntity("Subrat", "subrat.yeeshu@gmail.com");
        UserRepository userSaveToDBu1 = new UserRepository();
        userSaveToDBu1.saveUserToDB(u1);

        EmailService userEmailu1 = new EmailService();
        userEmailu1.sendMailToUser(u1);

    }
}