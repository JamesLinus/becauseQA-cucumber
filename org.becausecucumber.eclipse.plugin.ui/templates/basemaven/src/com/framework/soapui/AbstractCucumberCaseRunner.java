package com.framework.soapui;

import org.apache.log4j.Logger;

import com.eviware.soapui.SoapUIPro;
import com.eviware.soapui.SoapUIProTestCaseRunner;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;

public class AbstractCucumberCaseRunner extends SoapUIProTestCaseRunner {

	public AbstractCucumberCaseRunner() {
		// TODO Auto-generated constructor stub
		super(SoapUIPro.SOAPUI_PRO_TITLE + " TestCase CucumberSoapUIRunner");
	}

	public AbstractCucumberCaseRunner(String paramString) {
		super(paramString);
	}

	public static void main(String[] paramArrayOfString) {

		System.exit(new SoapUIProTestCaseRunner().init(paramArrayOfString));
	}

	@Override
	protected void runProject(WsdlProject arg0) {
		// TODO Auto-generated method stub
		super.runProject(arg0);
	}

	@Override
	protected void runTestCase(WsdlTestCase arg0) {
		// TODO Auto-generated method stub
		super.runTestCase(arg0);

	}

	@Override
	protected void runSuite(WsdlTestSuite arg0) {
		// TODO Auto-generated method stub
		super.runSuite(arg0);
	}

	public Logger getLog() {
		return this.log;
	}
}
