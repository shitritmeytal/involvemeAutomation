package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TemplatesPage extends TopMenuDark{

	@FindBy (css = ".c-list-header>h1")
	private WebElement pageTitle;

	@FindBy (css = "#filter-all")
	private WebElement allBtn;
	
	@FindBy (css = ".btn-filter-all")
	private WebElement viewAllBtn;

	public TemplatesPage(WebDriver driver) {
		super(driver);
	}

//	Actions

	public void clickViewAll() {
		click(viewAllBtn);
	}
	
	// click category By name
	public void clickCategory(String categoryName) {
		List<WebElement> list = driver.findElements(By.cssSelector(".flex-column a"));
		for (WebElement el : list) {
			String cName = getTextNode(el);
			if (cName.equalsIgnoreCase(categoryName)) {
				click(el);
				break;
			}
		}
	}
	
	// click 'Choose' button by project title 
	public void clickChooseItem(String pTitle) {
		// list of all items (squares)
		List<WebElement> list = driver.findElements(By.cssSelector("tbody>tr")); 
		for (WebElement el : list) {
			// get the element header
			String title = getText(el.findElement(By.cssSelector(" h3"))); 
			//locate the  wanted header - once fount click the 'Choose button'
			if (title.equalsIgnoreCase(pTitle)) {  
				// hover the mouse over the element to locate the 'Choose button' of the item
				moveToElement(el);
				click(el.findElement(By.cssSelector(".btn-primary")));
				break;
			}
		}
		
	}
	
	// click 'Preview' button by project title 
		public void clickPreviewItem(String pTitle) {
			// list of all items (squares)
			List<WebElement> list = driver.findElements(By.cssSelector("tbody>tr")); 
			for (WebElement el : list) {
				// get the element header
				String title = getText(el.findElement(By.cssSelector(" h3"))); 
				//locate the  wanted header - once fount click the 'Preview button'
				if (title.equalsIgnoreCase(pTitle)) { 
					// hover the mouse over the element to locate the 'Preview button' of the item
					moveToElement(el); 
					click(el.findElement(By.cssSelector(".btn-preview")));
					break;
				}
			}
			
		}

//	Validation

	public String getPageTitle() {
		return getText(pageTitle);
	}

	// get the category name and returns (int) badge num
	public int getBadgeNum(String categoryName) {
		int badgeNum = 0;
		List<WebElement> list = driver.findElements(By.cssSelector(".flex-column a"));
		for (WebElement el : list) {
			String cName = getTextNode(el);
			if (cName.equalsIgnoreCase(categoryName)) {
				String badgeText = getText(el.findElement(By.cssSelector(" span")));
				badgeNum = Integer.parseInt(badgeText);
				break;
			}
		}
		return badgeNum;
	}
	
	// Sub method --- getBadgeNum(String categoryName) --- get webElement and return element's text after strip the clidren's text
	private String getTextNode(WebElement e){
	    String text = e.getText().trim();
	    List<WebElement> children = e.findElements(By.cssSelector(" span"));
	    for (WebElement child : children){
	        text = text.replaceFirst(child.getText(), "").trim();
	    }
	    return text;
	}

	// return page item list size
	public int getItemListNum(String categoryName) {
		clickCategory(categoryName);
		List<WebElement> list = driver.findElements(By.cssSelector("tbody>tr")); // list of item in the page table
		return list.size();
	}
	
	// return true/false - if Badge Match the Items
	public boolean isBadgeMatchItems(String categoryName) {
		int badge = getBadgeNum(categoryName);
		int items = getItemListNum(categoryName);
		if (badge == items-1) {      //  -1 cause blank item doesn't include in the badge
			return true;
		}
		return false;
	}
	
	public boolean isAllSelected() {
		String selected = allBtn.getAttribute("class");
		if (selected.contains("selected")) {
			return true;
		}
		return false;
	}
	
}



















