package com.cg.dao;
import com.cg.bean.*;
import java.util.*;
public class BalanceComparator implements Comparator<Account>{
		@Override
		public int compare(Account arg0, Account arg1) {
			double diff=arg0.getBalance()-arg1.getBalance();
			if(diff>0)
				return 1;
			else if(diff<0)
				return -1;
			else
			return 0;
		}
	}
