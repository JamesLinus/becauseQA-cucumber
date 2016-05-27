package org.becausecucumber.eclipse.plugin.common;

import org.becausecucumber.eclipse.plugin.common.testrail.TestRailImpl;

public class FindRuby {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	//	CommonPluginUtils.getCucumberPath();
		TestRailImpl.getInstance().initTestRail("https://gdcqatestrail01/testrail", "ahu@greendotcorp.com", "gu.chan-10262", "Green Dot Network");
	}

}
