import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HelloWorldTest {

	private TicTacToe subject;

	@Before
	public void setup() {
		subject = new TicTacToe();
	}
	
	@Test
	public void testGetMessage1() {
		assertEquals("Hello World!", subject.getMessage(false));
	}
	
	@Test
	public void testGetMessage2() {
		assertEquals("Helle Universe!", subject.getMessage(true));
	}
	
}
