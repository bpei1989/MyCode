/**
 * Created by bpei
 */
 
 
 /**
 1. 数组
 数组的长度是后面new出来的
 */
  int[] i = new int[12];
  int[] days = {1,2,3,4,5,6,7};
  Calendar[] cal = new Calendar[3];
  int[][] my = new int[10][];
  for (int i=0; i<10; i++) {
	  my[i] = new int[24];
  }

  
  
/**
2. ArrayList
ArrayList封装了数组的功能，允许自动扩展，可以容纳任何类型的对象，以下方法也是List接口的主要方法及Vector和其他集合类的方法
add(Object o)	在末尾添加给定元素
add(int i, Object o)	在指定位置插入给定元素
clear()		从集合中删除全部元素
Contains(Object o)		如果包含给定元素，返回true
get(int i)		返回指定位置的对象句柄
indexOf(Object o)		如果找到给定对象，则返回其索引值，否则返回-1
remove(Object o)	根据引用或位置删除对象
remove(int i)
toArray()		返回包含集合对象的数组
*/
List a = new ArrayList();	//常用写法，接口在前，具体类对象在等号右边，可以用指定类型，如List<String>
a.add(xxx)
a.xxx



/**
3. Iterator
用于取集合中的元素，当不知道集合中是什么类型元素，用Iterator， Iterator是接口
如果知道是什么类型，则用for(Type a :: Collection col)
*/
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorDemo {
	public static void main(String[] argv) {
		List l = new ArrayList();
		StructureDemo source = new StructureDemo(15);
		
		l.add(source.getDate());
		l.add(source.getDate());
		l.add(source.getDate());
		
		int i = 0;
		
		//以下部分通用，适合各种容器
		Iterator it = l.iterator();
		while(it.hasNext()) {
			Object o = it.next;
			System.out.println(o);
		}
	}
}



/**
4. 链表
可以定义一个类实现，一般更常用LinkedList
*/


/**
5. 用Hashtable和HashMap映射
HashMap是Hashtable的轻量级实现（非线程安全的实现），他们都完成了Map接口，
主要区别在于HashMap允许空（null）键值（key）,由于非线程安全，效率上可能高于Hashtable。
*/



/**
6. Properties类和Preferences类
java.util.Properties是对properties这类配置文件的映射。支持key-value类型和xml类型两种。#打头的是注释行，Properties会忽略注释。允许只有key没有value。
properties继承自Hashtable,存储key-value数据，以key=value的形式打印出。
Properties对象可以通过属性文件载入。属性文件格式灵活，在键和值之间，用"=",":",空格分割，主键中非空字符后的空格将被忽略，反斜线\用于续行,# !为注释

它提供了几个主要的方法：
1． getProperty ( String key)，用指定的键在此属性列表中搜索属性。也就是通过参数 key ，得到 key 所对应的 value。
2． load ( InputStream inStream)，从输入流中读取属性列表（键和元素对）。通过对指定的文件进行装载来获取该文件中的所有键 - 值对。
以供 getProperty ( String key) 来搜索。
3． setProperty ( String key, String value) ，调用 Hashtable 的方法 put 。他通过调用基类的put方法来设置 键 - 值对。
4． store ( OutputStream out, String comments)，以适合使用 load 方法加载到 Properties 表中的格式，
将此 Properties 表中的属性列表（键和元素对）写入输出流。与 load 方法相反，该方法将键 - 值对写入到指定的文件中去。
5． clear ()，清除所有装载的 键 - 值对。该方法在基类中提供。

读Properties文件
方法1： InputStream in = getClass().getResourceAsStream("资源Name"); //最常用
方法2： InputStream in = new BufferedInputStream(new FileInputStream(filepath));

基本流程：
1. new一个Properties对象pps；
2. InputStream in = new BufferedInputStream (new FileInputStream(filePath)); 读配置文件
3. pps.load(in)
4. 操作
   1）读取指定key的value
     String value = pps.getProperty(key); 
   2）逐个读取全部
     Enumeration en = pps.propertyNames();
     while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
	3)写入
	    OutputStream out = new FileOutputStream(filePath); //打开要写入的文件
        pps.setProperty(pKey, pValue);
        pps.store(out, "Update " + pKey + " name"); //用store存入流中
     
   

Preferences的中文意思即偏好或喜好的意思，也就是说同一个程序在每次运行完后，可以通过Preferences来记录用户的偏好，
下次启动时，程序会利用这些信息来了解用户的喜好。而这些信息个人理解应该就是存储在系统的注册表中。
*/
import java.util.prefs.Preferences;
public class PreferenceTest {
    private Preferences prefs;
    public void setPreference() {
// This will define a node in which the preferences can be stored
        prefs = Preferences.userRoot().node(this.getClass().getName());
        String ID1 = "Test1";
        String ID2 = "Test2";
        String ID3 = "Test3";
// First we will get the values
// Define a boolean value
        System.out.println(prefs.getBoolean(ID1, true));
// Define a string with default "Hello World
        System.out.println(prefs.get(ID2, "Hello World"));
// Define a integer with default 50
        System.out.println(prefs.getInt(ID3, 50));
// Now set the values
        prefs.putBoolean(ID1, false);
        prefs.put(ID2, "Hello Europa");
        prefs.putInt(ID3, 45);
		
        prefs.remove(ID1);// Delete the preference settings for the first value
    }
    public static void main(String[] args) {
        PreferenceTest test = new PreferenceTest();
        test.setPreference();
    }
}


