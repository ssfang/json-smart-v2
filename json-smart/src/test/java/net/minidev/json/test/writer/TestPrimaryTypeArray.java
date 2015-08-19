package net.minidev.json.test.writer;

import junit.framework.TestCase;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;

public class TestPrimaryTypeArray extends TestCase {
	/**
	 * <h1>ClassCastException</h1>
	 * 
	 * <pre>
	 * <b>java.lang.ClassCastException: [B cannot be cast to [Ljava.lang.Object; at</b>
	 * <b>net.minidev.json.reader.ArrayWriter.writeJSONString(ArrayWriter.java:12) at</b>
	 * net.minidev.json.JSONValue.writeJSONString(JSONValue.java:596) at
	 * net.minidev.json.JSONValue.toJSONString(JSONValue.java:632) at
	 * net.minidev.json.test.writer.TestPrimaryTypeArray.testWriteBytes(TestPrimaryTypeArray.java:11) at
	 * sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at
	 * sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) at
	 * sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at
	 * java.lang.reflect.Method.invoke(Method.java:497) at junit.framework.TestCase.runTest(TestCase.java:164) at
	 * junit.framework.TestCase.runBare(TestCase.java:130) at junit.framework.TestResult$1.protect(TestResult.java:106) at
	 * junit.framework.TestResult.runProtected(TestResult.java:124) at junit.framework.TestResult.run(TestResult.java:109) at
	 * junit.framework.TestCase.run(TestCase.java:120) at junit.framework.TestSuite.runTest(TestSuite.java:230) at
	 * junit.framework.TestSuite.run(TestSuite.java:225) at
	 * org.eclipse.jdt.internal.junit.runner.junit3.JUnit3TestReference.run(JUnit3TestReference.java:131) at
	 * org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38) at
	 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:459) at
	 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:675) at
	 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382) at
	 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void testWriteRootBytes() throws Exception {
		byte[] bytes = new byte[] { 2, 2 };

		// // COMPLIE AND RUNTIME ERROR, Cannot cast from byte[] to Object[]
		// Object[] objInts = (Object[]) bytes;

		String s = JSONValue.toJSONString(bytes, JSONStyle.MAX_COMPRESS);
		assertEquals(s, "[2,2]");
	}

	public void testWriteBytes() throws Exception {
		byte[] bytes = new byte[] { 2, 2 };
		Object[] objArray = new Object[] { 22, bytes };
		
		// // COMPLIE AND RUNTIME ERROR, Cannot cast from byte[] to Object[]
		// Object[] objInts = (Object[]) bytes;

		String s = JSONValue.toJSONString(objArray, JSONStyle.MAX_COMPRESS);
		assertEquals(s, "[22,[2,2]]");
	}

	public void testWriteByte() throws Exception {
		byte b = 2;
		String s = JSONValue.toJSONString(b, JSONStyle.MAX_COMPRESS);
		assertEquals(s, "2");
	}

	public void testWriteInts() throws Exception {
		int[] ints = new int[] { 2, 2 };

		// // COMPLIE AND RUNTIME ERROR, Cannot cast from int[] to Object[]
		// Object[] objInts = (Object[]) ints;

		String s = JSONValue.toJSONString(ints, JSONStyle.MAX_COMPRESS);
		assertEquals(s, "[2,2]");
	}
}
