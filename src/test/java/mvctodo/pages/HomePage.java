package mvctodo.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

  private WebDriver driver;

  //page URL
  private static String PAGE_URL = "http://localhost:3000";


  @FindBy(tagName = "h1")
  WebElement heading;

  @FindBy(id = "add-todo")
  WebElement add_todo;

  @FindBy(id = "todo-list")
  WebElement todo_list;

  @FindBy(xpath = "/html/body/section/section/div/section/ul/li[1]/div/button")
  WebElement destroy_todo;

  @FindBy(xpath = "  /html/body/section/section/div/section/ul/li/div/input")
  WebElement todo_check;

  @FindBy(xpath = "/html/body/section/section/div/section/ul/li[1]")
  WebElement todo_item;

  @FindBy(xpath = "/html/body/section/section/div/section/ul/li[2]/div")
  WebElement todo_item_two;

  @FindBy(xpath = "/html/body/section/section/div/section/ul/li[3]/div")
  WebElement todo_item_three;

  public HomePage(WebDriver driver) {
    this.driver = driver;
    driver.get(PAGE_URL);
    PageFactory.initElements(driver, this);
  }

  public void submit_add_todo(String todo) {
    add_todo.clear();
    add_todo.sendKeys(todo);
    add_todo.sendKeys(Keys.ENTER);
  }

  public void click_destroy_todo() {
    Actions action = new Actions(driver);
    action.moveToElement(todo_item).build().perform();
    destroy_todo.click();
  }

  public void click_todo_check() {
    Actions action = new Actions(driver);
    action.moveToElement(todo_item).build().perform();
    todo_check.click();
  }

  public void click_destroy_todo_two() {
    Actions action = new Actions(driver);
    action.moveToElement(todo_item_two).build().perform();
    destroy_todo.click();
  }

  public void click_destroy_todo_three() {
    Actions action = new Actions(driver);
    action.moveToElement(todo_item_three).build().perform();
    destroy_todo.click();
  }

  public boolean isPageOpened() {
    return heading.getText().toString().contains("todos");
  }

}
