# 《疯狂的Java》

## 第四章 数组

- 创建 int[] array = new int[5] {1，2，3，4，5};
- Arrays类
	- 方法
	```java
	int binarySearch(type[] a, type key); //使用二分法查找key在a数组中的索引，要求a必须升序排列
	type[] copyOf(type[] original,int length); //复制original数组的length个元素给新数组
	void sort(type[] a);
	boolean equals(type[] a, type[] b); //a,b数组长度相等，元素也相同

	//使用方法
	Arrays.sort(nums);
	Arrays.copyOf(nums, 10);
	```

## 第五章 面向对象(上)

### 5.1 类与对象

- 区分栈内存和堆内存
- 类包括成员变量，方法，构造器
```java
public class 类名 {
	//成员变量
	//方法
	//构造器
}
```
- static可以修饰成员变量和方法，属于类本身，不属于该类的某个对象，需要通过类调用
	- static修饰的成员，不能访问没有static修饰的成员，即静态成员不能访问非静态成员
- this总是指向调用该方法的对象
	- 最大作用是让类中的一个方法，访问该类中的另外一个方法
	- 如果普通方法中有个局部变量与成员变量重名，程序需要访问这个被覆盖掉的成员变量，则需要使用this

### 5.2 方法详解

- 方法的基本类型参数传递是值传递，将实际参数的副本传入方法，参数本身不受影响
	- 引用类型的参数传递，也是值传递。但是参数由于是引用变量，类似指针，实参和形参都指向同一个对象，所以还是会改变对象本身
- 方法重载，方法名相同，形参列表不同

### 5.3 成员变量与局部变量

- 成员变量包括实例变量和类变量；局部变量包括形参，方法局部变量，代码块局部变量

### 5.4 隐藏与封装
- 封装是指将对象的状态信息隐藏在对象内部，通过类提供的方法实现对内部信息的访问和操作
- package,import,import static

### 5.5 深入构造器
- 构造器的重载
```java
public class Apple{
	String name;
	String color;
	double weight;
	// 无参数构造器
	public Apple(){}
	// 两个参数构造器
	public Apple(String name, String color){
		this.name = name;
		this.color = color;
	}
	// 三个参数构造器
	public Apple(String name, String color, double weight){
		this(name, color);
		this.weight = weight;
	}
}
```
### 5.6 类的继承
- 重写，子类对父类方法的覆盖
- 子类方法中调用父类被覆盖的实例方法或者实例变量，使用super关键字
```java
public void callOverrideedMethod(){
	super.fly();
	super.name = "xxx";
}
```
- 子类不会获得父类的构造器，但是可以调用父类的构造器，使用super，而且至少(隐式)调用一次
```java
class Base {
	public double size;
	public String name;
	public Base(double size, String name) {
		this.size = size;
		this.name = name;
	}
}
public class Sub extends Base {
	public String color;
	public Sub (double size, String name, String color) {
		super(size, name);
		this.color = color;
	}
}
```
- 如果父类方法需要被外部调用，用public修饰，但是又不希望子类重写，可以使用final

### 5.7 多态
- 相同类型的变量、调用同一个方法时呈现出多种不同的行为特征，这就是多态
	- 父类的引用指向子类对象
- instanceof，用于判断前面的对象是否是后面的类，或者子类
```java
Object hello = "hello";
System.out.println(hello instanceof Object)
```
- (type)variable 可以强制类型转换


### 5.8	继承与组合
- 组合是把旧类对象作为新类的成员变量组合进来

### 5.9 初始化块
- 在构造器前执行
- 静态块在类被加载时就会被调用


## 第六章 面向对象(下)

### 6.1 Java增强的包装类
- 基本类型转换成字符串
```java
String dbStr = String.valueOf(2.345);
String boolStr = String.valueOf(ture);
```
- 字符串转换成基本类型
```java
String intStr = "123";
// 方法一
int it1 = new Interger(intStr);
// 方法二
int it2 = Interger.parseInt(intStr);
```

### 6.2 处理对象
- toString()
- ==和equals()
	- 对于两个引用类型变量，只有他们指向同一个对象时，才会返回true
	- str1.equals(str2) // 如果str1和str2值相同，可以判断为true，因为String的equals()已经重写了

### 6.3 类成员
- 类成员包括类变量，类方法，静态初始化块，内部类

### 6.4 final修饰符
- 可以修饰类，变量，方法，用于表示它修饰的类，变量，方法不可变
- final修饰基本类型，不能对基本类型重新赋值，但是final修饰引用类型时，只保证引用类型变量引用的地址不改变，但是引用对象可以改变
- final修饰的方法，不可重写；子类不可重写父类的final方法；但是可以重载
- final修饰的类不可有子类

### 6.5 抽象类
- 抽象类和抽象方法用abstract修饰，有抽象方法的类必须定义成抽象类
- 抽象类不能实例化，只能当成父类被子类继承
- 抽象方法只有方法签名，没有方法实现的方法，用abstract修饰，方法体为空，最后增加;
- 抽象类举例
```java
public abstract class Shape{
	private String color;
	public abstract double calPerimeter();
	public Shape(){

	}
	public Shape(String color){
		this.color = color;
	}
}

public class Triangle extends Shape {
	private double a;
	private double b;
	private double c;
	public Triangle(String color, double a, double b, double c){
		super(color);
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public double calPerimeter(){
		return a + b + c;
	}	
}
```

### 6.6 接口
- 定义了某一批类所需要遵循的规范，接口包括成员变量(静态变量)，方法(抽象实例方法，类方法或默认方法)，内部类
```java
public interface Output {
	void out();
	void getData(String message);
}

public class machine implements Output {
	
}
```

### 6.7 内部类
- 非静态内部类
	- 可以访问外部类的私有变量，但是反过来不行；外部类必须显式的创建非静态内部类对象，才能使用内部类的成员
	- 如果外部类变量，内部类变量重名，则用外部类类名.this和this区分
- 静态内部类
- 使用内部类
	- 外部类内部使用内部类
	- 外部类之外使用非静态内部类
	- 外部类之外使用静态内部类

### 6.8 Lambda表达式

### 6.9 枚举类
- 关键字enum定义枚举类

### 6.10 对象与垃圾回收


## 第七章 Java基础类库

### 7.1 与用户互动
- main函数中public static void main(String[] args){}
	- 执行java 类名 参数，就可以将参数输入到args中
- Scanner 获取输入
```java
// 读取键盘输入
Scanner sc = new Scanner(System.in);
while(sc.hasNext()) {
	System.out.println(sc.next());
}

// 读取文件内容
Scanner scFile = new Scanner(new File("Test.java"));
while(scFile.hasNextLine) {
	System.out.println(scFile.nextLine());
}
```

### 7.2 系统相关

### 7.3 常用类
- Object类
	- equals() 
		- Object中的equals()是比较两个对象是否同一内存地址，即是否引用同一对象
		- 但是实际是对两个对象进行内容的比较，不是引用的比较，所以需要重写
- String类和StringBuffer
	- String创建之后字符串序列不可变，但是StringBuffer创建后字符串可以改变
	- String的方法
	```java
	// 构造器String()
	String s = new String("我是字符串");

	// char charAt(int index) 获取指定位置的字符
	System.out.println(s.charAt(5));

	// boolean equals(Object anObject) 比较字符串与Object对象的字符串内容是否相等

	// == 判断内容与地址是否相同

	// int indexOf(String str) 找出str子串在该字符串第一次出现的位置

	// String substring(int beginIndex, int endIndex) 返回从beginIndex到endIndex的子字符串

	// static String valueOf(X x) 将基本类型转换成String
	String dbStr = String.valueOf(2.345);

	// String[] arr = s.split(" "); String拆分

	// s.toCharArray()  把String拆成char数组

	// s.toUpperCase()  把String全部大写

	// s.toLowerCase()  把String全部小写
	```
	- null是String没有new,引用没有创建，没有内存;""和new String()是已经new了，只不过内部为空
	- 推荐直接赋值,String s = 'aa'，除非有必要才会创建String对象，String s = new String("aa")
	- 字符串的拼接
		- concat(); append(); "+"
	- StringBuffer的方法
	```java
	StringBuffle sb = new StringBuffle();

	sb.append("java");
	sb.insert("hello ");
	sb.replace(5, 6, ",");
	sb.delete(5, 6);
	sb.reverse();
	sb.setCharAt(i,'1'); 
	sb.setLength(10); //返回10位的sb
	```
