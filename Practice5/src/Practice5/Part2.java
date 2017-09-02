package Practice5;

import java.io.ByteArrayInputStream;

public class Part2 {
	public static void main(String[] args) throws InterruptedException {
		ByteArrayInputStream bais = new ByteArrayInputStream(System.lineSeparator().getBytes());
		bais.skip(System.lineSeparator().length());
		System.setIn(bais);
		Spam s = new Spam(new long[] {2000, 1500, 700, 1100, 900, 1000}, new String[]{"1", "2", "3", "4", "5", "6"});
		s.execute();
		Thread.sleep(5000);
		bais.reset();
	}

}
