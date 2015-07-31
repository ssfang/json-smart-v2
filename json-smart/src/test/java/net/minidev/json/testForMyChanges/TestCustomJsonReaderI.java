package net.minidev.json.testForMyChanges;

import static net.minidev.json.parser.JSONParser.DEFAULT_PERMISSIVE_MODE;

import java.io.IOException;

import junit.framework.TestCase;
import net.minidev.json.JSONAwareEx;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import net.minidev.json.writer.DefaultMapper;

public class TestCustomJsonReaderI extends TestCase {

	public static void main(String[] args) throws IOException {
		System.out.println(JSONValue.toJSONString(new Object[][] { { 2, 2 }, { "hh", null } }));
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("Runtime.getRuntime().addShutdownHook");
			}
		});
	}

	public void testNonstandardJsonTextParser() throws Exception {
		String txt = "[null, , null, {22:66, \"key1\":ss2, \" key2 \":22ss, k e y: s s }, 22 55, 22 , \"f ss\",\"f, ss\"]";
		System.out.println(txt);
		Object parsedObject = JSONValue.parse(txt);
		String strJsonStringify = JSONValue.toJSONString(parsedObject, JSONStyle.MAX_COMPRESS);

		// 解析后重新最大化压缩（去引号）文本化，前后对比：
		// 当原本含有奇异的符号，如空格和逗号等
		// 1) key1被去了引号，而key2前后含有空格还带有引号；
		// 2) f ss被去了引号，而f, ss前后含有逗号还带有引号
		System.out.println(strJsonStringify);

		assertNotSame(txt, strJsonStringify);
	}

	/**
	 * 自定义的myJsonReader只使用了第一层，第二层，如{}里的还是交给了JSONObject解决的，因为我们继承了DefaultMapper的json解析处理对象
	 * 
	 * <pre>
	 * current = [], index = 0, value = null
	 * ==========
	 * current = [null], index = 1, value = null
	 * ==========
	 * current = [null,null], index = 2, value = {"22":66,"key1":"ss2"," key2 ":"22ss","k e y":"s s"}
	 * ==========
	 * current = [null,null,{"22":66,"key1":"ss2"," key2 ":"22ss","k e y":"s s"}], index = 3, value = 22 55
	 * ==========
	 * current = [null,null,{"22":66,"key1":"ss2"," key2 ":"22ss","k e y":"s s"},"22 55"], index = 4, value = 22
	 * ==========
	 * current = [null,null,{"22":66,"key1":"ss2"," key2 ":"22ss","k e y":"s s"},"22 55",22], index = 5, value = f ss
	 * ==========
	 * current = [null,null,{"22":66,"key1":"ss2"," key2 ":"22ss","k e y":"s s"},"22 55",22,"f ss"], index = 6, value = f, ss
	 * ==========
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void testCustomJsonReader() throws Exception {
		String txt = "[null, , null, {22:66, \"key1\":ss2, \" key2 \":22ss, k e y: s s }, 22 55, 22 , \"f ss\",\"f, ss\"]";
		System.out.println(txt);
		JSONParser p = new JSONParser(DEFAULT_PERMISSIVE_MODE);
		try {
			Object parsedObject = p.parse(txt, myJsonReader);
			System.out.println(JSONValue.toJSONString(parsedObject, JSONStyle.MAX_COMPRESS));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 修改了 JSONParserBase的readArray方法，并为JsonReaderI<T>添加了addValue(Object current, int index, Object value) 方法。
	DefaultMapper<JSONAwareEx> myJsonReader = new DefaultMapper<JSONAwareEx>(JSONValue.defaultReader) {
		@Override
		public void setValue(Object current, String key, Object value) {
			System.out.println("current = " + current + ", key = " + key + ", value = " + value);
			// JSONValue.defaultReader.DEFAULT.setValue(current, key, value);
			super.setValue(current, key, value);
		}

		@Override
		public void addValue(Object current, int index, Object value) throws ParseException, IOException {
			// 在这里debug断点看堆栈，更好显示如下：
			// mapper.addValue(current, arrayIndex++, readMain(mapper, stopArray));
			// protected <T> T readArray(JsonReaderI<T> mapper) throws ParseException, IOException {}
			//
			System.out.println("current = " + current + ", index = " + index + ", value = " + value);
			super.addValue(current, index, value);
		}

		@Override
		public void addValue(Object current, Object value) {
			System.out.println("==========");
			// JSONValue.defaultReader.DEFAULT.addValue(current, value);
			super.addValue(current, value);
		}
	};

}
