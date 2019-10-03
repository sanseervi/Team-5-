package com.main;


import java.util.Scanner;

import com.account.PerformTransaction;




public class Main {

	public static void main(String[] args) {
	
		PerformTransaction pt = new PerformTransaction();
		Scanner sc = new Scanner(System.in);
		int n;
		
		do {
			System.out.println("enter your choice :"+"\n1.Enter New Account\n2.Perform Transaction\n3.Print Data\n4.Exit");
			n = sc.nextInt();
		switch(n) {
		case 1:
			pt.enterAccountData();
			break;
		case 2:
			pt.performTransaction();
			break;
		case 3:
			pt.printData();
			break;
		case 4:
			System.out.println("Good Bye!!");
			break;
		default:
			System.out.println("enter valid choice");
		}
		}while(n!=4);
		
		
	}

}
