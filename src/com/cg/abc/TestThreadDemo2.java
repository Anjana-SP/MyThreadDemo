package com.cg.abc;


class BankCustomer
{
	double amt=10000;
synchronized	void withdraw(double amount)
	{
		//
		System.out.println("I am going for withdraw...");
		if(this.amt<amount)
		{
			System.out.println("less balance, kindly deposit");
			try
			{
				wait();
			}catch (Exception e) {
				 
			}
		}
		this.amt=this.amt-amount;
		System.out.println(this.amt + " "+"remining");
	}
	synchronized void deposit(double amount)
	{
		System.out.println("depositing....");
		this.amt=this.amt+amount;
		System.out.println("deposited");
		notify();
	}
	
}

 class AccountHolder1 extends Thread
{
	BankCustomer customer;
	
	public AccountHolder1(BankCustomer customer)
	{
		this.customer=customer;
	}
	public  void run()
	{
		customer.withdraw(15000.00);
	}
}


public class TestThreadDemo2 {

	public static void main(String[] args) {
		
		//if there account holder
		// access the account at same tim from location
		//150000
BankCustomer customer=new BankCustomer();
AccountHolder1 ac1=new AccountHolder1(customer);
ac1.start();
/*new Runnable()
{
	public void run()
	{
		customer.withdraw(15000.00);
	}
}.start()*/

//Runnable r=()->{ customer.withdraw(150000.00)};
new Thread()
{
	public void run()
	{
		customer.deposit(8000.00);
	}
}.start();

}
}