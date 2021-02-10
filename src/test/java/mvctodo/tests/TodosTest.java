package mvctodo.tests;

import static org.junit.Assert.assertEquals;

import mvctodo.pages.HomePage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TodosTest {

  static WebDriver driver;


  @BeforeClass
  public static void setUp() {

    System.setProperty("webdriver.chrome.driver",
        "C:\\Users\\dzelenka\\chromedriver_win32\\chromedriver.exe");
    driver = new ChromeDriver();

    driver.navigate().to("http://localhost:3000/");
  }


  @Test
  public void siteCheck() {
    assertEquals("Title is correct", driver.getCurrentUrl(), "http://localhost:3000/");
  }


  @Test
  public void addATodo() {
    HomePage home = new HomePage(driver);
    home.submit_add_todo("something");
    WebElement todo = driver.findElement(By.cssSelector(".view"));
    assertEquals(todo.getText(), "something");
  }

  @Test
  public void removeATodo() {
    HomePage home = new HomePage(driver);
    WebElement todoList = driver.findElement(By.id("todo-list"));
    home.submit_add_todo("something");

    home.click_destroy_todo();

    assertEquals(todoList.isDisplayed(), false);

  }

  @Test
  public void checkOffTodo() {
    HomePage home = new HomePage(driver);
    home.submit_add_todo("something");
    home.click_todo_check();
    WebElement todo = driver.findElement(By.xpath("/html/body/section/section/div/section/ul/li"));

    assertEquals(todo.getAttribute("className"), "todo completed");
  }

  @Test
  public void listDoesNotReorder() {
    HomePage home = new HomePage(driver);
    home.submit_add_todo("something");
    home.submit_add_todo("something else");
    home.submit_add_todo("something more");
    home.click_destroy_todo();
    WebElement todoItemOne = driver
        .findElement(By.xpath("/html/body/section/section/div/section/ul/li[1]/div"));
    WebElement todoItemTwo = driver
        .findElement(By.xpath("/html/body/section/section/div/section/ul/li[2]/div"));
    assertEquals(todoItemOne.getText(), "something else");
    assertEquals(todoItemTwo.getText(), "something more");
  }

  @AfterClass
  public static void close() {
    driver.close();
    driver.quit();
  }
}
