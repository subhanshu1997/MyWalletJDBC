package com.cg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.cg.bean.Account;

public class AccountDAOImpl implements AccountDAO{
static Map<Long,Account> accmap=new HashMap<Long,Account>();
	@Override
	public boolean addAccount(Account ob){
		Connection gc=null;
		try{ gc=GetConnection.getConnection();
		PreparedStatement ps=gc.prepareStatement("Select * from account");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			if(rs.getInt(1)==ob.getAid()) {
				System.out.println("Employe with the same id exist");
				return false;
			}
		}
		ps=gc.prepareStatement("insert into account values(?,?,?,?)");
		ps.setInt(1,ob.getAid());
		ps.setLong(2,ob.getMobile());
		ps.setString(3,ob.getAccountholder());
		ps.setDouble(4,ob.getBalance());
		int a=ps.executeUpdate();
		if(a!=1) {
			System.out.println("Employee not inserted");
			return false;
		}
		gc.commit();
		}
		catch(Exception e) {
		System.out.println(e);	
		}
		finally {
			if(gc!=null)
			{System.out.println("Closing Connection");
			try{gc.close();
			}catch(Exception e){
				System.out.println(e);
			}
			}
		
		}
		System.out.println("Employee Added");
		return true;
	}

	@Override
	public boolean updateAccount(Account ob,long mob) {
		Connection gc=null;
		try{ gc=GetConnection.getConnection();
		PreparedStatement ps=gc.prepareStatement("Select aid from account where mobileno=?");
		ps.setLong(1,mob);
		ResultSet rs=ps.executeQuery();
		if(rs==null) {
			System.out.println("Account Does Not Exist");
		}
		ps=gc.prepareStatement("update account set aid=?,mobileno=?,accountholder=?,balance=? where mobileno=?");
		ps.setInt(1,ob.getAid());
		ps.setLong(2,ob.getMobile());
		ps.setString(3,ob.getAccountholder());
		ps.setDouble(4,ob.getBalance());
		ps.setLong(5,mob);
		int a=ps.executeUpdate();
		if(a!=1) {
			System.out.println("Employee not Updated");
			return false;
		}
		gc.commit();
		}
		catch(Exception e) {
		e.printStackTrace();	
		}
		finally {
			if(gc!=null)
			{System.out.println("Closing Connection");
			try{gc.close();
			}catch(Exception e){
				System.out.println(e);
			}
			}
		
		}
		System.out.println("Employee Updated");
		return true;
	}

	@Override
	public boolean deleteAccount(long mob) {
		Connection gc=null;
		try{ gc=GetConnection.getConnection();
		PreparedStatement ps=gc.prepareStatement("Select aid from account where mobileno=?");
		ps.setLong(1,mob);
		ResultSet rs=ps.executeQuery();
		if(rs==null) {
			System.out.println("Account Does Not Exist");
		}
		ps=gc.prepareStatement("delete from account where mobileno=?");
		ps.setLong(1,mob);
		int a=ps.executeUpdate();
		if(a!=1) {
			System.out.println("Employee not Deleted");
			return false;
		}
		gc.commit();
		}
		catch(Exception e) {
		e.printStackTrace();	
		}
		finally {
			if(gc!=null)
			{System.out.println("Closing Connection");
			try{gc.close();
			}catch(Exception e){
				System.out.println(e);
			}
			}
		
		}
		System.out.println("Employee Deleted");
		return true;
	}

	@Override
	public void findAccount(Long mobileno) {
		Connection gc=null;
		double balance=0.0;
		double new_balance=0.0;
		try{ gc=GetConnection.getConnection();
		PreparedStatement ps=gc.prepareStatement("Select * from account where mobileno=?");
		ps.setLong(1,mobileno);
		ResultSet rs=ps.executeQuery();
		if(rs==null) {
			System.out.println("No Account Linked to this Mobile Number");
			return;
		}
		while(rs.next()){
			System.out.print("Account Id: "+rs.getInt("aid")+" ");
			System.out.print("Mobile Number: "+rs.getLong("mobileno")+" ");
			System.out.print("Account Holder Name: "+rs.getString("accountholder")+" ");
			System.out.print("Account balance: "+rs.getDouble("balance")+" ");
			System.out.println();
		}
		gc.commit();
		return;
		}
		catch(Exception e) {
		System.out.println(e);	
		}
		finally {
			if(gc!=null)
			{System.out.println("Closing Connection");
			try{gc.close();
			}catch(Exception e){
				System.out.println(e);
			}
			}
		
		}
		return;
	}

	public void getAllAccounts() {
		Connection gc=null;
		double balance=0.0;
		double new_balance=0.0;
		try{ gc=GetConnection.getConnection();
		PreparedStatement ps=gc.prepareStatement("Select * from account");
		ResultSet rs=ps.executeQuery();
		if(rs==null) {
			System.out.println("No Account Exist");
			return;
		}
		while(rs.next()){
			System.out.print("Account Id: "+rs.getInt("aid")+" ");
			System.out.print("Mobile Number: "+rs.getLong("mobileno")+" ");
			System.out.print("Account Holder Name: "+rs.getString("accountholder")+" ");
			System.out.print("Account balance: "+rs.getDouble("balance")+" ");
			System.out.println();
		}
		gc.commit();
		return;
		}
		catch(Exception e) {
		System.out.println(e);	
		}
		finally {
			if(gc!=null)
			{System.out.println("Closing Connection");
			try{gc.close();
			}catch(Exception e){
				System.out.println(e);
			}
			}
		
		}
		return;
	}

	@Override
	public boolean transferMoney(Long from,Long to,double amount) {
		Connection gc=null;
		double frombalance=0.0;
		double tobalance=0.0;
		double new_balance=0.0;
		try{ gc=GetConnection.getConnection();
		PreparedStatement ps=gc.prepareStatement("select * from account where mobileno=?");
		ps.setLong(1,from);
		ResultSet rs=ps.executeQuery();
		if(rs==null) {
			System.out.println("Account does not exist");
			return false;
		}
		 ps=gc.prepareStatement("select * from account where mobileno=?");
		 ps.setLong(1,to);
		 ResultSet rs1=ps.executeQuery();
		 if(rs1==null) {
				System.out.println("Account does not exist");
				return false;
			}
		while(rs.next()){
			frombalance=rs.getLong("balance");
		}
		while(rs1.next()){
			tobalance=rs1.getLong("balance");
			System.out.println(tobalance);
		}
		double fromAccount=withdraw(from,amount);
		double toAccount=deposit(to,amount);
		if(fromAccount!=0.0 && toAccount!=0.0) {
			System.out.println("Transfer Sucessfull");
		gc.commit();
		}
		return true;
		}
		catch(Exception e) {
		System.out.println(e);	
		}
		finally {
			if(gc!=null)
			{System.out.println("Closing Connection");
			try{gc.close();
			}catch(Exception e){
				System.out.println(e);
			}
			}
		
		}
		return true;
	}
	@Override
	public double withdraw(Long mob, double amount) {
		Connection gc=null;
		double balance=0.0;
		double new_balance=0.0;
		try{ gc=GetConnection.getConnection();
		PreparedStatement ps=gc.prepareStatement("Select * from account where mobileno=?");
		ps.setLong(1,mob);
		ResultSet rs=ps.executeQuery();
		if(rs==null) {
			System.out.println("Account does not exist");
			return new_balance;
		}
		while(rs.next()){
			balance=rs.getLong("balance");
		}
		 new_balance=balance-amount;
		if(new_balance<1000.00) {
			new_balance=balance;
			System.out.println("Insufficient Balance");
			return 0.0;
		}
		ps=gc.prepareStatement("update account set balance=? where mobileno=?");
		ps.setDouble(1,new_balance);
		ps.setLong(2,mob);
		int a=ps.executeUpdate();
		if(a!=1) {
			System.out.println("Amount not withdrawn");
			return 0.0;
		}
		gc.commit();
		return new_balance;
		}
		catch(Exception e) {
		System.out.println(e);	
		}
		finally {
			if(gc!=null)
			{System.out.println("Closing Connection");
			try{gc.close();
			}catch(Exception e){
				System.out.println(e);
			}
			}
		
		}
		return new_balance;
//		System.out.println("Your new balance is "+new_balance);
//		accmap.put(ob.getMobile(),ob);
//		return accmap.get(ob.getMobile()).getBalance();
	}
	public double deposit(Long mob,double amount) {
		Connection gc=null;
		double balance=0.0;
		double new_balance=0.0;
		try{ gc=GetConnection.getConnection();
		PreparedStatement ps=gc.prepareStatement("Select * from account where mobileno=?");
		ps.setLong(1,mob);
		ResultSet rs=ps.executeQuery();
		if(rs==null) {
			System.out.println("Account does not exist");
			return new_balance;
		}
		while(rs.next()){
			balance=rs.getLong("balance");
		}
		 new_balance=balance+amount;
		ps=gc.prepareStatement("update account set balance=? where mobileno=?");
		ps.setDouble(1,new_balance);
		ps.setLong(2,mob);
		int a=ps.executeUpdate();
		if(a!=1) {
			System.out.println("Amount not deposited");
			return 0.0;
		}
		gc.commit();
		return new_balance;
		}
		catch(Exception e) {
		System.out.println(e);	
		}
		finally {
			if(gc!=null)
			{System.out.println("Closing Connection");
			try{gc.close();
			}catch(Exception e){
				System.out.println(e);
			}
			}
		
		}
		return new_balance;	
	}	

	
}
