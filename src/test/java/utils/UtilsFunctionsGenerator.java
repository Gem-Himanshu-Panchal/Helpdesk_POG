package utils;

import com.gemini.generic.ui.utils.DriverManager;
import japa.parser.ASTHelper;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.*;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import pageobjectgenerator.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class UtilsFunctionsGenerator {

    /**
     * @author - Sajith, Hem, Ayush, Jasleen, Priyanshu, Rahul Tagra and Siddhanshi
     * @version - 1.0
     * @since - 5/16/2023
     */

    private static String meaningFulName = "";

    /**
     * returns a basic plain CompilationUnit object
     *
     * @return
     */
    public static CompilationUnit createBasicCompilationUnit() {
        return new CompilationUnit();
    }

    /**
     * returns a CompilationUnit object decorated with package and some basic import
     * instructions
     *
     * @return
     */
    public static CompilationUnit createEnhancedCompilationUnit(String name, String type) throws IOException {
        CompilationUnit cu = new CompilationUnit();
        cu.setPackage(new PackageDeclaration(ASTHelper.createNameExpr(name)));
        cu.setImports(UtilsFunctionsGenerator.getAllImports(type));
        return cu;

    }

    /**
     * set the TypeDeclaration of a CompilationUnit i.e., whether is a class or an
     * interface
     *
     * @param c
     * @param className
     */
    public static void setTypeDeclaration(CompilationUnit c, String className) throws IOException {
        // create the type declaration and class
        String classToExtend = "";
        if (readProperties("Framework").contains("GEMJAR")) {
            classToExtend = classToExtend + "DriverAction";
        } else {
            classToExtend = classToExtend + "PageObject";
        }
        ClassOrInterfaceType typeForExtends = new ClassOrInterfaceType(classToExtend);
        List<ClassOrInterfaceType> extendsList = new ArrayList<ClassOrInterfaceType>();
        extendsList.add(typeForExtends);
        ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(null, ModifierSet.PUBLIC, null, false, className, null, extendsList, null, null);

        ASTHelper.addTypeDeclaration(c, type);

    }

    /**
     * adds a WebDriver instance to the CompilationUnit c
     *
     * @param c CompilationUnit
     */
    //set implementation variable
    public static void setWebDriverVariable(CompilationUnit c) throws IOException {
        //setting the driver for the current class
        VariableDeclarator v = new VariableDeclarator();
        if (!(readProperties("Framework").contains("GEMJAR"))) {
            v.setId(new VariableDeclaratorId("driver =" + "getDriver(" + ")"));
//        v.setInit(new ObjectCreationExpr(null, new ClassOrInterfaceType(null, classname), null));
            FieldDeclaration f = ASTHelper.createFieldDeclaration(ModifierSet.PRIVATE, ASTHelper.createReferenceType("WebDriver", 0), v);
            ASTHelper.addMember(c.getTypes().get(0), f);
        }
    }

    public static String readProperties(String property) throws IOException { // Function to read Data from Properties File
        FileReader read = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
        Properties credential = new Properties();
        credential.load(read);
        return credential.getProperty(property);
    }


    /**
     * adds a WebDriver instance to the CompilationUnit c
     *
     * @param c CompilationUnit
     */

    /**
     * set the package of the CompilationUnit c
     *
     * @param c
     */
    public static void setPackage(CompilationUnit c) {
        c.setPackage(new PackageDeclaration(ASTHelper.createNameExpr("locators")));
    }

    /**
     * adds Selenium imports to the compilation unit
     */
    private static List<ImportDeclaration> getAllImports(String type) throws IOException {
        List<ImportDeclaration> imports = new LinkedList<>();

        if (StringUtils.equalsIgnoreCase(type, "PageObject")) {
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium"), false, true));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.FindBy"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.PageFactory"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.io.File"), false, false));
        } else {
            //adding imports for implementation class
            if (readProperties("Framework").contains("GEMJAR")) {
                imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.reporting.GemTestReporter"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.reporting.STATUS"), false, false));
