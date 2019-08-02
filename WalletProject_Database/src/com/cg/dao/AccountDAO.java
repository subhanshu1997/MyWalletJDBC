package com.cg.dao;
import java.util.Map;
import com.cg.bean.*;
public interface AccountDAO {
public boolean addAccount(Account ob);
public boolean updateAccount(Account ob,long mob);
public boolean deleteAccount(long mob);
public void findAccount(Long mobileno);
public double withdraw(Long mob, double amount);
public double deposit(Long mob,double amount);
public boolean transferMoney(Long from,Long to,double amount);
public void getAllAccounts();

}
