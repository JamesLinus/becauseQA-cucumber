package com.framework.soapui.utils;

import org.junit.Test;

import com.eviware.soapui.impl.wsdl.teststeps.JdbcRequestTestStep;
import com.eviware.soapui.impl.wsdl.teststeps.assertions.basic.XPathContainsAssertion;
import com.eviware.soapui.model.testsuite.TestAssertion;
import com.eviware.soapui.model.testsuite.TestCaseRunContext;
import com.framework.soapui.TestCaseRunner;

public class AddAssertDynamic {

	@Test
	public void addAssert(TestCaseRunner testRunner, TestCaseRunContext context) {

		String expand = context
				.expand("${CheckRetailerWebTable#ResponseAsXml#count(//Row)}");

		int parseInt = Integer.parseInt(expand);

		XPathContainsAssertion containerpath;
		JdbcRequestTestStep jdbc = (JdbcRequestTestStep) context.getTestCase()
				.getTestStepByName("CheckRetailerWebTable");

		for (int i = 1; i <= parseInt; i++) {
			String assertname = "Match content of [RecordRow" + i + "]";
			String xpath = "//Results[1]/ResultSet[1]/Row[" + i
					+ "]/ISCREDITCARDBILLPAY[1]/text()";
			TestAssertion assertionByName = jdbc.getAssertionByName(assertname);
			// log.info "test ojbect is:"+assertionByName
			if (assertionByName != null) {
				jdbc.removeAssertion(assertionByName);
				// log.info "remove assert index: "+i
			}
			containerpath = (XPathContainsAssertion) jdbc
					.addAssertion("XPath Match");
			containerpath.setPath(xpath);

			containerpath.selectFromCurrent();
			containerpath.setName(assertname);
			containerpath.setExpectedContent("1");

		}

	}
	
	
	public void parseXmlResponse(TestCaseRunner testRunner,TestCaseRunContext context){
		
	}

}
