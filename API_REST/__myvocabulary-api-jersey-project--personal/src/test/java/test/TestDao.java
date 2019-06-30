package test;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "/META-INF/spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDao {

	private static Logger logger = Logger.getLogger(TestDao.class);

	@Test
	public void test() {
		assertTrue(true);
	}
}