- Math类
- Random类
- BigDecimal类
- Calendar类
- Integer类
	- 进制之间的转换
	 	- 十进制转二进制 Integer.ToBinaryString(int i)
	 	- 二进制转十进制 Integer.valueOf(String str,2).toString() 

### 7.5 正则表达式


## 第八章 Java集合

### 8.1 Java集合概述
- 集合主要由Collection和Map两个接口派生而来
	- Collection包括Set，Queue，List三个子接口，已经派生出来的实现类
	- Map接口派生众多实现类

### 8.2 Collection和Iterator接口
- Collection的接口方法
```java
boolean add(Object o);
boolean addAll(Collection c);
void clear();
boolean contains(Object o);
boolean containsAll(Collection c);
boolean isEmpty();
boolean remove(Object o);
boolean removeAll(Collection c); //删除集合c中的所有元素
boolean retainAll(Collection c); //删除集合c中不包含的元素
int size();
Iterator iterator(); //返回Iterator对象
Object[] toArray(); //集合转换成数组，所有集合元素变为对应的数组元素,变成了Object
T[] toArray(T[] a); //集合转换成类型为T的数组 eg:res.ToArray(new String[0])
```
- Iterator的方法
```java
boolean hasNext();
Object next();
void remove(); //删除集合上一次next方法返回的元素

Collection books = new ArrayList();
books.add("Think in Java");
books.add("python");
Iterator it = books.iterator();
while (it.hasNext()) {
	System.out.println((String)it.next()); //next()返回的对象是Object，需要强制类型转换
}
```
### 8.3 Set集合
- 不可重复，没有顺序，基本操作与Collection相同
- HashSet类
	- 是Set接口的典型实现，使用Hash算法存储集合元素
- TreeSet类
	- 是SortedSet接口的实现类，是有序的
	- TreeSet的额外方法
	```java
	Object first() //返回集合第一个元素
	Object last() 
	Object lower(Object e) // 返回小于e的最大元素
	Object higher(Object e) 
	SortedSet subSet(Object fromElement, Object toElement) //返回从fromElement(包含)到toElement(不包含的子序列
	```
	- 如果将一个对象插入到TreeSet中，则该对象的类必须实现Comparable接口
- EnumSet类

### 8.4 List集合
- List接口方法
```java
void add(int index, Object element);
Object get(int index); //返回索引为index的对象
Object remove(int index);
int indexOf(Object e); //返回e第一次出现的索引
Object set(int index, Object e); 将index处的元素替换成e
List subList(int fromIndex, int toIndex);  
sort();
replaceAll();
```
- ListIterator
	- 提供了反向迭代的能力
	```java
	boolean hasPrevious()
	Object Previous()
	```
- ArrayList和Vector实现类

### 8.5 Queue集合
- Queue接口的方法
```java
void add(Object e) //添加到尾部
Object peek()  //返回头部元素，但不删除
Object poll()  //返回头部元素，删除
```
- PriorityQueue实现类
	- 保存队列顺序是按照大小顺序排列，这点已经不是队列了

- Deque接口
	- 是Queue的子接口，双端队列

- ArrayDeque是Deque的实现类，可以当栈和队列使用
	- 当栈使用
	```java
	ArrayDeque stack = new ArrayDeque();
	stack.push(Object e);
	stack.peek();
	stack.pop();
	```
	- 当队列使用
	```java
	ArrayDeque queue = new ArrayDeque();
	queue.offer(Object e);
	queue.peek();
	queue.poll();
	```
- LinkedList实现类
	- LinkedList是基于链表，而ArrayList和ArrayDeque是基于数组
	- LinkedList 可以作为List集合，双端队列，栈使用

### 8.6 Map集合
- Map接口的方法
```java
boolean containsKey(Object key)
boolean containsValue(Object Value)
Object get(Object key)
Set keySet() //返回Map中所有key组成的Set集合
Object put(Object key, Object value)
Object remove(Object key)
Collection values() //返回Map中所有value组成的Collection集合
//遍历
for (Object key : map.keySet()) {
	map.get(key);
}
```
- HashMap实现类
	- 作为Key的对象必须实现hashCode()和equals()方法
	- LinlkedHashMap子类，使用双向链表维护key-value的次序
- SortedMap接口和TreeMap实现类

### 8.7 操作集合的工具类Collections
- 排序操作
```java
void reverse(List list);
void sort(List list); 
void shuffle(list);
// 使用方法
Collections.reverse(list);
```
- 查找,替换操作
```java
int binarySearch(List list, Object key); //使用二分查找搜索list,获得指定对象key在list集合中的索引
int frequency(Collection c, Object o); //返回指定集合中指定对象的出现次数
Object max(Collection c);
// 使用方法
Collections.frequency(list, -5);
```
- 同步控制
	- 保证线程安全
	```java
	List list = Collections.synchronizedList(new ArrayList());
	```

## 第九章 泛型
- 泛型入门
	- 允许程序在创建集合时指定集合元素的类型
	```java
	List<String> list = new ArrayList<>();
	Map<Integer, String> map = new HashMap<>();
	```
- 深入泛型
	- 可以为任何类，接口增加泛型声明

- 泛型方法


## 第十章 异常处理
- 异常处理机制
	- try...catch捕获异常,先捕获小异常，再捕获大异常
	```java
	try{
		// 业务实现代码
	}catch (SubException e1) {
		// 异常处理块1
	}catch (SubException e2) {
		// 异常处理块2
	}finally {
		// 资源回收
	}
	```
	- 访问异常信息
	```java
	getMessage() //返回异常的详细描述信息
	printStactTrace() //返回异常的跟踪栈信息
	```
	- 使用finally回收物理资源
		- 垃圾回收机制回收内存，但是如数据库连接，网络连接，磁盘文件等物理资源必须显示回收
		- finally一定会执行

- Checked异常和Runtime异常体系

- 使用throw抛出异常
	- 与throws有区别，throw是自行抛出的异常
	- throw new Exception()

## 第十五章 输入/输出

### 15.1 File类

- 访问文件名相关的方法
```java
String getName(); //返回File对象所表示的文件名或路径名
String getPath(); //返回路径名
String getAbsulutePath(); //绝对路径名
boolean renameTo(File newName); //重命名
```
- 文件检测相关的方法
```java
boolean exists();
boolean isFile();
boolean isDirectory();
```
- 文件操作相关的方法
```java
boolean createNewFile(); //如果File对象对应的文件不存在，就创建一个File对象指定的新文件
boolean delete();
```
- 目录操作相关的方法
```
boolean mkdir();  //创建File目录
String[] list(); //返回File对象所有子文件名
File[] listFile(); //返回File数组
```

### 15.2 理解Java的IO流
- InputStream/Reader 所有输入流的基类，前者是字节输入流，后者是字符输入流
  OutputStream/Writer 所有输出流的基类，前者是字节输出流，后者是字符输出流

### 字节流和字符流
- InputStream/Reader是抽象基类，不能创建实例
	- InputStream方法，实例是FileInputStream
	```java
	int read() //读取单个字节
	int read(byte[] b) //读取b.length个字节的数据，并存储在b中
	```
	- Reader方法，实例是FileReader
	```java
	int read()
	int read(char[] chuf)

	// example
    FileReader reader = new FileReader("/Users/guoxingyu/Documents/work/java/CrazyJava/Ch04/test/hello.txt");
    char[] chuf = new char[100];  // 每次只取这么多字符
    int hasRead = 0;
    while ((hasRead = reader.read(chuf)) > 0) {
        System.out.println(new String(chuf, 0 , hasRead));
    }
    reader.close()
	```
- OutputStream和Writer
	- Writer方法，实例是FileWriter
	```java
	void write(String str);

	// example
    FileWrite wr = new FileWriter("/Users/guoxingyu/Documents/work/java/CrazyJava/Ch04/test/test.txt");
    wr.write("我是郭大宝");
    wr.close() // 必加，否则写不进去
	```

