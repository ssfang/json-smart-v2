# json-smart-v2


== My Changes ==
* JsonReaderI<T>抽象函数添加了一个方法，新增json数组时多了一个索引入参：  
  addValue(Object current, int index, Object value);默认调用addValue(Object current, Object value);
* JSONParserBase解析json基础类的readArray里修改上述的addValue为带索引的新方法
* 添加了测试代码，自定义json-smart\src\test\java\net\minidev\json\testForMyChanges\TestCustomJsonReaderI.java

* Bug: 原始数组类型序列化错误，两种解决方案：注册处理原始数组类型的写方法，如JsonWriterI<byte[]>；或者修改ArrayWriter类的writeJSONString方法里的((Object[]) value)强制转换。
== Changelog: ==

*V 2.2*
* rename asm to accessors-smart due to conflict name with asm.ow2.org lib.
* fix OSGI error
* add support for BigDecimal
* improve JSONObject.getAsNumber() helper
* add a Field Remaper

*V 2.1*
  * net.minidev.json.mapper renamed to net.minidev.json.writer
  * Add ACCEPT_TAILLING_SPACE Parssing Flag.
  * Mapper classes now non static.
  * Reader mapper are now available in net.minidev.json.reader.JsonReader class
  * Writer mapper are now available in net.minidev.json.writer.JsonWriter class

*V 2.0*
  * Fix Double Identification [http://code.google.com/p/json-smart/issues/detail?id=44 issue 44]
  * Fix Collection Interface Serialisation
  * Fix security Exception in ASM code
  * Project moved to GitHub
  * Fix [http://code.google.com/p/json-smart/issues/detail?id=42 issue 42]

*V 2.0-RC3*
  * Add custom data binding inside the ASM layer.
  * Add Date support
  * Add \x escape sequence support [http://code.google.com/p/json-smart/issues/detail?id=39 issue 39]
  * fix issue [http://code.google.com/p/json-smart/issues/detail?id=37 issue 37]

*V 2.0-RC2*
  * Fix critical [http://code.google.com/p/json-smart/issues/detail?id=23 issue 23]
  * Improve Javadoc in JSONStyle [http://code.google.com/p/json-smart/issues/detail?id=23 issue 24]

*V 2.0-RC1*
  * speed improvement in POJO manipulation
  * add JSONStyle.LT_COMPRESS predefined generate strct json, but ignoring / escapement.
