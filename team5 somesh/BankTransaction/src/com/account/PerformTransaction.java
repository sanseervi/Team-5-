package com.account;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PerformTransaction {

	public void performTransaction() {
		try {
			//creating only one sessionfactory object per database
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			//creating session for every transaction
			Session session = sessionFactory.openSession();
			
			Transaction transaction = session.beginTransaction();
			String accName;
			String accType;
			int targetAccNo = 0;
			int accNo = 0;
			double amount = 0;
			double srcBalance = 0;
			double targetBalance = 0;
			Scanner sc = new Scanner(System.in);
			// create two Accounts, to do transactions from one account to another account
			Account account1 = new Account();
			Account account2 = new Account();
			System.out.println("Enter your Account Number");
			accNo = sc.nextInt();

			account1 = (Account) session.get(Account.class, accNo);
			System.out.println(account1.getAccName());
			srcBalance = account1.getBalance();
			System.out.println("Your balance is: " + srcBalance);
			//do transaction, if balance is >0 
			if (srcBalance > 0) {
				System.out.println("Enter the amount to be transfered");
				amount = sc.nextDouble();
				System.out.println("Enter the Target Account Number ");
				targetAccNo = sc.nextInt();
				account2 = (Account) session.get(Account.class, targetAccNo);
				targetBalance = account2.getBalance();
				account2.setBalance(targetBalance + amount);
				session.update(account2);

				account1.setBalance(srcBalance - amount);
				session.update(account1);
				System.out.println("updated");

			} else {
				System.out.println("Transaction failed");
			}

			session.save(account1);
			session.save(account2);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterAccountData() {

		try {
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			String accName;
			String accType;
			double balance = 0;
			int accNo = 0;
			Scanner sc = new Scanner(System.in);
			// get the values from user to do transaction
			for (int i = 0; i < 2; i++) {
				System.out.println("enter the values accNo");
				accNo = sc.nextInt();
				System.out.println("enter the values accName");
				accName = sc.next();
				System.out.println("enter the values accNType");
				accType = sc.next();
				System.out.println("enter the values Balance");
				balance = sc.nextDouble();

				Account account = new Account();
				account.setAccNo(accNo);
				account.setAccName(accName);
				account.setAccType(accType);
				account.setBalance(balance);
				session.save(account);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printData() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		// session started
		Transaction transaction = session.beginTransaction();
		Account acc = new Account();
		Query query = session.createQuery("from Account");
		List list = query.list();
		System.out.println(list.toString());
		
	}
}