//                imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.ui.utils.DriverAction.takeSnapShot"), true, false));
                imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.ui.utils.DriverAction"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.utils.GemJarUtils"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.chrome.ChromeOptions"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("io.github.bonigarcia.wdm.WebDriverManager"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.firefox.FirefoxDriver"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.utils.GemJarGlobalVar"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("java.io.File"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("net.thucydides.core.webdriver.SerenityWebdriverManager"), false, false));
            } else {
                imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.WebElementFacade"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.PageObject"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("org.apache.commons.io.FileUtils"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("net.thucydides.core.webdriver.SerenityWebdriverManager"), false, false));
            }
            imports.add(new ImportDeclaration(new NameExpr("com.gemini.generic.ui.utils.DriverManager"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("pageobjectgenerator.Settings"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.Serenity"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("static org.junit.Assert.assertTrue"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.junit.Assert"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("static org.junit.Assert.assertFalse"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("locators" + "." + Settings.LOCATOR_FILE_NAME), false, false));
//                imports.add(new ImportDeclaration(new NameExpr("static locators" + "." + Settings.LOCATOR_FILE_NAME + "." + "driver"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.ui.ExpectedConditions"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.ui.WebDriverWait"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.time.Duration"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.WebElement"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.JavascriptExecutor"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.apache.commons.lang3.StringUtils"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.interactions.Actions"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.WebDriver"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.Keys"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.util.Objects"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.*"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.SerenityActions"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.By"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.lang.reflect.Field"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.util.List"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.util.ArrayList"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("utils.UtilsMethodCodeGenerator"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.io.File"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.WebElementFacade"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.util.Set"), false, false));
        }

        return imports;
    }

    // get attribute method
    public static void setLinkMethodsIsFileDownloaded(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("boolean", 0), "isFileDownloaded");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "fileName"));
        method.setParameters(parameters);
        Settings.LOGGER.info(parameters.toString());
        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for File Download"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tFile dir = new File(System.getProperty(\"user.home\") + \"/Downloads\");\n\t\t\tFile[] files = dir.listFiles()"));
            ASTHelper.addStmt(block, new NameExpr("\tlong startTime = System.currentTimeMillis();\n\t\t\twhile ((System.currentTimeMillis() - startTime) < Long.parseLong(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))){\n\t\t\t\t" + "for (File file : files) {\n\t\t\t\t\tif (file.getName().equalsIgnoreCase(fileName)) {\n\t\t\t\t\tGemTestReporter.addTestStep(\"Verify if file is downloaded\",\"File downloaded successfully\", STATUS.PASS, takeSnapShot());\n\t\t\t\t\tSettings.LOGGER.info(\"File downloaded successfully\");\n\t\t\t\t\treturn true"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t}\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tGemTestReporter.addTestStep(\"Verify if file is downloaded\",\"Unable to verify if file is downloaded\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings.LOGGER.info(\"Unable to verify if file is downloaded\")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            ASTHelper.addStmt(block, new NameExpr("return false"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tFile dir = new File(System.getProperty(\"user.home\") + \"/Downloads\");\n\t\t\tFile[] files = dir.listFiles()"));
            ASTHelper.addStmt(block, new NameExpr("\tlong startTime = System.currentTimeMillis();\n\t\t\twhile ((System.currentTimeMillis() - startTime) < Long.parseLong(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))){\n\t\t\t\t" + "for (File file : files) {\n\t\t\t\t\tif (file.getName().equalsIgnoreCase(fileName)) {;\n\t\t\t\t\tSettings.LOGGER.info(\"File downloaded successfully\");\n\t\t\t\t\treturn true"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t}\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to verify if file is downloaded\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to verify is file is downloaded\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            ASTHelper.addStmt(block, new NameExpr("return false"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
     }

    public static void setLinkMethodsGetWindowFocus(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "getWindowFocus");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "windowHandle"));
        method.setParameters(parameters);
        Settings.LOGGER.info(parameters.toString());
        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function changes the window focus"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDriverManager.getWebDriver().switchTo().window(windowHandle)"));
            ASTHelper.addStmt(block, new NameExpr("\tJavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();\n\t\t\tjs.executeScript(\"window.focus();\");\n\t\t\tSettings.LOGGER.info(\"get window focus successfully\");{\n\t\t\t\t\tGemTestReporter.addTestStep(\"Focus window for give window handle\",\"focused successfully \", STATUS.PASS, takeSnapShot());"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t}\n\t\t}catch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\t\t\tGemTestReporter.addTestStep(\"Focus window for give window handle\",\"Unable to focus o window\", STATUS.FAIL, takeSnapShot());"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDriverManager.getWebDriver().switchTo().window(windowHandle)"));
            ASTHelper.addStmt(block, new NameExpr("\tJavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver()"));
            ASTHelper.addStmt(block, new NameExpr("\tjs.executeScript(\"window.focus();\");\n\t\t\t\tSettings.LOGGER.info(\"get window focus successfully\")"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t}\tcatch(" + "Exception e" + "){\n\t\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to get window in focus\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get window in focus\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsNoOfTabs(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "noOfTabs");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for getting no of tabs"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tArrayList<String> tabs = new ArrayList<String>(DriverManager.getWebDriver().getWindowHandles())"));
            ASTHelper.addStmt(block, new NameExpr("\tif(tabs.size()>0)\n\t\t\t{\n\t\t\t\tGemTestReporter.addTestStep(\"User fetches no of tabs\",\"No of tabs fetched successfully\", STATUS.PASS, takeSnapShot());\n\t\t\t\tSettings.LOGGER.info(\"No. of tabs fetched successfully\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\telse\n\t\t\t{\n\t\t\t\tGemTestReporter.addTestStep(\"User fetches no of tabs\",\"Unable to fetch no of tabs\", STATUS.FAIL, takeSnapShot());\n\t\t\t\tSettings.LOGGER.info(\"Unable to fetch no of tabs.\");}\n\t\t\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to fetch no of tabs\");\n\t\t\tGemTestReporter.addTestStep(\"User fetches no of tabs\",\"Unable to fetch no of tabs\", STATUS.FAIL, takeSnapShot());"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles())"));
            ASTHelper.addStmt(block, new NameExpr("\tif(tabs.size()>0)\n\t\t\t{\n\t\t\t\tSettings.LOGGER.info(\"No. of tabs fetched successfully\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\telse{\n\t\t\tSettings.LOGGER.info(\"Unable to fetch no of tabs.\");\n\t\t\tAssert.fail(\"Unable to fetch no of tabs.\");\n\t\t\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to fetch no of tabs\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to fetch no of tabs\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }


    public static void setLinkMethodsSwitchToTab(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchToTab");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "tabName"));
        method.setParameters(parameters);
        Settings.LOGGER.info(parameters.toString());
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBoolean flag = false;\n\t\t\tWebDriver driver = DriverManager.getWebDriver();\n\t\t\tString currentHandle = driver.getWindowHandle()"));
            ASTHelper.addStmt(block, new NameExpr("\tfor(String handle : driver.getWindowHandles())\n\t\t\t\t{\n\t\t\t\t\tdriver.switchTo().window(handle);\n\t\t\t\t\tString title = driver.getTitle();\n\t\t\t\t\tif (title.contains(tabName)) {\n\t\t\t\t\t flag=true;\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t\tif(flag){\n\t\t\t\t\tdriver.switchTo().window(currentHandle).close();\n\t\t\t\t\tdriver.switchTo().window(currentHandle);\n\t\t\t\t\tGemTestReporter.addTestStep(\"User switches to tab\"+tabName,\"Tab switched successfully\", STATUS.PASS, takeSnapShot());\n\t\t\t\t\tSettings.LOGGER.info(\"Tab switched successfully\");\n\t\t\t\t}\n\t\t\t\telse\n\t\t\t\t{"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tGemTestReporter.addTestStep(\"User switches to tab\"+tabName,\"Unable to switch to tab\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to switch tab\");\n\t\t\t\t}\n\t\t\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to switch to tab\");\n\t\t\tGemTestReporter.addTestStep(\"User switches to tab\"+tabName,\"Unable to switch to Tab\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBoolean flag = false;\n\t\t\tWebDriver driver = SerenityWebdriverManager.inThisTestThread().getCurrentDriver();\n\t\t\tString currentHandle = driver.getWindowHandle()"));
            ASTHelper.addStmt(block, new NameExpr("\tfor(String handle : driver.getWindowHandles())\n\t\t\t\t{\n\t\t\t\t\tdriver.switchTo().window(handle);\n\t\t\t\t\tString title = driver.getTitle();\n\t\t\t\t\tif (title.contains(tabName)) {\n\t\t\t\t\t flag=true;\n\t\t\t\t\t}\n\t\t\t\t}\n\t\t\t\tif(flag){\n\t\t\t\t\tdriver.switchTo().window(currentHandle).close();\n\t\t\t\t\tdriver.switchTo().window(currentHandle);\n\t\t\t\t\tSettings.LOGGER.info(\"Tab switched successfully\");\n\t\t\t\t}\n\t\t\t\telse\n\t\t\t\t{"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to fetch no of tabs\");\n\t\t\t\t}\n\t\t\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to switch to tab\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to fetch no of tabs\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsCopy(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "copy");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function copy using keyboard actions (Ctrl+C)"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnew SerenityActions(DriverManager.getWebDriver()).keyDown(Keys.CONTROL).sendKeys(\"c\").keyUp(Keys.CONTROL).build().perform();\n\t\t\t\t\tGemTestReporter.addTestStep(\"Press Ctrl + C to copy the content\",\"Successfully copied\", STATUS.PASS, takeSnapShot());"));
            ASTHelper.addStmt(block, new NameExpr("}catch(" + "Exception e" + "){\n\t\t\t Settings.LOGGER.info(\"Failed to copy\");\n\t\t\t\t\tGemTestReporter.addTestStep(\"Press Ctrl + C to copy the content\",\"Failed to copy\", STATUS.FAIL, takeSnapShot());"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twithAction().keyDown(Keys.CONTROL).sendKeys(\"c\").keyUp(Keys.CONTROL).build().perform();\n\t\t\tSettings.LOGGER.info(\"Successfully copied\")"));
            ASTHelper.addStmt(block, new NameExpr("}catch(" + "Exception e" + "){\n\t\t\tSettings.LOGGER.info(\"Could not perform copy operation\");\n\t\t\tAssert.fail(\"Could not perform copy operation\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not perform copy operation\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }
    public static void setLinkMethodsPressEnter(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "pressEnter");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for switching tabs"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnew Actions(DriverManager.getWebDriver()).sendKeys(Keys.ENTER).build().perform()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"User presses enter\",\"Enter pressed successfully\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings.LOGGER.info(\"Enter Pressed successfully\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to press enter\");\n\t\t\tGemTestReporter.addTestStep(\"User presses enter\",\"Unable to press enter\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twithAction().sendKeys(Keys.ENTER).build().perform()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Enter pressed successfully\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to press enter\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to press enter\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsAddCookies(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "addCookies");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "key"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "value"));
        method.setParameters(parameters);
        Settings.LOGGER.info(parameters.toString());
        ASTHelper.addStmt(block, new NameExpr("//This function add new cookies"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("WebDriver driver = DriverManager.getWebDriver();"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tdriver.get(url);\n\t\t\tdriver.manage().addCookie(new Cookie(key, value));\n\t\t\tSettings.LOGGER.info(\"Successfully added cookies\");\n\t\t\t\t\tGemTestReporter.addTestStep(\"Add cookie\",\"successfully added cookies\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}catch(" + "Exception e" + "){\n\t\t\tSettings.LOGGER.info(\"Failed to add Cookies\");\n\t\t\t\t\tGemTestReporter.addTestStep(\"Add cookie\",\"Failed to add cookie\", STATUS.FAIL, takeSnapShot());"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("WebDriver driver = DriverManager.getWebDriver();"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tdriver.get(url);\n\t\t\tdriver.manage().addCookie(new Cookie(key, value));\n\t\t\tSettings.LOGGER.info(\"Successfully added cookies\")"));
            ASTHelper.addStmt(block, new NameExpr("}catch(" + "Exception e" + "){\n\t\t\tSettings.LOGGER.info(\"Failed to add Cookies\");\n\t\t\tAssert.fail(\"Failed to add Cookies\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Failed to add Cookies\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
            ASTHelper.addMember(c.getTypes().get(0), method);
        }

    public static void setLinkMethodsPaste(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "paste");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for switching tabs"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnew Actions(DriverManager.getWebDriver()).keyDown(Keys.CONTROL).sendKeys(\"v\").keyUp(Keys.CONTROL).build().perform()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"User performs paste action\",\"Paste action performed successfully\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings.LOGGER.info(\"Paste action performed successfully\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to perform paste action\");\n\t\t\tGemTestReporter.addTestStep(\"User performs paste action\",\"Unable to perform paste action\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twithAction().keyDown(Keys.CONTROL).sendKeys(\"v\").keyUp(Keys.CONTROL).build().perform()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Paste Operation performed successfully\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Could not perform paste operation\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not perform paste operation\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsGetAllCookies(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("Object",0), "getAllCookies");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        // add a statement do the method body
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//This function is to fetch all cookies"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("WebDriver driver = DriverManager.getWebDriver();\n\t\t\tObject browserCookies=null;\n\t\t\ttry{\n\t\t\tdriver.get(url);\n\t\t\tSet<Cookie> cookies = driver.manage().getCookies()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Get all cookies\",\"User is able to get all cookies\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings.LOGGER.info(\"User is able to get all cookies\");\n\t\t\tbrowserCookies = cookies"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to get all cookies\");\n\t\t\tGemTestReporter.addTestStep(\"Get all cookies\",\"User is unable to get all cookies\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t}return browserCookies"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("WebDriver driver = SerenityWebdriverManager.inThisTestThread().getCurrentDriver();\n\t\t\tObject browserCookies=null;\n\t\t\ttry{\n\t\t\tdriver.get(url);\n\t\t\tSet<Cookie> cookies = driver.manage().getCookies()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"User is able to get all cookies\");browserCookies = cookies"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to get all cookies\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"User is unable to get all cookies\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\treturn browserCookies"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsDeleteCookies(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "deleteCookies");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "key"));
        method.setParameters(parameters);
        Settings.LOGGER.info(parameters.toString());
        ASTHelper.addStmt(block, new NameExpr("//This function deletes cookies"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("WebDriver driver = DriverManager.getWebDriver()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tdriver.get(url);\n\t\t\tdriver.manage().deleteCookieNamed(key);\n\t\t\tSettings.LOGGER.info(\"Successfully deleted cookies\");\n\t\t\t\t\tGemTestReporter.addTestStep(\"delete cookie\",\"successfully deleted cookies\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}catch(" + "Exception e" + "){\n\t\t\tSettings.LOGGER.info(\"Failed to delete Cookies\");\n\t\t\t\t\tGemTestReporter.addTestStep(\"delete cookie\",\"Failed to delete cookie\", STATUS.FAIL, takeSnapShot());"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("WebDriver driver = DriverManager.getWebDriver()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tdriver.get(url);\n\t\t\tdriver.manage().deleteCookieNamed(key);\n\t\t\tSettings.LOGGER.info(\"Successfully deleted cookies\")"));
            ASTHelper.addStmt(block, new NameExpr("}catch(" + "Exception e" + "){\n\t\t\tSettings.LOGGER.info(\"Failed to delete Cookies\");\n\t\t\tAssert.fail(\"Failed to delete Cookies\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Failed to delete Cookies\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsDeleteAllCookies(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "deleteAllCookies");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        // add a statement do the method body
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//This function is to fetch all cookies"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("WebDriver driver = DriverManager.getWebDriver();\n\t\t\ttry{\n\t\t\tdriver.get(url);\n\t\t\tdriver.manage().deleteAllCookies()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Delete all cookies\",\"User is able to delete all cookies\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings.LOGGER.info(\"User is able to delete all cookies\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to delete all cookies\");\n\t\t\tGemTestReporter.addTestStep(\"Delete all cookies\",\"User is unable to delete all cookies\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("WebDriver driver = SerenityWebdriverManager.inThisTestThread().getCurrentDriver();\n\t\t\ttry{\n\t\t\tdriver.get(url);\n\t\t\tdriver.manage().deleteAllCookies()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"User is able to delete all cookies\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to delete all cookies\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"User is unable to delete all cookies\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("\t}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsGetCookie(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("Object",0), "getCookie");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        // add a statement do the method body
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "key"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//This function is to fetch all cookies"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("WebDriver driver = DriverManager.getWebDriver();\n\t\t\tObject browserCookie=null;\n\t\t\ttry{\n\t\t\tdriver.get(url);\n\t\t\tCookie cookie = driver.manage().getCookieNamed(key)"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Get cookie for: key \"+key,\"User is able to get cookie for: key \"+key, STATUS.PASS, takeSnapShot());\n\t\t\tSettings.LOGGER.info(\"User is able to get cookie for: key \"+key);\n\t\t\tbrowserCookie = cookie"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to get cookie for: key \"+key);\n\t\t\tGemTestReporter.addTestStep(\"Get cookie for: key \"+key,\"User is unable to get cookie for: key \"+key, STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t}return browserCookie"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("WebDriver driver = SerenityWebdriverManager.inThisTestThread().getCurrentDriver();\n\t\t\tObject browserCookie=null;\n\t\t\ttry{\n\t\t\tdriver.get(url);\n\t\t\tCookie cookie = driver.manage().getCookieNamed(key)"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"User is able to get cookie for: key \"+key);\n\t\t\tbrowserCookie = cookie"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to get cookie for: key \"+key);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"User is unable to get cookie for: key \"+key);\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\treturn browserCookie"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsSelectAll(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "selectAll");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for select all"));
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnew Actions(DriverManager.getWebDriver()).keyDown(Keys.CONTROL).sendKeys(\"a\").keyUp(Keys.CONTROL).build().perform()"));
                ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"User performs select all action\",\"Select all action performed successfully\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings.LOGGER.info(\"Select all action performed successfully\")"));
                ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to perform select all action\");\n\t\t\tGemTestReporter.addTestStep(\"User performs select all action\",\"Unable to perform select all action\", STATUS.FAIL, takeSnapShot())"));
                ASTHelper.addStmt(block, new NameExpr("}"));
            } else {
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twithAction().keyDown(Keys.CONTROL).sendKeys(\"a\").keyUp(Keys.CONTROL).build().perform()"));
                ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Select All Operation performed successfully\")"));
                ASTHelper.addStmt(block, new NameExpr("\t}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Could not perform select all operation\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not perform select all operation\");\n\t\t\tAssert.fail(e.getMessage())"));
                ASTHelper.addStmt(block, new NameExpr("}"));
            }
            ASTHelper.addMember(c.getTypes().get(0), method);
    }

}
