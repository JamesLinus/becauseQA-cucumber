package com.framework.testcase.testrail;

import java.util.List;

public class PlanRunEntry {

	private boolean include_all = false;
	private List<Integer> case_ids = null;
	private List<Integer> config_ids = null;

	public boolean isInclude_all() {
		return include_all;
	}

	public void setInclude_all(boolean include_all) {
		this.include_all = include_all;
	}

	public List<Integer> getCase_ids() {
		return case_ids;
	}

	public void setCase_ids(List<Integer> case_ids) {
		this.case_ids = case_ids;
	}

	public List<Integer> getConfig_ids() {
		return config_ids;
	}

	public void setConfig_ids(List<Integer> config_ids) {
		this.config_ids = config_ids;
	}
}
