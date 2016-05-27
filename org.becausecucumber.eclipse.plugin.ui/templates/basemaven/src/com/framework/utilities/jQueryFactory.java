package com.framework.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.IOUtils;

public class jQueryFactory {

	private String ref = "SeleniumjQuery";
	private String url = "jquery/jquery-1.11.3.min.js";
	private JavascriptExecutor js;
	private long defaultTimeout = GlobalDefinition.JS_TIMEOUT;
	private final AtomicLong id_factory = new AtomicLong();

	public long getDefaultTimeout() {
		return defaultTimeout;
	}

	public void setDefaultTimeout(long defaultTimeout) {
		this.defaultTimeout = defaultTimeout;
	}

	public jQueryFactory(JavascriptExecutor js) {
		this.js = js;
	}

	public jQueryFactory() {
	}

	public void setJs(JavascriptExecutor js) {
		this.js = js;
	}

	public long count(String query) {
		return query(query).length();
	}

	public jQuery querySafe(String query) {
		jQuery q = query(query);
		if (q.length() == 0) {
			throw new NoSuchElementException(query);
		}
		return q;
	}

	public jQuery queryUntilAtLeast(String query, int min)
			throws TimeoutException {
		return queryUntilAtLeast(query, min, getDefaultTimeout());
	}

	public jQuery queryUntil(String query) throws TimeoutException {
		return queryUntilAtLeast(query, 1, getDefaultTimeout());
	}

	public jQuery queryUntil(String query, long timeout)
			throws TimeoutException {
		return queryUntilAtLeast(query, 1, timeout);
	}

	public jQuery queryUntilAtLeast(String query, int min, long timeout)
			throws TimeoutException {
		return queryUntil(query, min, -1, timeout);
	}

	public jQuery queryUntilAtMost(String query, int max, long timeout)
			throws TimeoutException {
		return queryUntil(query, 0, max, timeout);
	}

	public jQuery queryUntilAtMost(String query, int max)
			throws TimeoutException {
		return queryUntil(query, 0, max, getDefaultTimeout());
	}

	public jQuery queryUntilNone(String query, long timeout)
			throws TimeoutException {
		return queryUntil(query, 0, 0, timeout);
	}

	public jQuery queryUntilNone(String query) throws TimeoutException {
		return queryUntil(query, 0, 0, getDefaultTimeout());
	}

	public jQuery queryUntil(String query, int min, int max, long timeout)
			throws TimeoutException {
		final jQuery q = query(query);
		q.setTimeout(timeout);
		try {
			while (true) {
				if (q.length() >= min) {
					if (max == -1 || q.length() <= max) {
						return q;
					}
				}
				q.pause("Looking for " + query + " with " + min + " to " + max
						+ " results.");
				q.init();
			}
		} finally {
			q.clearTimeout();
		}
	}

	public jQuery query(String query) {
		return new jQuery(this, createId(), query);
	}

	public jQuery query(WebElement we) {
		return new jQuery(this, createId(), we);
	}

	public Object js(String script, Object... args) {
		if (js == null) {
			throw new IllegalStateException(
					"Cannot run js without setting the js executor!");
		}
		try {
		
			return js.executeScript(script, args);
		} catch (Exception e) {
			if (ensurejQuery()) {
				return js(script, args);
			}
			throw new RuntimeException(e);
		}
	}
	public Object js(String script) {
		if (js == null) {
			throw new IllegalStateException(
					"Cannot run js without setting the js executor!");
		}
		try {
		
			return js.executeScript(script);
		} catch (Exception e) {
			if (ensurejQuery()) {
				return js(script);
			}
			throw new RuntimeException(e);
		}
	}

	public String getUrl() {
		return url;
	}

	public jQueryFactory setUrl(String url) {
		this.url = url;
		return this;
	}

	public boolean ensurejQuery(){

		if ((Boolean) js.executeScript(
				"return typeof window[arguments[0]] != typeof __undefined",
				getRef())) {
			return false;
		}
		// String resource = jQueryFactory.class.getResource(url).getFile();
		InputStream is = jQueryFactory.class.getClassLoader()
				.getResourceAsStream(getUrl());
		try {
			js.executeScript(IOUtils.readFully(is));
			is.close();
			js.executeScript("window[arguments[0]] = jQuery.noConflict(true)",
					getRef());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	public long createId() {
		return id_factory.incrementAndGet();
	}

	public jQueryFactory setRef(String ref) {
		this.ref = ref;
		return this;
	}

	public String getRef() {
		return ref;
	}
}