## 第十六章 多线程

### 16.1 线程概述
- 所有运行的任务对应一个进程，进程是处于运行中的程序。进程是系统进行资源分配和调度的独立单元。线程是进程的执行单元，线程在程序中是独立的，并发的执行流。线程是进程的组成部分，一个进程有多个线程，一个线程必须有一个父进程。线程有自己的堆栈，局部变量，程序计数器，会和其他线程一起共享父进程的所有资源。

### 16.2 线程的创建和启动
- 通过继承Thread类创建并启动多线程步骤
	- 定义Thread类的子类，重写run()方法
	- 创建Thread子类的实例
	- 调用对象的start()方法
- 实现Runnable接口创建线程类
	- 定义Runnable接口的实现类，实现run()方法
	- 创建实例，并以此实例作为Thread的target创建Thread对象，该Thread对象才是真正的线程对象
	- 调用线程对象的start()方法
- 使用Callable和Future创建线程
	- 创建Callable接口的实现类，实现call()方法，该有返回值。再创建对象
	- 使用FutherTask类包装Callable对象，该FutherTask对象封装了Callable对象的call()方法的返回值
	- 使用FutherTask对象作为Thread对象的target创建和启动新进程
	- 调用FutherTask对象的get()方法获得子线程结束后的返回值
- 三种创建线程的对比
	- 后两种方式可以归为一类，编程类只是实现一个接口，还可以继承其他类；多个线程可以共享一个target对象，非常适合多个相同线程处理同一份资源的情况
	- 继承Thread类的方法编写代码简单，但是不能继承其他父类

### 16.3 线程的生命周期
- 线程的周期有新建，就绪，运行，阻塞，死亡五种状态
	- 创建 new创建一个线程，线程处于新建状态，分配内存，初始化成员变量等，不会执行线程执行体
	- 就绪 start后处于就绪状态，但是并没有运行，取决于JVM里线程调度器
	- 运行 获得CPU，执行run中的线程执行体
	- 阻塞 阻塞的程序会在合适时机重新进入就绪状态，等待线程调度器再次调用
	- 死亡 

### 16.4 控制线程
- join() 让进程等待另一个进程完成的方法，某个程序执行流调用其他线程的join()方法，调用线程被阻塞，直到被join的线程执行完为止
- setDaemon(true) 设为后台进程
- sleep() 正在执行的线程暂停一段时间，并进入阻塞状态
- yield() 强制线程从运行状态进入就绪状态
- setPriority(int newPriority) getPriority() 可以更改和查询优先级，数值越大，优先级约高

### 16.5 线程同步
- 三个方法，同步代码块、同步方法、Lock。同步的逻辑是“加锁->修改->释放锁”，释放锁会自动完成
- 使用synchronized将run()方法里的方法体修改成同步代码，同时同步代码快需要指定同步监视对象,这样任何线程修改指定资源前，首先对该资源加锁，修改完后再释放锁。
```
synchronized(xx) {}
```
- 使用synchronized关键字修饰某个方法，如果修饰的是实例方法，则监视的是调用该方法的对象(this)
- 同步锁Lock对象，作用对象是共享资源，可以方便的显示的加锁，解锁。
- 死锁 两个线程相互等待对方释放同步监视器时发生死锁

## 第十八章 类加载机制和反射

### 18.1 类的加载、连接和初始化
- 当程序主动使用某个类时，如果类没有加载到内存中，则系统通过类的加载，连接，初始化三个步骤对类进行初始化
	- 类的加载由类加载器完成，有系统类加载器和自定义加载器，可以加载本地class,jar包中的class，网络class文件等
	- 类的连接，分为验证，准备，解析三个阶段
	- 类的初始化，对类变量进行初始化

### 18.2 类加载器
- 类加载器负责加载所有类，系统为所有载入内存的类生成一个java.lang.Class的实例
- JVM启动时，会有三个类加载器，分别是根类加载器，扩展类加载器，系统类加载器。分别负责Java核心类，JRE拓展目录中JAR包中的类，本地类
- 类的加载机制
	- 全盘负责 该Class所依赖和引用的其他Class也由该加载器负责载入
	- 父类委托 先让父类加载器试图加载该Class，无法加载时才尝试从自己的类路径中加载该类
	- 缓存机制 保证所有加载过的Class都缓存，当需要使用某个类时，类加载器先从缓存区寻找，没有再去加载类到缓冲区
- 过程
	- 1.检查该Class是否载入过，有直接进入第8
	- 2.如果父类加载器不存在(本事是根类加载器或parent是根类加载器)，则跳第4；如果存在跳到3
	- 3.请求父类加载器载入目标类，成功跳入8，否则5
	- 4.请求根类加载器载入目标类，成功跳入8，否则7
	- 5.当前类加载器在ClassLoader相关的类路径中寻找Class文件，找到执行6，否则7
	- 6.从文件中载入Class，成功到8
	- 7.抛出异常
	- 8.返回对应的java.lang.Class对象


## 总结

### Java集合框架

#### 总体框架
![](raw/1.jpg?raw=true)

#### List
- ArrayList
- LinkedList

#### Set
- HashSet
- LinkedHashSet
- TreeSet
	- 是一种排序二叉树，有序

#### Map
- HashMap，基于散列表实现，采用对象的Hashcode可以快速查询
- TreeMap，基于红黑树的数据结构实现



# 《Java程序员面试宝典》

## Java基础知识
- Java程序初始化的顺序
	- 父类静态变量、父类静态代码块、子类静态变量、子类静态代码块、父类非静态变量、父类非静态代码块、父类构造函数、子类非静态变量、子类非静态代码块、子类构造函数
	- 针对一个类来说，静态变量、静态代码块、非静态变量、非静态代码块、构造函数
- Java中的作用域
	- 成员变量与实例化的对象作用范围相同;静态变量被所有实例共享，与类作用范围相同;局部变量在花括号内有效
	- public当前类，同一package，子类，其他package都可用;private只有第一个;protected可在前三个;default可在前两个
- clone方法的作用 
	- Java在处理基本数据类型时，使用的是值传递(传递输入参数的复制)，其他类型都是按引用传递(传递是对象的一个引用)的方式执行;对象除了函数调用是引用传递外，在使用“=”赋值时也是引用传递
	- 针对上述情况，如果需要对已有对象A创造出相同状态的B，且对B修改不影响A，则使用obj类中的clone方法
	- 浅复制和深复制，判断依据是类中是否存在非基本类型(即对象)的数据成员
	- 浅复制：被复制对象的所有变量都与原来对象相同，但是对象的引用仍指向原来的对象；深度复制则把引用其他对象的变量指向被复制的新对象
- 反射机制 可以在运行时动态的创建类的对象
- 组合 在新类里面创建原有类的对象，重复利用类的功能
- 多态两种表现形式，编译时的多态通过方法的重载实现；运行时的多态通过方法的重写实现
- 抽象类和接口的异同
- this用于区别对象的成员变量和方法的形参;super访问父类的方法和成员变量
- final声明的变量，方法和类，表示变量不可更该，方法不可覆盖，类不可被继承
- static
	- static成员变量(静态变量)，属于类，所有对象共享静态变量，即对象可以调用，可以当做全局变量
	- static成员方法，只能类调用，对象不可调用；stack方法只能访问静态变量和方法；重要用途是单例模式
	- static代码块，初始化静态变量
	- static内部类
	- static修饰的方法不可覆盖
- 值传递和引用传递的区别
	- 值传递，形参是用实参的值初始化一个临时存储单元，对形参的改变不影响实参;原始数据类型都是值传递
	- 引用传递，传递的是对象，形参和实参的对象指向同一存储单元，对形参的修改会影响实参;包装类型都是引用传递
- 不可变类 创建类的实例后，不允许修改它的值，所有基本类型的包装类，String都是不可变类
- 字符串创建与存储的机制
	- JVM存在一个字符串池，保存String对象，可以共享使用
	- 如果是String s1 = new String("abc"),则会创建新对象，也会指向字符串池中的对象
	- 如果是String s2 = "abc"，指向常量池中的String对象，由于String是不可变类，所以String对象可以被共享不会导致程序混乱
	- ![](raw/2.jpeg?raw=true)
