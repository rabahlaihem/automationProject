package med.webpages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import med.webproperties.MedicarePropertyConfig;
import med.webutil.MedicareFileUtil;

public class MedicareAdminPage extends TestPageBase {
	
	private final String saveBtnXpathBuilder = "//form[@id='product']//input[@type='submit']";
	private final String managePdtXpathBuilder = "//a[text()='Manage Product']";
	private final String addCatXpathBuilder = "//button[text()='Add New Category']";
	private final String orderPageScrollJsBuilder = "window.scrollBy(0,document.body.scrollHeight)";
	private final String unitPriceJsBuilder = "document.querySelector('#unitPrice').value =";
	private final String quantityJsBuilder = "document.querySelector('#quantity').value =";
	
	
	
	@FindBy(how=How.XPATH, using="//a[text()='Manage Product']") WebElement managePdtLinkEl;
	@FindBy(how=How.XPATH, using="//button[text()='Add New Category']") WebElement addCategoryEl;
	@FindBy(how=How.XPATH, using="//form[@id='categoryForm']//input[@id='name']") WebElement popNameEl;
	@FindBy(how=How.XPATH, using="//form[@id='categoryForm']//textarea[@id='description']") WebElement popDescEl;
	@FindBy(how=How.XPATH, using="//form[@id='categoryForm']//input[@type='submit']") WebElement popBtnSaveEl;
	@FindBy(how=How.XPATH, using="//select[@id='categoryId']/option") List<WebElement> drpdnBtnEl;
	
	
	@FindBy(how=How.XPATH, using="//form[@id='product']//input[@id='name']") WebElement addPdtNameEl;
	@FindBy(how=How.XPATH, using="//form[@id='product']//input[@id='brand']") WebElement addPdtBndEl;
	@FindBy(how=How.XPATH, using="//form[@id='product']//textarea[@id='description']") WebElement addPdtDescEl;
	@FindBy(how=How.XPATH, using="//form[@id='product']//input[@id='unitPrice']") WebElement addPdtUnitEl;
	@FindBy(how=How.XPATH, using="//form[@id='product']//input[@id='quantity']") WebElement addPdtQtyEl;
	@FindBy(how=How.XPATH, using="//form[@id='product']//input[@id='file']") WebElement addPdtFileEl;
	@FindBy(how=How.XPATH, using="//form[@id='product']//select[@id='categoryId']") WebElement drpdnPdtCatEl;
	@FindBy(how=How.XPATH, using="//form[@id='product']//input[@type='submit']") WebElement pdtBtnSaveEl;
		
	
	public MedicareAdminPage(WebDriver driver){
		super(driver);
	}	
	
	public void validateAddNewCategory(String cat, String desc,MedicareViewProductsPage viewProductPage) throws InterruptedException {
		
		clickElementWithFluentWait(managePdtXpathBuilder);
		waitForPageToBeVisible(saveBtnXpathBuilder);
		clickElementWithFluentWait(addCatXpathBuilder);
		popNameEl.sendKeys(cat);
		popDescEl.sendKeys(desc);
		popBtnSaveEl.click();
		viewProductPage.clickViewProductLink();
		viewProductPage.validateCategory(cat);
		clickElementWithFluentWait(managePdtXpathBuilder);
		Assert.assertTrue(validateCategoryDropdown(cat));
		
	}
	
	public void validateAddNewProduct(HashMap<String,String> hashMapProductVal,MedicareViewProductsPage viewProductPage) throws InterruptedException {
		
		clickElementWithFluentWait(managePdtXpathBuilder);
		waitForPageToBeVisible(saveBtnXpathBuilder);
		addPdtNameEl.sendKeys(hashMapProductVal.get("productName"));
		addPdtBndEl.sendKeys(hashMapProductVal.get("productBrand"));
		addPdtDescEl.sendKeys(hashMapProductVal.get("productDesc"));
		addPdtUnitEl.clear();
		addPdtUnitEl.sendKeys(hashMapProductVal.get("productPrice"));
		addPdtQtyEl.clear();
		addPdtQtyEl.sendKeys(hashMapProductVal.get("productQty"));
		//String filePath = MedicareFileUtil.getMedicareTestImagesResourcePath()+"\\"+hashMapProductVal.get("fileLocation");
		addPdtFileEl.sendKeys(MedicarePropertyConfig.PRODUCTIMAGEURI);
		Select dropdown = new Select(drpdnPdtCatEl);
  		dropdown.selectByVisibleText(hashMapProductVal.get("productCategory"));
  		pdtBtnSaveEl.click();
  		viewProductPage.clickViewProductLink();
  		viewProductPage.validateAdminProduct(hashMapProductVal);
  		//Thread.sleep(5000);
		
	}
	
