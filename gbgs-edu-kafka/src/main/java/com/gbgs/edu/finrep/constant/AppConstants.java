package com.gbgs.edu.finrep.constant;

import java.util.HashSet;
import java.util.Set;

public class AppConstants
{
	public static void  main(String args[]){
		Set<Integer> intSet = new HashSet<>();
		intSet.add(10);
		intSet.add(5);
		System.out.println(intSet.add(10));

	}
	public static final String TOPIC_NAME_TEST = "gbgs-rwa";
	public static final String TOPIC_NAME_USER_LOG = "log-audit";
	public static final String GROUP_ID = "gbgs-grp";
}
