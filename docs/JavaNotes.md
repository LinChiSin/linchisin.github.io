
#集合框架（Collection）
Collection(extends Iterable)、List（ArrayList、LinkedList、Vector）、Set（SortedSet）、Queue(Deque)、Map（SortedMap）


##ArrayList、Vector、LinkedList区别

都实现了List<E>接口，可从原理、多线程安全性、速度、实现方式、容量与扩容等几个方面比较不同：

1. 原理：ArrayList和Vector底层基于数组实现，因此随机访问更快，但删除、增加元素较慢；LinkedList底层基于链表实现，访问较慢，但修改元素更快(实际上还实现了Deque()接口，因此可以实现队列、堆栈、双队列等多种数据结构的基本方法)
2. 多线程安全：ArrayList和LinkedList属于非线程安全，Vector使用了Synchronized同步。
3. 速度：单线程下ArrayList会比Vector更快
4. 容量与扩容：ArrayList的初始容量为10，此后扩容方式为：` int newCapacity = oldCapacity + (oldCapacity >> 1);`，即增加原有容量的50%；Vector在使用不指定初始容量的情况下，初始容量也为10，但此后的扩容方式为：` int newCapacity = oldCapacity + ((capacityIncrement > 0) ? capacityIncrement : oldCapacity);`，即在原有的基础上增加一倍。ArrayList和Vector的最大容量均为`Integer.MAX_VALUE-8`；LinkedList采用链表实现，因此实时增加。


##HashMap与Hashtable的区别

都实现了 Map <K, V\>接口，可从内容、多线程安全性、遍历、速度、同步和hash数组几个方面比较不同：

1. 内容：HashMap可以接受<null, null>关联，而Hashtable不行。

2. 多线程安全性：HashMap是非Synchronized，而Hashtable则是进行了Synchronized同步，因此HashMap可能会产生多线程安全问题。产生多线程安全问题的直接原因有两类：1是put()方法可能会由于多线程切换放入错误内容，2是多线程可能会影响扩容机制的预判。

> HashMap会引起死循环问题：HashMap在并发执行put操作时，多线程会导致HashMap的Node链表数据结构，一旦形成环形数据结构，Node的node节点永不为空，就会在获取Node时发生死循环。

3. 遍历：HashMap的迭代器是Iterator，而Hashtable的迭代是枚举Enumerararion，Iterator的迭代存在Fail-fast机制，如果其他线程试图在遍历的过程中修改哈希表的结构，则可能造成ConcurrentModificationException，除非是迭代器本身的remove()方法移除元素，则不会抛出concurrentModificationException异常。同样这样是Enumeration和Iterator的区别。

4. 速度：由于Hashtable借助synchroniezed保证多线程安全，因此在单线程环境下hashtable会比hashmap要慢一些。

5. 让HashMap实现同步的方法：Hashmap<K, V> hashmap=new HashMapMap(); m=Collections.synchronizedMap(hashmap);

6. 容量与扩容：HashMap的初始容量为2^4（最大容量为2^30），负载因子为0.75，而Hashtable的初始容量为11，负载因子为0.75。HashMap的扩容方式为`newCap=oldCap<<1`，Hashtable的扩容方式为  `int newCapacity = (oldCapacity << 1) + 1`,即乘2加1。

7. Hashtable的哈希值是直接使用Key的.hashCode()，而HashMap则是使用了`h = key.hashCode()) ^ (h >>> 16)`在原有hashCode()的基础上再进行按位与运算

##ConcurrentHashMap

背景：HashMap 不安全，一个解决方案是Hashtable，使用Synchronized同步，但是该方式效率较低，如果在线程过多，单一锁的方式会极大地降低运行效率。Java 1.5推出了ConcurrentHashMap，作为Hashtable的代替，它使用分段锁的思想，对数据进行分段(Segments)加锁，这样能使得需要不同数据时分发不同的锁，提升效率。(和Hashtable类似， 不允许<null, null\>)

重点：ConcurrentHashMap的一些更新操作如 put(),remove(),putAll(),clear()只锁住操作的部分，所以在检索操作不能保证返回最新的结果（解决办法：putIfAbsent(key,value)避免上面的线程竞争）;
另一个重要点是在迭代遍历CHM时，keySet返回的iterator是弱一致和fail-safe的，可能不会返回某些最近的改变，并且在遍历过程中，如果已经遍历的数组上的内容变化了，不会抛出ConcurrentModificationExceptoin的异常。

##Collections.SynchronizedMap(HashMap)

+ 在 Object 级别的锁
+ 每次的 读/写 操作都要求获得锁，性能较差
+ 锁定整个集合是一个性能的开销
+ 这本质上只允许一条线程访问整个 Map 并且会阻塞所有其他的线程
+ 它可能会引起争议
+ SynchronizedHashMap 返回 Iterator ，它会在并发修改时失败



###补充：Enumeration(枚举类)和 Iterator（迭代器） 

