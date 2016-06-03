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


