/*
    Open Closed Principle: Open for extension but closed for modification 
*/

// Violating OCP
class Customer {
    public String customerType;
    public Integer purchaseAmount;
    
    public Customer(String customerType, Integer purchaseAmount){
        this.customerType = customerType;
        this.purchaseAmount = purchaseAmount; 
    }

    public double discountCalculate(){
        if(customerType.equals("Regular")){
            return purchaseAmount * .20;
        }else if (customerType.equals("Premium")){
            return purchaseAmount * .30;
        }
        // Adding more user will modify Customer class logic
        return 0.00;
    }
}

// Following OCP
abstract class CustomerOCP {
    public double purchaseAmount;

    public CustomerOCP(double purchaseAmount){
        this.purchaseAmount = purchaseAmount;
    }

    // Making it abstract such that it must be overriden in subclasses
    public abstract double calculateDiscount();
}

class RegularCustomer extends CustomerOCP { 
    public RegularCustomer(double purchaseAmount){
        super(purchaseAmount);
    }

    @Override
    public double calculateDiscount() {
        return purchaseAmount * 0.20;
    }
}

class PremiumCustomer extends RegularCustomer {
    public PremiumCustomer(double purchaseAmount) {
        super(purchaseAmount);
    }

    @Override
    public double calculateDiscount() {
        return purchaseAmount * 0.30;
    }
}



public class OpenClosedPrinciple {
    public static void main(String[] args){
        // Not follwing OCP
        Customer c1 = new Customer("Regular", 5000);
        double discount = c1.discountCalculate();
        System.out.println("Discount for given customer: " + discount);

        // Following OCP
        PremiumCustomer p1 = new PremiumCustomer(50000);
        double PremiumDiscount = p1.calculateDiscount();
        System.out.println(PremiumDiscount);

        RegularCustomer r1 = new RegularCustomer(10000);
        double RegularDiscount = r1.calculateDiscount();
        System.out.println(RegularDiscount);
        
    }
}