- ”==“，equals，hashCode
	- "=="用来比较两个变量的值是否相等，基本数据类型直接对比，引用类型是对比是否指向同一个对象，但是无法对比两个对象的内容是否相同
	- equals，如果没有被重写，效果跟”==“一样是比较是否为同一对象，如果被重写可以比较两个对象的值是否相同，比如String中就是比较值是否相同
	- hashCode，判断是否指向同一个对象
	- 两个对象相等，则hashcode值相同，但是两个对象不同，也可能有相同的hashcode值；可以用Hashcode不等判断两个对象不等
- String,StringBuffer,StringBuilder,StringTokenizer
	- String是不变类，StringBuffer是可变类，如果字符串被修改，使用sb;此外对String的修改，本质是创建sb,调用sb.append,然后调用sb.toString()返回结果
	- StringBuilder也可以被修改字符串，但是线程不安全;但是效率最高，sb次之，String最慢
	- StringTokenizer是分割字符串工具，类似于split
- length是数组，length()是字符串，size()是针对泛型集合
- Java提供两种异常类
	- Error表示运行中出现JVM层次的严重错误，不可恢复，会导致程序终止执行，比如OutOfMemoryError和ThreadDeath
	- Exception表示可恢复的错误，包含两种类型
		- 检查异常，发生在编译阶段，编译器会强制程序捕获此类型异常，可以将此异常放在try中，处理放在catch中;比如IO异常，SQL异常
		- 运行异常，JVM处理，系统会向上层抛出异常，直到遇到处理代码为止；如果没有处理块，就会抛到最上层，会导致主程序终止
- File类，对文件或目录进行管理和操作
	- createNewFile delete isFile isDirectory listFiles mkdir exists
- Java常见两种类型的流，字节流(8bit为单位，包含inputStream和outputStream抽象类)和字符流(16bit,两个抽象类Reader和Writer)
- JVM用来将java编译生成的字节码转换成机器可以识别的代码运行，它有自己的处理器，堆栈，寄存器等，还有相应的指令系统
	- 类加载器讲.class文件按照需求和一定规则加载在内存上，并组织成一个完整的Java应用程序，由ClassLoader和它的子类完成
	- 类加载有隐式加载(new的方式创建对象)和显示加载(调用class.forName)
	- 类分为三种，系统类，扩展类，自定义类，分别由三种不同的加载器加载
- GC,垃圾回收，主要任务是分配内存，确保被引用的对象的内存不被错误回收，回收不再被引用的对象的内存
	- 如果没有任何变量去引用某个对象，对象不可访问，可以判断为垃圾信息
	- 回收的算法有，追踪回收法，压缩回收法，复制回收法，按代回收法
	- 开发人员不能实时调用垃圾回收器收集对象，但是可以调用Ststem.gc()去“通知”垃圾回收器运行，但是会停止所有响应，对程序正常执行有极大威胁
- 内存泄漏指不在被程序使用的对象或变量还在内存中占有存储空间
	- 回收标准两个：给对象赋予了空值null,以后再也没有被使用;给对象赋予了新值，重新分配了内存
	- 内存泄漏有两种：堆中申请的空间没有被释放，二是对象已不在被使用，但是内存仍保留
	- 垃圾回收机制只能解决第一种问题，第二种不能保证不再使用的对象会被释放掉
	- 常见的内存泄漏原因
		- 静态集合类，别不HashMap和Vector，这些容器为静态的，生命周期与程序一样，容器中的对象无法释放
		- 各种连接，比如数据库，IO连接，网络联结等
		- 监听器
		- 变量不合理的作用域
- Java中的堆和栈，都是内存中存放数据的地方
	- 栈存放基本数据类型和对象的引用变量，变量出了作用域会被自动回收，不是垃圾回收机制回收的
	- 堆或者常量池(字符串常量池和基本数据类型常量)存放程序运行中的对象，需要new创建
	- JVM是基于堆栈的虚拟机，每个Java程序运行在一个单独的JVM实例上，每个实例唯一对应一个堆，但一个java程序内的多个线程都运行在同一个JVM实例上，所以这些线程共享堆内存
- ArrayList,Vector,LinkedList的区别
	- ArrayList,Vector都是基于数组实现的;由于数据存储是连续的，索引数据比较快，但是随机插入元素执行很慢;Vector每次扩充2倍，ArrayList是1.5倍;两者最大区别在于Vector线程安全
	- LinkedList采用双向列表实现，随意访问效率低，但是插入元素效率高，线程不安全
	- 如果对数据操作主要是索引和在集合末端增改删数据，选前者；如果需要随机访问就选后者；如果多线程操作，选择Vector
- HashMap,HashTable,TreeMap
	- HashMap,HashTable都是基于HashCode值存储数据，后者是线程安全的;前者可以允许一条(null)空键值，后者不可以
	- TreeMap根据键排序
- sleep()和wait()区别
	- sleep不会释放锁，只是让线程暂停一段时间，时间到自动恢复；wait会释放锁，
- JDK和JRE区别
	- JRE,java运行环境，它包括Java虚拟机、Java核心类库和支持文件。
	- JDK，Java开发工具包，包含了JRE，编译器和其他的工具，可以让开发者开发、编译、执行Java应用程序。
- 什么是自动拆装箱
	- 自动装箱就是Java编译器在基本数据类型和对应的对象包装类型间的转化，即int转化为Integer,自动拆箱是Integer调用其方法将其转化为int的过程
- 接口和抽象类的区别
	- 类可以实现很多个接口，但是只能继承一个抽象类
	- 接口中所有的方法隐含的都是抽象的。而抽象类则可以同时包含抽象和非抽象的方法
- 值传递和引用传递
	- 值传递是对基本型变量，String而言的,传递的是该变量的一个副本,改变副本不影响原变量，
	- 引用传递一般是对于对象型变量而言的,传递的是该对象地址的一个副本，而不是对象本身，但可以修改原变量
	- String, Integer, Double等immutable的类型特殊处理，可以理解为传值，最后的操作不会修改实参对象
- Comparable和Comparator接口是干什么的？列出它们的区别
	- Java提供了只包含一个compareTo()方法的Comparable接口。这个方法可以个给两个对象排序。具体来说，它返回负数，0，正数来表明已经存在的对象小于，等于，大于输入对象。
	- Java提供了包含compare()和equals()两个方法的Comparator接口。compare()方法用来给两个输入参数排序，返回负数，0，正数表明第一个参数是小于，等于，大于第二个参数。equals()方法需要一个对象作为参数，它用来决定输入参数是否和comparator相等。只有当输入参数也是一个comparator并且输入参数和当前comparator的排序结果是相同的时候，这个方法才返回true。
- 



## Java web
- 页面请求的工作流程
	- 用户通过浏览器输入网址请求资源
	- 浏览器通过HTTP协议，将请求网址，参数，通过get/post请求方法组装成指定格式发给服务器端
	- 服务器收到客户端请求，查询资源
	- 服务器将响应消息组装成特定消息格式返回给客户端，包括状态编码，消息内容等
	- 浏览器解析HTML
- GET是客户端向服务器请求资源；POST除了请求资源外，还能向服务器上传资源
- ajax,不刷新页面的情况下，通过与服务器少量数据交互提高页面交互性，减少响应时间，改善用户体验。
- cookie和session区别
	- cookie是服务器保存在浏览器的小文件，包含用户信息；session是服务器端保存客户信息的结构
	- cookie保存在客户端，session在服务器端
	- cookie安全性差，session安全
	- cookie性能好，session保存在服务器，当访问量增多，降低服务器性能
	- cookie保存数据不超过4K，一般浏览器最多保存20个，session不限制

## 数据库
- delete和truncate不同
	- delete,被删除的数据占用的存储空间还在，可以恢复；truncate不能恢复
	- truncate执行速度快
- 事务
	- 是数据库中的单独执行单元，当数据库完成更改数据成功时，事务中的更改数据会提交，不再更改；否则事物就取消或者回滚，更改无效
	- 事务四个属性，原子性，一致性，隔离性，持久性
		- 原子性，要求事务要么全执行，要么不执行，原子性要求事务必须完整执行
		- 一致性
		- 隔离性，多个事务并发时，不能同时并发
		- 持久性，事务完成后，数据库保证对数据的修改是永久的