	public void validateEditNewProduct(HashMap<String,String> hashMapPdtEditVal,MedicareViewProductsPage viewProductPage) throws InterruptedException {
		
		//clickElementWithFluentWait(managePdtXpathBuilder);
		System.out.println("Program starting");
		viewProductPage.clickViewProductLink();
		System.out.println("Program starting - View Product link clicked");
		//execJsScript(orderPageScrollJsBuilder);		
		viewProductPage.selectDropdownViewProduct(hashMapPdtEditVal.get("dropdownSelectValue"));
		execJsScript(orderPageScrollJsBuilder);
		System.out.println("Program starting - scroll clicked");
		viewProductPage.selectPageViewProduct(hashMapPdtEditVal.get("pageNumber"));
		System.out.println("Program starting - page number clicked");
		viewProductPage.clickviewProductLast(hashMapPdtEditVal.get("rowNumber"));
		System.out.println("Program starting - row number clicked");
		waitForPageToBeVisible(saveBtnXpathBuilder);		
		System.out.println("Program starting - editing values");
		//addPdtNameEl.clear();
		//addPdtNameEl.sendKeys(hashMapPdtEditVal.get("expProductName"));
		//System.out.println(hashMapPdtEditVal.get("expProductName"));
		//addPdtBndEl.clear();
		//addPdtBndEl.sendKeys(hashMapPdtEditVal.get("expProductBrand"));
		//System.out.println(hashMapPdtEditVal.get("expProductBrand"));
		//addPdtDescEl.clear();
		//addPdtDescEl.sendKeys(hashMapPdtEditVal.get("expProductDesc"));
		//System.out.println(hashMapPdtEditVal.get("expProductDesc"));
		//addPdtUnitEl.clear();
		execJsScript(unitPriceJsBuilder+hashMapPdtEditVal.get("expUnitPrice"));
		//addPdtUnitEl.sendKeys(hashMapPdtEditVal.get("expUnitPrice"));
		System.out.println(hashMapPdtEditVal.get("expUnitPrice"));
		//addPdtQtyEl.clear();
		execJsScript(quantityJsBuilder+hashMapPdtEditVal.get("expProductQty"));
		//addPdtQtyEl.sendKeys(hashMapPdtEditVal.get("expProductQty"));
		System.out.println(hashMapPdtEditVal.get("expProductQty"));
		//addPdtFileEl.clear();
		//String filePath = MedicareFileUtil.getMedicareTestImagesResourcePath()+"\\"+hashMapPdtEditVal.get("fileLocation");
		addPdtFileEl.sendKeys(MedicarePropertyConfig.PRODUCTIMAGEURI);
		System.out.println("file selected");
		Select dropdown = new Select(drpdnPdtCatEl);
  		dropdown.selectByVisibleText(hashMapPdtEditVal.get("productCategory"));
  		System.out.println("dropdown category selected");
		pdtBtnSaveEl.click();
		System.out.println("save clicked");
  		viewProductPage.clickViewProductLink();
  		viewProductPage.validateAdminProduct(hashMapPdtEditVal);  		
		
		}	
	
	
	public Boolean validateCategoryDropdown(String catVal) {
		
	    for(WebElement e :drpdnBtnEl){  
			
	    	if(e.getText().equals(catVal))
	    		return true;
	    	
		}  
		
		return false;
	}

}
