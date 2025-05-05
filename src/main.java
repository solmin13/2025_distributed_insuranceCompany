
public class main {

  public static void main(String[] args) {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    System.out.println("Enter customer details:");

    System.out.print("Account Number: ");
    String accountNumber = scanner.nextLine();

    System.out.print("Address: ");
    String address = scanner.nextLine();

    System.out.print("Age: ");
    int age = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    System.out.print("Job: ");
    String job = scanner.nextLine();

    System.out.print("Name: ");
    String name = scanner.nextLine();

    System.out.print("Phone Number: ");
    String phoneNumber = scanner.nextLine();

    System.out.print("RRN: ");
    String rrn = scanner.nextLine();

    System.out.print("Sex (M/F): ");
    String sexStr = scanner.nextLine();
    Sex sex = sexStr.equalsIgnoreCase("M") ? Sex.MALE : Sex.FEMALE;

    Sales sales = new Sales();
    if(sales.createCustomer(accountNumber, address, age, job, name, phoneNumber, rrn, sex)) {
    	System.out.println("Customer added successfully.");
    }
    else System.out.printf("false");
 
    

  }


}