- 索引
	- 索引用于快速找出在某个列中有一特定值的行
	- 索引是在存储引擎中实现的，也就是说不同的存储引擎，会使用不同的索引
		- MyISAM和InnoDB存储引擎：只支持BTREE索引， 也就是说默认使用BTREE，不能够更换
		- MEMORY/HEAP存储引擎：支持HASH和BTREE索引
	- 索引我们分为四类来讲 单列索引(普通索引，唯一索引，主键索引)、组合索引、全文索引、空间索引
		- 单列索引 一个索引只包含单个列，但一个表中可以有多个单列索引
			- 普通索引 MySQL中基本索引类型，没有什么限制，允许在定义索引的列中插入重复值和空值，纯粹为了查询数据更快一点。
			- 唯一索引 索引列中的值必须是唯一的，但是允许为空值
			- 主键索引 是一种特殊的唯一索引，不允许有空值
		- 组合索引 在表中的多个字段组合上创建的索引，只有在查询条件中使用了这些字段的左边字段时，索引才会被使用，使用组合索引时遵循最左前缀集合
		- 全文索引
		- 空间索引
	- 索引操作
		- 创建索引
		```
		CREATE TABLE 表名[字段名 数据类型]  [UNIQUE|FULLTEXT|SPATIAL|...] [INDEX|KEY] [索引名字] (字段名[length]) 　　[ASC|DESC]

		eg:
　　　　  				CREATE TABLE book　　　　　　　　　　　　　　　　　　　　

　　　　　　　　　　　　　　　　(　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　

　　　　　　　　　　　　　　　　　　bookid INT NOT NULL,　　　　　　　　　　　　　　　　　　

　　　　　　　　　　　　　　　　　　bookname VARCHAR(255) NOT NULL,　　　　　　　　　　 

　　　　　　　　　　　　　　　　　　authors VARCHAR(255) NOT NULL,　　　　　　　　　　　  

　　　　　　　　　　　　　　　　　　info VARCHAR(255) NULL,　　　　　　　　　　　　　　　

　　　　　　　　　　　　　　　　　　comment VARCHAR(255) NULL,　　　　　　　　　　　　　

　　　　　　　　　　　　　　　　　　year_publication YEAR NOT NULL,　　　　　　　　　　　　

　　　　　　　　　　　　　　　　　　INDEX Year_publication(year_publication)　　　　　　　　　　　　　　　　　

　　　　　　　　　　　　　　　　);　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
		```
		- 在已经存在的表上创建索引








## 设计模式
- 工厂模式
- 单例模式
	- 每个类只有一个实例
- 观察者模式
- 适配器模式

	
# 《JVM知识点总览》
- 参考地址 http://www.cnblogs.com/ityouknow/p/6482464.html
- 总体分为4大部分
	- 类的加载机制
	- jvm内存结构
	- GC算法 垃圾回收
	- GC分析 命令调优

## 类的加载机制

- 什么是类的加载
	- 将.class文件中的二进制数据读取到内存中，将其放在运行时方法区内，然后在堆区创建一个java.lang.Class对象，用来封装类在方法区内的数据结构。类的加载的最终产品是位于堆区中的Class对象，Class对象封装了类在方法区内的数据结构，并且向Java程序员提供了访问方法区内的数据结构的接口。

- 类初始化的情况
	- 1.使用 new 关键字实例化对象 2.读取或设置一个类的静态字段 3.调用一个类的静 态方法。
	- 使用 java.lang.reflect 包的方法对类进行反射调用时，若类没有进行初始化，则需要触发其初始化
	- 当初始化一个类时，若发现其父类还没有进行初始化，则要先触发其父类的初始化。
	- 当虚拟机启动时，用户需要制定一个要执行的主类(有main方法的那个类)，虚拟机会先初始化这个类。

- 类的生命周期
	- 加载，连接(验证，准备，解析)，初始化，使用，卸载。其中前三个是类的加载过程。
	- 加载，查找并加载类的二进制数据，在Java堆中也创建一个java.lang.Class类的对象，是方法区的数据结构的访问入口
	- 连接，连接又包含三块内容：验证、准备、初始化。1）验证，文件格式、元数据、字节码、符号引用验证；2）准备，为类的静态变量分配内存，并将其初始化为默认值，分配的内存在方法区；3）解析，把类中的常量池中的符号引用转换为直接引用。符号引用就是一组符号来描述目标，可以是任何字面量。直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄。
	- 初始化，为类的静态变量赋予正确的初始值
	- 使用，new出对象程序中使用
	- 卸载，执行垃圾回收
- 类加载器
	- Bootstrap启动类加载器，加载存放在JDK\jre\lib，负责加载JVM基础核心类库
	- Extension拓展类加载器，加载存放在JDK\jre\lib\ext，负责加载拓展的类
	- System应用类加载器，它从环境变量classpath或者系统属性java.class.path所指定的目录中记载类
	- 用户自定义类加载器

- 类的加载机制
	- 全盘负责，当一个类加载器负责加载某个Class时，该Class所依赖的和引用的其他Class也将由该类加载器负责载入，除非显示使用另其其它一个类加载器来载入
	- 父类委托，先让父类加载器试图加载该类，只有在父类加载器无法加载该类时才尝试从自己的类路径中加载该类
	- 缓存机制，缓存机制将会保证所有加载过的Class都会被缓存，当程序中需要使用某个Class时，类加载器先从缓存区寻找该Class，只有缓存区不存在，系统才会读取该类对应的二进制数据，并将其转换成Class对象，存入缓存区。

- 双亲委派机制
	- 如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，而是把请求委托给父加载器去完成，依次向上，因此，所有的类加载请求最终都应该被传递到顶层的启动类加载器中，只有当父加载器在它的搜索范围中没有找到所需的类时，即无法完成该加载，子加载器才会尝试自己去加载该类
	- 这种方式保证了Oject类在各个加载器加载环境中都是同一个类.比如Java中的Object类，它存放在rt.jar之中,无论哪一个类加载器要加载这个类，最终都是委派给处于模型最顶端的启动类加载器进行加载，因此Object在各种类加载环境中都是同一个类。如果不采用双亲委派模型，那么由各个类加载器自己取加载的话，那么系统中会存在多种不同的Object类

- 最近实践
	- Student s= new Student(),在内存中做了那些事情
		- 加载Student.class 文件进内存，就是类加载那一套
		- 在栈内存为s开辟空间
        - 在堆内存为学生对象开辟空间
        - 学生对象的成员变量进行显示初始化
        - 通过构造方法对学生对象变量赋值
        - 学生对象初始完毕，把对象地址赋值给s变量

## JVM内存结构

- jvm内存结构
	- JVM内存结构主要有三大块：堆内存、方法区和栈
	- Java堆内存
		- 内存中最大的一块，被所有线程共享，存放对象实例。又分为年轻代和老年代，年轻代又分为Eden空间，survivor1,survivor2空间，比例8:1:1，年轻代经历了N次（可配置）垃圾回收后仍然存活的对象，就会被复制到年老代中。因此，可以认为年老代中存放的都是一些生命周期较长的对象。
	- 方法区
		- 被所有线程共享，它用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据
		- 运行常量池，用于存放编译器生成的各种字面量和符号引用，是方法区的一部分。无法申请内存时抛出OutOfMemoryError
	- 程序计时器
		- 线程私有内存，是当前线程所执行的字节码的行号指示器。
	- jvm栈
		- 线程私有，它的生命周期与线程相同。虚拟机栈描述的是Java方法执行的内存模型：每个方法被执行的时候都会同时创建一个栈帧（Stack Frame）用于存储局部变量表、操作栈、动态链接、方法出口等信息。每一个方法被调用直至执行完成的过程，就对应着一个栈帧在虚拟机栈中从入栈到出栈的过程。如果线程请求的栈深度大于虚拟机所允许的深度就报StackOverflowError,如果虚拟机栈可以动态扩展，当拓展时无法申请到足够的内存会抛出OutOfMemoryError. 
		- 栈中存放的是一个一个的栈帧，每一个栈帧对应一个被调用的方法。栈帧包括局部变量表,操作数栈，方法的返回地址，指向当前方法所属的类的运行时常量池的引用，附加信息.
	- 本地方法栈
		- 本地方法栈（Native Method Stacks）与虚拟机栈所发挥的作用是非常相似的，其区别不过是虚拟机栈为虚拟机执行Java方法（也就是字节码）服务，而本地方法栈则是为虚拟机使用到的Native方法服务

