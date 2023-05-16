package pageobjectgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Settings {
//file name settings
	/**
	 * @author - Sajith, Hem, Ayush, Jasleen, Priyanshu, Rahul Tagra and Siddhanshi
	 * @version - 1.0
	 * @since - 5/16/2023
	 */

	public static String LOCATOR_FILE_NAME;
	// Crawler Settings
	public static final int CRAWL_DEPTH = 0; /* 0 = unlimited. */
	public static final int MAX_STATES = 0; /* 0 = unlimited. */
	public static final int MAX_RUNTIME = 5; /* 5 minutes. */
	public static final int WAIT_TIME_AFTER_EVENT = 500;
	public static final int WAIT_TIME_AFTER_RELOAD = 500;

	//public static final String URL = "http://www.google.com/";

	//public static final String URL = "https://mymis.geminisolutions.com";
	public static final String URL = "https://geminisolutions.com/";

	public static final String TITLE = "Sample HTML Page";
	//Used For Logging the steps
	public static final Logger LOGGER= LoggerFactory.getLogger(Settings.class);
	// Output Settings
	public static final String FILESEPARATOR = File.separator;
	public static final String GEN_PO_DIR = "locators" + FILESEPARATOR;
	public static final String IMPLEMENTATION_PO_DIR = "implementation" + FILESEPARATOR;
	public static final String STEP_DEFINITION_PO_DIR = "stepdefinition" + FILESEPARATOR;
	public static final String OUT_DIR = "output" + FILESEPARATOR;
	public static final String DOMS_DIR = OUT_DIR + "doms" + FILESEPARATOR;
	public static final String CLUST_DIR = OUT_DIR + "clusters" + FILESEPARATOR;
	public static final String BROWSER = "FF"; // PH-FF


	// Page Object Generation Settings
	public static boolean CRAWLING = true;
	public static boolean CLUSTERING = false;
	public static boolean REPEAT_STATIC_ANALYSIS = true;
	public static boolean GENERATE_CODE = true;
	public static boolean USE_INPUT_SPECIFICATION = true;
	public static String NUMBER_OF_CLUSTERS = "2";

	public static final String USER_CLICK_ANNOTATION ="User clicks on";
	public static final String USER_NAVIGATE_FUNCTION ="AndNavigateBack";
	public static final String USER_NAVIGATE_ANNOTATION ="and navigates back";
	public static final String USER_GETTEXT_FUNCTION ="userGetText";
	public static final String USER_TYPE_AND_ENTER_FUNCTION ="typeTextAndEnterFor";
	public static final String USER_TYPE_AND_TAB_FUNCTION ="typeTextAndTabFor";
	public static final String USER_GET_ATTRIBUTE_FUNCTION ="userGetsAttribute";
	public static final String USER_VERIFIES_VALUE="userVerifyValueFor";
	public static final String USER_CLICK_HOLD="userClicksAndHoldsElement";
	public static final String USER_DRAG_DROP="userDragsAndDrops";
	public static final String USER_UPLOAD_FILE="userUploadsFile";
	public static final String USER_VERIFIES_CHECKED="verifyIsSelectedFor";
	public static final String USER_VERIFIES_VALUE_ATTRIBUTE="userVerifyAttributeFor";
	public static final String VERIFY_ROW_COUNT ="^User verifies row count is (.*) for ";
	public static final String VERIFY_COLUMN_COUNT ="^User verifies column count is (.*) for ";
	public static final String USER_CLEAR_ANNOTATION ="User clears";
	public static final String USER_VERIFY_CLEAR_ANNOTATION ="User verifies";
	public static final String USER_CLEAR_FUNCTION ="userClear";
	public static final String USER_VERIFY_CLEAR_FUNCTION ="verifyValueClearedFor";
	public static final String USER_GETTEXT_ANNOTATION ="User gets the text of";
	public static final String USER_GET_ATTRIBUTE_ANNOTATION ="User gets " +"\\\"(.*)\\\""+ " attribute of ";
	public static final String USER_INPUT_ANNOTATION ="User enters "+"\\\"(.*)\\\""+" as";
	public static final String USER_TYPE_AND_ENTER_ANNOTATION ="User enters "+"\\\"(.*)\\\""+" as";
	public static final String USER_TYPE_AND_TAB_ANNOTATION ="User enters "+"\\\"(.*)\\\""+" as";
	public static final String USER_SELECT_FUNCTION ="userSelectFromDropdown";
	public static final String USER_DESELECT_FUNCTION ="userDeselectFromDropdown";
	public static final String USER_CONTAINS_FUNCTION ="userVerifiesTextContainsIn";
	public static final String USER_CONTAINS_ANNOTATION ="User verifies \\\"(.*)\\\" is present in";
	public static final String USER_CLICK_FUNCTION ="userClicksOn";
	public static final String USER_CLICKABLE_FUNCTION ="verifyUserIsClickable";
	public static final String USER_COUNT_CHILD_ELEMENTS ="verifyChildElementsCount";
	public static final String USER_COUNT_ELEMENTS ="verifyCountOfElementsFor";
	public static final String USER_CLICKABLE_ANNOTATION ="User is able to click";
	public static final String USER_VERIFY_CHILD_ELEMENTS_COUNT_ANNOTATION ="User verifies (.*) is the count of child elements for";
	public static final String USER_VERIFY_ELEMENTS_COUNT_ANNOTATION ="User verifies (.*) is the count of elements for";
	public static final String USER_DOUBLE_CLICK_FUNCTION ="userDoubleCLickON";
	public static final String USER_DOUBLE_CLICK_ANNOTATION ="User double click on";
	public static final String USER_ENABLED_FUNCTION ="userIsEnabled";
	public static final String USER_ENABLED_ANNOTATION ="User verifies";
	public static final String USER_IMAGE_FUNCTION ="userUploadImage";
	public static final String USER_INPUT_FUNCTION ="userEntersAs";
	public static final String USER_IMAGE_ANNOTATION ="User uploads image having path \\\"(.*)\\\" for";
	public static final String USER_HOME_PAGE ="openApplication";
	public static final String USER_SCROLL_CLICK_FUNCTION ="userScrollCLickOn";
	public static final String USER_SCROLL_CLICK_ANNOTATION ="User scroll and clicks on ";

	public static final String USER_HOME_PAGE_ANNOTATION ="^User is on homepage";
	public static final String USER_SELECT_ANNOTATION="User selects \\\"(.*)\\\" from";
	public static final String USER_DESELECT_ANNOTATION="User deselects \\\"(.*)\\\" from";
	public static final String USER_NAVIGATE_BACK ="^User navigates Back to Previous Page";
	public static final String SWITCH_ACTIVE_ELEMENT = "^User switches to Active Element";
	public static final String SWITCH_PARENT_FRAME = "^User switches to Parent Frame";
	public static final String SWITCH_DEFAULT_CONTENT = "^User switches to Default Content";
	public static final String SWITCH_FRAME_STRING = "^User switches to \\\"(.*)\\\" frame";
	public static final String SWITCH_FRAME_INT = "^User switches to (.*) frame";
	public static final String SWITCH_WINDOW = "^User switches to \\\"(.*)\\\" window";
	public static final String GET_LOGS = "^User gets console messages";
	public static final String NO_LOGS = "^User verifies there are no console messages";
	public static final String CLEAR_CONSOLE = "^User clears the console";
	public static final String NO_ERROR_LOGS = "^User verifies there are no console error messages";
	public static final String USER_CLICKS_JS = "^User clicks \\\"(.*?)\\\"";
	public static final String USER_WAITS = "^User waits for (.*) seconds";
	public static final String USER_CLOSES_BROWSER ="^User closes browser";

	public static final String MAXIMIZE_TO_DEFAULT ="^User maximizes window to default$";

	public static final String MINIMIZE_TO_DEFAULT ="^User minimizes window to default$";
	public static final String GET_PAGE_SOURCE ="^User gets page source$";

	public static final String SWITCH_ALERT ="^User switches to alert$";
	public static final String ACCEPT_ALERT ="^User accepts alert$";

	public static final String DISMISS_ALERT ="^User dismisses alert$";
	public static final String SCROLL_UP ="^User scrolls page up$";
	public static final String SCROLL_DOWN ="^User scrolls page down$";
	public static final String REFRESH_PAGE ="^User refreshes page$";
	public static final String TAKE_SNAPSHOT ="^User takes snapshot$";
	public static final String SCROLL_PAGE ="^User scrolls page to \\\"(.*)\\\" x coordinate and \\\"(.*)\\\" y coordinate$";
	public static final String SCROLL_ELEMENT ="^User scrolls element to \\\"(.*)\\\" x coordinate and \\\"(.*)\\\" y coordinate$";

	public static final String NAVIGATE_URL ="^User navigates to \\\"(.*)\\\" url$";
	public static final String ALERT_INPUT ="^User enters \\\"(.*)\\\" as alert input$";
	public static final String BROWSER_SIZE ="^User gets browser size$";

	public static final String BROWSER_POSITION ="^User gets browser positions$";
	public static final String GET_WINDOW_HANDLE ="^User gets window handle$";
	public static final String GET_WINDOW_HANDLES ="^User gets window handles$";
	public static final String SET_BROWSER_SIZE ="^User sets browser size to \\\"(.*)\\\" width and \\\"(.*)\\\" height$";

	public static final String SET_BROWSER_POSITION ="^User sets browser position to \\\"(.*)\\\" x coordinate and \\\"(.*)\\\" y coordinate$";
	public static final String CLOSE_CURRENT_TAB ="^User closes current tab$";

	public static final String CLOSE_TAB_AND_SWITCH ="^User closes current tab and switches to \\\"(.*)\\\" tab$";
	public static final String USER_NAVIGATE_FORWARD ="^User navigates Forward to Next Page";
	public static final String USER_NAVIGATE_TO ="^User navigates to \\\"(.*)\\\"$";

	public static final String USER_VERIFY_URL ="^User verifies \\\"(.*)\\\" is the current URL$";

	public static final String USER_VERIFY_TITLE ="^User verifies \\\"(.*)\\\" is the title of the page$";
	public static final String USER_PRESSES_ENTER ="^User presses Enter key$";
	public static final String USER_COPY ="^User performs Copy Action$";
	public static final String USER_PASTE ="^User performs Paste Action$";
	public static final String USER_WINDOW_FOCUS ="^User shifts focus to \\\"(.*)\\\" window$";
	public static final String USER_SELECT_ALL ="^User performs Select All Action$";

	public static final String USER_GET_URL ="^User gets current url of the page";

	public static final String USER_GET_TITLE ="^User gets title of the page";

	public static String BEFORE_FUNCTION="setDriverInitialisation";
	public static final String USER_SELECTS_FUNCTION ="userSelects";
	public static final String USER_SELECTS_ANNOTATION ="User selects";
	public static final String USER_RIGHT_CLICK_FUNCTION ="rightClickOn";
	public static final String USER_RIGHT_CLICK_ANNOTATION ="User right clicks on";









}
