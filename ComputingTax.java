/*
 * Name:Bar Rakovsky
 * Due Date: 06/30/18
 * Project 2
 * In this project we were requested to create a program that takes the user input of "filing status" and "income" and calculate the tax 
 * 		according to a formula that was presented in the project description. 
 * 
 *constant- I chose to do the percentage as a constant since there is a repetition in the code for all filing status, and also, i those
 *		numbers are important to the formula so no one should be able to change them. 
 *
 * flow of control- 
 * 	Main loop- The main loop is responsible to repeat the program until the user choose to end it. I control the loop with a boolean 
 * 		cont- "continue"
 * 	inputIntCheck method- This method checks the user input for the filing status. I check for two things. First if the user enter an int, 
 * 		and second if the user enter a valid filing status. I need to check first if the user enter an int since i don't want to get an 
 * 		exception when i try to assign the user input into the int variable. This method return an int. 
 * inputDoubleCheck- This method is responsible to check the user input when the user enter his income amount. This has a different method since
 * 		the user input can be a double and not an int, so the method should return a double. 
 * calculateTax method- This method is responsible to calculate the tax according to the formula that was given in the project description. 
 * 		This method return a double variable which is the tax that was calculated according to the filing status. 
*/

import java.text.DecimalFormat;
import java.util.Scanner;

public class ComputingTax {
	final static double precent10 = 0.10;
	final static double precent15 = 0.15;
	final static double precent25 = 0.25;
	final static double precent28 = 0.28;
	final static double precent33 = 0.33;
	final static double precent35 = 0.35;

	public static void main(String[] args) {
		
		
		Scanner input = new Scanner(System.in);
		boolean cont = true;
		
		System.out.println("Welcome to the Tax Calculator program");
		
		do{
			//I decided to pass the strings without saving it into a variable since this why it doesn't take memory. 
			//I split the string into two lines so it will be easier to read. 
			int status = inputIntCheck("\nPlease enter your filing status:(0- Single, 1- Married filing jointly, "
													+ "2- Married filing separatly, 3- Head of a household): ",input);
			
			
			double taxable = inputDoubleCheck("Please Enter the taxable income: ",input);		
			double taxes = calculateTax(status, taxable);
			
			//format the output with two decimal points. 
			DecimalFormat dm = new DecimalFormat("0.00");
			System.out.println("\nTax is " + dm.format(taxes));
			
			System.out.print("\nWould you like to input diferent data? (enter y to continue) ");
			
			if(!input.hasNext("y")){
				System.out.println("Thanks for using the Tax Calculator program, Goodbye!");
				cont = false;
			}
		
			input.next();
			
		}while(cont);
		
		
		//closes the scanner. 
		input.close();
	}
	
	
	//This method checks that the user input for valid filing status and return the valid filing status. 
	//I pass the open scanner and the question string and return a valid income for the user.
	public static int inputIntCheck(String str, Scanner sc){
		
		boolean cont = true;
		int x = 0;
		
		
		do{
			System.out.print(str);
			//checks if the user input an int. 
			if(!sc.hasNextInt()){
				System.out.println("\nYou did not enter a number, please try again");
				sc.next();
			}else{
				x = sc.nextInt();
				//check if the user input a valid filing status. 
				if(x == 0 || x == 1 || x == 2 || x == 3){
					cont = false; 		
				}else{
					System.out.println("\nYou did not enter a valid filing status, please try again");
				}
				
			}
		}while(cont);
		
		
		return x;
		
	}
	
	//This method checkS the user input for a valid taxable income. 
	//I pass the open scanner and the question string and return a valid income for the user. 
	public static double inputDoubleCheck(String str, Scanner sc){
		
		double x = 0; 
		boolean cont = true; 
		
		do{			
			System.out.print("\n"+str);
			if(!sc.hasNextDouble()){
				System.out.println("\nYou did not enter a number, please try again");
				sc.next();
			}else{
				x =  sc.nextDouble();
				if(x < 0){
					System.out.println("\nYou did not enter a positive number, please try again");
				}else{
					cont = false; 
				}			
			}
		}while(cont);
		
		return x;
		
	}
	