Enumeration(枚举类)和 Iterator（迭代器） 的区别在于，Enumeration只有两个函数接口（hasMoreElements和nextElement），只能用于读取集合的数据，而不能对数据进行修改；而Iterator有三个函数接口（hasNext()，next()，remove()），也就是说Iterator除了能读取集合的数据之外，还能进行数据删除操作。 Enumeration用于Vector和Hashtable，本身不支持同步，Vector、Hashtable在实现Enumeration时，添加了同步。 而Iterator是为了Hashmap 和 ArrayList等集合提供了遍历接口，支持fail-fast机制：当多个线程对同一集合进行操作时，就可能产生fail-fast事件。



##LinkedHashMap 

+ 双链表（前后指针）+哈希表，有序存放哈希内容（按照插入顺序），三个重点实现的函数（afterNodeAccess()、afterNodeInsertion()、afterNodeRemoval()为了保证双向链表中的节点次序或者双向链表容量）

+ 当选择accessOrder时，且此时迭代器Iterator中执行put和get操作时（remove() 方法除外），就会产生structural modification，也会造成CurrentModificarionException）。


#多线程

##多线程的实现方式：
1. 继承Thread类，重写run方法，没有返回值，不能抛出异常，且只能单一继承；
2. 实现Runnable接口，重写run方法，没有返回值，不能抛出异常，可以实现多个接口；
3. 实现Callable接口，重写call方法，可以返回值和抛出异常


#Object类

Object的构造方法
Object类的方法总结：

##equals(). hashCode()总结

##重写equals()方法的要求

##重写hashCode()方法的一般方法

#Java IO

## 序列化与反序列化

序列化的背景：Java平台允许我们在内存中创建可复用的Java对象，但一般情况下，只有当JVM处于运行时，这些对象才可能存在，即，这些对象的生命周期不会比JVM的生命周期更长。但在现实应用中，就可能要求在JVM停止运行之后能够保存(持久化)指定的对象，并在将来重新读取被保存的对象。Java对象序列化就能够帮助我们实现该功能。

1. 在Java中，只要一个类实现了java.io.Serializable接口，那么它就可以被序列化。

2. 通过ObjectOutputStream和ObjectInputStream对对象进行序列化及反序列化

3. 虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点是两个类的序列化 ID 是否一致（就是 private static final long serialVersionUID）

4. 序列化并不保存静态变量。

5. 要想将父类对象也序列化，就需要让父类也实现Serializable 接口。

6. transient 关键字 关键字的作用是控制变量的序列化，在变量声明前加上该关键字，可以阻止该变量被序列化到文件中，在被反序列化后，transient 变量的值被设为初始值，如 int 型的是 0，对象型的是 null。(一个静态变量不管是否被transient修饰，均不能被序列化，反序列化后类中static 型变量的值为当前JVM中对应static变量的值，这个值是JVM中的不是反序列化得出的)

补充：transient会在使用Java进行序列化的另一个接口Externalizable的writeExternal()方法时失效。
补充：
>在序列化过程中，如果被序列化的类中定义了writeObject 和 readObject 方法，虚拟机会试图调用对象类里的 writeObject 和 readObject 方法，进行用户自定义的序列化和反序列化。
>如果没有这样的方法，则默认调用是 ObjectOutputStream 的 defaultWriteObject 方法以及 ObjectInputStream 的 defaultReadObject 方法。
>用户自定义的 writeObject 和 readObject 方法可以允许用户控制序列化的过程，比如可以在序列化的过程中动态改变序列化的数值。

如果没有这样的方法，则默认调用是 ObjectOutputStream 的 defaultWriteObject 方法以及 ObjectInputStream 的 defaultReadObject 方法。

用户自定义的 writeObject 和 readObject 方法可以允许用户控制序列化的过程，比如可以在序列化的过程中动态改变序列化的数值，实现自定义的序列化和反序列化策略。

>问：如何自定义序列化和反序列化策略
>答：可以通过在被序列化的类中增加writeObject 和 readObject方法。

>问：如果一个类中包含writeObject 和 readObject 方法，那么这两个方法是怎么被调用的?

>答：在使用ObjectOutputStream的writeObject方法和ObjectInputStream的readObject方法时，会通过反射的方式调用。（调用栈：`writeObject ---> writeObject0 --->writeOrdinaryObject--->writeSerialData--->invokeWriteObject`）

>问：Serializable明明就是一个空的接口，它是怎么保证只有实现了该接口的方法才能进行序列化与反序列化的呢？

>答：`writeObject ---> writeObject0 --->writeOrdinaryObject--->writeSerialData--->invokeWriteObject`调用栈中，writeObject0方法会在序列化操作时，会判断要被序列化的类是否是Enum、Array和Serializable类型，否则抛出NotSerializableException


7. 服务器端给客户端发送序列化对象数据，对象中有一些数据是敏感的，比如密码字符串等，希望对该密码字段在序列化时，进行加密，而客户端如果拥有解密的密钥，只有在客户端进行反序列化时，才可以对密码进行读取，这样可以一定程度保证序列化对象的数据安全。


###序列化与反序列化总结

1. 如果一个类想被序列化，需要实现Serializable接口。否则将抛出NotSerializableException异常，这是因为，在序列化操作过程中会对类型进行检查，要求被序列化的类必须属于Enum、Array和Serializable类型其中的任何一种。

2. 在变量声明前加上该关键字，可以阻止该变量被序列化到文件中。

3. 在类中增加writeObject 和 readObject 方法可以实现自定义序列化策略
