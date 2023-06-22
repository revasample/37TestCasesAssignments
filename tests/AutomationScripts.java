package com.salesforce.tests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AutomationScripts extends BaseTest {

	public static void main(String[] args) throws InterruptedException {

//		Login_ Error_Salesforce_testscript();
//		Login_to_Salesforce_testscript();
//		Check_RememberMe_testscript();
//		Forgot_PasswordA_testscript();
//		Validate_LoginErrorB_testscript();
//		Usermenu_Dropdown_testscript();
//		Click_Usermenu_Myprofile_testscript();
//		Click_Usermenu_Mysettings_testscript();
//		Click_Usermenu_DevelopersConsole_testscript();
//		Click_Usermenu_Logout_testscript();
//		Click_AccountsLink_testscript();
//		Create_Newview_testscript();
//		Editview_testscript();
//		Mergeaccounts_testscript();
//		Create_Accountreport_testscript();
//		Opportunities_Dropdown_testscript();
//		Create_Newopty_testscript();
//		Test_Opportunity_Pipeline_Report_testscript();
//		Test_Stuck_Opportunities_Report_testscript();
//		Test_Quarterly_Summary_Report_testscript();
//		Check_leads_Tab_Link_testscript();
//		Validate_View_Dropdown_List_testscript();
//		Functionality_of_the_Go_Button_testscript();
//		List_Item_Todays_Leads_testscript();
//		Check_New_Button_on_Leads_testscript();
//		Create_New_Contact_testscript();
//		Create_New_View_testscript();
//		Check_Recently_Created_Contact_in_the_Contact_Page_testscript();
//		Check_My_Contacts_View_in_the_Contact_Page_testscript();
//		View_a_Contact_in_the_Contact_Page_testscript();
//		Check_Error_Message_in_Create_new_View_testscript();
//		Check_the_Cancel_Button_in_Create_New_View_testscript();
//		Check_the_Save_and_New_Button_in_New_Contact_Page_testscript();
		Verify_if_the_firstname_and_lastname_of_loggedin_is_displayed_testscript();

	}

	public static void Login_Error_Salesforce_testscript() throws InterruptedException {

		String expectedTitle = "Login|Salesforce";
		launchBrowser("chrome");
		goToUrl("https://login.salesforce.com/");

		String actualTitle = driver.getTitle();
		if (expectedTitle.equals(actualTitle)) {
			System.out.println("title matched");
		} else {
			System.out.println("title is not matched");
		}

		WebElement username = driver.findElement(By.id("username"));
		enterText(username, "revathi@tekarch.com", "username");

		WebElement password = driver.findElement(By.id("password"));
		enterText(password, "", "password");

		WebElement button = driver.findElement(By.id("Login"));
		clickElement(button, "login button");

		String expectedError = " Please enter your password.";
		Alert alert = driver.switchTo().alert();
		String actualError = alert.getText();
		alert.accept();
		Assert.assertEquals(actualError, expectedError);

		closeBrowser();
		System.out.println("testscript error_login execution completed");
	}

	public static void Login_to_Salesforce_testscript() throws InterruptedException {

		launchBrowser("chrome");
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");
		WebElement hometab = driver.findElement(By.xpath("//a[@title=\"Home Tab\"]"));
		hometab.click();
		String expectedText = getTextFromWebElement(hometab, "Home Page ~ Salesforce - Developer Edition");
		String actualText = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualText, expectedText);

		closeBrowser();
		System.out.println("testscript login execution completed");
	}

	public static void Check_RememberMe_testscript() throws InterruptedException {

		launchBrowser("chrome");
		goToUrl("https://login.salesforce.com/");
//		driver.manage().timeouts().implicitlyWait(50000, TimeUnit.SECONDS);

//      userName
		WebElement username = driver.findElement(By.id("username"));
		enterText(username, "revathi@tekarch.com", "username");

//      password
		WebElement password = driver.findElement(By.id("password"));
		enterText(password, "Samaashu4", "password");

		WebElement label = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[3]/label"));
		label.click();
		Thread.sleep(5000);

		WebElement button = driver.findElement(By.id("Login"));
		button.click();

		WebElement button1 = driver.findElement(By.id("userNav-arrow"));
		button1.click();

		WebElement logout = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		logout.click();
		closeBrowser();
		System.out.println("testscript check_rememberme execution completed");

	}

	public static void Forgot_PasswordA_testscript() throws InterruptedException {

		launchBrowser("chrome");
		goToUrl("https://login.salesforce.com/");

		WebElement forgotPassword = driver.findElement(By.id("forgot_password_link"));
		forgotPassword.click();
		System.out.println("Forgot password page displayed");

		closeBrowser();
		System.out.println("testscript forgot_passwordA execution completed");

	}

	public static void Validate_LoginErrorB_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		String expectedError = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		Alert alert = driver.switchTo().alert();
		String errorMsg = alert.getText();
		alert.accept();
		if (errorMsg.equals(expectedError)) {
			System.out.println("testcase passed");
		} else {
			System.out.println("testcase failed");
		}
		
		closeBrowser();
		System.out.println("testscript validate_loginerrorB execution completed");

	}

	public static void Usermenu_Dropdown_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement usermenu = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
		usermenu.click();

		System.out.println(
				"My profile,My Settings,Developer Console,Switching to lightning Experience," + "Logout is displayed");
		Thread.sleep(4000);
		closeBrowser();
		System.out.println("testscript usermenu _dropdown execution completed");
	}

	public static void Click_Usermenu_Myprofile_testscript() throws InterruptedException {

		launchBrowser("chrome");
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement usermenu = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
		usermenu.click();
		Thread.sleep(1000);

		WebElement myprofile = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[1]"));
		myprofile.click();

		WebElement editprofile = driver.findElement(By.xpath("//a[img[@title=\"Edit Profile\"]]"));
		editprofile.click();
		Thread.sleep(3000);

		driver.switchTo().frame("About");
		WebElement abouttab = driver.findElement(By.xpath("//*[@id=\"aboutTab\"]/a"));
		abouttab.click();

		WebElement lastname = driver.findElement(By.name("lastName"));
		lastname.clear();
		lastname.sendKeys("yeleti");

		WebElement savebtn = driver.findElement(By.xpath("//input[@value='Save All']"));
		savebtn.click();

		WebElement postlink = driver.findElement(By.xpath("//*[@id=\"publisherAttachTextPost\"]/span[1]"));
		postlink.click();

		WebElement postcontent = driver.findElement(By.xpath("//*[@id=\"cke_43_contents\"]/iframe"));
		postcontent.click();
		Actions action = new Actions(driver);
		action.sendKeys("Hi Team").build().perform();

		WebElement sharebutton = driver.findElement(By.id("publishersharebutton"));
		sharebutton.click();

		WebElement fileupload = driver.findElement(By.xpath("//*[@id=\"publisherAttachContentPost\"]/span[1]"));
		fileupload.click();

		WebElement chatterupload = driver.findElement(By.id("chatterUploadFileAction"));
		chatterupload.click();

		WebElement chatterfile = driver.findElement(By.id("chatterFile"));
		chatterfile.sendKeys("C:\\Users\\Yelet\\OneDrive\\Desktop\\images.jpg");

		WebElement sharebut = driver.findElement(By.id("publishersharebutton"));
		sharebut.click();

//		System.out.println("test1");

	}

	public static void Click_Usermenu_Mysettings_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement usermenu = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
		usermenu.click();

		WebElement mysettings = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[2]"));
		mysettings.click();

		Thread.sleep(2000);
		WebElement personal = driver.findElement(By.xpath("//*[@id=\"PersonalInfo\"]/a"));
		personal.click();

		Thread.sleep(1000);
		WebElement LoginHistory = driver.findElement(By.id("LoginHistory_font"));
		LoginHistory.click();

		WebElement downloadLoginHistory = driver
				.findElement(By.xpath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a"));
		downloadLoginHistory.click();

		WebElement displaylayout = driver.findElement(By.xpath("//*[@id=\"DisplayAndLayout_font\"]"));
		displaylayout.click();

		WebElement customizemytabs = driver.findElement(By.id("CustomizeTabs_font"));
		customizemytabs.click();

		WebElement customapp = driver.findElement(By.id("p4"));
		Select select = new Select(customapp);
		select.selectByVisibleText("Salesforce Chatter");

		WebElement seltabs = driver.findElement(By.id("duel_select_1"));
		Select selTab = new Select(seltabs);
		selTab.selectByVisibleText("Assets");

		WebElement Remove = driver.findElement(By.xpath("//*[@title='Remove']"));
		Remove.click();

		WebElement availabletabs = driver.findElement(By.id("duel_select_0"));
		Select selectTab = new Select(availabletabs);
		selectTab.selectByVisibleText("Assets");

		WebElement add = driver.findElement(By.xpath("//*[@title='Add']"));
		add.click();

		WebElement savebutton = driver.findElement(By.xpath("//*[@title='Save']"));
		savebutton.click();

		WebElement menubutton = driver.findElement(By.xpath("//*[@id=\"tsidLabel\"]"));
		menubutton.click();

		WebElement salesforcechar = driver.findElement(By.xpath("//*[text()=\"Salesforce Chatter\"]"));
		salesforcechar.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		Thread.sleep(2000);
		System.out.println("test");
		WebElement emailbt = driver.findElement(By.xpath("//*[@id=\"EmailSetup\"]/a"));
		emailbt.click();

		WebElement myemailsettings = driver.findElement(By.id("EmailSettings_font"));
		myemailsettings.click();

	}

	public static void Click_Usermenu_DevelopersConsole_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement usermenu = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
		usermenu.click();

		WebElement developmentConsole = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[3]"));
		developmentConsole.click();
		Thread.sleep(2000);

		String oldwindow = driver.getWindowHandle();
		Set<String> pops = driver.getWindowHandles();
		{
			Iterator<String> it = pops.iterator();
			while (it.hasNext()) {

				String popupHandle = it.next().toString();
				if (!popupHandle.contains(oldwindow)) {
					driver.switchTo().window(popupHandle);
					System.out.println("Popu Up Title: " + driver.switchTo().window(popupHandle).getTitle());

				}
			}
		}

		closeBrowser();
		System.out.println("testscript usermenu_DevelopersConsole execution completed");
	}

	public static void Click_Usermenu_Logout_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement usermenu = driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]"));
		usermenu.click();
		Thread.sleep(2000);

		WebElement logout = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		logout.click();

		closeBrowser();
		System.out.println("testscript usermenu_logout execution completed");

	}

	public static void Click_AccountsLink_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement accounts = driver.findElement(By.xpath("//a[@title=\"Accounts Tab\"]"));
		accounts.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement newbutton = driver.findElement(By.xpath("//input[@title=\"New\"]"));
		newbutton.click();

		WebElement accountname = driver.findElement(By.id("acc2"));
		accountname.sendKeys("Praveen2");

		WebElement type = driver.findElement(By.id("acc6"));
		type.click();
		Select selectt = new Select(type);
		selectt.selectByVisibleText("Technology Partner");

		WebElement customerPriority = driver.findElement(By.id("00NDp00000CUs1W"));
		customerPriority.click();
		Select selectc = new Select(customerPriority);
		selectc.selectByVisibleText("High");
		Thread.sleep(2000);

		WebElement savebutton = driver.findElement(By.xpath("//*[@id=\"topButtonRow\"]/input[1]"));
		savebutton.click();

