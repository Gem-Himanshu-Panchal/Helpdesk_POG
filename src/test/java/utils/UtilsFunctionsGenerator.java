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

    // Page Object Generator 1.0 (Contributors -> Ayush, Hem, Jasleen, Priyanshu, Rahul Tagra, Sajith and Siddanshi)
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
            } else {
                imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.WebElementFacade"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.PageObject"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("org.apache.commons.io.FileUtils"), false, false));
                imports.add(new ImportDeclaration(new NameExpr("java.io.File"), false, false));
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
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tGemTestReporter.addTestStep(\"Verify if file is downloaded\",\"Unable to verify if file is downloaded\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings.LOGGER.info(\"Unable to verify if file is downloaded\")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            ASTHelper.addStmt(block, new NameExpr("return false"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tFile dir = new File(System.getProperty(\"user.home\") + \"/Downloads\");\n\t\t\tFile[] files = dir.listFiles()"));
            ASTHelper.addStmt(block, new NameExpr("\tlong startTime = System.currentTimeMillis();\n\t\t\twhile ((System.currentTimeMillis() - startTime) < Long.parseLong(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))){\n\t\t\t\t" + "for (File file : files) {\n\t\t\t\t\tif (file.getName().equalsIgnoreCase(fileName)) {;\n\t\t\t\t\tSettings.LOGGER.info(\"File downloaded successfully\");\n\t\t\t\t\treturn true"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\tcatch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to verify if file is downloaded\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to verify is file is downloaded\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t\t}"));
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
            ASTHelper.addStmt(block, new NameExpr("\tjs.executeScript(\"window.focus();\");\n\t\t\tSettings.LOGGER.info(\"get window focus successfully\")"));
            ASTHelper.addStmt(block, new NameExpr("}catch(" + "Exception e" + "){\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to get window in focus\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get window in focus\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }
    public static void setLinkMethodsIsImage(CompilationUnit c) throws IOException {
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "isImage");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("WebElementFacade", 0), "element"));
        method.setParameters(parameters);
        Settings.LOGGER.info(parameters.toString());
        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t if((\"img\").equals(element.getTagName()));\n\t\t\t\t\tGemTestReporter.addTestStep(\"Enter text for alert\",\"Successfully entered text\", STATUS.PASS, takeSnapShot());"));
            ASTHelper.addStmt(block, new NameExpr("}catch(" + "Exception e" + "){\n\t\t\t Settings.LOGGER.info(\"Could not enter text for alert\");\n\t\t\t\t\tGemTestReporter.addTestStep(\"Could not enter text for alert\",\"Failed to enter\", STATUS.FAIL, takeSnapShot());"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t Assert.assertEquals(\"img\", element.getTagName())"));
            ASTHelper.addStmt(block, new NameExpr("}catch(" + "Exception e" + "){\n\t\tSettings.LOGGER.info(\"Could not enter text for alert\");\n\t\t\tAssert.fail(\"Could not enter text for alert\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not enter text for alert\");\n\t\t\tAssert.fail(e.getMessage())"));
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


}
