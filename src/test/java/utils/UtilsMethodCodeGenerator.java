package utils;

import japa.parser.ASTHelper;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.*;
import japa.parser.ast.expr.AnnotationExpr;
import japa.parser.ast.expr.MemberValuePair;
import japa.parser.ast.expr.NameExpr;
import japa.parser.ast.expr.NormalAnnotationExpr;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjectgenerator.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class UtilsMethodCodeGenerator {

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
        cu.setImports(UtilsMethodCodeGenerator.getAllImports(type));
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
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.logging.LogEntries"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.logging.LogType"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.logging.LogEntry"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("utils.UtilsMethodCodeGenerator"), false, false));
        }

        return imports;
    }

    // get attribute method
    public static void setLinkMethodsAttributeGetter(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "getAttributeFrom" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        Settings.LOGGER.info(parameters.toString());
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeValue"));
        method.setParameters(parameters);
        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitUntilElementAppear(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\ttext = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ", attributeValue)"));
            ASTHelper.addStmt(block, new NameExpr("\tif(!text.equals(\"\"))\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Get value of \" + attributeValue + \" attribute for \" + \"" + field.getName() + " field\",\"Successfully fetched \" + attributeValue + \" value for " + field.getName() + "\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Successfully fetched \" + attributeValue + \" value for " + field.getName() + "\");\n\t\t\t}\n\t\t\t else \n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Get value of \" + attributeValue + \" attribute for \" + \"" + field.getName() + " field\",\"Unable to get \" + attributeValue + \" value for " + field.getName() + " as attribute does not exist\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Unable to get \" + attributeValue + \" value for " + field.getName() + " as attribute does not exist\");\n\t\t\t}"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Get value of \" + attributeValue + \" attribute for \" + \"" + field.getName() + " field\",\"Unable to get \" + attributeValue + \" value for " + field.getName() + " as attribute does not exist\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to get \" + attributeValue + \" value for " + field.getName() + " as attribute does not exist\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            ASTHelper.addStmt(block, new NameExpr("return text"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String valueOfAttribute = \"\""));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))))"));
            ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "));\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets attribute as \"" + "+" + "attributeValue" + "+" + "\" for " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tvalueOfAttribute = $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(" + "attributeValue" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not get value for attribute \" + attributeValue)"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "return " + "valueOfAttribute"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
        Settings.LOGGER.info(String.valueOf(new NameExpr("return " + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ".getAttribute(" + "attributeValue" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
     }

    public static void setLinkMethodsIsSelected(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyElementSelectedFor" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        Settings.LOGGER.info(parameters.toString());
        method.setParameters(parameters);
        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))));\n\t\t\twebDriverWait.until(ExpectedConditions.elementSelectionStateToBe(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",true));\n\t\t\t" + "text = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ", \"checked\")"));
            ASTHelper.addStmt(block, new NameExpr("\tif(text.length()>0){\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " checkbox is selected\",\"Checkbox " + field.getName() + " selected Successfully\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Checkbox " + field.getName() + " selected Successfully\" );" + "\t}"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " checkbox is selected\",\"Unable to select " + field.getName() + " checkbox\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to select " + field.getName() + " checkbox\" );" + "\t}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))))"));
            ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"" + meaningFulName + " is not selected\", wait.until(ExpectedConditions.elementToBeSelected(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User verifies if checkbox is selected\")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\tAssert.fail(e.getMessage());\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Unable to verify element Selected or not\")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to select " + field.getName() + " checkbox\" );" + "\n\t}"));

        }
        Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
        Settings.LOGGER.info(String.valueOf(new NameExpr("return " + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ".getAttribute(" + "attributeValue" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
     }

    public static void setLinkMethodsIsNotSelected(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsNotSelected");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        Settings.LOGGER.info(parameters.toString());
        method.setParameters(parameters);
        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName() of Gemjar Framework to get the specific attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))));\n\t\t\twebDriverWait.until(ExpectedConditions.elementSelectionStateToBe(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",false));\n\t\t\t" + "text = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ", \"checked\")"));
            ASTHelper.addStmt(block, new NameExpr("\tif(text==null)\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " checkbox is not selected\",\"Checkbox " + field.getName() + " is deselected Successfully\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Checkbox " + field.getName() + " is deselected Successfully\" );" + "\t}\n\t\t\telse\n\t\t\t{\n\t\t\t\tGemTestReporter.addTestStep(\"Verify " + field.getName() + " checkbox is not selected\",\"Unable to deselect " + field.getName() + " checkbox\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"Unable to deselect " + field.getName() + " checkbox\" );" + "\n\t\t\t}"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " checkbox is not selected\",\"Unable to deselect " + field.getName() + " checkbox\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to deselect " + field.getName() + " checkbox\" );\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))))"));
            ASTHelper.addStmt(block, new NameExpr("\tassertFalse(\"" + meaningFulName + " is selected\", wait.until(ExpectedConditions.elementToBeSelected(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User verifies if checkbox is selected\")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Unable to verify element Selected or not\")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to select " + field.getName() + " checkbox\" );" + "\n\t}"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
        Settings.LOGGER.info(String.valueOf(new NameExpr("return " + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ".getAttribute(" + "attributeValue" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    //navigate back to the tab method
    public static void setLinkMethodsClickAndNavigateBack(CompilationUnit c, Field field) throws IOException {


        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clickOn" + meaningFulName + "AndNavigateBack");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\telementIsClickable" + meaningFulName + "();\n\t\t\tclick(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tnavigateBack()"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\tcatch(" + "Exception e" + "){\n\t\t\tGemTestReporter.addTestStep(\"Click on " + field.getName()+" and navigate back\",\"Unable to click on " + field.getName() + " and navigate back\", STATUS.FAIL, takeSnapShot());\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User is unable to click on " + field.getName() + " and navigate back\"" + ");"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User click on " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\telementIsClickable" + meaningFulName + "();\n\t\t\t" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "click" + "(" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "getDriver()" + "." + "navigate().back()"));
            Settings.LOGGER.info(String.valueOf(new NameExpr(Settings.LOCATOR_FILE_NAME + "." + "driver" + "." + "navigate().back()")));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User navigates back  to previous page\")"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    // get value assertion
    public static void setLinkMethodsValueVerification(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        // /////////////////////////////////////////////////////////////
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyValueFrom" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);

        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName(element,"value") of Gemjar Framework to get the value attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitUntilElementAppear(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\ttext = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",\"value\")"));
            ASTHelper.addStmt(block, new NameExpr("\tif(typeText.equals(text))\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify value of " + field.getName() + " matches \" +typeText,\"Value for " + field.getName() + " verified successfully\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Value for " + field.getName() + " verified successfully\" );" + "\t\n\t\t\t}\n\t\t\telse\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify value of " + field.getName() + " matches \" +typeText,\"Unable to verify value for " + field.getName() + ". Actual: \"+text+\" Expected: \"+typeText, STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to verify value for " + field.getName() + ". Actual: \"+text+\" Expected: \"+typeText );" + "\t\n\t\t\t}\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify value of " + field.getName() + " field\",\"Unable to get attribute value\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to get attribute value\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))))"));
            ASTHelper.addStmt(block, new NameExpr("WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "));\n\t\tassertTrue(\"Actual value: \" + $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(\"value\"), " + "StringUtils.equalsIgnoreCase(" + "typeText" + "," + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(\"value\")" + "))"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User verifies value: \"" + " + " + "typeText + \"for " + meaningFulName + "\")"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("return " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + ".getAttribute(\"value\")")));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not verify value for " + field.getName() + ")\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsAttributeValueVerification(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        // /////////////////////////////////////////////////////////////
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "verifyAttributeValueFor" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeName"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeValue"));
        method.setParameters(parameters);

        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName(element,"value") of Gemjar Framework to get the value attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitUntilElementAppear(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\ttext = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",attributeName)"));
            ASTHelper.addStmt(block, new NameExpr("\tif(attributeValue.equals(text))\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify if value of \"+attributeName+\" matches\" +attributeValue,\"Validation Successful\", STATUS.PASS, takeSnapShot());\n\t\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Value of \"+attributeName+\" matches \" +attributeValue)"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\telse\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify if value of \"+attributeName+\" matches\" +attributeValue,\"Validation Failed\", STATUS.FAIL, takeSnapShot());\n\t\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Value of \"+attributeName+\" does not matche \" +attributeValue)"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Get value of Value attribute for " + field.getName() + " field\",\"Unable to get attribute value\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to get attribute value\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            ASTHelper.addStmt(block, new NameExpr("return text"));

        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "assertTrue(" + "StringUtils.equalsIgnoreCase(" + "attributeValue" + "," + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(attributeName)" + "))"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "return " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + ".getAttribute(attributeName)"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("return " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + ".getAttribute(attributeName)")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    //isSelect Method verification
    public static void setLinkMethodForIsSelected(CompilationUnit c, Field field) throws IOException {
        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsSelected");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))));\n\t\t\twebDriverWait.until(ExpectedConditions.elementSelectionStateToBe(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",true));\n\t\t\t" + "getElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").isSelected()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\""+field.getName() + " element is selected successfully\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify " + field.getName() + " element is selected\",\"" + field.getName() + " is selected successfully\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify if " + field.getName() + " element is selected\",\"Unable to select " + field.getName() + " element\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to select " + field.getName() + " element\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))))"));
            ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"" + meaningFulName + " is not selected\", wait.until(ExpectedConditions.elementToBeSelected(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User verifies if checkbox is selected\")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Unable to verify element Selected or not\")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to select " + field.getName() + " checkbox\" );" + "\n\t\t}"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("assertTrue(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "." + "isSelected" + "(" + "))")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsTypeSetter(CompilationUnit c, Field field) throws IOException {
        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "typeTextInto" + meaningFulName);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        Settings.LOGGER.info(parameters.toString());
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.typetext() of Gemjar Framework to type the text into the input box

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitUntilElementAppear(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\t" + "typeText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Enter the text in " + field.getName() + " field\",\"Unable to Enter Text in " + field.getName() + " field\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to Enter Text in " + field.getName() + " field\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))))"));
            ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))"));
            ASTHelper.addStmt(block, new NameExpr("\ttypeInto(" + "element" + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not enter \" + typeText + \" into " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "Element" + "." + "type" + "(" + "typeText" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }


    public static void setLinkMethodsClear(CompilationUnit c, Field field) throws IOException {


        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clear" + meaningFulName);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.clearText() of Gemjar Framework to clear the text from the input field

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tverify" + meaningFulName + "IsEnabled();\n\t\t\tclearText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Clear text for " + field.getName() + " field\",\"Input for " + field.getName() + " field cleared successfully\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " field cleared successfully\");\n\t\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Clear text for " + field.getName() + " field\",\"Unable to clear text for " + field.getName() + " field\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Unable to clear text for " + field.getName() + " field\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\t}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))))"));
            ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "element" + "." + "clear" + "(" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User deletes the value for " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not clear " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }


    public static void setLinkMethodsTypeAndEnter(CompilationUnit c, Field field) throws IOException {
        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "typeTextAndEnterFor" + meaningFulName);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        Settings.LOGGER.info(parameters.toString());
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.typetext() of Gemjar Framework to type the text into the input box

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tverify" + meaningFulName + "IsDisplayed();\n\t\t\t" + "typeText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tActions action = new Actions(DriverManager.getWebDriver());"));
            ASTHelper.addStmt(block, new NameExpr("\taction.sendKeys(Keys.ENTER);"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value and presses enter\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Enter the text in " + field.getName() + " field and press enter\",\"Unable to Enter Text in " + field.getName() + " field and perform enter action\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to Enter Text in " + field.getName() + " field and perform enter action\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))))"));
            ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "$(element).typeAndEnter(" + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value and presses enter\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not enter \" + typeText + \" into " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "Element" + "." + "type" + "(" + "typeText" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsTypeAndTab(CompilationUnit c, Field field) throws IOException {
        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "typeTextAndTabFor" + meaningFulName);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        Settings.LOGGER.info(parameters.toString());
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.typetext() of Gemjar Framework to type the text into the input box

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tverify" + meaningFulName + "IsDisplayed();\n\t\t\t" + "typeText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tActions action = new Actions(DriverManager.getWebDriver());"));
            ASTHelper.addStmt(block, new NameExpr("\taction.sendKeys(Keys.TAB);"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value and presses Tab\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Enter the text in " + field.getName() + " field and presses tab\",\"Unable to Enter Text in " + field.getName() + " field and perform tab action\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to Enter Text in " + field.getName() + " field and perform tab action\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))))"));
            ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "typeInto(" + "element" + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value and presses Tab\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not enter \" + typeText + \" into " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "Element" + "." + "type" + "(" + "typeText" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsVerifyClear(CompilationUnit c, Field field) throws IOException {


        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        // /////////////////////////////////////////////////////////////
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyValueClearedFor" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName(element,"value") of Gemjar Framework to get the value attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitUntilElementAppear(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\ttext = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",\"value\")"));
            ASTHelper.addStmt(block, new NameExpr("\tif(text.equals(\"\"))\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify value cleared for " + field.getName() + " field\",\"Verified input value for " + field.getName() + " is successfully cleared\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Verified input value for " + field.getName() + " is successfully cleared\");\n\t\t\t}\n\t\t\telse\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify value cleared for " + field.getName() + " field\",\"Input value for " + field.getName() + " not cleared successfully \", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Input value for " + field.getName() + " not cleared successfully \");\n\t\t\t}\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify value gets cleared for " + field.getName() + " field\",\"Unable to clear value\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to clear value\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));

        } else {
            ASTHelper.addStmt(block, new NameExpr("assertTrue(\"Actual value: \" + $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(\"value\"), " + "StringUtils.equalsIgnoreCase(" + "" + "\"\", " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(\"value\")" + "))"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User verifies value is cleared for " + meaningFulName + "\")"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("return " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + ".getAttribute(\"value\")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsHoverOver(CompilationUnit c, Field field) throws IOException {


        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        // /////////////////////////////////////////////////////////////
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "hoverOver" + meaningFulName);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "labelText"));
        method.setParameters(parameters);
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName(element,"value") of Gemjar Framework to get the value attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tverify" + meaningFulName + "IsEnabled();\n\t\t\thoverOver(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "," + "labelText)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){\n\t\t\tGemTestReporter.addTestStep(\"Hover on \"+labelText,\" Hovering on \" + labelText + \" Failed\", STATUS.FAIL, takeSnapShot());" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\" Hovering on \" + labelText + \" Failed\")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("SerenityActions serenityActions = new SerenityActions(getDriver())"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tverify" + meaningFulName + "IsEnabled();\n\t\t\t" + "serenityActions" + "." + "moveToElement($(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))" + "." + "build().perform()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully hovers over\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("serenityActions" + "." + "moveToElement($(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))" + "." + "build().perform()")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsElementPresence(CompilationUnit c, Field field) throws IOException {


        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        // /////////////////////////////////////////////////////////////
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "Exists");
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName(element,"value") of Gemjar Framework to get the value attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitUntilElementAppear(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\tif(isExist(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))\n\t\t\t{"));
            ASTHelper.addStmt(block, new NameExpr("\t\tGemTestReporter.addTestStep(\"Verify element " + field.getName() + " is present on Screen\",\"" + field.getName() + " element is present on Screen\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " is present on Screen\");\n\t\t\t}\t\n\t\t\t else\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify element " + field.getName() + " is present on Screen\",\"" + field.getName() + " element is not present on Screen\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " element is not present on Screen\");\n\t\t\t}\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify element " + field.getName() + " is present on Screen\",\"" + field.getName() + " element is not present on Screen\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " element is not present on Screen\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tif(getDriver().findElements(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").size()>0){"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " is present on Screen\");\n\t\t\t}\t\n\t\t\t else{\n\t\t\t\t" + "Assert.fail(\"" + field.getName() + " element is not present on Screen\")"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " element is not present on Screen\");}\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Assert.fail(\"" + field.getName() + " element is not present on Screen\")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsVerifyCountChildElements(CompilationUnit c, Field field) throws IOException {


        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        // /////////////////////////////////////////////////////////////
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, Settings.USER_COUNT_CHILD_ELEMENTS + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "count"));
        method.setParameters(parameters);

        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName(element,"value") of Gemjar Framework to get the value attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("List<WebElement> allChildElements = new ArrayList<>()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebElement parentElement = DriverManager.getWebDriver().findElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ");\n\t\t\tallChildElements = parentElement.findElements(By.xpath(\"*\"));\n\t\t\tAssert.assertEquals(count, allChildElements.size())"));
            ASTHelper.addStmt(block, new NameExpr("\t\tGemTestReporter.addTestStep(\"Verify element " + meaningFulName + " has \" + count + \" child elements)\",\"" + meaningFulName + " has\" + allChildElements.size() + \"child elements\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + meaningFulName + " has" + " count " + "child elements\");\n\t\t\t}" + " catch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify element " + meaningFulName + " has\"" + " + count + " + "\"child elements)\",\"" + meaningFulName + " has\" + allChildElements.size() + \"child elements\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("List<WebElement> allChildElements = new ArrayList<>()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebElementFacade parentElement = $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ");\n\t\t\tallChildElements = parentElement.findElements(By.xpath(\"*\"));\n\t\t\tAssert.assertEquals(count, allChildElements.size())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " has\" + allChildElements.size() + \"child elements\");\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Assert.fail(\"Actual child count - \" + allChildElements.size())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsVerifyCountElements(CompilationUnit c, Field field) throws IOException {


        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        // /////////////////////////////////////////////////////////////
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, Settings.USER_COUNT_ELEMENTS + meaningFulName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "count"));
        method.setParameters(parameters);

        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName(element,"value") of Gemjar Framework to get the value attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("List<WebElement> allElements = new ArrayList<>()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tallElements = DriverManager.getWebDriver().findElements(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ");\n\t\t\tAssert.assertEquals(count, allElements.size())"));
            ASTHelper.addStmt(block, new NameExpr("\t\tGemTestReporter.addTestStep(\"Verify element " + meaningFulName + " count" + ")\",\"" + meaningFulName + " count is\" + allElements.size(), STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + meaningFulName + " count is\" + " + "allElements.size());\n\t\t\t}" + " catch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify element " + meaningFulName + " count is\"" + " + count,\"" + meaningFulName + " count is\" + allElements.size(), STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("List<WebElement> allElements = new ArrayList<>()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tallElements = getDriver().findElements(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ");\n\t\t\tAssert.assertEquals(count, allElements.size())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " count is \" + allElements.size());\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Assert.fail(\"Actual count - \" + allElements.size())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsScrollToView(CompilationUnit c, Field field) throws IOException {


        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        // /////////////////////////////////////////////////////////////
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollTo" + meaningFulName + "Element");
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName(element,"value") of Gemjar Framework to get the value attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {

            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tscrollIntoView(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ");\n\t\t\twaitUntilElementAppear(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")))"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Scroll to " + field.getName() + " element\",\"Successful able to scroll to " + field.getName() + " element\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Successful able to scroll to " + field.getName() + " element\");\n\t\t\t}\n\t\tcatch(Exception e){"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Scroll to " + field.getName() + " element\",\"Unable to scroll to " + field.getName() + " element\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to scroll to " + field.getName() + " element\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) getDriver();\n\t\t\tjs.executeScript(\"arguments[0].scrollIntoView();\", " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\" Successful able to scroll to " + field.getName() + " element \");}\t\n\t\t\tcatch(Exception e){"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Assert.fail(\" Unable to scroll to " + field.getName() + " element \")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsTypeGetter(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "getTextFrom" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getElementText() of Gemjar Framework to get the text of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tverify" + meaningFulName + "IsEnabled();\n\t\t\ttext = getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ");\n\t\t\tGemTestReporter.addTestStep(\"Get text of " + field.getName() + " element\",\"Successful able to fetch text of " + field.getName() + " element\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\tGemTestReporter.addTestStep(\"Get text of " + field.getName() + " element\",\"Unable to fetch text of " + field.getName() + " element\", STATUS.FAIL, takeSnapShot());\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"Unable to fetch text of " + field.getName() + ", User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\treturn text"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String text = \"\""));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))))"));
            ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "text = element" + "." + "getText" + "(" + ");\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets the text of " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not get text of " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "return " + "text"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("return " + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "." + "getText" + "(" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setMethodScrollClick(CompilationUnit c, Field field) throws IOException {
        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollClick" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tscrollIntoView(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "); \n\t\t\t" + "click(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "JavascriptExecutor" + " " + "js" + "=" + "(" + "JavascriptExecutor" + ")" + "driver"));
            ASTHelper.addStmt(block, new NameExpr("\tjs" + "." + "executeScript(\"arguments[0].scrollIntoView()\"" + "," + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))"));
            ASTHelper.addStmt(block, new NameExpr("\t$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "click" + "(" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User is able to scroll and click on the " + field.getName() + " element\"" + ")"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("js" + "." + "executeScript(\"arguments[0].scrollIntoView()\"" + "," + field.getName() + "Element" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsDropDown(CompilationUnit c, Field field) throws IOException {
        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "select" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))));\n\t\t\twebDriverWait.until(ExpectedConditions.elementSelectionStateToBe(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",true));\n\t\t\t" + "dropDown(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User is able to select \" + " + "typeText" + " +\" visible text in the dropdown\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\tGemTestReporter.addTestStep(\"User selects \" + " + "typeText" + " +\" visible text in the dropdown\",\"User is unable to select \" + " + "typeText" + " +\" visible text in the dropdown\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to select \" + \"typeText\"+\" visible text in the dropdown\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "selectByVisibleText" + "(" + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User is able to select \" + " + "typeText" + " +\" visible text in the dropdown\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not select \" + typeText)"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("new Select (" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "selectByVisibleText" + "(" + "typeText" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsDeselects(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "deselect" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "dropDown(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User is able to select \" + " + "typeText" + " +\" visible text in the dropdown\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\tGemTestReporter.addTestStep(\"User deselects \" + " + "typeText" + " +\" visible text in the dropdown\",\"User is unable to deselect \" + " + "typeText" + " +\" visible text in the dropdown\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to deselect \" + \"typeText\"+\" visible text in the dropdown\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "deselectByVisibleText" + "(" + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User is able to deselect \" + " + "typeText" + " +\" visible text in the dropdown\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not deselect \" + typeText)"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("new Select (" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "deselectByVisibleText" + "(" + "typeText" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setMethodClickable(CompilationUnit c, Field field) throws IOException {
        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "elementIsClickable" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds((long) 10))"));
            ASTHelper.addStmt(block, new NameExpr("\twait.until(ExpectedConditions.elementToBeClickable(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Check if " + field.getName() + " is clickable\",\"" + field.getName() + " is clickable\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " is clickable\");\n\t\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Check if " + field.getName() + " is clickable\",\"" + field.getName() + " is not clickable\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " is not clickable\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnew WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")))).until(ExpectedConditions.elementToBeClickable(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))"));
            ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"" + meaningFulName + " is not clickable\", " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "isClickable" + "(" + "))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User verifies " + field.getName() + " is clickable\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\tAssert.fail(e.getMessage());\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Unable to click\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("assertTrue(" + field.getName() + "Element" + "." + field.getName() + "." + "isClickable" + "(" + "))")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodForEnabled(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsEnabled");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getElement().isEnabled() of Gemjar Framework to check whether the element is enabled

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait webDriverWait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))));\n\t\t\twebDriverWait.until(ExpectedConditions.presenceOfElementLocated(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "));\n\t\t\tgetElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").isEnabled()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify " + field.getName() + " field is enabled\",\"" + field.getName() + " field is enabled\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " field is enabled\");\n\t\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " field is enabled\",\"" + field.getName() + " field is not enabled\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " field is not enabled\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\t}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tWebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\"))))"));
            ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))"));
            ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"" + meaningFulName + " is not enabled\", " + "element" + "." + "isEnabled" + "(" + "))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User verifies the given " + field.getName() + " element is enabled\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\tAssert.fail(e.getMessage());\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Element is not enabled\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }


    public static void setLinkMethodForVisibility(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "IsDisplayed");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitUntilElementAppear(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\tgetElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").isDisplayed()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify " + field.getName() + " field is visible\",\"" + field.getName() + " field is visible\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " field is visible\");\n\t\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " field is visible\",\"" + field.getName() + " field is not visible\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " field is not visible\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e);}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("assertTrue(\"" + meaningFulName + " is not visible\", " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "isDisplayed" + "(" + "))"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User verifies " + field.getName() + " element is displayed\"" + ")"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }


    public static void setLinkMethodForText(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "Text");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tverify" + meaningFulName + "IsDisplayed();\n\t\t\tif(getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").equals(typeText))\n\t\t\t{"));
            ASTHelper.addStmt(block, new NameExpr("\t\tGemTestReporter.addTestStep(\"Verify text of " + field.getName() + " field is equal to \" + typeText,\"Text of " + field.getName() + " field is equal to \" + typeText, STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Text of " + field.getName() + " field is equal to \" + typeText);\n\t\t\t}\t\n\t\t\telse\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify text of " + field.getName() + " field is equal to \" + typeText,\"Text of " + field.getName() + " field is not equal. Expected: \"+typeText+\" Actual: \"+getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "), STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Text of " + field.getName() + " field is not equal. Expected: \"+typeText+\" Actual: \"+getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "));\n\t\t\t}\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify text of " + field.getName() + " field is equal to \" + typeText,\"Text of " + field.getName() + " field is not equal. Expected: \"+typeText+\" Actual: \"+getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "), STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Text of " + field.getName() + " field is not equal. Expected: \"+typeText+\" Actual: \"+getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "));\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "assertTrue(\"Actual text: \" + $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getText" + "(" + "), " + "StringUtils.equalsIgnoreCase(" + "typeText" + "," + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getText" + "(" + ")))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets the text of " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("assertTrue(" + "StringUtils.equalsIgnoreCase(" + "typeText" + "," + field.getName() + "Element" + "." + "getText" + "(" + ")))")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodForContains(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName + "ContainsText");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tverify" + meaningFulName + "IsDisplayed();\n\t\t\tif(getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").contains(typeText))\n\t\t\t{"));
            ASTHelper.addStmt(block, new NameExpr("\t\tGemTestReporter.addTestStep(\"Verify " + field.getName() + " field contains \" + typeText,\"Text of " + field.getName() + " field contains \" + typeText, STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Text of " + field.getName() + " field contains\" + typeText);\n\t\t\t}\n\t\t\telse\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " field contains \" + typeText,\"Text\" + typeText + \"is not present in " + field.getName() + " field. Expected: \"+typeText+\" Actual: \"+ getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "), STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Text of " + field.getName() + " field does not contain\" + typeText + \". Expected: \"+typeText+\" Actual: \"+getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "));\n\t\t\t}\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " field contains \" + typeText,\"Text of " + field.getName() + " field does not contain\" + typeText + \". Expected: \"+typeText+\" Actual: \"+getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "), STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Text of " + field.getName() + " field does not contain\" + typeText + \". Expected: \"+typeText+\" Actual: \"+getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "));\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "assertTrue(\"Actual text: \" + $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getText" + "(" + "), " + "StringUtils.containsIgnoreCase($(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getText" + "(" + "), " + "typeText))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User verifies " + field.getName() + " contains \"" + " + typeText)"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("assertTrue(" + "StringUtils.containsIgnoreCase(" + field.getName() + "." + "getText" + "(" + "))), " + "typeText")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodForAttributeContains(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyAttributeContainsValueFor" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attribute"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tverify" + meaningFulName + "IsDisplayed();\n\t\t\tif(getElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").getAttribute(attribute).contains(typeText))\n\t\t\t{"));
            ASTHelper.addStmt(block, new NameExpr("\t\tGemTestReporter.addTestStep(\"Verify attribute \" + attribute + \" of " + field.getName() + " field contains \" + typeText,\"Attribute \" + attribute + \" of " + field.getName() + " field contains \" + typeText, STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Attribute \" + attribute + \" of " + field.getName() + " field contains\" + typeText);\n\t\t\t}\n\t\t\telse\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify attribute \" + attribute + \" of " + field.getName() + " field contains \" + typeText, typeText + \"is not present in " + field.getName() + "\" + attribute + \". Expected: \"+typeText+\" Actual: \"+ getElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").getAttribute(attribute), STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Attribute \" + attribute + \" of " + field.getName() + " field does not contain\" + typeText + \". Expected: \"+typeText+\" Actual: \"+getElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").getAttribute(attribute));\n\t\t\t}\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify attribute \" + attribute + \" of " + field.getName() + " field contains \" + typeText, typeText + \"is not present in " + field.getName() + "\" + attribute + \". Expected: \"+typeText+\" Actual: \"+ getElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "), STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info( typeText + \"is not present in " + field.getName() + "\" + attribute + \". Expected: \"+typeText+\" Actual: \"+ getElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "));\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "assertTrue(\"Actual value: \" + $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(attribute), " + "StringUtils.contains(" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(attribute)" + ", typeText" + "))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User verifies " + field.getName() + " contains \"" + " + typeText)"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("try{\n\t\t\t" + "assertTrue(\"Actual value: \" + $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(attribute), " + "StringUtils.contains(" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(attribute)" + ", typeText" + "))")));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static String readProperties(String property) throws IOException { // Function to read Data from Properties File
        FileReader read = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
        Properties credential = new Properties();
        credential.load(read);
        return credential.getProperty(property);
    }

    public static void setLinkMethodsOpenHomePage(CompilationUnit c) throws IOException {


        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, Settings.USER_HOME_PAGE);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("if (GemJarUtils.getGemJarConfigData(\"chromeOptions\") != null && !GemJarUtils.getGemJarConfigData(\"chromeOptions\").isEmpty())" +
                    " {\n\t\t\tChromeOptions options = new ChromeOptions()"));
            ASTHelper.addStmt(block, new NameExpr("\toptions.addArguments(\"--remote-allow-origins=*\")"));
            ASTHelper.addStmt(block, new NameExpr("\toptions.addArguments(\"--\" + GemJarUtils.getGemJarConfigData(\"chromeOptions\"))"));
            ASTHelper.addStmt(block, new NameExpr("\tDriverManager.initializeChrome(options)"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\telse{\n\t\t\tif(GemJarUtils.getGemJarConfigData(\"browserName\").equals(\"firefox\")){\n\t\t\t\tWebDriverManager.firefoxdriver().clearDriverCache().setup()"));
            ASTHelper.addStmt(block, new NameExpr("\t\tDriverManager.setWebDriver(new FirefoxDriver())"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\telse{\n\t\t\tChromeOptions options = new ChromeOptions()"));
            ASTHelper.addStmt(block, new NameExpr("\toptions.addArguments(\"--remote-allow-origins=*\")"));
            ASTHelper.addStmt(block, new NameExpr("\tDriverManager.initializeChrome(options)"));
            ASTHelper.addStmt(block, new NameExpr("\t\t}\n\t}\n\t\tmaximizeBrowser()"));
            ASTHelper.addStmt(block, new NameExpr("launchUrl(GemJarUtils.getGemJarConfigData(\"launchUrl\"))"));
            ASTHelper.addStmt(block, new NameExpr("setImplicitTimeOut(Long.parseLong(GemJarGlobalVar.implicitTime))"));
            ASTHelper.addStmt(block, new NameExpr("setPageLoadTimeOut(Long.parseLong(GemJarGlobalVar.pageTimeout))"));
            ASTHelper.addStmt(block, new NameExpr("setScriptTimeOut(Long.parseLong(GemJarGlobalVar.scriptTimeout))"));
//            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDriverManager.setUpBrowser()"));
//            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
//            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver(" + ")" + "." + "get" + "(" + "Settings.URL" + ")"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User launches the application\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not launch the application with URL: \" + Settings.URL);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not launch the application with URL: \" + Settings.URL);\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));

            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkMethodsClick(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clickOn" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\telementIsClickable" + meaningFulName + "();\n\t\t\tclick(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User clicks on " + field.getName() + " successfully\"" + ");\n\t\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\tGemTestReporter.addTestStep(\"Click on " + field.getName() + "\",\"Unable to click on " + field.getName() + "\", STATUS.FAIL, takeSnapShot());\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User is unable to click on " + field.getName() + "\"" + ");\n\t\t\t}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "elementIsClickable" + meaningFulName + "()"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "click" + "(" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User click on the " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not click " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }


    public static void setLinkMethodsDoubleClick(CompilationUnit c, Field field) throws IOException {


        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "doubleClickOn" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        //Double click on element
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tdoubleClick(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "elementIsClickable" + meaningFulName + "()"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Actions" + " " + "action" + "=" + "new" + " " + "Actions(" + "driver" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\taction" + "." + "doubleClick(" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + ")" + "." + "perform" + "(" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User double click on the element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodForUpload(CompilationUnit c, Field field) throws IOException {
        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "uploadFileTo" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "fileName"));
        method.setParameters(parameters);
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\data\\";
        Settings.LOGGER.info(parameters.toString());
        //path of file
        filePath = filePath.replace("\\", "\\\\");
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "typeText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ", \"" + filePath + " \" + fileName" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User uploads the " + '"' + "+" + "fileName" + "+" + '"' + " file\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Upload the\" + fileName + \"file\",\"Unable to upload\" + fileName + \"file\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "sendKeys" + "(" + '"' + filePath + '"' + "+" + "fileName" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User uploads the file\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "Element" + "." + "sendKeys" + "(" + '"' + filePath + '"' + "+" + "fileName" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    /**
     * formats a string to camelCase
     *
     * @param tempVarName
     * @return
     */
    private static String getMeaningFullName(String tempVarName, boolean isMethodCall) {
        String res = tempVarName;
        res = res.replaceAll("\\s", "");
        res = res.replaceAll("&amp", "");
        res = res.replaceAll("#", "");

        StringBuilder meaningfulName = new StringBuilder();
        for (String name : res.split("_")) {
            meaningfulName.append(StringUtils.capitalize(name));
        }

        return meaningfulName.toString();
    }

    /**
     * makes the source code of each state persistent
     *
     * @throws IOException
     */
    public static void savePageObjectsOnFileSystem(String directoryName, String className, CompilationUnit c, boolean stepGeneration) throws IOException {

        String data = "";
        String fileNameToCreate = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + directoryName + className;
        // String fileNameToCreate = System.getProperty("user.dir") +poName;
        File f = new File(fileNameToCreate + ".java");

        if (BooleanUtils.isTrue(stepGeneration)) {
            data = StringUtils.replace(c.toString(), "xpath = ", "").toString();
        } else {
            data = c.toString();
        }
        FileUtils.writeStringToFile(f, data);
    }

    public static void setLinkMethodsNavigateBack(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "backwardNavigation");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnavigateBack()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify user is able to navigate backward\",\"Unable to navigate backward\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to navigate backward\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().back()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully navigated back\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not navigate back\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not navigate back\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().navigate().back())")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsSwitchToActiveElement(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchActiveElement");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tswitchToActiveElement()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify user is able to switch to element\",\"User is able to switch to element\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to switch to element\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify user is able to switch to element\",\"Unable to switch to element\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to switch to element\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().activeElement()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to active element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not switch to active element\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to active element\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().activeElement()")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsSwitchToParentFrame(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchParentFrame");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tswitchToParentFrame()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify user is able to switch to Parent Frame\",\"User is able to switch to Parent Frame\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to switch to Parent Frame\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify user is able to switch to element\",\"Unable to switch to Parent Frame\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to switch to Parent Frame\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().parentFrame()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to parent frame\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not switch to parent frame\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to parent frame\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().parentFrame()")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsSwitchToFrame(CompilationUnit c, boolean argumentType) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchFrame");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        if (argumentType) {
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "nameOrId"));
            if (readProperties("Framework").contains("GEMJAR")) {
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tswitchToFrame(nameOrId)"));
                ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify user is able to switch to Frame\",\"User is able to switch to Frame\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to switch to Frame\")"));
                ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify user is able to switch to Frame\",\"Unable to switch to Frame\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to switch to Frame\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
                ASTHelper.addStmt(block, new NameExpr("}"));
            } else {
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().frame(nameOrId)"));
                ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to frame\" + nameOrId" + ")"));
                ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not switch to frame: \" + nameOrId);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to frame: \" + nameOrId);\n\t\t\tAssert.fail(e.getMessage())"));
                ASTHelper.addStmt(block, new NameExpr("}"));
                Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().frame(nameOrId)")));
            }
        } else {
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "index"));
            if (readProperties("Framework").contains("GEMJAR")) {
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tswitchToFrame(index)"));
                ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify user is able to switch to Frame\",\"User is able to switch to Frame\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to switch to Frame\")"));
                ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify user is able to switch to Frame\",\"Unable to switch to Frame\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to switch to Frame\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
                ASTHelper.addStmt(block, new NameExpr("}"));
            } else {
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().frame(index)"));
                ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to frame\" + index" + ")"));
                ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not switch to frame: \" + index);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to frame: \" + index);\n\t\t\tAssert.fail(e.getMessage())"));
                ASTHelper.addStmt(block, new NameExpr("}"));
                Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().frame(index)")));
            }
        }
        method.setParameters(parameters);
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsSwitchWindow(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchWindow");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "nameOrHandle"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\tDriverManager.getWebDriver().switchTo().window(nameOrHandle)"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify user is able to switch to Window\",\"User is able to switch to Window\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to switch to Window\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify user is able to switch to Window\",\"Unable to switch to Window\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to switch to Window\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().window(nameOrHandle)"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to window\" + nameOrHandle" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not switch to window: \" + nameOrHandle);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to window: \" + nameOrHandle);\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().window(nameOrHandle)")));
        }
        method.setParameters(parameters);
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsWait(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "wait");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "duration"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDriverManager.getWebDriver().wait(duration)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitABit(duration)"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User waits for \" + duration + \" seconds\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not wait for : \" + duration + \" seconds\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not wait for : \" + duration + \" seconds\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("waitABit(duration)")));
        }
        method.setParameters(parameters);
        ASTHelper.addMember(c.getTypes().get(0), method);

    }

    public static void setLinkMethodsClickAndHold(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clicksAndHold");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "locatorName"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy locator = getLocator(locatorName);\n\t\t\tnew Actions(DriverManager.getWebDriver()).moveToElement((WebElement) locator).clickAndHold().build().perform()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify user is able to click and hold \"+locatorName+\" element\",\"User is able to click and hold \"+locatorName+\" element\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to click and hold \"+locatorName+\" element\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify user is able to click and hold \"+locatorName+\" element\",\"Unable to click and hold \"+locatorName+\" element\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to click and hold \"+locatorName+\" element\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy locator = getLocator(locatorName);\n\t\t\tnew WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")))).until(ExpectedConditions.elementToBeClickable(locator))"));
            ASTHelper.addStmt(block, new NameExpr("\tnew SerenityActions(getDriver()).moveToElement($(locator)).clickAndHold().build().perform()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully clicks and holds \" + locator + \" element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not click and hold: \" + locatorName);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not click and hold: \" + locatorName);\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("new SerenityActions(getDriver()).moveToElement(locator).clickAndHold().build().perform()")));
        }
        method.setParameters(parameters);
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setMethodRightClick(CompilationUnit c, Field field) throws IOException {
        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "rightClickOn" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\trightClick(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User right clicks on " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("SerenityActions serenityActions = new SerenityActions(getDriver())"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "serenityActions" + "." + "moveToElement($(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))" + "." + "contextClick().build().perform()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User right clicks on " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsSwitchToDefaultContent(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchDefaultContent");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tswitchToDefaultContent()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify user is able to switch to default content\",\"User is able to switch to default content\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to switch to default content\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify user is able to switch to default content\",\"Unable to switch to default content\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to switch to default content\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().defaultContent()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to default content\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not switch to default element\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not switch to default element\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().defaultContent()")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsQuit(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "tearDown");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDriverManager.quitDriver()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify user is able to close driver\",\"User is able to close driver successfully\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to close driver successfully\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify user is able to close driver\",\"Unable to close driver\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to close driver\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "quit()")));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().quit()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully closed driver\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not close driver\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not close driver\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "quit()")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsNavigateForward(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "forwardNavigation");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnavigateForward()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify user is able to navigate forward\",\"Unable to navigate forward\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to navigate forward\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().forward()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully navigated Forward\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not navigate forward\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not navigate forward\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsNavigateTo(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "navigateTo");
        // add a body to the method
        List<Parameter> parameters = new LinkedList<>();
        Settings.LOGGER.info(parameters.toString());
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        method.setParameters(parameters);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDriverManager.getWebDriver().navigate().to(url);\n\t\t\tGemTestReporter.addTestStep(\"Verify user is able to navigate to \"+url,\"User is able to navigate to \"+url+\" successfully\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to navigate to \"+url+\" successfully\")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to navigate to \"+url,\"User is unable to navigate to \"+url, STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to navigate to \"+url);\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().to(url)"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully navigated to URL: \" + url" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not navigate to URL: \" + url);\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not navigate to URL: \" + url);\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().navigate().to(url)")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsVerifyUrl(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyURL");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "expectedURL"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString text = getCurrentURL()"));
            ASTHelper.addStmt(block, new NameExpr("\tif(text.equals(expectedURL)) {\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify if current URL matches <a href ='\" +expectedURL+\"'>\"+expectedURL+\"</a>\",\"Validation Successful\", STATUS.PASS, takeSnapShot());\n\t\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"URL verified successfully\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\telse {\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify if current URL matches <a href ='\" +expectedURL+\"'>\"+expectedURL+\"</a>\",\"Validation Failed\", STATUS.FAIL, takeSnapShot());\n\t\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to verify url\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){" + "GemTestReporter.addTestStep(\"Verify if current URL matches <a href ='\" +expectedURL+\"'>\"+expectedURL+\"</a>\",\"Validation Failed\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to verify url\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString actualURL = getURL()"));
            ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"Actual URL: \" + getURL(), actualURL.equals(expectedURL))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"URL verified successfully\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Actual URL: \" + getURL());\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Actual URL: \" + getURL());\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsGetUrl(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "getURL");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String url = getCurrentURL();\n\t\ttry{\n\t\t\tGemTestReporter.addTestStep(\"Verify user is able to fetch url\",\"User is able to fetch url successfully\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to fetch url\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "GemTestReporter.addTestStep(\"Verify user is able to fetch url\",\"User is unable to fetch url\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to fetch url\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn url"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String currentUrl = \"\""));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tcurrentUrl = getDriver().getCurrentUrl()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully gets current URL\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to get current URL\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get current URL\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn currentUrl"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsGetBrowserSize(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("Object", 0), "browserSize");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("Integer sizeOfBrowser = null"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\tObject size = getBrowserSize()"));
            ASTHelper.addStmt(block, new NameExpr("if(size!=null){\n\t\t\tGemTestReporter.addTestStep(\"Get Browser Size \",\"Browser Size fetched successfully.\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Browser Size fetched successfully.\")"));
            ASTHelper.addStmt(block, new NameExpr("\tsizeOfBrowser = Integer.valueOf(size.toString());"+ "\n\t\t\t}\n\t\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Get Browser Size \",\"Unable to fetch Browser Size.\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to fetch browser size\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
            ASTHelper.addStmt(block, new NameExpr("return sizeOfBrowser"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("Integer sizeOfBrowser = null"));
            ASTHelper.addStmt(block, new NameExpr("try { \n\t\t\tObject size = getDriver().manage().window().getSize()"));
            ASTHelper.addStmt(block, new NameExpr("\tif(size!=null)"));
            ASTHelper.addStmt(block, new NameExpr("\tsizeOfBrowser = Integer.valueOf(size.toString())"));
            ASTHelper.addStmt(block, new NameExpr("\n\t\t} catch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not get size of browser\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not get size of browser\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
            ASTHelper.addStmt(block, new NameExpr("return sizeOfBrowser"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsGetBrowserLocation(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("Object", 0), "browserPosition");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("Integer positionOfBrowser = null"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\tObject position = getBrowserLocation()"));
            ASTHelper.addStmt(block, new NameExpr("if(position!=null){\n\t\t\tGemTestReporter.addTestStep(\"Get Browser Position \",\"Browser Position fetched successfully.\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Browser Position fetched successfully.\")"));
            ASTHelper.addStmt(block, new NameExpr("\tpositionOfBrowser = Integer.valueOf(position.toString());"+ "\n\t\t\t}\n\t\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Get Browser Position \",\"Unable to fetch Browser position.\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to fetch browser position\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
            ASTHelper.addStmt(block, new NameExpr("return positionOfBrowser"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("Integer positionOfBrowser = null"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\tObject position = getDriver().manage().window().getPosition()"));
            ASTHelper.addStmt(block, new NameExpr("if(position!=null){\t"));
            ASTHelper.addStmt(block, new NameExpr("\tpositionOfBrowser = Integer.valueOf(position.toString());}"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to get browser position\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get browser position\");\n\t\t\tAssert.fail(e.getMessage())"));

            ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn positionOfBrowser"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsGetWindowHandle(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("Object", 0), "windowHandle");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String windowHandle =null;\n\t\ttry{\n\t\twindowHandle = getWindowHandle()"));
            ASTHelper.addStmt(block, new NameExpr("if(windowHandle!=null){\n\t\t\tGemTestReporter.addTestStep(\"Get Window Handle\",\"Window Handle fetched successfully.\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Window Handle fetched successfully.\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Get Window Handle\",\"Unable to fetch Window Handle\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to fetch Window Handle\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            ASTHelper.addStmt(block, new NameExpr("return windowHandle"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String windowHandle = null"));
            ASTHelper.addStmt(block, new NameExpr("try { \n\t\t\twindowHandle = getDriver().getWindowHandle()"));
            ASTHelper.addStmt(block, new NameExpr("\tif(windowHandle==null){\n\t\t\tthrow new NullPointerException()"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to get window handle\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get window handle\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
            ASTHelper.addStmt(block, new NameExpr("return windowHandle"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsGetWindowHandles(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "windowHandles");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String windowHandles =null;\n\t\ttry{\n\t\twindowHandles = getWindowHandles().toString()"));
            ASTHelper.addStmt(block, new NameExpr("if(windowHandles!=null){\n\t\t\tGemTestReporter.addTestStep(\"Get Window Handles\",\"Window Handles fetched successfully.\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Window Handles fetched successfully.\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Get Window Handles\",\"Unable to fetch Window Handles\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to fetch Window Handles\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            ASTHelper.addStmt(block, new NameExpr("return windowHandles"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String windowHandles = null"));
            ASTHelper.addStmt(block, new NameExpr("try {\n\t\t\twindowHandles = getDriver().getWindowHandles().toString()"));
            ASTHelper.addStmt(block, new NameExpr("\tif(windowHandles==null){\n\t\t\tthrow new NullPointerException()"));
            ASTHelper.addStmt(block, new NameExpr("\t} \n\t\t}\n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to get window handles\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get window handles\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
            ASTHelper.addStmt(block, new NameExpr("return windowHandles"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsGetPageSource(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "pageSource");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String pageSource = getPageSource();\n\t\ttry{"));
            ASTHelper.addStmt(block, new NameExpr("if(pageSource!=null){\n\t\t\tGemTestReporter.addTestStep(\"Get Page Source\",\"Page Source fetched successfully.\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Page Source fetched successfully.\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Get Page Source\",\"Unable to fetch Page Source\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to fetch Page Source\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            ASTHelper.addStmt(block, new NameExpr("return pageSource"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String pageSource = null"));
            ASTHelper.addStmt(block, new NameExpr("try { \n\t\t\tpageSource = getDriver().getPageSource()"));
            ASTHelper.addStmt(block, new NameExpr("\tif(pageSource==null){\n\t\t\tthrow new NullPointerException()"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to get page source\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get page source\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
            ASTHelper.addStmt(block, new NameExpr("return pageSource"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsCloseCurrentTab(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "closeTab");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tcloseCurrentTab()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify user is able to close current tab\",\"User successfully closes current tab.\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User successfully closes current tab.\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to close current tab\",\"User is unable to close current tab.\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to close current tab.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().close()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to close current tab\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to close current tab\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodSwitchToAlert(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "alertSwitch");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tswitchToAlert()"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to switch to alert\",\"User is unable to switch to alert.\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to switch to alert.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to switch to Alert\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to switch to Alert\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodAcceptAlert(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "alertAccept");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\tacceptAlert()"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to accept alert\",\"User is unable to accept alert.\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to accept alert.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert().accept()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to accept Alert\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to accept Alert\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodDismissAlert(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "alertDismiss");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\tdismissAlert()"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to dismiss alert\",\"User is unable to dismiss alert.\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to dismiss alert.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert().dismiss()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to dismiss Alert\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to dismiss Alert\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodAlertInput(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "inputForAlert");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "input"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\talertInput(input)"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to input for an alert\",\"User is unable to enter input for an alert.\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to enter input for an alert.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert().sendKeys(input)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to enter \" + input + \"into Alert\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to enter \" + input + \"into Alert\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodScrollToTop(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollUp");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tscrollToTop();\n\t\t\tGemTestReporter.addTestStep(\"Verify user is able to scroll up\",\"User is able to scroll up successfully.\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to scroll up successfully.\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to scroll up\",\"Unable to scroll up\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to scroll up\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) getDriver();\n\t\t\tjs.executeScript(\"window.scrollTo(0, -document.body.scrollHeight)\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to scroll to top of page\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to scroll to top of page\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodScrollToBottom(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollDown");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tscrollToBottom();\n\t\t\tGemTestReporter.addTestStep(\"Verify user is able to scroll down\",\"User is able to scroll down successfully.\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to scroll down successfully.\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to scroll down\",\"Unable to scroll down\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to scroll down\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t JavascriptExecutor js = (JavascriptExecutor) getDriver();\n\t\t\tjs.executeScript(\"window.scrollBy(0,document.body.scrollHeight)\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to scroll to bottom of page\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to scroll to bottom of page\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodPageScroll(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollPage");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "y"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tpageScroll(x,y);\n\t\t\tGemTestReporter.addTestStep(\"Verify user is able to scroll page to x: \"+x+\" and y: \"+y+\" coordinates\",\"User is able to scroll page to x: \"+x+\" and y: \"+y+\" coordinates\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to scroll page to x: \"+x+\" and y: \"+y+\" coordinates\")"));
            ASTHelper.addStmt(block, new NameExpr("}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to scroll page to x: \"+x+\" and y: \"+y+\" coordinates\",\"Unable to scroll page to x: \"+x+\" and y: \"+y+\" coordinates\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to scroll page to x: \"+x+\" and y: \"+y+\" coordinates\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) getDriver();\n\t\t\tjs.executeScript(\"window.scrollBy(\"+x+\",\"+y+\")\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to scroll page\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to scroll page\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodScrollElementToPosition(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "elementScroll");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "y"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tscrollAnElementToSpecificPosition(x,y);\n\t\t\tGemTestReporter.addTestStep(\"Verify user is able to scroll element to x: \"+x+\" and y: \"+y+\" coordinates\",\"User is able to scroll element to x: \"+x+\" and y: \"+y+\" coordinates\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to scroll element to x: \"+x+\" and y: \"+y+\" coordinates\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to scroll element to x: \"+x+\" and y: \"+y+\" coordinates\",\"Unable to scroll element to x: \"+x+\" and y: \"+y+\" coordinates\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to scroll element to x: \"+x+\" and y: \"+y+\" coordinates\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) getDriver();\n\t\t\tjs.executeScript(\"window.scrollBy(\"+x+\",\"+y+\")\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodNavigateToUrl(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "urlNavigation");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnavigateToUrl(url)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().to(url)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodRefresh(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "refreshPage");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\trefresh()"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to refresh page\",\"Unable to refresh page\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to refresh page\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().refresh()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to refresh page\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to refresh page\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }
    public static void setLinkMethodGetLogs(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("LogEntries", 0), "getLogs");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("LogEntries logs = null"));
            ASTHelper.addStmt(block, new NameExpr("try {\n\t\t\twait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\tlogs = DriverManager.getWebDriver().manage().logs().get(LogType.BROWSER)"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Get message in console log \", \"Log messages are present in console.- \", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Log messages are present in console.\")"));
            ASTHelper.addStmt(block, new NameExpr("} catch (Exception e) { \n\t\t\tGemTestReporter.addTestStep(\"Verify no message in console log \", \"Unable to get logs.\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Unable to get logs.\")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"User gets an exception: \" + e) "));
            ASTHelper.addStmt(block, new NameExpr(("} \n\t\treturn logs")));
        } else {
            ASTHelper.addStmt(block, new NameExpr("LogEntries logs = null"));
            ASTHelper.addStmt(block, new NameExpr("try {\n\t\t\tnew WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")))).until(driver -> ((JavascriptExecutor) driver).executeScript(\"return document.readyState\").equals(\"complete\"));\n\t\t\tlogs = getDriver().manage().logs().get(LogType.BROWSER)"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Log messages are present in console.\")"));
            ASTHelper.addStmt(block, new NameExpr("} catch (Exception e) { \n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Unable to get logs.\")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"User gets an exception: \" + e)"));
            ASTHelper.addStmt(block, new NameExpr("\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get logs.\")"));
            ASTHelper.addStmt(block, new NameExpr(("} \n\t\treturn logs")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }
    public static void setLinkMethodNoErrorLogs(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyNoErrorLogs");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("LogEntries logs = getLogs()"));
            ASTHelper.addStmt(block, new NameExpr("boolean errorLogFound = false"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tfor (LogEntry log : logs\n\t\t\t) {\n\t\t\t\tif (StringUtils.equalsIgnoreCase(\"SEVERE\", log.getLevel().getName())) {\n\t\t\t\tGemTestReporter.addTestStep(\"Verify no error message in console log \", \"Error Log messages are present in console. \" + log.getMessage(), STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\tSettings.LOGGER.info(\"Error Log messages are present in console.\")"));
            ASTHelper.addStmt(block, new NameExpr("\t\terrorLogFound = true"));
            ASTHelper.addStmt(block, new NameExpr("\t\t} \n\t\t\t} \n\t\t\tif(!errorLogFound) {\n\t\t\t\tGemTestReporter.addTestStep(\"Verify no error message in console log \", \"No error message in console log verified successfully. \", STATUS.PASS, takeSnapShot());\n" +
                    "\t\t\t\tSettings.LOGGER.info(\"No error message in console log verified successfully\"); \n\t\t\t} \n\t\t} catch (Exception e) { \n\t\t\tGemTestReporter.addTestStep(\"Verify no error message in console log \", \"Unable to get logs.\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Unable to get logs.\")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"User gets an exception: \" + e) "));
            ASTHelper.addStmt(block, new NameExpr(("\t}")));
        } else {
            ASTHelper.addStmt(block, new NameExpr("LogEntries logs = getLogs()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tfor (LogEntry log : logs\n\t\t\t) {\n\t\t\tassertFalse(\"Error Log messages are present in console.\" + log.getMessage(), StringUtils.equalsIgnoreCase(\"SEVERE\", log.getLevel().getName()))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Error Log messages are present in console.\" + log.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t} catch (Exception e) { \n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Unable to get logs.\")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"User gets an exception: \" + e) "));
            ASTHelper.addStmt(block, new NameExpr("\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get logs.\")"));
            ASTHelper.addStmt(block, new NameExpr(("\t}")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }
    public static void setLinkMethodClearConsole(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clearConsole");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor executor = (JavascriptExecutor)DriverManager.getWebDriver();\n\t\t\texecutor.executeScript(\"console.clear();\")"));
            ASTHelper.addStmt(block, new NameExpr("\t\tGemTestReporter.addTestStep(\"Clear console. \", \"Console cleared successfully. \", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\tSettings.LOGGER.info(\"Cleared the console.\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to clear console.\")"));
            ASTHelper.addStmt(block, new NameExpr("\t\tGemTestReporter.addTestStep(\"Clear console. \", \"Could not clear console. \", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor executor = (JavascriptExecutor)getDriver();\n\t\t\texecutor.executeScript(\"console.clear();\")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Cleared the console.\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to clear console\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to clear console\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }
    public static void setLinkMethodNoLogs(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyNoLogs");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("LogEntries logs = getLogs()"));
            ASTHelper.addStmt(block, new NameExpr("try {\n\t\t\tif (logs.getAll().size() > 0) {\n\t\t\t\tGemTestReporter.addTestStep(\"Verify no message in console log \", \"Log messages are present in console.\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\tSettings.LOGGER.info(\"Log messages are present in console.\")"));
            ASTHelper.addStmt(block, new NameExpr("\t} else {\n\t\t\t\tGemTestReporter.addTestStep(\"Verify no message in console log \", \"Log messages are present in console. \", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\tSettings.LOGGER.info(\"Log messages are not present in console\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t} catch (Exception e) { \n\t\t\tGemTestReporter.addTestStep(\"Verify no message in console log \", \"Unable to get logs.\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Unable to get logs.\")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"User gets an exception: \" + e) "));
            ASTHelper.addStmt(block, new NameExpr(("}")));
        } else {
            ASTHelper.addStmt(block, new NameExpr("LogEntries logs = getLogs()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tAssert.assertNotEquals(\"Log messages are present in console.\", 0, logs.getAll().size())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Error Log messages are present in console.\")"));
            ASTHelper.addStmt(block, new NameExpr("} catch (Exception e) { \n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"Unable to get logs.\")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings.LOGGER.info(\"User gets an exception: \" + e) "));
            ASTHelper.addStmt(block, new NameExpr(("\t}")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodTakeSnapshot(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "takeScreenshot");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\ttakeSnapShot()"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to take screenshot\",\"Unable to take screenshot\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to take screenshot\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            List<Parameter> parameters = new LinkedList<>();
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
            method.setParameters(parameters);
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tTakesScreenshot scrShot =((TakesScreenshot)getDriver());\n\t\t\tFile SrcFile=scrShot.getScreenshotAs(OutputType.FILE);\n\t\t\tFile DestFile=new File(filePath);\n\t\t\tFileUtils.copyFile(SrcFile, DestFile)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodGetLocatorWithName(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("By", 0), "getLocator");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "locatorName"));
        method.setParameters(parameters);
        ASTHelper.addStmt(block, new NameExpr("By locator = null"));
        ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString className = locatorName.split(\"\\\\.\")[0];\n\t\t\tClass<?> clazz = Class.forName(\"locators.\" + className);\n\t\t\tField loc = clazz.getField(locatorName.split(\"\\\\.\")[1]);\n\t\t\tlocator = (By) loc.get(className)"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to get locator\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get locator\");\n\t\t\tAssert.fail(e.getMessage())"));
        ASTHelper.addStmt(block, new NameExpr("} \n\t\treturn locator"));
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodClickUsingJS(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clickUsingJS");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "locatorName"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy locator = getLocator(locatorName);\n\t\t\tJavascriptExecutor executor = (JavascriptExecutor)DriverManager.getWebDriver();\n\t\t\texecutor.executeScript(\"arguments[0].click();\", locator);\n\t\t\tGemTestReporter.addTestStep(\"Verify user is able to force click on javascript element\",\"User is able to click element : "+meaningFulName+"\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to click element : "+meaningFulName+"\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to force click\",\"User is unable to click element : "+meaningFulName+"\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to click element : "+meaningFulName+"\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy locator = getLocator(locatorName);\n\t\t\tJavascriptExecutor executor = (JavascriptExecutor)getDriver();\n\t\t\texecutor.executeScript(\"arguments[0].click();\", $(locator))"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to click using JS\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to click using JS\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodDragAndDrop(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "dragAndDrop");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "from"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "to"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy fromLocator = getLocator(from);\n\t\t\tBy toLocator = getLocator(to);\n\t\t\tnew SerenityActions(DriverManager.getWebDriver()).dragAndDrop((WebElement)fromLocator, (WebElement)toLocator).build().perform();\n\t\t\tGemTestReporter.addTestStep(\"Verify user is able to scroll drag to drop element to x: "+meaningFulName+"\",\"User is able to drag and drop element : "+meaningFulName+"\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to drag and drop element : "+meaningFulName+"\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to scroll drag to drop element to x: "+meaningFulName+"\",\"User is able to drag and drop element : "+meaningFulName+"\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to drag and drop element : "+meaningFulName+"\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy fromLocator = getLocator(from);\n\t\t\tBy toLocator = getLocator(to);\n\t\t\tnew SerenityActions(getDriver()).dragAndDrop($(fromLocator), $(toLocator)).build().perform()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to drag and drop element\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to drag and drop element\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodFileUpload(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "fileUpload");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "locatorName"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy locator = getLocator(locatorName);\n\t\t\t" + "typeText(" + "(WebElement)locator" + ", \"" + "filePath" + " \" + filePath" + ");\n\t\t\tGemTestReporter.addTestStep(\"Verify user is able to upload file to drop element : "+meaningFulName+"\",\"User is able to upload file to element : "+meaningFulName+"\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is able to upload file to element : "+meaningFulName+"\")"));
            ASTHelper.addStmt(block, new NameExpr("\t}\t\t\n\t\tcatch(Exception e){GemTestReporter.addTestStep(\"Verify user is able to upload file to element: "+meaningFulName+"\",\"User is unable to upload file to element : "+meaningFulName+"\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to upload file to element : "+meaningFulName+"\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \" + e);"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tBy locator = getLocator(locatorName);\n\t\t\t$(locator).sendKeys(filePath)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to upload file\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to upload file\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsMinimizeBrowser(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "minimizeGivenBrowser");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tminimizeBrowser()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){\n\t\t\tGemTestReporter.addTestStep(\"Verify Browser Minimized\",\"Unable to minimize browser.\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to minimize browser.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().manage().window().minimize()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Browser Minimization successful.\");" + "\n\t\t}\n\t\tcatch(Exception e){"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to minimize browser.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not minimize browser\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsMaximizeBrowserToDefault(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "maximizeBrowserToDefault");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\tSTATUS status = maximizeToDefaultBrowserSize()"));
            ASTHelper.addStmt(block, new NameExpr("Boolean maximizeStatus = Objects.equals(status, \"PASS\");"));
            ASTHelper.addStmt(block, new NameExpr("if(maximizeStatus)\n\t\t\t{\n\t\t\tGemTestReporter.addTestStep(\"Verify Browser Maximized to Default Size \",\"Browser Maximization successful.\", STATUS.PASS, takeSnapShot());"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Verify Browser Maximized to Default Size \",\"Browser Maximization successful.\" );" + "\n\t\t\t}\n\t\telse\n\t\t\t{\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify Browser Maximized to Default Size \",\"Unable to maximize browser.\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Verify Browser Maximized to Default Size \",\"Unable to maximize browser.\");" + "\n\t\t\t}\n\t\t}\n\t\tcatch(" + "Exception e" + ")\n\t\t{\n\t\t\tGemTestReporter.addTestStep(\"Verify Browser Maximized to Default Size \",\"Unable to maximize browser.\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to maximize browser.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().manage().window().maximize()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Browser Maximization to default successful\");" + "\n\t\t} catch(Exception e){"));
            ASTHelper.addStmt(block, new NameExpr("\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to maximize browser to default\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not maximize browser to default\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsSetBrowserPosition(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "setPositionOfBrowser");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "y"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tsetBrowserPosition(x,y)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify User is able to set browser position\",\"User is unable to set browser position to x: \"+x+\" and y: \"+y+\" coordinates.\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to set browser position to x: \"+x+\" and y: \"+y+\" coordinates.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().manage().window().setPosition(new Point(x,y))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully set position of browser\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not set position of browser\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not set position of browser\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsSetBrowserSize(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "setSizeOfBrowser");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "width"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "height"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tsetBrowserSize(width,height)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){GemTestReporter.addTestStep(\"Verify User is able to set browser size\",\"User is unable to set browser size to width: \"+width+\" and height: \"+height+\" coordinates.\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to set browser position to width: \"+width+\" and height: \"+height+\" coordinates.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDimension newDimension = new Dimension(width, height);\n\t\t\tgetDriver().manage().window().setSize(newDimension)\t\t"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully set size of browser\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Could not set size of browser\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Could not set size of browser\");\n\t\t\tAssert.fail(e.getMessage());\n\t\t}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsVerifyTitle(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyTitle");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "expectedTitle"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("\tString actualTitle = getTitle(getCurrentURL());\n\t\t\ttry{"));
            ASTHelper.addStmt(block, new NameExpr("\tif(actualTitle.equals(expectedTitle)) {\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify page title \",\"Page title verified successfully. Expected: '\" +expectedTitle+\"' Actual: '\" +actualTitle+\"'\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Page title verified successfully. Expected: '\" +expectedTitle+\"' Actual: '\" +actualTitle+\"'\" );" + "\n\t\t\t}\n\t\t\telse {\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify page title \",\"Unable to verify page title. Expected: '\" +expectedTitle+\"' Actual: '\" +actualTitle+\"'\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to verify page title. Expected: '\" +expectedTitle+\"' Actual: '\" +actualTitle+\"'\" );" + "\n\t\t\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tGemTestReporter.addTestStep(\"Verify page title \",\"Unable to verify page title. Expected: '\" +expectedTitle+\"' Actual: '\" +actualTitle+\"'\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to verify title\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {

            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString actualTitle = getTitle()"));
            ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"Actual title: \" + actualTitle, actualTitle.equals(expectedTitle))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully verifies title\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Actual Title: \" + getTitle());\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Actual Title: \" + getTitle());\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.TITLE" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsGetTitle(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "getTitle");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String title = getTitle();\n\t\t\ttry{\n\t\t\tGemTestReporter.addTestStep(\"Verify user is able to fetch title\",\"User is able to fetch title successfully\", STATUS.PASS, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to fetch title\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tGemTestReporter.addTestStep(\"Verify user is able to fetch title\",\"User is unable to fetch title\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User is unable to fetch title\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn title"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String title = \"\""));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\ttitle = getDriver().getTitle()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully gets current title\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ");\n\t\t\tSettings.LOGGER.info(\"Unable to get current title\");\n\t\t\tSerenity.recordReportData().withTitle(\"Failure\").andContents(\"Unable to get current title\");\n\t\t\tAssert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn title"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "getTitle()")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

    public static void setLinkMethodsSelect(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "select" + meaningFulName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\telementIsClickable" + meaningFulName + "();\n\t\t\tclick(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\tGemTestReporter.addTestStep(\"Click on " + field.getName() + "\",\"Unable to click on " + field.getName() + "\", STATUS.FAIL, takeSnapShot());\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"Unable to click on " + field.getName() + "\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(\"User gets an exception: \"+e)"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "click" + "(" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User selects the " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not select " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
    }

}