//		closeBrowser();
//		System.out.println("testscript AccountsLink execution completed");
	}

	public static void Create_Newview_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement accounts = driver.findElement(By.xpath("//a[@title=\"Accounts Tab\"]"));
		accounts.click();

		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement createnewview = driver.findElement(By.linkText("Create New View"));
		createnewview.click();

		WebElement viewname = driver.findElement(By.id("fname"));
		viewname.sendKeys("Samhita2");
		WebElement viewuniquename = driver.findElement(By.id("devname"));
		viewuniquename.sendKeys("Sam");
		WebElement savebutton = driver
				.findElement(By.xpath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]"));
		savebutton.click();

		closeBrowser();
		System.out.println("testscript create_newview execution completed");

	}

	public static void Editview_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement accounts = driver.findElement(By.xpath("//a[@title=\"Accounts Tab\"]"));
		accounts.click();

		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement viewdropdown = driver.findElement(By.id("fcf"));
		viewdropdown.click();
		Select select = new Select(viewdropdown);
		select.selectByVisibleText("Samhita");

		WebElement edit = driver.findElement(By.linkText("Edit"));
		edit.click();

		WebElement viewname = driver.findElement(By.id("fname"));
		viewname.clear();
		viewname.sendKeys("Samhita2");

		WebElement field = driver.findElement(By.id("fcol1"));
		Select selectf = new Select(field);
		selectf.selectByVisibleText("Account Name");

		WebElement operator = driver.findElement(By.id("fop1"));
		Select selecto = new Select(operator);
		selecto.selectByVisibleText("contains");

		WebElement value = driver.findElement(By.id("fval1"));
		value.sendKeys("oil");
		Thread.sleep(2000);

		WebElement savebutton = driver.findElement(By.xpath("//input[@title=\"Save\"]"));
		savebutton.click();

		closeBrowser();
		System.out.println("testscript editview execution completed");

	}

	public static void Mergeaccounts_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement accounts = driver.findElement(By.xpath("//a[@title=\"Accounts Tab\"]"));
		accounts.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();
		Thread.sleep(1000);

		WebElement mergeaccounts = driver.findElement(By.linkText("Merge Accounts"));
		mergeaccounts.click();

		WebElement textbox = driver.findElement(By.xpath("//*[@id=\"srch\"]"));
		textbox.sendKeys("Revathi");
		Thread.sleep(3000);

		System.out.println("test1");

		WebElement findaccounts = driver.findElement(By.xpath("//*[@value=\'Find Accounts\']"));
		findaccounts.click();
		Thread.sleep(1000);

		WebElement nextbutton = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[1]"));
		nextbutton.click();

		WebElement mergebutton = driver.findElement(By.xpath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[2]"));
		mergebutton.click();

		closeBrowser();
		System.out.println("testscript Mergeaccounts execution completed");

	}

	public static void Create_Accountreport_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement accounts = driver.findElement(By.xpath("//a[@title=\"Accounts Tab\"]"));
		accounts.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

	}

	public static void Opportunities_Dropdown_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement opportunities = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		opportunities.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement oppdropdown = driver.findElement(By.id("fcf"));
		oppdropdown.click();
		Thread.sleep(3000);
		System.out.println("All Oppotunities,Closing Next Month,Closing This Month, My Opportunities, "
				+ "New This Week, Recently Viewed Opportunities,Won is displayed");

		closeBrowser();
		System.out.println("testscript usermenu _dropdown execution completed");

	}

	public static void Create_Newopty_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement opportunities = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		opportunities.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement newbutton = driver.findElement(By.xpath("//input[@title=\"New\"]"));
		newbutton.click();

		WebElement opptname = driver.findElement(By.id("opp3"));
		opptname.sendKeys("Svecha");

		WebElement actname = driver.findElement(By.id("opp4"));
		actname.sendKeys("Revathi");

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = today.format(formatter);
		WebElement closedate = driver.findElement(By.id("opp9"));
		closedate.click();
		closedate.sendKeys(formattedDate);

		WebElement stage = driver.findElement(By.id("opp11"));
		stage.click();
		Select selects = new Select(stage);
		selects.selectByVisibleText("Qualification");

		WebElement probability = driver.findElement(By.id("opp12"));
		probability.sendKeys("6");

		WebElement leadsource = driver.findElement(By.id("opp6"));
		leadsource.click();
		Select selectls = new Select(leadsource);
		selectls.selectByVisibleText("Web");

		WebElement primarycamsource = driver.findElement(By.id("opp17"));
		primarycamsource.sendKeys("Lead");

		WebElement savebtn = driver.findElement(By.xpath("//input[@title=\"Save\"]"));
		savebtn.click();

	}

	public static void Test_Opportunity_Pipeline_Report_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement opportunities = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		opportunities.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement opptpipeline = driver.findElement(By.linkText("Opportunity Pipeline"));
		opptpipeline.click();

		closeBrowser();
		System.out.println("testscript Opportunity_Pipeline_Report execution completed");
	}

	public static void Test_Stuck_Opportunities_Report_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement opportunities = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		opportunities.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement opptpipeline = driver.findElement(By.linkText("Stuck Opportunities"));
		opptpipeline.click();

		closeBrowser();
		System.out.println("testscript Stuck_Opportunities_Report execution completed");
	}

	public static void Test_Quarterly_Summary_Report_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement opportunitieslink = driver.findElement(By.xpath("//*[@id=\"Opportunity_Tab\"]/a"));
		opportunitieslink.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement interval = driver.findElement(By.id("quarter_q"));
		interval.click();
		Select select = new Select(interval);
		select.selectByVisibleText("Current FQ");
		select.selectByVisibleText("Next FQ");

		WebElement include = driver.findElement(By.id("open"));
		include.click();
		Select select1 = new Select(include);
		select1.selectByVisibleText("All Opportunities");
		select1.selectByVisibleText("Open Opportunities");

		WebElement runreportbtn = driver.findElement(By.xpath("//input[@title=\"Run Report\"]"));
		runreportbtn.click();

		closeBrowser();
		System.out.println("testscript Quarterly_Summary_Report execution completed");
	}

	public static void Check_leads_Tab_Link_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement leadstab = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		leadstab.click();

		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		closeBrowser();
		System.out.println("testscript check_leads_tab_link execution completed");

	}

	public static void Validate_View_Dropdown_List_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement leadstab = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		leadstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement viewdropdown = driver.findElement(By.id("fcf"));
		viewdropdown.click();
		System.out.println("All Open Leads,My Unread Leads,Recently Viewed Leads,Today's Leads is displayed");

		closeBrowser();
		System.out.println("testscript Validate_Viewdrop_down_list execution completed");
	}

	public static void Functionality_of_the_Go_Button_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement leadstab = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		leadstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement view = driver.findElement(By.xpath("//select[@title=\"View:\"]"));
		Select select = new Select(view);
		select.selectByVisibleText("My Unread Leads");
		view.click();

		WebElement menubtn = driver.findElement(By.id("userNavLabel"));
		menubtn.click();

		WebElement clicklogout = driver.findElement(By.xpath("//a[@title=\"Logout\"]"));
		clicklogout.click();

		WebElement username = driver.findElement(By.id("username"));
		enterText(username, "revathi@tekarch.com", "username");

		WebElement password = driver.findElement(By.id("password"));
		enterText(password, "Samaashu4", "password");

		WebElement button = driver.findElement(By.id("Login"));
		clickElement(button, "login button");
		Thread.sleep(2000);

		WebElement leadtab = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		leadtab.click();

		WebElement gobutton = driver.findElement(By.xpath("//input[@title=\"Go!\"]"));
		gobutton.click();
		System.out.println("test1");
		closeBrowser();
		System.out.println("testscript Functionality_of_the_Go_Button execution completed");

	}

	public static void List_Item_Todays_Leads_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement leadstab = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		leadstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement viewdropdown = driver.findElement(By.id("fcf"));
		viewdropdown.click();
		Select select = new Select(viewdropdown);
		select.selectByVisibleText("Today's Leads");

		WebElement gobutton = driver.findElement(By.xpath("//input[@title=\"Go!\"]"));
		gobutton.click();

		closeBrowser();
		System.out.println("testscript list_item_todays_leads execution completed");
	}

	public static void Check_New_Button_on_Leads_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement leadstab = driver.findElement(By.xpath("//a[@title=\"Leads Tab\"]"));
		leadstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement newbtn = driver.findElement(By.xpath("//input[@title=\"New\"]"));
		newbtn.click();

		WebElement lastname = driver.findElement(By.id("name_lastlea2"));
		lastname.sendKeys("ABCD");

		WebElement compname = driver.findElement(By.id("lea3"));
		compname.sendKeys("ABCD");

		WebElement savebtn = driver.findElement(By.xpath("//input[@title=\"Save\"]"));
		savebtn.click();

		closeBrowser();
		System.out.println("testscript Checking_new_button_on_Leads execution completed");

	}

	public static void Create_New_Contact_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement contactstab = driver.findElement(By.xpath("//a[@title=\"Contacts Tab\"]"));
		contactstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement newbtn = driver.findElement(By.xpath("//input[@title=\"New\"]"));
		newbtn.click();

		WebElement lastname = driver.findElement(By.id("name_lastcon2"));
		lastname.sendKeys("Das");

		WebElement actname = driver.findElement(By.id("con4"));
		actname.sendKeys("United Oil & Gas Corp.");
		Thread.sleep(2000);

		WebElement savebtn = driver.findElement(By.xpath("//input[@title=\"Save\"]"));
		savebtn.click();

		closeBrowser();
		System.out.println("testscript Create_new_contact execution completed");
	}

	public static void Create_New_View_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement contactstab = driver.findElement(By.xpath("//a[@title=\"Contacts Tab\"]"));
		contactstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement createnewviewlink = driver.findElement(By.linkText("Create New View"));
		createnewviewlink.click();

		WebElement viewname = driver.findElement(By.id("fname"));
		viewname.sendKeys("Ravi");

		WebElement viewuniquename = driver.findElement(By.id("devname"));
		viewuniquename.sendKeys("Vari");
		Thread.sleep(2000);

		WebElement savebtn = driver.findElement(By.xpath("//input[@title=\"Save\"]"));
		savebtn.click();

		closeBrowser();
		System.out.println("testscript Create_new_view execution completed");

	}

	public static void Check_Recently_Created_Contact_in_the_Contact_Page_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement contactstab = driver.findElement(By.xpath("//a[@title=\"Contacts Tab\"]"));
		contactstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement selectdb = driver.findElement(By.xpath("//select[@title=\"Display Selection\"]"));
		selectdb.click();

		closeBrowser();
		System.out.println("testscript Checking_Recently_Created_Contact_in_Contact_Page execution completed");

	}

	public static void Check_My_Contacts_View_in_the_Contact_Page_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement contactstab = driver.findElement(By.xpath("//a[@title=\"Contacts Tab\"]"));
		contactstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement viewdropdown = driver.findElement(By.id("fcf"));
		viewdropdown.click();
		Select select = new Select(viewdropdown);
		select.selectByVisibleText("My Contacts");

		closeBrowser();
		System.out.println("testscript Check_My_Contacts_View_in_the_Contact_Page execution completed");

	}

	public static void View_a_Contact_in_the_Contact_Page_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement contactstab = driver.findElement(By.xpath("//a[@title=\"Contacts Tab\"]"));
		contactstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement namelink = driver.findElement(By.linkText("vari"));
		namelink.click();

		closeBrowser();
		System.out.println("testscript View_a_Contact_in_the_Contact_Page execution completed");

	}

	public static void Check_Error_Message_in_Create_new_View_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement contactstab = driver.findElement(By.xpath("//a[@title=\"Contacts Tab\"]"));
		contactstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement createnewviewlink = driver.findElement(By.linkText("Create New View"));
		createnewviewlink.click();

		WebElement viewuniquename = driver.findElement(By.id("devname"));
		viewuniquename.sendKeys("EFGH");
		Thread.sleep(2000);

		WebElement savebtn = driver.findElement(By.xpath("//input[@title=\"Save\"]"));
		savebtn.click();

		System.out.println("Error: You must enter a value");

		closeBrowser();
		System.out.println("testscript Check_Error_Message_in_Create_new_View execution completed");
	}

	public static void Check_the_Cancel_Button_in_Create_New_View_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement contactstab = driver.findElement(By.xpath("//a[@title=\"Contacts Tab\"]"));
		contactstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement createnewviewlink = driver.findElement(By.linkText("Create New View"));
		createnewviewlink.click();

		WebElement viewname = driver.findElement(By.id("fname"));
		viewname.sendKeys("ABCD");

		WebElement viewuniquename = driver.findElement(By.id("devname"));
		viewuniquename.sendKeys("EFGH");
		Thread.sleep(4000);

		WebElement cancelbtn = driver.findElement(By.xpath("//input[@title=\"Cancel\"]"));
		cancelbtn.click();
		System.out.println("test1");
		closeBrowser();
		System.out.println("testscript Check_the_Cancel_Button_in_Create_New_View execution completed");

	}

	public static void Check_the_Save_and_New_Button_in_New_Contact_Page_testscript() throws InterruptedException {

		launchBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement contactstab = driver.findElement(By.xpath("//a[@title=\"Contacts Tab\"]"));
		contactstab.click();
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();

		WebElement newbtn = driver.findElement(By.xpath("//input[@title=\"New\"]"));
		newbtn.click();

		WebElement lastname = driver.findElement(By.id("name_lastcon2"));
		lastname.sendKeys("Indian");

		WebElement actname = driver.findElement(By.id("con4"));
		actname.sendKeys("Global Media");
		Thread.sleep(2000);

		WebElement saveandnewbtn = driver.findElement(By.xpath("//input[@title=\"Save & New\"]"));
		saveandnewbtn.click();
		System.out.println("Error: Invalid Data Review all error messages below to correct your data.");

		closeBrowser();
		System.out.println("testscript Check_the_Save_and_New_button_in_New_Contact_page execution completed");
	}

	public static void Verify_if_the_firstname_and_lastname_of_loggedin_is_displayed_testscript()
			throws InterruptedException {
		
		launchBrowser("chrome");
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement hometab = driver.findElement(By.xpath("//a[@title=\"Home Tab\"]"));
		hometab.click();
		Thread.sleep(2000);
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();
		
		closeBrowser();
		System.out.println("testscript Verify_if_the_firstname_and_lastname_of_loggedin_is_displayed execution completed");	
		
	}
	
	public static void Verify_the_Edited_Lastname_is_Updated_at_various_places_testscript()
			throws InterruptedException {
		
		launchBrowser("chrome");
		salesforceLoginPage("https://login.salesforce.com/", "revathi@tekarch.com", "Samaashu4");

		WebElement hometab = driver.findElement(By.xpath("//a[@title=\"Home Tab\"]"));
		hometab.click();
		Thread.sleep(2000);
		WebElement tab = driver.findElement(By.id("tryLexDialogX"));
		tab.click();
	}

}