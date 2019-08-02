package com.cg.dao;
import com.cg.bean.*;
import java.util.*;
public class NameComparator implements Comparator<Account>{

	@Override
	public int compare(Account arg0, Account arg1) {
		String ah1=arg0.getAccountholder();
		String ah2=arg1.getAccountholder();
		int a=ah1.compareTo(ah2);
		return a;

}
}