- 对象分配规则
	- 优先分配在eden区，当没有空间，报一次Minor GC(发生在新生代的垃圾收集动作，非常频繁，一般回收速度也比较快，full gc:发生在老年代的 gc)，
	- 大对象(大量连续内存)直接进入老年代
	- 长期存活对象进入老年代，虚拟机为每个对象定义了一个年龄计数器，如果对象经过了1次Minor GC那么对象会进入Survivor区，之后每经过一次Minor GC那么对象的年龄加1，知道达到阀值对象进入老年区。
	- 若在survivor空间中相同年龄all对象大小的总和>survivor空间的一半，则年龄>=该年龄的对象直接进入老年代，无须等到 MaxTeuringThreshold(默认为 15)中的要求
	- 空间分配担保
		- 在发生 minor gc 前，虚拟机会检测老年代最大可用的连续空间是否>新生代 all 对象总空 间，若这个条件成立，那么 minor gc 可以确保是安全的。若不成立，则虚拟机会查看 HandlePromotionFailure 设置值是否允许担保失败。若允许，那么会继续检测老年代最大可 用的连续空间是否>历次晋升到老年代对象的平均大小。若大于，则将尝试进行一次 minor gc，尽管这次 minor gc 是有风险的。若小于或 HandlePromotionFailure 设置不允许冒险，则 这时要改为进行一次 full gc。

## GC算法 垃圾回收
- 对象存活判断 没有引用就是垃圾对象
	- 引用计数：每个对象有一个引用计数属性，新增一个引用时计数加1，引用释放时计数减1，计数为0时可以回收。
	- 根搜索分析：从GC Roots开始向下搜索，搜索所走过的路径称为引用链。当一个对象到GCRoots没有任何引用链相连时，则证明此对象是不可用的，不可达对象。
	- GC Roots对象一般是
		- 虚拟机栈(栈帧中的本地变量表)中引用的对象
		- 方法区中类静态属性引用的对象
		- 方法区常量引用的对象等
		- 本地方法栈中JNI引用的对象
- GC算法
	- 标记-清除算法
		- 首先标记出所有需要回收的对象，在标记完成后统一回收掉所有被标记的对象。一般是老生代
		- 缺点两个，标记和清除效率低；空间问题，标记清除之后会产生大量不连续的内存碎片，空间碎片太多可能会导致，需要分配较大对象时无法找到足够的连续内存而不得不提前触发另一次垃圾收集动作
	- 复制算法
		- 它将可用内存按容量划分为大小相等的两块，每次只使用其中的一块。当这一块的内存用完了，就将还存活着的对象复制到另外一块上面，然后再把已使用过的内存空间一次清理掉，一般是新生代
		- 优点是每次只需对其中一块内存回收，不考虑内存碎片的问题，缺点是内存缩小为原来一半，持续复制长生存期的对象导致效率降低
	- 标记-压缩算法
		- 标记过程仍然与“标记-清除”算法一样，但后续步骤不是直接对可回收对象进行清理，而是让所有存活的对象都向一端移动，然后直接清理掉端边界以外的内存，一般是老生代
	- 分代收集算法
		- 把Java堆分为新生代和老年代，这样就可以根据各个年代的特点采用最适当的收集算法。在新生代中，每次垃圾收集时都发现有大批对象死去，只有少量存活，那就选用复制算法，只需要付出少量存活对象的复制成本就可以完成收集。而老年代中因为对象存活率高、没有额外空间对它进行分配担保，就必须使用“标记-清理”或“标记-整理”算法来进行回收。
- 垃圾收集器（前三个新生代，后三个老生代）
	- serial，单线程收集器，只会使用一个CPU或一条收集器线程去完成垃圾回收工作，更重要的是在进行垃圾回收时，必须暂停其他所有的工作线程，简单高效，对于运行在client模式下的虚拟机来说是个很好的选择，用于新生代,使用的是复制算法
	- ParNew: 是Serial收集器的多线程版本，垃圾回收时采用多线程方式进行回收。默认情况下使用的线程数是cpu数量。除了serial收集器，目前只有它能和CMS收集器配合工作，是许多运行在server模式下的虚拟机首选的新生代收集器，使用新生代，复制算法
	- Parallel scaverge:使用复制算法收集器，也是一个并行的多线程收集器，它关心吞吐量，适合在后台运算，没有太多的交互，复制算法
	- Serial Old: serial 的老年代版本，单线程收集器，使用标记整理算法。
	- parallel old:parallel scaverge 老年代的版本，使用标记整理算法。主要与Parallel Scavenge配合使用
	- CMS收集器：是以获得最短回收停顿时间为目标的收集器，使用标记清除算法。

