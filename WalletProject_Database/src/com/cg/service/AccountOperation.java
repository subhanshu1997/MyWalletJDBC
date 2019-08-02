package com.cg.service;
import java.util.*;
import com.cg.bean.Account;
public interface AccountOperation {
public boolean addAccount(Account ob);
public boolean deleteAccount(Long mob);
public void findAccount(Long mobileno);
public void printAllAccounts();
public boolean updateAccount(Account ob,long mob);
}
