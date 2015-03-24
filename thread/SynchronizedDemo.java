package com.thread;

public class SynchronizedDemo {
	public static void main(String[] args) {
		Message message = new Message();
		new Thread(new Producer(message)).start();
		new Thread(new Cousmer(message)).start();
	}

}

class Producer implements Runnable {

	private Message message = null;

	public Producer(Message message) {
		this.message = message;
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			i++;
			message.setMessage("ѧ��" + i, i + "������");

		}
	}
}

class Cousmer implements Runnable {

	private Message message = null;

	public Cousmer(Message message) {
		super();
		this.message = message;
	}

	@Override
	public void run() {

		while (true) {

			message.getMessage();

		}

	}
}

class Message {
	private String no;
	private String name;
	private int a = 0;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public synchronized void setMessage(String no, String name) {
		if (a == 5) {
			try {
				super.wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (a < 5) {
			a++;
			System.out.println("��ϢС��5��,����һ��");
			this.setNo(no);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setName(name);
			System.out.println("��" + a + "����Ϣ");
			super.notify();

		}
	}

	public synchronized void getMessage() {
		if (a == 0) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (a > 0 && a < 5) {
			a--;
			System.out.println("����һ��");
			System.out.println(getNo() + ":" + getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("����" + a + "����Ϣ");
			super.notify();
		}

	}
}