## 集合框架
- Collect包括List,Set,Map
	- List是有序的，可重复的集合，包括Vector，ArrayList，LinkedList三个常用类。前两者底层基于数组结果，后者使用链表；第一线程安全，后两者线程不安全；后两者中，前者随机查询快，增删复杂，后者反之
		- Stack是Vector提供的一个子类，用于模拟"栈"这种数据结构，Queue用于模拟"队列"这种数据结构(
	- Set不可重复，无序，包括HashSet,LinkedHashSet,TreeSet三个常用类。Set 最多有一个 null 元素
		- HashSet是由一个hash表来实现的，线程不安全，因此，它的元素是无序的。依赖的是元素的hashCode方法和euqals方法实现唯一性
		- LinkedHashSet，具有HashSet的查询速度，且内部采用双向链表维护元素的顺序，
		- TreeSet，线程不安全，是由一个树形的结构来实现的，它里面的元素是有序的。因此，add()，remove()，contains()方法的时间复杂度是O(logn)。通过compareTo或者compare方法中的来保证元素的唯一性。元素是以二叉树的形式存放的。意味着TreeSet中的元素要实现Comparable接口。或者有一个自定义的比较器。
	- Queue 队列，两个实现类LinkedList 和PriorityQueue
	- Map，包括hashMap,HashTable,LinkedHashMap,TreeMap
		- HashMap允许键和值是null，线程不安全，HashMap的迭代器(Iterator)是fail-fast迭代器，而Hashtable的enumerator(列举)迭代器不是fail-fast的
		- Hashtable不允许键或者值是null；HashTable是线程安全的
		- LinkedHashMap: 可以保证HashMap集合有序
		- TreeMap：可以用来对Map集合中的键进行排序.底层是采用红黑树

- iterator迭代器
	- 确保遍历可靠的原则是：只在一个线程中使用这个集合，或者在多线程中对遍历代码进行同步。只能向前遍历
	- List还提供一个listIterator()方法，返回一个ListIterator接口，和标准的Iterator接口相比，ListIterator多了一些add()之类的方法，允许添加，删除，设定元素，还能向前或向后遍历。
	- 迭代器fail-fast属性,每次我们尝试获取下一个元素的时候，Iterator fail-fast属性检查当前集合结构里的任何改动。如果发现任何改动，它抛出ConcurrentModificationException

- Collection和Collections的区别
	- Collection是集合类的上级接口，子接口主要有Set 和List、Map。 
	- Collections是针对集合类的一个帮助类，提供了操作集合的工具方法：一系列静态方法实现对各种集合的搜索、排序、线程安全化等操作。

- Array和ArrayList区别
	- Array可以容纳基本类型和对象，而ArrayList只能容纳对象；
	- Array是指定大小的，而ArrayList大小是固定的； 
	- Array没有提供ArrayList那么多功能，比如addAll、removeAll和iterator等。

- ArrayList 和 LinkedList
	- ArrayList的底层是数组。它可以以O(1)时间复杂度对元素进行随机访问。与此对应，LinkedList是列表的形式存储它的数据，查找某个元素的时间复杂度是O(n)。
	- 相对于ArrayList，LinkedList的插入，添加，删除操作速度更快
	- LinkedList比ArrayList更占内存，因为LinkedList为每一个节点存储了两个引用，一个指向前一个元素，一个指向下一个元素。

- HashMap实现原理
	- HashMap的主干是一个Entry数组。Entry是HashMap的基本组成单元，每一个Entry包含一个key,value,next指针,hash值
	- HashMap由数组+链表组成的，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的
	- 如果定位到的数组位置不含链表（当前entry的next指向null）,那么对于查找，添加等操作很快，仅需一次寻址即可；如果定位到的数组包含链表，对于添加操作，其时间复杂度依然为O(1)，因为最新的Entry会插入链表头部，急需要简单改变引用链即可，而对于查找操作来讲，此时就需要遍历链表，然后通过key对象的equals方法逐一比对查找。
	- 最早存储位置的判断 key->hashcode()->hash%[].length->存储下标
	- 如果类重写equals的方法的时候，必须注意重写hashCode方法
	- 其它关于HashMap比较重要的问题是容量、加载因子和阀值调整,HashMap默认的初始容量是16，加载因子是0.75。阀值是为加载系数乘以容量，超过阈值后，会扩展为原来的两倍，并且rehash
	- HashMap可以通过下面的语句进行同步：Map m = Collections.synchronizeMap(hashMap);

- ConcurrentHashMap实现原理
	- Hashtabl在竞争激烈的环境下表现效率低下的原因是一把锁锁住整张表，导致所有线程同时竞争一个锁
	- 一个ConcurrentHashMap由segment数组组成,一个Segment里包含一个HashEntry数组，每个Segment守护者一个HashEntry数组里的元素,当对HashEntry数组的数据进行修改时，必须首先获得它对应的Segment锁
	- 相当于把数组分成多个多个锁，每个锁只锁部分数据，这样其他线程可以访问没有被锁的数据，提高并发

- Hash冲突的解决方法
	- 开放定址法,发生冲突时，有一个增量，然后再次Hash，直到不冲突为止，通用的形式 Hi = (H(key) + di) % m  m是表长，di为增量序列，根据增量序列的不同，又有三种方式
		- 线性探测再散列 di = 1,2,3,...m-1
		- 二次探测再散列 1的平方，1的平方的相反数...
		- 伪随机探测再散列
	- 链地址法
	- 再哈希 构造多个不同的Hash函数，这个不行换下一个
	- 建立公共溢出区 

- Comparable和Comparator接口
	- Comparable & Comparator 都是用来实现集合中元素的比较、排序的，只是 Comparable 是在集合内部定义的方法实现的排序，Comparator 是在集合外部实现的排序
	- Comparator位于包java.util下，而Comparable位于包java.lang下
	- Comparator定义了俩个方法，分别是int compare(T o1,T o2)和boolean equals(Object obj)；Comparable接口只提供了int compareTo(T o)方法.
	- Comparable 是在集合内部定义的方法实现的排序,Comparator 是在集合外部实现的排序.Comparable 是一个对象本身就已经支持自比较所需要实现的接口,自定义的类要在加入list容器中后能够排序，可以实现Comparable接口.在用Collections类的sort方法排序时，如果不指定Comparator，那么就以自然顺序排序.而 Comparator 是一个专用的比较器，当这个对象不支持自比较或者自比较函数不能满足你的要求时，你可以写一个比较器来完成两个对象之间大小的比较。用 Comparator 是策略模式（strategy design pattern），就是不改变对象自身，而用一个策略对象（strategy object）来改变它的行为。


## 多线程
- 线程和进程区别
	- 进程是执行着的应用程序，而线程是进程内部的一个执行序列。一个进程可以有多个线程。线程又叫做轻量级进程
	- 线程的划分小于进程，线程隶属于某个进程
	- 进程是程序的一种动态形式，是CPU、内存等资源占用的基本单位，而线程是不能占有这些资源的。 
	- 进程之间相互独立，通信比较困难，而线程之间共享一块内存区域，通信比较方便
	- 进程在执行的过程中，包含比较固定的入口，执行顺序，出口，而线程的这些过程会被应用程序所控制
	- 进程是资源分配的最小单位，线程是cpu调度的最小单位

- 创建多线程的方式
	- 继承Thread类，重写run()方法
	- 实现runnable接口,重写run()方法，返回值是void
	- 实现callable接口，重写run()方法，返回值是一个泛型
	- 使用Executor框架来创建线程池

- 线程池
	- corepoolsize:核心池的大小，默认情况下，在创建了线程池之后，线程池中线程数为 0，
当有任务来之后，就会创建一个线程去执行任务，当线程池中线程数达到 corepoolsize 后， 就把任务放在任务缓存队列中
	- 当有任务提交到线程池之后的一些操作:
		- 若当前线程池中线程数<corepoolsize，则每来一个任务就创建一个线程去执行
		- 若当前线程池中线程数>=corepoolsize，会尝试将任务添加到任务缓存队列中去，若添
加成功，则任务会等待空闲线程将其取出执行，若添加失败，则尝试创建线程去执行这个任 务。
		- 若当前线程池中线程数>= Maximumpoolsize，则采取拒绝策略(有4种，1)abortpolicy 丢弃任务，抛出RejectedExecutionException 2)discardpolicy拒绝执行，不抛异常 3) discardoldestpolicy丢弃任务缓存队列中最老的任务，并且尝试重新提交新的任务 4) callerrunspolicy 有反馈机制，使任务提交的速度变慢)。

- 线程的几种可用状态
	- 新建new,新创建了一个线程对象。
	- 可运行runnable,线程对象创建后，其他线程调用了该对象的strat方法。该状态的线程位于可运行线程池中，等待被线程调度选中，获取cpu的使用权 。
	- 运行running,可运行状态的线程获得了cpu时间片，执行程序代码。
	- 阻塞block，阻塞状态是指线程因为某种原因放弃了cpu使用权，暂时停止运行
		- 等待阻塞 运行的线程执行o.wait()方法， JVM会把该线程放入等待队列中，wait会释放持有的锁，直到其他线程调用此对象的 notify()方法或notifyAll()唤醒方法
		- 同步阻塞 运行的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线程放入锁池(lock pool)中。
		- 其他阻塞 运行的线程执行Thread.sleep()或t.join()方法，或者发出了I/O 请求时， JVM会把该线程置为阻塞状态，当sleep ()状态超时、 join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入可运行(runnable)状态。（注意,sleep是不会释放持有的锁）
	- 死亡dead，线程run()，main()方法执行结束，或者因异常退出了run()方法，则该线程结束生命周期。
	![](raw/thread.jpg?raw=true)

- 线程同步方法
	- 使用sychronized关键字
	- lock
	- reentrantLock

- 锁的等级：方法锁、对象锁、类锁

- 同步方法和同步代码块的区别是什么
	- 同步方法默认用this或者当前类class对象作为锁,同步代码块可以选择以什么来加锁，比同步方法要更细颗粒度，我们可以选择只同步会发生同步问题的部分代码而不是整个方法；
	- 同步方法使用关键字 synchronized修饰方法，而同步代码块主要是修饰需要进行同步的代码，用synchronized（object）{代码内容}进行修饰；

- 什么是死锁
	- 所谓死锁是线程A和线程B相互等待对方持有的锁导致程序无限死循环下去。
	- 死锁产生的4个必要条件：互斥条件，请求和保持条件，不可抢占条件，循环等待条件
	- 解决方法，打破四个必要条件之一，比如进程开始前，必须申请到所有资源；进程在新资源请求不能被满足时，它必须释放已经保持的所有资源，允许一个进程只获得初期的资源就开始运行，然后再把运行完的资源释放出来。然后再请求新的资源等；

