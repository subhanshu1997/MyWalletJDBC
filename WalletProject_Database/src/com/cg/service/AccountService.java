package com.cg.service;

import java.util.Map;
import com.cg.dao.*;
import com.cg.bean.Account;

public class AccountService implements Gst,Transaction{
	AccountDAO dao=new AccountDAOImpl();
	@Override
	public boolean addAccount(Account ob) {
		dao.addAccount(ob);
		return true;
	}

	@Override
	public boolean deleteAccount(Long mob) {
		dao.deleteAccount(mob);
		return true;
	}

	@Override
	public void findAccount(Long mobileno) {
		dao.findAccount(mobileno);
		return;
	}

	@Override
	public void printAllAccounts() {
		dao.getAllAccounts();
		return;
	}

	@Override
	public double deposit(Long mob, double amount) {
		return dao.deposit(mob, amount);
	}

	@Override
	public boolean transfer(Long from,Long to, double amount) {
		return dao.transferMoney(from, to, amount);
	}

	@Override
	public double calculateTax(double PCT, double amount) {
		return 0;
	}

	@Override
	public boolean updateAccount(Account ob, long mob) {
		return dao.updateAccount(ob, mob);
	}

	@Override
	public double withdraw(Long mob, double amount) {
		return dao.withdraw(mob, amount);
	}
}