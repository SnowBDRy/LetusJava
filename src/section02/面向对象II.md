# 面向对象II

## 1.构造方法

* 规约：与类同名；没有返回值，不能用void修饰；默认在最后return;

* 用途：对象初始化

* 特性：在一个类中没有显式定义构造方法，编译器会自动提供一个默认的无参构造方法，但一旦显式定义构造方法，那么编译器不再提供默认的构造方法了

* 构造方法只会在对象创建时调用一次

* 根据参数类型和个数不同，构造方法可以重载

  ```java
  public class Person {
    private String name;
    private int age;
  
    public Person(String name, int age){
      name = "小明";
      age = 10;
    }
  
    public void speak(){
      System.out.println(name + ":" + age);
    }
  
    public static void main(String[] args){
      Person p = new Person();
    }
  }
  ```
  * 这段代码的内存图解如下
    ![构造方法内存图解](https://cdn.jsdelivr.net/gh/SnowBDRy/mos@master/uPic/构造方法内存图解.png)
  

## 2.this关键字

* 使用场景1：当局部变量和成员变量重名时，用this来区分成员变量，this代表所在方法所属对象的引用

  ```java
  public class Person {
    private String name;
    private int age;
  
    public Person(String name){
      name = name;
      age = 10;
    }
  
    public void speak(){
      System.out.println(name + ":" + age);
    }
  
    public static void main(String[] args){
      Person p = new Person("小明");
      p.speak();
      // 这段代码的运行结果是 null：10
       // 原因是第6行，右侧name是"小明"，左侧name和右侧同名，会导致编译器认定左侧name是局部变量和右侧是一致的，局部变量赋值，成员变量没赋值（第5行name是局部变量，在栈内存中，第2行的name是成员变量，在堆内存中，寻找变量的优先顺序栈>堆，在栈内存中找到name这个变量，就不会去堆内存找了）
    }
  }
  ```
  
* **this**关键字同时也是判断java调用哪个对象的依据，隐藏在调用的最初，如下代码，内存中如何区分p1和p2

  ```java
  public class Person {
    private String name;
    private int age;
  
    public Person(String name){
      this.name = name;
      age = 10;
    }
  
    public void speak(){
      System.out.println(name + ":" + age);
    }
  
    public static void main(String[] args){
      Person p1 = new Person("小明");
      Person p2 = new Person("小强");
    }
  }
  ```

  * 这段代码的内存图解如下
    ![this内存图解](https://cdn.jsdelivr.net/gh/SnowBDRy/mos@master/uPic/this内存图解.png)
  
* 使用场景2：用于在构造方法中调用其他构造方法，**只能定义在构造方法的第一行，因为初始化操作要先执行**，调用方法如下
  ```java
  public class Person {
    private String name;
    private int age;
  
    // 一个参数的构造方法
    public Person(String name){
      this.name = name;
      this.age = 10;
    }
  
    // 两个参数的构造方法
    public Person(String name, int age){
      // 调用一个参数的构造方法
      // this.Person(name);不能这样写的原因：this代表当前对象，对象尚未创建如何调用另一个构造器
      this(name);
      this.age = age;
    }
  
    public void speak(){
      System.out.println(name + ":" + age);
    }
  
    public static void main(String[] args){
  	  Person p = new Person("小明", "20");
    }
  }
  ```

* this关键字的具体应用，如：比较两个人的年龄是否一致，如下：

  ```java
  public class Person {
    private String name;
    private int age;
  
    public Person(String name, int age){
  		this.name = name;
      this.age = age;
    }
  	
  	public boolean compare(Person p){
    	return this.age = p.age;
    }
  
    public static void main(String[] args){
  		Person p1 = new Person("小明", "20");
      Person p2 = new Person("小强", "22");
      p1.compare(p2);
    	// compare方法中的this代表p1，p代表p2 
    }
  }
  ```

## 3. static（静态）关键字

* 本类内被**static**修饰的成员变量或是方法可以被所有对象所共享；static优先于对象存在，它随着类的加载就已经存在了

* 被**static**修饰的成员变量或是方法既可以被对象调用，又可以被类（类名）调用

* 静态变量和成员变量的不同点：

  * 生命周期不同：静态变量和类的生命周期一致；成员变量和对象的生命周期一致

  * 调用方式不同：静态变量可以被类和对象调用；成员变量只能被对象调用

  * 存储位置不同：静态变量存储于方法区（方法区的静态区中）；成员变量存储于堆内存的对象中

  * PS：Java运行时五大内存区域

    | 名称       | 用途                           |
    | ---------- | ------------------------------ |
    | 程序计数器 | 供CPU调用                      |
    | 栈         | 调用方法，局部变量等           |
    | 本地方法栈 | 调用系统底层代码               |
    | 堆         | 主要存储对象                   |
  | 方法区     | 方法、类信息、常量、静态变量等 |

* 静态方法只能访问静态成员，不可以访问非静态成员（成员变量和方法）。（原因：静态成员存储于方法区中，会随着类的建立而存在，对象存在于堆中，晚于类的建立，故而不能调用尚未建立的内容）

* 静态方法不可以使用**this**或**super**关键字

  ```java
  public class Person{
    private String name;
    private int age;
    static String country = "CN";
    public Person(String name, int age){
      this.name = name;
      this.age = age;
    }
    public void show(){
      System.out.pritnln(Person.country + "：" + this.name + "：" + this.age);
    }
    public static void method(){
      System.out.println(Person.country);
    }
  }

  public class StaticDemo{
    public static void main(String[] args){
      Person.method();
      Person p = new Person("A", 10);
      p.show();
    }
  }
  ```
  
  * 这段代码的内存图解如下
  
    ![静态方法内存图解](https://cdn.jsdelivr.net/gh/SnowBDRy/mos@master/uPic/静态方法内存图解.png)

* 如何使用：

  * 成员变量：本类内所有对象共用同样的成员（值是相同的），可以考虑static修饰，共享，不需要存在各个对象中
  * 方法：该方法的功能不需要需要访问对象的特有成员（非静态成员），可以考虑static修饰

* 块（静态块和构造块）

  * 静态块中的内容随着类的加载而执行，且只执行一次
  * 作用：用于给类初始化

    ```java
    public class Person{
      // 这就是一个静态块
      static{
        System.out.println("static Run");
      }
      public void show(){
        System.out.println("show Run");
      }
    
      public static void main(String[] args){
        new Person.show();
      }
    }
    ```

  * 构造块中的内容随着对象的创建而执行，优先级高于构造方法，创建多少个对象，执行多少次

  * 作用：给所有对象初始化

    ```java
    public class Person{
      private String name;
      private int age;
      // 这是一个构造代码块
      {
        System.out.println("1234567");
      }
      Person(){
        name = "baby";
      }
      Person(String name){
        this.name = name;
      }
      
      public static void main(String[] args){
        Person p = new Person();
        Person p = new Person("小明");
      }
    }
    ```




## 4.练习

* 数组工具类：参照代码ArrayTool.java

  * 获取数组最大值
  * 数组排序
  * 取数组下标（第一次）
  * 数组变成字符串

## 5.单例模式（Java23种设计模式之一，引申内容）

* 意图：保证一个类在内存中对象的唯一性

* 解决的问题：一个全局性的类频繁地创建和销毁对象

* 主要实现方式：饿汉式（通常，不延迟加载，线程安全）、懒汉式（延迟加载，分线程安全和线程不安全两种）、双重校验锁（延迟加载，线程安全且高性能）、静态内部类（明确要求延迟加载）和枚举（反序列化创建对象）

* 解决思路：

  * 1.不允许其他类可以使用new来创建这个对象
  * 2.在本类中创建一个本类的对象，但需要被所有对象所共享
  * 3.定义一个公有方法用于返回本类创建的对象，不可以通过其他方式访问

  ```java
  /**
   * 单例类（饿汉式）
   *
   * @author bdry
   */
  public class Single {
    /** 2.的解决策略：静态化创建的对象，使其变为共有，且只执行一次 */
    private static Single single = new Single();
  
    /** 1.的解决策略：私有化构造方法，使其无法使用new来创建对象 */
    private Single() {}
  
    /**
     * 3.的解决策略：a.由于不能创建对象，方法应该静态化 b.除本方法，不能有其他方式访问对象，故其他成员私有化（不能使用Single.single这种方式访问）
     *
     * @return Single
     */
    public static Single getInstance() {
      return single;
    }
  
    public void printMessage() {
      System.out.println("我是全局变量哈。");
    }
  }
  
  /**
   * 单例类的使用
   *
   * @author bdry
   */
  public class Demo02 {
    public static void main(String[] args) {
      Single.getInstance().printMessage();
    }
  }
  ```

  * 这段代码的内存图解如下

    ![单例设计模式内存图解](https://cdn.jsdelivr.net/gh/SnowBDRy/mos@master/uPic/单例设计模式内存图解.png)

* 开发一般用饿汉式，其他方式如下

  ```java
  // 懒汉式，线程不安全
  public class Singleton {  
    private static Singleton instance;  
    private Singleton (){}  
    
    public static Singleton getInstance() {  
    	if (instance == null) {  
      	instance = new Singleton();  
    	}  
    	return instance;  
    }  
  }
  
  // 懒汉式，线程安全
  public class Singleton {  
    private static Singleton instance;  
    private Singleton (){}  
    public static synchronized Singleton getInstance() {  
    	if (instance == null) {  
      	instance = new Singleton();  
    	}  
    	return instance;  
    }  
  }
  
  // 双重校验锁
  public class Singleton {  
    private volatile static Singleton singleton;  
    private Singleton (){}  
    public static Singleton getSingleton() {  
    	if (singleton == null) {  
      	synchronized (Singleton.class) {  
      		if (singleton == null) {  
        		singleton = new Singleton();  
      		}  
      	}  
    	}  
    	return singleton;  
    }  
  }
  
  // 静态内部类
  public class Singleton {  
    private static class SingletonHolder {  
    	private static final Singleton INSTANCE = new Singleton();  
    }  
    private Singleton (){}  
    public static final Singleton getInstance() {  
    	return SingletonHolder.INSTANCE;  
    }  
  }
  
  // 枚举
  public enum Singleton {  
      INSTANCE;  
      public void whateverMethod() {  
      }  
  }
  ```

  