import java.util.Properties;
 
public class ReadJVM {
    public static void main(String[] args) {
        Properties pps = System.getProperties();
        pps.list(System.out);
    }
}


//关于Properties类常用的操作
public class TestProperties {
    //根据Key读取Value
    public static String GetValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream (new FileInputStream(filePath));  
            pps.load(in);
            String value = pps.getProperty(key);
            System.out.println(key + " = " + value);
            return value;
            
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
	
	
    //读取Properties的全部信息
    public static void GetAllProperties(String filePath) throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        pps.load(in); //必须load，再操作
        Enumeration en = pps.propertyNames(); //得到配置文件的名字
        
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
        
    }
    
    //写入Properties信息
    public static void WriteProperties (String filePath, String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        
        InputStream in = new FileInputStream(filePath);
        //从输入流中读取属性列表（键和元素对） 
        pps.load(in); //先load
        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
        //以适合使用 load 方法加载到 Properties 表中的格式，  
        //将此 Properties 表中的属性列表（键和元素对）写入输出流  
        pps.store(out, "Update " + pKey + " name");
    }
    
    public static void main(String [] args) throws IOException{
        //String value = GetValueByKey("Test.properties", "name");
        //System.out.println(value);
        //GetAllProperties("Test.properties");
        WriteProperties("Test.properties","long", "212");
    }
}



/**
7. 排序
用静态方法Collections.sort()，此外，还可实现Comparator接口自定义排序
sort除了无参用法，还有下面的用法
Arrays.sort(int[] a, int fromIndex, int toIndex)
这种形式是对数组部分排序，也就是对数组a的下标从fromIndex到toIndex-1的元素排序，注意：下标为toIndex的元素不参与排序

如果定义类时就内置了比较功能，就不用实现Comparator接口（类中有compareTo()方法和equals()方法，即直接实现了Comparable接口）
接口名					说明									方法
java.lang.Comparable	提供对象的自然排序，内置于类中				int compareTo(Object o1);
																boolean equals(Object o2)
java.util.Comparator	提供特定的比较方法，作为参数传递给sort()	int compare(Object o1, )														
*/
//根据字符串首字母排序，新建SubstringComparator类，实现Comparator接口
public class SubstringComparator implements Comparator {
	public int compare(Object 01, Object o2) {
		String s1 = o1.toString().substring(1);
		String s1 = o1.toString().substring(1);
		return s1.compareTo(s2)
	}
}

public class SubstrCompDemo {
	public static void main(String[] unused) {
		String[] strings = {
			"painful", "mainly", "gaining"
		}
		Arrays.sort(strings) //用默认排序，根据元素的自然顺序 对指定列表按升序进行排序
		Arrays.sort(strings, new SubstringComparator()) //注意需要new一个Comparator实现
	}
}



/**
8. 避免频繁排序
保持数据有序，用TreeSet,TreeMap，可以把Hashtable或HashMap的对象转为TreeMap对象，这样就是排序的
TreeMap sortedMap = new TreeMap(unsortedHashMap);

还可以对大小写不敏感
TreeSet t = new TreeSet(String.CASE_INSENSITIVE_ORDER);

场景：
从数据库查询出来的数据放在List中，顺序都还是对的，但放在HashMap中，顺序就完全乱了。java.util.TreeMap 放入其中的value，自动按key进行排序， 
默认的排序规则按ascii码排序，如果是key是英文和数字不会有问题，如果中文则会出现问题，需要自己写排序规则。

TreeMap的实现是红黑树算法的实现
  红黑树顾名思义就是节点是红色或者黑色的平衡二叉树，它通过颜色的约束来维持着二叉树的平衡。对于一棵有效的红黑树二叉树而言我们必须增加如下规则：
       1、每个节点都只能是红色或者黑色
       2、根节点是黑色
       3、每个叶节点（NIL节点，空节点）是黑色的。
       4、如果一个结点是红的，则它两个子节点都是黑的。也就是说在一条路径上不能出现相邻的两个红色结点。
       5、从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。
*/



/**
9. 排除重复元素
用Set，其中元素是独一无二的，如果添加对象与Set中对象相等，由equals()方法来决定，那么只保留一个
*/


/**
10. 在集合中搜索特定对象
用以下方法
binarySearch()	
contains()
containsKey()
containsValue()
indexOf()
search()
用哪个根据用的哪个集合，可以在编辑器中.后面选择
*/


/**
11. 将集合转成数组
用toArray()方法
Object[] a = xxx.toArray()
*/


/**
12. 自定义Iterator
需要实现Iterator接口，Itrator接口的实现依赖于数据存储格式，一般有hasNext，next，remove方法
*/


/**
13. 栈
用Stack类，
*/


/**
14. 多维结构
ArrayList里还可以是ArrayList，还可以是其他集合
所有集合都可以嵌套
*/


