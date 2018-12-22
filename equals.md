# Java中equals()和==的比较

Java中的equals()和==比较是个基础问题，之前在项目中也犯过错，简单对比一下。

## Object类的equals()和==

Object类的equals()是比较两个对象内存地址是否相同，==也是一样。

![](/resource/equals1.jpg?raw=true)

也就是如果object1.equals(object2)为true，那么object1和object2实际引用的是同一个对象。


## String类的equals()和==比较

String类重写了equals()方法，对比是字符串包含的内容是否相同。

![](/resource/equals2.jpg?raw=true)

如图我们可以看到，String中equals()是对内容的比较，通过char数组一位一位去比较是否相同。

==仍然是对内存地址的比较，也就是必须都指向同一字符串。

举个栗子：

![](/resource/equals3.jpg?raw=true)

## 非字符串类变量的equals()和==比较

equals()和==一样，都是比较对象堆内存的首地址，也就是比较是否指向同一对象。

再举个栗子：

![](/resource/equals4.jpg?raw=true)

![](/resource/equals5.jpg?raw=true)

可以看到，返回的都是false

如果也只想比较值的话，可以重写Person类的equals方法。

![](/resource/equals6.jpg?raw=true)

## 基本数据类型==

基本类型比较，那么只能用==来比较，没有equals方法

![](/resource/equals7.jpg?raw=true)

## 基本类型的包装类型的equals()和==比较

基本类型的包装类型，比如Boolean、Character、Byte、Shot、Integer、Long、Float、Double等的引用变量，==是比较地址的，而equals是比较内容的。

看一下Integer的源码

![](/resource/equals9.jpg?raw=true)

![](/resource/equals8.jpg?raw=true)

## 小结

1、对于基本数据类型，==比较的是两者的值是否相等

2、对于引用数据类型

	- ==比较的是引用的地址是否相同；Object中的.equals()方法和==功能一样
	- 但是String类中的.equals()方法重写了，比较的是两个引用对象的内容是否想同。


## 参考文献
- https://blog.csdn.net/andyzhaojianhui/article/details/75176807
- https://blog.csdn.net/g_66_hero/article/details/71081315
