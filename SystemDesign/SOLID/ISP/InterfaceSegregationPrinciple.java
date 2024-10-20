/*
 - One interface should not handle mulitple responsibility.
 - Interface should not be bulky (too many functions)
 - Design interaces such that it does not have many unused functions 
*/

// Fat interface 
interface Machine {
    void print();
    void scan();
    void fax();
}

class MultiFunctionPrinter implements Machine {
    @Override
    public void print() {
        System.out.println("Printing .......");
    }

    @Override
    public void scan() {
        System.out.println("Scanning .......");
    }

    @Override 
    public void fax() {
        System.out.println("Fax .......");
    }
}

class SingleFunctionPrinter implements Machine {
    @Override
    public void print() {
        System.out.println("Printing .......");
    }

    @Override
    public void scan() {
        System.out.println("Feature Not Available ......");
    }

    @Override 
    public void fax() {
        System.out.println("Feature Not Available ......");
    }
}

// Segregated interface 
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

interface Fax {
    void fax();
}


class MFP implements Printer, Scanner, Fax {
    @Override
    public void print() {
        System.out.println("Printing .......");
    }

    @Override
    public void scan() {
        System.out.println("Scanning ......");
    }

    @Override 
    public void fax() {
        System.out.println("Fax ......");
    }
}

class SFP implements Printer {
    @Override
    public void print() {
        System.out.println("Printing .......");
    }
}


public class InterfaceSegregationPrinciple {
    public static void main(String[] args) {
        MultiFunctionPrinter m1 = new MultiFunctionPrinter();
        m1.print();
        m1.scan();
        m1.fax();

        SingleFunctionPrinter s1 = new SingleFunctionPrinter();
        s1.print();
        s1.scan();
        s1.fax();

        MFP m2 = new MFP();
        m2.print();
        m2.fax();

        SFP s2 = new SFP();
        s2.print();
        // s2.fax(); Not available
    }
}
