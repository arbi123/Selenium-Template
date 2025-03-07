package Utilities;

import Globals.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseInformation {

    private static WebDriver driver;
    private static WebDriverWait wait  =new WebDriverWait(driver, Duration.ofSeconds(30));;


    public static BaseInformation getBaseInformation() {
        return new BaseInformation();
    }


    public static WebDriver getDriver() {


        if (driver == null) {
            String browserType = Globals.browserType.toLowerCase();

            switch (browserType) {

                case "chrome" -> {


                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");

                    String downloadFilepath = "C:\\Users\\Arbi.topi\\Downloads";
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("download.default_directory", downloadFilepath);
                    prefs.put("download.prompt_for_download", false);
                    prefs.put("download.directory_upgrade", true);
                    prefs.put("safebrowsing.enabled", true);


                    // Additional security bypasses
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--allow-running-insecure-content");
                    options.addArguments("--unsafely-treat-insecure-origin-as-secure=http://213.32.46.87:6070/");
                    options.setAcceptInsecureCerts(true);
                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Arbi.topi\\IdeaProjects\\PSQD\\src\\main\\resources\\chromedriver.exe");
                    driver = new ChromeDriver(options);
                }

                case "firefox" -> {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    // Set the correct path to the Firefox binary
                    firefoxOptions.setBinary("C:\\Users\\Arbi.topi\\AppData\\Local\\Mozilla Firefox\\firefox.exe");

                    String downloadFilepath = "C:\\Users\\Arbi.topi\\Downloads";
                    firefoxOptions.addPreference("browser.download.folderList", 2);
                    firefoxOptions.addPreference("browser.download.dir", downloadFilepath);
                    firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false);
                    firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/octet-stream");
                    firefoxOptions.setAcceptInsecureCerts(true);

                    // Ensure GeckoDriver is correctly set up
                    System.setProperty("webdriver.gecko.driver", "C:\\Users\\Arbi.topi\\IdeaProjects\\PSQD\\src\\main\\resources\\geckodriver.exe");

                    driver = new FirefoxDriver(firefoxOptions);
                }
                case "edge" -> {
                    EdgeOptions options = new EdgeOptions();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    prefs.put("autofill.profile_enabled", false);
                    options.setExperimentalOption("prefs", prefs);
                    options.addArguments("--disable-extensions");
                    options.addArguments("--disable-infobars");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--remote-allow-origins=*");
                    System.setProperty("webdriver.edge.driver", "C:\\Users\\Arbi.topi\\IdeaProjects\\PSQD\\src\\main\\resources\\msedgedriver.exe");
                    driver = new EdgeDriver(options);
                }
                default -> {
                    System.out.println("Unsupported browser: " + browserType);
                }
            }
        }
        driver.manage().window().maximize();

        return driver;
    }}
