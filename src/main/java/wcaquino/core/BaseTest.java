package wcaquino.core;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import static wcaquino.core.DriverFactory.getDriver;
import static wcaquino.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

public class BaseTest {

	@Rule
	public TestName testName = new TestName();
	
	@After
	public void finaliza() throws IOException {
		
		TakesScreenshot ss = (TakesScreenshot) getDriver();
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File("target" + File.separator +"screenshot" + File.separator + testName.getMethodName() + ".jpg"));
		
		killDriver();
	}
}