	//This method calculates the tax according to the income and the filing status and return the tax amount. 
	//I pass the filing status and the income from the user input after it was checked for accuracy and validity. 
	//It calculated the tax amount by taking each filing status and divide it into 
	public static double calculateTax(int filingStatus, double income){
		
		double tax = 0; 
		
		switch(filingStatus){
		case 0:
			if(income <= 8350){
				 tax = income*precent10; 
				 break; 
			}else if(income <= 33950){
				 tax = 8350*0.10 + ((income-8350)*precent15);
				 break;
			}else if(income <= 82250){
				 tax = 8350*precent10 + 25600*precent15 + ((income-33950)*precent25);
				 break;
			}else if (income <= 171550){
				 tax = (8350*precent10) + (25600*0.15) + (48300*0.25) + ((income-82250)*0.28);
				 break;
			}else if (income <= 372950){
				 tax = 8350*precent10 + 25600*precent15 + 48300*precent25 + 89300*precent28 + ((income-171500)*precent33);
				 break;
			}else if(income >= 372951){
				 tax = 8350*precent10 + 25600*precent15 + 48300*precent25 + 89300*precent28 + 201400*precent33 + ((income-372950)*precent35);
				 break;
			}			
		case 1:
			if(income <= 16700){
				 tax = income*precent10; 
				 break;
			}else if(income <= 67900){
				 tax = 16700*precent10 + ((income-16700)*precent15);
				 break;
			}else if(income <= 137050){
				 tax = 16700*precent10 + 51200*precent15 + ((income-67900)*precent25);
				 break;
			}else if (income <= 208850){
				 tax = 16700*precent10 + 51200*precent15 + 69150*precent25 + (income-137050)*precent28;
				 break;
			}else if (income <= 372950){
				 tax = 16700*precent10 + 51200*precent15 + 69150*precent25 + 71800*precent28 + (income-208850)*precent33;
				 break;
			}else if(income >= 372951){
				 tax = 16700*precent10 + 51200*precent15 + 69150*precent25 + 71800*precent28 + 164100*precent33 + ((income-372950)*precent35);
				 break;
			}						
		case 2:
			if(income <= 8350){
				 tax = income*precent10; 
				 break;
			}else if(income <= 33950){
				 tax = 8350*precent10 + ((income-8350)*precent15);
				 break;
			}else if(income <= 68525){
				 tax = 8350*precent10 + 25600*precent15 + ((income-33950)*precent25);
				 break;
			}else if (income <= 107425){
				 tax = 8350*precent10 + 25600*precent15 + 34575*precent25 + (income-68525)*precent28;
				 break;
			}else if (income <= 186475){
				 tax = 8350*precent10 + 25600*precent15 + 34575*precent25 + 35900*precent28 + (income-104425)*precent33;
				 break;
			}else if(income >= 186476){
				 tax = 8350*precent10 + 25600*precent15 + 34575*precent25 + 35900*precent28 + 82050*precent33 + ((income-186475)*precent35);
				 break;
			}						
		case 3:
			if(income <= 11950){
				 tax = income*precent10; 
				 break;
			}else if(income <= 45500){
				 tax = 11950*precent10 + ((income-11950)*precent15);
				 break;
			}else if(income <= 117450){
				 tax = 11950*precent10 + 33550*precent15 + ((income-45500)*precent25);
				 break;
			}else if (income <= 190200){
				 tax = 11950*precent10 + 33550*precent15 + 71950*precent25 + (income-117450)*precent28;
				 break;
			}else if (income <= 372950){
				 tax = 11950*precent10 + 33550*precent15 + 71950*precent25 + 72750*precent28 + (income-190200)*precent33;
				 break;
			}else if(income >= 372951){
				 tax = 11950*precent10 + 33550*precent15 + 71950*precent25 + 72750*precent28 + 182750*precent33 + ((income-372950)*precent35);
				 break;
			}		
		}
		
			
		return tax; 
		
	}
	
}
