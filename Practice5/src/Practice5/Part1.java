package Practice5;

public class Part1 {

	public static void main(String[] args) {
		new Thread(){
			@Override
			public void run() {
				for(int i = 0; i < 5; i++){
					System.out.println(getName());
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		Thread t = new Thread(new A());
		t.start();
	}
	static class A implements Runnable{
		@Override
		public void run() {
			for(int i = 0; i < 5; i++){
				System.out.println(Thread.currentThread().getName());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
