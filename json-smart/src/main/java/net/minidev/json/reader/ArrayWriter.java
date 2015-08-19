package net.minidev.json.reader;

import java.io.IOException;

import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;

public class ArrayWriter implements JsonWriterI<Object> {
	@Override
	public <E> void writeJSONString(E value, Appendable out, JSONStyle compression) throws IOException {
		compression.arrayStart(out);
		boolean needSep = false;

		// int arraySize = Array.getLength(value);
		// for (int index = 0; index < arraySize; index++) {
		// if (needSep)
		// compression.objectNext(out);
		// else
		// needSep = true;
		// // java.lang.reflect.Array can automatically wrap the primary type element to an object
		// JSONValue.writeJSONString(Array.get(value, index), out, compression);
		// }

		// 原始类型数组不可以强制转换为Object[]。
		 //primary type array cann't be cast here.
		 for (Object o : ((Object[]) value)) {
		 if (needSep)
		 compression.objectNext(out);
		 else
		 needSep = true;
		 JSONValue.writeJSONString(o, out, compression);
		 }
		compression.arrayStop(out);
	}
}