- Java 内存模型和线程
	- 每个线程都有一个工作内存，线程只可以修改自己工作内存中的数据，然后再同步回主内存，主内存由多个内存共享。
	- 下面 8 个操作都是原子的，不可再分的:
		- lock:作用于主内存的变量，它把一个变量标识为一个线程独占的状态。 
		- unlock:作用于主内存的变量，他把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定。 
		- read:作用于主内存变量，他把一个变量的值从主内存传输到线程的工作内存，以便随后的 load 操作使用。
		- load:作用于工作内存的变量，他把 read 操作从主内存中得到的变量值放入工作内存的变量副本中。 
		- use:作用于工作内存的变量，他把工作内存中一个变量的值传递给执行引擎，每当虚拟机遇到一个需要使用到变量的值得字节码指令时将会执行这个操作。
		- assign:作用于工作内存的变量，他把一个从执行引擎接收到的值付给工作内存的变量，每当虚拟机遇到一个给变量赋值的字节码指令时执行这个操作。 
		- store:作用于工作内存的变量，他把工作内存中一个变量的值传送到主内存中，以便随后 write 使用。
		- write:作用于主内存的变量，他把 store 操作从工作内存中得到的变量的值放入主内存的变量中。

- volatile关键字和synchronized的比较
	- volatile轻量级，只能修饰变量。synchronized重量级，可以修饰方法，变量
	- volatile主要用在多个线程感知实例变量被更改了场合，从而使得各个线程获得最新的值。它强制线程每次从堆内存中获得变量，而不是从线程的栈内存中读取变量，从而保证了数据的可见性。
	- volatile仅能实现变量的修改可见性,而synchronized则可以保证变量的修改可见性和原子性
	- volatile不会造成线程的阻塞,而synchronized可能会造成线程的阻塞.

- synchronized(S) VS lock(L)
	- L是接口，S是关键字
	- S在发生异常时，会自动释放线程占有的锁，不会发生死锁。L 在发生异常时，若没有主动通过 unlock()释放锁，则很有可能造成死锁。所以用 lock 时要在 finally 中释放锁
	- L可以当等待锁的线程响应中断，而S不行，使用S时，等待的线程将会一直等下去，不能响应中断。
	- 通过L可以知道是否成功获得锁，S不可以。
	- L可以提高多个线程进行读写操作的效率。

- 线程安全，你的代码在多线程下执行和在单线程下执行永远都能获得一样的结果，那么你的代码就是线程安全的
	- 不可变 像String、Integer、Long这些，都是final类型的类，任何一个线程都改变不了它们的值，要改变除非新创建一个，因此这些不可变对象不需要任何同步手段就可以直接在多线程环境下使用
	- 绝对线程安全 
	- 相对线程安全  相对线程安全也就是我们通常意义上所说的线程安全，像Vector这种，add、remove方法都是原子操作，不会被打断
	- 线程不安全 ArrayList、LinkedList、HashMap等都是线程非安全的类

- wait和sleep的区别
	- sleep方法和wait方法都可以用来放弃CPU一定的时间，不同点在于如果线程持有某个对象的监视器，sleep方法不会放弃这个对象的监视器，wait方法会放弃这个对象的监视器

## 数据库
- 存储引擎区别
	- InnoDB，支持事务，行级别锁定，不支持全文索引，用于事务处理应用程序，具有众多特性，包括ACID事务支持。如果应用中需要执行大量的INSERT或UPDATE操作，则应该使用InnoDB,这样可以提高多用户并发操作的性能。
	- MyISAM，不支持事务，表级锁定，支持全文索引，MyISAM 管理非事务表，它提供高速存储和检索，以及全文搜索能力。如果应用中需要执行大量的SELECT查询，那么MyISAM是更好的选择。
	- memory 出发点是速度 采用的逻辑存储介质是内存
	- merge 一组 myisam 表的组合

- 数据库锁机制
	- 数据库锁定机制简单来说就是数据库为了保证数据的一致性而使各种共享资源在被并发访问，访问变得有序所设计的一种规则。MySQL各存储引擎使用了三种类型（级别）的锁定机制：行级锁定，页级锁定和表级锁定
	- 表级锁定 表级别的锁定是MySQL各存储引擎中最大颗粒度的锁定机制。该锁定机制最大的特点是实现逻辑非常简单，带来的系统负面影响最小。所以获取锁和释放锁的速度很快。由于表级锁一次会将整个表锁定，所以可以很好的避免困扰我们的死锁问题。当然，锁定颗粒度大所带来最大的负面影响就是出现锁定资源争用的概率也会最高，致使并大度大打折扣。表级锁分为读锁和写锁
	- 页级锁定（page-level）：页级锁定的特点是锁定颗粒度介于行级锁定与表级锁之间，所以获取锁定所需要的资源开销，以及所能提供的并发处理能力也同样是介于上面二者之间。另外，页级锁定和行级锁定一样，会发生死锁
	- 行级锁定（row-level）：行级锁定最大的特点就是锁定对象的颗粒度很小，也是目前各大数据库管理软件所实现的锁定颗粒度最小的。由于锁定颗粒度很小，所以发生锁定资源争用的概率也最小，能够给予应用程序尽可能大的并发处理能力而提高一些需要高并发应用系统的整体性能。虽然能够在并发处理能力上面有较大的优势，但是行级锁定也因此带来了不少弊端。由于锁定资源的颗粒度很小，所以每次获取锁和释放锁需要做的事情也更多，带来的消耗自然也就更大了。此外，行级锁定也最容易发生死锁。InnoDB的行级锁同样分为两种，共享锁和排他锁，同样InnoDB也引入了意向锁（表级锁）的概念，所以也就有了意向共享锁和意向排他锁，所以InnoDB实际上有四种锁，即共享锁（S）、排他锁（X）、意向共享锁（IS）、意向排他锁（IX
	- 在MySQL数据库中，使用表级锁定的主要是MyISAM，Memory，CSV等一些非事务性存储引擎，而使用行级锁定的主要是Innodb存储引擎和NDBCluster存储引擎，页级锁定主要是BerkeleyDB存储引擎的锁定方式。

- 数据库事务属性
	- 事务具有以下4个属性，通常简称为事务的ACID属性。 
	- 原子性（Atomicity）：事务是一个原子操作单元，其对数据的修改，要么全都执行，要么全都不执行。 
	- 一致性（Consistent）：在事务开始和完成时，数据都必须保持一致状态，即要求事务做完后，要求满足数据库的一些完整性约。这意味着所有相关的数据规则都必须应用于事务的修改，以保持数据的完整性；事务结束时，所有的内部数据结构（如B树索引或双向链表）也都必须是正确的。 
	- 隔离性（Isolation）：数据库系统提供一定的隔离机制，保证事务在不受外部并发操作影响的“独立”环境执行。这意味着事务处理过程中的中间状态对外部是不可见的，反之亦然。 
	- 持久性（Durable）：事务完成之后，它对于数据的修改是永久性的，即使出现系统故障也能够保持。

- 索引实现
	- MyISAM索引实现
		- MyISAM索引使用了B+Tree作为索引结构，叶子结点的data域存放的是数据记录的地址。MyISAM中索引检索的算法为首先按照B+Tree搜索算法搜索索引，如果指定的Key存在，则取出其data域的值，然后以data域的值为地址，读取相应数据记录。主索引和辅助索引的存储结构没有任何区别。

	- InnoDB索引实现
		- 虽然InnoDB也使用B+Tree作为索引结构，但具体实现方式却与MyISAM截然不同。 第一个重大区别是InnoDB的数据文件本身就是索引文件。从上文知道，MyISAM索引文件和数据文件是分离的，索引文件仅保存数据记录的地址。而在InnoDB中，表数据文件本身就是按B+Tree组织的一个索引结构，这棵树的叶节点data域保存了完整的数据记录。这种索引叫做聚集索引。因为InnoDB的数据文件本身要按主键聚集，所以InnoDB要求表必须有主键（MyISAM可以没有），如果没有显式指定，则MySQL系统会自动选择一个可以唯一标识数据。 第二个与MyISAM索引的不同是InnoDB的辅助索引data域存储相应记录主键的值而不是地址。换句话说，InnoDB的所有辅助索引都引用主键作为data域。

	- Memory索引实现
		- Memory索引适用于需要快速访问数据的场景，显示支持哈希索引。内部基于哈希表数据结构实现，只包含哈希值和行指针，对于每一行数据，存储引擎都会对所有的引擎列计算一个哈希码，在哈希表对应位置存放该行数据的指针或地址。为了解决多个hash冲突问题，哈希索引采用了链地址法来解决冲突问题。所以采用链表数组作为存储结构。这种索引结构十分紧凑，且具有很快的查询速度。但也存在一些问题，

