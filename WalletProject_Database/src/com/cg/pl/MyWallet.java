package com.cg.pl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.cg.bean.*;
import com.cg.dao.Validator;
import com.cg.service.AccountService;
import com.cg.service.Gst;

public class MyWallet {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String choice="";
		while(true) {
		System.out.println("Menu");
		System.out.println("====");
		System.out.println("1 Create new Account");
		System.out.println("2 Withdraw");
		System.out.println("3 Deposit");
		System.out.println("4 Print all accounts");
		System.out.println("5 Transfer Money");
		System.out.println("6 Update Account");
		System.out.println("7 Delete Account");
		System.out.println("8 Find Account");
		System.out.println("9 Exit");
		System.out.println("Enter Your Choice");
		choice=br.readLine();
		AccountService ac=new AccountService();
		switch(choice) {
		case "1":	{int id=0;
					long mb=0L;
					String ah="";
					double bal=0.0;
					System.out.println("Enter account number");
					//accepting and validating input for account number
					while(true) {
						String s_id=br.readLine();
						boolean ch1=Validator.validatedata(s_id,Validator.aidpattern);
						if(ch1==true) {
							try {
								id=Integer.parseInt(s_id);
								break;
							}
							catch(NumberFormatException e) {
								System.out.println("Account number must be numeric.Re Enter");
							}
						}
						else {
							System.out.println("Re Enter Account Number in 3 digits");
						}
					}//end of account number while
					//accepting and validating input for mobile number
					System.out.println("Enter mobile number");
					while(true) {
						String mobile=br.readLine();
						boolean ch1=Validator.validatedata(mobile,Validator.mobilepattern);
						if(ch1==true) {
							try {
								mb=Long.parseLong(mobile);
								break;
							}
							catch(NumberFormatException e) {
								System.out.println("Mobile number must be numeric.Re Enter");
							}
						}
						else {
							System.out.println("Re Enter Mobile Number in 10 digits");
						}
					}//end of mobile number while
//==========================================================================================================
					//accepting and validating input for name
					System.out.println("Enter name");
					while(true) {
						String name=br.readLine();
						boolean ch1=Validator.validatedata(name,Validator.name);
						if(ch1==true) {
							try {
								ah=name;
								break;
							}
							catch(Exception e) {
								System.out.println("Name should ne alphabatical");
							}
						}
						else {
							System.out.println("Re Enter Name");
						}
					}//end of name while
					//accepting and validating input for balance
					System.out.println("Enter balance");
					String balance=br.readLine();
					bal=Double.parseDouble(balance);
//					while(true) {
//						boolean ch1=Validator.validatedata(balance,Validator.balance);
//						if(ch1==true) {
//							try {
//								break;
//							}
//							catch(Exception e) {
//								System.out.println("Mobile number must be numeric.Re Enter");
//							}
//						}
//						else {
//							System.out.println("Re Enter Mobile Number in 10 digits");
//						}
//						
//					}//end of mobile balance while
//============================================================================================================
					Account ob=new Account(id,mb,ah,bal);
					ac.addAccount(ob);}
					break;
		case "2":{
			long mob=0L;
			System.out.println("Enter mobile no");
			while(true) {
				String mobile=br.readLine();
				boolean ch1=Validator.validatedata(mobile,Validator.mobilepattern);
				if(ch1==true) {
					try {
						mob=Long.parseLong(mobile);
						break;
					}
					catch(NumberFormatException e) {
						System.out.println("Mobile number must be numeric.Re Enter");
					}
				}
				else {
					System.out.println("Re Enter Mobile Number in 10 digits");
				}
			}
			System.out.println("Enter Amount");
			String amount1=br.readLine();
			double amount=Double.parseDouble(amount1);
			double response=ac.withdraw(mob, amount);
			if(response!=0.0) {
				System.out.println(amount+" Withrawn From Account");
			}
		}
		break;
		case "3":{long mob=0L;
		System.out.println("Enter mobile no");
		while(true) {
			String mobile=br.readLine();
			boolean ch1=Validator.validatedata(mobile,Validator.mobilepattern);
			if(ch1==true) {
				try {
					mob=Long.parseLong(mobile);
					break;
				}
				catch(NumberFormatException e) {
					System.out.println("Mobile number must be numeric.Re Enter");
				}
			}
			else {
				System.out.println("Re Enter Mobile Number in 10 digits");
			}
		}
			System.out.println("Enter Amount");
			String amount1=br.readLine();
			double amount=Double.parseDouble(amount1);
			double response=ac.deposit(mob, amount);
			if(response!=0.0) {
				System.out.println(amount+" Deposited From Account");
			}
			
		}break;
		case "4":
			ac.printAllAccounts();
			break;
		case "5":{
			long from=0L;
			long to=0L;
			System.out.println("Input debitter mobile number");
			while(true) {
				String mobile=br.readLine();
				boolean ch1=Validator.validatedata(mobile,Validator.mobilepattern);
				if(ch1==true) {
					try {
						from=Long.parseLong(mobile);
						break;
					}
					catch(NumberFormatException e) {
						System.out.println("Mobile number must be numeric.Re Enter");
					}
				}
				else {
					System.out.println("Re Enter Mobile Number in 10 digits");
				}
			}
			System.out.println("Input creditter mobile number");
			while(true) {
				String mobile=br.readLine();
				boolean ch1=Validator.validatedata(mobile,Validator.mobilepattern);
				if(ch1==true) {
					try {
						to=Long.parseLong(mobile);
						break;
					}
					catch(NumberFormatException e) {
						System.out.println("Mobile number must be numeric.Re Enter");
					}
				}
				else {
					System.out.println("Re Enter Mobile Number in 10 digits");
				}
			}
			System.out.println("Input amount");
			double amount=Double.parseDouble(br.readLine());
			//Map<Long,Account> mp=ac.getAllAccounts();
			ac.transfer(from,to,amount);
			
			
		}
		break;
		case "6":{
			long amb=0L;
			int id=0;
			long mb=0L;
			String ah="";
			double bal=0.0;
			System.out.println("Enter mobile number of account to be updated");
			while(true) {
				String mobile=br.readLine();
				boolean ch1=Validator.validatedata(mobile,Validator.mobilepattern);
				if(ch1==true) {
					try {
						amb=Long.parseLong(mobile);
						break;
					}
					catch(NumberFormatException e) {
						System.out.println("Mobile number must be numeric.Re Enter");
					}
				}
				else {
					System.out.println("Re Enter Mobile Number in 10 digits");
				}
			}
			System.out.println("Enter Updated account number");
			//accepting and validating input for account number
			while(true) {
				String s_id=br.readLine();
				boolean ch1=Validator.validatedata(s_id,Validator.aidpattern);
				if(ch1==true) {
					try {
						id=Integer.parseInt(s_id);
						break;
					}
					catch(NumberFormatException e) {
						System.out.println("Account number must be numeric.Re Enter");
					}
				}
				else {
					System.out.println("Re Enter Account Number in 3 digits");
				}
			}//end of account number while
			//accepting and validating input for mobile number
			System.out.println("Enter Updated mobile number");
			while(true) {
				String mobile=br.readLine();
				boolean ch1=Validator.validatedata(mobile,Validator.mobilepattern);
				if(ch1==true) {
					try {
						mb=Long.parseLong(mobile);
						break;
					}
					catch(NumberFormatException e) {
						System.out.println("Mobile number must be numeric.Re Enter");
					}
				}
				else {
					System.out.println("Re Enter Mobile Number in 10 digits");
				}
			}//end of mobile number while
//==========================================================================================================
			//accepting and validating input for name
			System.out.println("Enter Updated name");
			while(true) {
				String name=br.readLine();
				boolean ch1=Validator.validatedata(name,Validator.name);
				if(ch1==true) {
					try {
						ah=name;
						break;
					}
					catch(Exception e) {
						System.out.println("Name should ne alphabatical");
					}
				}
				else {
					System.out.println("Re Enter Name");
				}
			}//end of name while
			//accepting and validating input for balance
			System.out.println("Enter Updated balance");
			String balance=br.readLine();
			bal=Double.parseDouble(balance);
//			while(true) {
//				boolean ch1=Validator.validatedata(balance,Validator.balance);
//				if(ch1==true) {
//					try {
//						break;
//					}
//					catch(NumberFormatException e) {
//						System.out.println("Mobile number must be numeric.Re Enter");
//					}
//				}
//				else {
//					System.out.println("Re Enter Mobile Number in 10 digits");
//				}
//				
//			}//end of mobile balance while
//============================================================================================================
//			Map<Long,Account> mp=ac.getAllAccounts();
//			ac.deleteAccount(mp.get(amb));
			Account ob=new Account(id,mb,ah,bal);
			ac.updateAccount(ob,amb);
		}break;
		case "7":{
			System.out.println("Enter mobile no");
			String mob1=br.readLine();
			long mob=Long.parseLong(mob1);
			ac.deleteAccount(mob);
		}
		break;
		case "8":{
			System.out.println("Enter mobile no");
			String mob1=br.readLine();
			long mob=Long.parseLong(mob1);
			ac.findAccount(mob);
		}
		break;
		case "9":System.out.println("Exiting Program");
			System.exit(0);
			break;
		default:System.out.println("Invalid Choice");
		
		}
		}//end of menu
		
		//======================================
//		AccountService service=new AccountService();
//		Account ob2=new Account(105,2243424,"SomeName",55000.00);
//		service.printStatement(ob2);
//		double b1=service.withdraw(ob2,5000.00);
//		System.out.println("After withdraw balance is"+b1);
//		double tax=service.calculateTax(Gst.PCT_5,b1);
//		System.out.println("Gst is"+tax);
	}

}
