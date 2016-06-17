1. Runnable
实现线程都需要实现Runnable接口
三种实现Runnable接口的方法：
	扩展Thread，适用于不需要扩展其他类的独立程序
	实现Runnable接口，适用于扩展类似多继承的情况（Thread是类，继承Thread就不能继承其他类，可以继承某个类，实现Runnable接口）
	构造Thread，传入实现Runnable的内部类，使用于类似于一次性的，比较小的交互

public class ThreadDemo1 extends Thread {
	String mesg;
	int count;
	
	public void run() {
		while (count-- > 0) {
			println(mesg);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				return;
			}
		}
		println(mesg + "all done");
	}
	
	void println(String s) {
		System.out.println(s);
	}
	
	public ThreadDemo1(String m, int n) {
		count = n;
		mesg = m;
		setName(m + " run thread");
	}
	
	public static void main(String[] argv) {
		new ThreadDemo1("Hello from x", 10).start();
		new ThreadDemo1("Hello from Y", 15).start();
	}
}

public class ThreadDemo2 implements Runnable {
	String mesg;
	Thread t;
	int count;
	
	public ThreadDemo2(String m, int n) {
		count = n;
		mesg = m;
		t = new Thread(this);
		t.setName(m + " printer thread");
		t.start();
	}
	//run method is the same as Demo1
}

public class ThreadDemo3 {
	String mesg;
	Thread t;
	int count;
	
	public static void main(String argv[]) {
		new ThreadDemo3("Hello from X", 10);
		new ThreadDemo3("Hello from Y", 15);
		
		public ThreadDemo3(String m, int n) {
			count = n;
			mesg = m;
			t = new Thread(new Runnable() {
				public void run() {
					while (count-- > 0) {
						print(mesg);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							return;
						}
					}
				}
			});
			t.start();
		}
	}
}



2. 停止线程
不要用Thread.stop()， 因为这个方法可能会导致无法可靠工作，用run方法中主循环加一个布尔判断，Jmeter也是这么做的
public class BooleanCheck extends Thread {
	protected boolean done = false;
	public void run() {
		while (!done) {
			System.out.println("stop");
			try {
				sleep(720);
			} catch (InterruptedException e) {
				break;
			}
		}
		System.out.println("Finished");
	}
	
	public void shutDown() {
		done = true;
	}
}
主循环加判断，如果需要停止就sleep，退出运行态，这时会触发InterruptedException，catch后不做操作或者break，让java虚拟机
来清理，即让run方法自己退出，而不是用stop终止，其实还可用Thread.interrupt方法，触发InterruptedException，这时再catch，
一样的效果，因为从运行到sleep也会有这个exception，总之，让thread自己跳出run，线程方法自己结束，让虚拟机来清理，而不是
强制stop



3. 超时
join方法会暂停当前线程，等待目标线程结束，即从run方法返回后线程结束，无参版本无限等待，一个参数版本指定时间
public class Join {
	public static void main(String[] args) {
		Thread t = new Thread() {
			public void run() {
				System.out.println("Reading");
				try {
					System.in.read();
				} catch (java.io.IOException ex) {
					System.out.println(ex);
				}
				System.out.println("Thread Done");
			};
			System.out.println("Starting");
			t.start();
			System.out.println("Joining");
			try {
				t.join();
			} catch (InterruptedException e) {
				System.out.println("xxx");
			}
			System.out.println("Main");
		}
	}
}


4. synchronized
把方法标记为synchronized，则如果有一个线程启动运行了这个方法，任何对它的调用都必须等待
public synchronized void add (Object o){}

如果要同步一段代码，而不是方法，则
synchronized(someObject) {
	//这段代码只能在一个线程中执行
}
someObject一般是要操作的对象
synchronized(myArrayList) {
	if (myArrayList.indexOf(someObject) != 1) {
		skip
	} else {
		new
	}
}


5. Lock
Lock lock = ...
try {
	lock.lock();
	//Lock protected part
} finally {
	lock.unlock();
}

ReadWriteLock接口其实现类ReentrantReadWriteLock类，该接口有两个方法：readLock和writeLock
rwlock.readLock().lock();
rw.lock.readLock().unlock()


6. 线程的通信：wait()和notifyAll()
synchronized可以加锁，可是无法线程通信
wait() 使当前线程在给定对象上阻塞，知道被notify()/notifyAll()解锁
notify() 使随机选择的线程等待对象被唤醒，它还会视图重获监视锁(monitor lock)，如果唤醒的是“错误的”线程，程序将死锁
notifyAll() 使所有线程都等待对象被唤醒，每个线程都要试图重获监视锁，会有一个成功获取

没有办法唤醒拥有锁的线程，这也是大多数程序都用notifyAll而不是notify的原因，这三个方法都必须在同步化的方法代码中使用

生产者消费者模式：
public class ProdCons {
	protected LinkedList list = new LinkedList();
	protected in MAX = 10;
	protected boolean done = false;
	
	//Internal class
	class Producer extends Thread {
		public void run() {
			while (true) {
				Object justProduced = getRequestFromNetwork();//MOCK
				synchronized(list) {
					while(list.size() == MAX) {
						try {
							System.out.println("Producer WAITING");
							list.wait();
						} catch (InterruptedException ex) {
							System.out.println("Producer INTERRUPTED");
						}
					}
					list.addFirst(justProduced);
					if(done)
						break;
					list.notifyAll(); //Must have lock
					System.out.println("Produce 1, list size: " + list.size();
				}
			}
		}
	}
	
	//Internal class
	class Consumer extends Thread {
		public void run() {
			while(true) {
				Object obj = null
				int len = 0;
				synchronized(list) {
					while(list.size() == 0) {
						try {
							System.out.println("CONSUMER　ＷＡＩＴＩＮＧ")；
							list.wait();
						} catch (InterruptedException ex) {
							System.out.printly("COUNSUMER INTERRUPTED");
						}
					}
					if (done)
						break;
					obj = list.removeLast();
					list.notifyAll();
					len = list.size();
					System.out.println("List size now: " + len);
				}
				process();//不需要拥有锁
			}
		}
	}
	
	ProdCons(int p, int c) {
		for (int i=0; i<p; i++)
			new Producer().start();
		for (int i=0; i<c; i++)
			new Consumer.start();
	}
	
	public static void main(String args[]) {
		int p = 1;
		int c = 2;
		ProdCons pc = new ProdCons(p, c);
		
		...
	}
}


7. 用Queue接口简化生产者消费者模式
#Java有实现了的类，用于并发编程，不要自己写lock，semaphore之类的，直接用concurrent的类
import java.util.*;
import java.io.*;
import java.util.concurrent.*;

public class ProdCons {
	class Producer implements Runnable {
		protected BlockingQueue queue;
		Producer(BlockingQueue theQueue) { this.queue = theQueue; }
		
		public void run() {
			try {
				while(true) {
					Object justProduced = getRequestFromNetwork();
					queue.put(justProduced);
					if(done) {
						return;
					}
				}
			} catch (InterruptedException ex) {
				System.out.println("Producer Interrupted");
			}
		}
	}
	
	class Consumer implements Runnable {
		protected BlockingQueue queue;
		Consumer(BlockingQueue theQueue) { this.queue = theQueue; }
		
		public void run() {
			try {
				while(true) {
					Object obj = queue.take();
					int len = queue.size();
					process(obj);
					if (done) {
						return;
					}
				}
			} catch (InterruptedException e) {
				xxx
			}
		}
	}
	
	ProdCons(int nP, int nC) {
		BlockingQueue myQueue = new LinkedBlockingQueue();
		for (int i=0; i<nP; i++)
			new Thread(new Producer(myQueue)).start();
		for (int i=0; i<nC; i++)
			new Thread(new Consumer(myQueue)).start();
	}
	
	public static void main(String[] args) {
		int nP = 4;
		int nC = 5;
		ProdCons p = new ProdCons(nP, nC);
		p.done = true;
	}
}
#基本思路，把需要同步的任务交给BlockingQueue


8. 自动保存文件
#daemon 线程
public class AutoSave extends Thread {
	protected FileSaver model;
	private static final int SECONDS = MINUTES*60;
	
	public AutoSave (FileSaver m) {
		setDaemon(true);
		model = m;
	}
	public void run() {
		while(true) {
			try {
				sleep(xxx);
			} catch (InterruptedException e) {
				
			}
			if (model.wantAutoSave() && model.hasUnsavedChanges())
				model.saveFile(null);
		}
	}
}


9. 网络服务器
#用Executor线程池
#几乎可以说，java中用jdk提供的并发框架，不要自己弄

public class HttpdConcurrent extends Httpd {
	Executor myThreadPool = Executors.newPixedThreadPool(5);
	
	public HttpdConcurrent() throws Exception {
		super();
	}
	
	public static void main(String[] argv) throws Exception {
		HttpdConcurrent w = new HttpdConcurrent();
		if (argv.length ==2 && argv[0].equals("-p")) {
			w.startServer(Integer.parseInt(argv[1]));
		} else {
			w.startServer(HTTP);
		}
		w.runServer();
	}
	public void runServer() throws Exception {
		while (true) {
			final Socket clientSocket = sock.accept();
			myThreadPool.execute(new Runnable() {
				public void run() {
					new Handler(HttpConcurrent.this).process(clientSocket);
				}
			});
		}
	}
}

MIME(Multipurpose Internet Mail Extensions)多用途互联网邮件扩展类型。是设定某种扩展名的文件用一种应用程序来打开的方式类型，当该扩展名文件被访问的时候，浏览器会自动使用指定应用程序来打开。多用于指定一些客户端自定义的文件名，以及一些媒体文件打开方式。
它是一个互联网标准，扩展了电子邮件标准，使其能够支持：
非ASCII字符文本；非文本格式附件（二进制、声音、图像等）；由多部分（multiple parts）组成的消息体；包含非ASCII字符的头信息（Header information）。
public class Httpd {
	public static final int HTTP = 80;
	protected ServerSocket sock;
	private Properties wsp;
	private Properties mimeTypes;
	private String rootDir;
	
	Httpd() throws Exception {
		super();
		wsp = new FileProerties("httpd.properties");
		rootDir = wsp.getProerty("rootDir", ".");
		mimeTypes = new FileProerties(wsp.getProperty("mimeProerties", "mime.properties"));
	}
	
	public void startServer(int portNum) throws Exception {
		String portNumString = null;
		if (portNum == HTTP) {
			portNumString = wsp.getProperty("portNum");
			if (portNumString != null) {
				portNum = Integer.parseInt(portNumString);
			}
		}
		sock = new ServerSocket(portNum);
	}
	xxxget xxxset method , skip
}
