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
import locatorstrategyform.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import pageobjectgenerator.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class UtilsMethodCodeGenerator {

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
     * adds a constructor to the CompilationUnit c with a WebDriver instance and
     * PageFactory initialization
     *
     * @param c
     * @param s
     */
    public static void setDefaultConstructor(CompilationUnit c, State s) {

        // creates the class constructor
        ConstructorDeclaration constructor = new ConstructorDeclaration();
        constructor.setName(s.getName());
        constructor.setModifiers(ModifierSet.PUBLIC);

        // sets the WebDriver instance parameter
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("WebDriver", 0), "driver"));

        constructor.setParameters(parameters);
        constructor.setJavaDoc(new JavadocComment("\n\t\tPage Object for " + s.getName() + " (" + s.getStateId() + ") \n\t"));

        // add the body to the constructor
        BlockStmt constructor_block = new BlockStmt();
        constructor.setBlock(constructor_block);

        // add basic statements do the constructor method body
        ASTHelper.addStmt(constructor_block, new NameExpr("this.driver = driver"));
        ASTHelper.addStmt(constructor_block, new NameExpr("PageFactory.initElements(driver, this)"));

        ASTHelper.addMember(c.getTypes().get(0), constructor);

    }

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
            imports.add(new ImportDeclaration(new NameExpr("org.apache.commons.lang.StringUtils"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.interactions.Actions"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.WebDriver"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.Keys"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.util.Objects"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.*"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("net.serenitybdd.core.pages.SerenityActions"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.By"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("utils.UtilsMethodCodeGenerator"), false, false));
        }

        Settings.LOGGER.info("Imports added are:" + imports);
        return imports;
    }

    /**
     * creates the webElements and WedDriver variables together with the correct
     * locators (for now XPath or CSS locators are used)
     *
     * @param c           CompilationUnit
     * @param webElements List<CandidateWebElement>
     */
    public static void setVariables(CompilationUnit c, Set<CandidateWebElement> webElements) {

        setWebElements(c, webElements);
//        setWebDriverVariable(c,String className);
        //setGetter(c,webElements);

    }

    /**
     * creates the webElements instances
     *
     * @param c           CompilationUnit
     * @param webElements List<CandidateWebElement>
     */
    private static void setWebElements(CompilationUnit c, Set<CandidateWebElement> webElements) {

        for (CandidateWebElement cwe : webElements) {

            VariableDeclarator webElement = new VariableDeclarator();
            webElement.setId(new VariableDeclaratorId(cwe.getVariableName()));

            FieldDeclaration field = ASTHelper.createFieldDeclaration(ModifierSet.PRIVATE, ASTHelper.createReferenceType("WebElement", 0), webElement);
            List<AnnotationExpr> list_espr = new LinkedList<>();

            NormalAnnotationExpr locator_annotation = new NormalAnnotationExpr();
            locator_annotation.setName(new NameExpr("FindBy"));

            List<MemberValuePair> list_mvp = new LinkedList<>();
            MemberValuePair mvp = new MemberValuePair();

            if (cwe.getCssLocator() == null) {
                String xpathLocator = cwe.getXpathLocator();
                xpathLocator = "\"" + xpathLocator + "\"";
                mvp = new MemberValuePair("xpath", new NameExpr(xpathLocator));
            } else if (cwe.getCssLocator() != null) {
                String cssLocator = cwe.getCssLocator();
                cssLocator = "\"" + cssLocator + "\"";
                mvp = new MemberValuePair("css", new NameExpr(cssLocator));
            } else if (cwe.getVariableName() == null) {

            }
            list_mvp.add(mvp);
            locator_annotation.setPairs(list_mvp);
            list_espr.add(0, locator_annotation);

            field.setAnnotations(list_espr);
            ASTHelper.addMember(c.getTypes().get(0), field);
        }
    }

    /**
     * creates the webElements instances
     *
     * @param c           CompilationUnit
     * @param webElements List<CandidateWebElement>
     */
    private static void setGetter(CompilationUnit c, Set<CandidateWebElement> webElements) {

        for (CandidateWebElement cwe : webElements) {

            // Add the Getter method
            // /////////////////////////////////////////////////////////////
            MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("WebElement", 0), "get" + cwe.getVariableName());
            // add a body to the method
            BlockStmt block = new BlockStmt();
            method.setBody(block);

            // add a statement do the method body
            ASTHelper.addStmt(block, new NameExpr("return " + cwe.getVariableName()));
            ASTHelper.addMember(c.getTypes().get(0), method);
        }
    }

    /**
     * For each CompilationUnit c associated to a State s creates the link methods
     * to navigate towards other page objects
     *
     * @param c
     * @param s
     */
    public static void setLinkMethods(CompilationUnit c, State s) {

        for (Edge edge : s.getLinks()) {

            String towards = UtilsStaticAnalyzer.getStateNameFromStateId(edge.getTo());

            // add the necessary import
            ImportDeclaration new_import = new ImportDeclaration(new NameExpr("locators." + towards), false, false);

            if (!towards.equals("") && !c.getImports().contains(new_import)) {
                c.getImports().add(new_import);
            }

            String l = edge.getVia();
            l = l.replace("xpath ", "");
            String we = s.getWebElementNameFromLocator(l);

            if (we.equals("")) {
                System.err.println("[ERROR] UtilsCodeGenerator.setLinkMethods getWebElementNameFromLocator failed");
                // System.exit(1);
            }

            String eventType = edge.getEvent() + "()";

            MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType(towards, 0), "goTo" + towards);
            // + "_via_" + we);

            // add a body to the method
            BlockStmt block = new BlockStmt();
            method.setBody(block);

            // add a statement do the method body
            ASTHelper.addStmt(block, new NameExpr(we + "." + eventType));
            ASTHelper.addStmt(block, new NameExpr("return new " + towards + "(driver)"));

            String name = method.getName();
            int occ = 0;

            for (BodyDeclaration bd : c.getTypes().get(0).getMembers()) {
                if (bd instanceof MethodDeclaration) {
                    if (((MethodDeclaration) bd).getName().contains(name)) {
                        occ++;
                    }
                }
            }

            if (occ > 0) {
                method.setName(name + "_" + occ);
            }

            ASTHelper.addMember(c.getTypes().get(0), method);
        }
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitUntilElementAppear(" + Settings.LOCATOR_FILE_NAME + "." + field.getName()+",Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\ttext = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ", attributeValue)"));
            ASTHelper.addStmt(block, new NameExpr("\tif(!text.equals(\"\"))\n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Get value of \" + attributeValue + \" attribute for \" + \"" + field.getName() + " field\",\"Successfully fetched \" + attributeValue + \" value for " + field.getName() + "\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Successfully fetched \" + attributeValue + \" value for " + field.getName() + "\");\n\t\t\t}\n\t\t\t else \n\t\t\t{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Get value of \" + attributeValue + \" attribute for \" + \"" + field.getName() + " field\",\"Unable to get \" + attributeValue + \" value for " + field.getName() + " as attribute does not exist\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Unable to get \" + attributeValue + \" value for " + field.getName() + " as attribute does not exist\");\n\t\t\t}"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Get value of \" + attributeValue + \" attribute for \" + \"" + field.getName() + " field\",\"Unable to get \" + attributeValue + \" value for " + field.getName() + " as attribute does not exist\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            ASTHelper.addStmt(block, new NameExpr("return text"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String valueOfAttribute = \"\""));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets attribute as \"" + "+" + "attributeValue" + "+" + "\" for " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tvalueOfAttribute = $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(" + "attributeValue" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not get value for attribute \" + attributeValue)"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "return " + "valueOfAttribute"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
        Settings.LOGGER.info(String.valueOf(new NameExpr("return " + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ".getAttribute(" + "attributeValue" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\ttext = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ", \"checked\")"));
            ASTHelper.addStmt(block, new NameExpr("\tif(text.length()>0){\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " checkbox is selected\",\"Checkbox " + field.getName() + " selected Successfully\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Checkbox " + field.getName() + " selected Successfully\" );" + "\t}"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " checkbox is selected\",\"Unable to select " + field.getName() + " checkbox\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to select " + field.getName() + " checkbox\" );" + "\t}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("assertTrue(\"" + meaningFulName + " is not selected\", $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "isSelected())"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User verifies if checkbox is selected\")"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
        Settings.LOGGER.info(String.valueOf(new NameExpr("return " + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ".getAttribute(" + "attributeValue" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsIsNotSelected(CompilationUnit c, Field field) throws IOException {

        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyElementNotSelectedFor" + meaningFulName);
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\ttext = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ", \"checked\")"));
            ASTHelper.addStmt(block, new NameExpr("\tif(text==null){\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " checkbox is not selected\",\"Checkbox " + field.getName() + " is deselected Successfully\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Checkbox " + field.getName() + " is deselected Successfully\" );" + "\t}\n\t\t\t else {" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " checkbox is not selected\",\"Unable to deselect " + field.getName() + " checkbox\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"Unable to deselect " + field.getName() + " checkbox\" );" + "\t}"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " checkbox is not selected\",\"Unable to deselect " + field.getName() + " checkbox\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to deselect " + field.getName() + " checkbox\" );" + "\t}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("assertFalse(\"" + meaningFulName + " is selected\", $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "isSelected())"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User verifies if checkbox is not selected\")"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "." + "getAttribute(" + "attributeValue" + ")")));
        Settings.LOGGER.info(String.valueOf(new NameExpr("return " + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ".getAttribute(" + "attributeValue" + ")")));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tclick(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tnavigateBack()"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User click on " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "click" + "(" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "getDriver()" + "." + "navigate().back()"));
            Settings.LOGGER.info(String.valueOf(new NameExpr(Settings.LOCATOR_FILE_NAME + "." + "driver" + "." + "navigate().back()")));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User navigates back  to previous page\")"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\ttext = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",\"value\")"));
            ASTHelper.addStmt(block, new NameExpr("\tif(typeText.equals(text)){\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify value of " + field.getName() + " matches \" +typeText,\"Value for " + field.getName() + " verified successfully\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Value for " + field.getName() + " verified successfully\" );" + "\t}\n\t\t\telse{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify value of " + field.getName() + " matches \" +typeText,\"Unable to verify value for " + field.getName() + ". Actual: \"+text+\" Expected: \"+typeText, STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to verify value for " + field.getName() + ". Actual: \"+text+\" Expected: \"+typeText );" + "\t}\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify value of " + field.getName() + " field\",\"Unable to get attribute value\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("assertTrue(\"Actual value: \" + $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(\"value\"), " + "StringUtils.equalsIgnoreCase(" + "typeText" + "," + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(\"value\")" + "))"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User verifies value: \"" + " + " + "typeText + \"for " + meaningFulName + "\")"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("return " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + ".getAttribute(\"value\")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\ttext = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",attributeName)"));
            ASTHelper.addStmt(block, new NameExpr("\tif(attributeValue.equals(text)){\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify if value of '+attributeName+' matches\" +attributeValue,\"Validation Successful\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\telse{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify if value of '+attributeName+' matches\" +attributeValue,\"Validation Failed\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Get value of Value attribute for " + field.getName() + " field\",\"Unable to get attribute value\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            ASTHelper.addStmt(block, new NameExpr("return text"));

        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "assertTrue(" + "StringUtils.equalsIgnoreCase(" + "attributeValue" + "," + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(attributeName)" + "))"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "return " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + ".getAttribute(attributeName)"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("return " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + ".getAttribute(attributeName)")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "getElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").isSelected()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User verifies " + field.getName() + " element is selected\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify " + field.getName() + " element is selected\",\"" + field.getName() + " is selected successfully\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify if " + field.getName() + " element is selected\",\"Unable to select " + field.getName() + " element\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("assertTrue(\"" + meaningFulName + " is not selected\", " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "isSelected" + "(" + "))"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User verifies " + field.getName() + " is selected\"" + ")"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("assertTrue(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "." + "isSelected" + "(" + "))")));

        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\twaitUntilElementAppear(" + Settings.LOCATOR_FILE_NAME + "." + field.getName()+",Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));\n\t\t\t" + "typeText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Enter the text in " + field.getName() + " field\",\"Unable to Enter Text in " + field.getName() + " field\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").wait(Integer.parseInt(UtilsMethodCodeGenerator.readProperties(\"timeOut\")));" + "\n\t\t\ttypeInto(" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not enter \" + typeText + \" into " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "Element" + "." + "type" + "(" + "typeText" + ")")));
        }


        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tclearText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Clear text for " + field.getName() + " field\",\"Input for " + field.getName() + " field cleared successfully\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " field cleared successfully\");}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Clear text for " + field.getName() + " field\",\"" + field.getName() + "Unable to clear text for " + field.getName() + " field\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Unable to clear text for " + field.getName() + " field\");}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "clear" + "(" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User deletes the value for " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not clear " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "typeText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t Actions action = new Actions(DriverManager.getWebDriver());"));
            ASTHelper.addStmt(block, new NameExpr("\t action.sendKeys(Keys.ENTER);"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value and presses enter\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Enter the text in " + field.getName() + " field and press enter\",\"Unable to Enter Text in " + field.getName() + " field\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "typeInto(" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("Actions action = new Actions(DriverManager.getWebDriver());"));
            ASTHelper.addStmt(block, new NameExpr("action.sendKeys(Keys.ENTER);"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value and presses enter\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not enter \" + typeText + \" into " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "Element" + "." + "type" + "(" + "typeText" + ")")));
        }


        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "typeText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tActions action = new Actions(DriverManager.getWebDriver());"));
            ASTHelper.addStmt(block, new NameExpr("\taction.sendKeys(Keys.TAB);"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value and presses Tab\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Enter the text in " + field.getName() + " field and presses tab\",\"Unable to Enter Text in " + field.getName() + " field\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "typeInto(" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User enters " + '"' + "+" + "typeText" + "+" + '"' + " as value and presses Tab\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not enter \" + typeText + \" into " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr(field.getName() + "Element" + "." + "type" + "(" + "typeText" + ")")));
        }


        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\ttext = getAttributeName(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ",\"value\")"));
            ASTHelper.addStmt(block, new NameExpr("\tif(text.equals(\"\")){\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify value cleared for " + field.getName() + " field\",\"Verified input value for " + field.getName() + " is successfully cleared\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Verified input value for " + field.getName() + " is successfully cleared\");}\n\t\t\telse{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify value cleared for " + field.getName() + " field\",\"Input value for " + field.getName() + " not cleared successfully \", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Input value for " + field.getName() + " not cleared successfully \");}\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify value gets cleared for " + field.getName() + " field\",\"Unable to clear value\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));

        } else {
            ASTHelper.addStmt(block, new NameExpr("assertTrue(\"Actual value: \" + $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(\"value\"), " + "StringUtils.equalsIgnoreCase(" + "" + "\"\", " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getAttribute(\"value\")" + "))"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User verifies value is cleared for " + meaningFulName + "\")"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("return " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + ".getAttribute(\"value\")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\thoverOver("+Settings.LOCATOR_FILE_NAME + "." + field.getName()+","+"labelText)"));
                ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
                ASTHelper.addStmt(block, new NameExpr("}"));
            } else {
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().forward()"));
                ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully navigated Forward\"" + ")"));
                ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
                ASTHelper.addStmt(block, new NameExpr("}"));
                Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
            }

            ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsElementPresence(CompilationUnit c, Field field) throws IOException {


        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        // /////////////////////////////////////////////////////////////
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verify" + meaningFulName+"Exists");
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName(element,"value") of Gemjar Framework to get the value attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tif(isExist("+Settings.LOCATOR_FILE_NAME + "." + field.getName()+")){"));
            ASTHelper.addStmt(block, new NameExpr("\t\tGemTestReporter.addTestStep(\"Verify element " + field.getName() + " is present on Screen\",\""+field.getName() + " element is present on Screen\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\""+field.getName() + " is present on Screen\");}\t\n\t\t\t else{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify element " + field.getName() + " is present on Screen\",\""+field.getName() + " element is not present on Screen\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\""+field.getName() + " element is not present on Screen\");}\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify element " + field.getName() + " is present on Screen\",\""+field.getName() + " element is not present on Screen\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tif(getDriver().findElements("+Settings.LOCATOR_FILE_NAME + "." + field.getName()+").size()>0){"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\""+field.getName() + " is present on Screen\");}\t\n\t\t\t else{\n\t\t\t\t" + "Assert.fail(\""+field.getName() + " element is not present on Screen\")"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\""+field.getName() + " element is not present on Screen\");}\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Assert.fail(\""+field.getName() + " element is not present on Screen\")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }

        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsScrollToView(CompilationUnit c, Field field) throws IOException {


        meaningFulName = UtilsMethodCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        // Add the Getter method
        // /////////////////////////////////////////////////////////////
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollTo" + meaningFulName+"Element");
        BlockStmt block = new BlockStmt();
        method.setBody(block);

        // add a statement do the method body
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));

        //DriverAction.getAttributeName(element,"value") of Gemjar Framework to get the value attribute of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tscrollIntoView("+Settings.LOCATOR_FILE_NAME + "." + field.getName()+")"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Scroll to " + field.getName() + " element\",\"Successful able to scroll to "+field.getName() + " element\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Successful able to scroll to "+field.getName() + " element\");\n\t\t\t}\t\t\tcatch(Exception e){"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "GemTestReporter.addTestStep(\"Scroll to " + field.getName() + " element\",\"Unable to scroll to "+field.getName() + " element\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();\n\t\t\tjs.executeScript(\"arguments[0].scrollIntoView();\", "+Settings.LOCATOR_FILE_NAME + "." + field.getName()+")"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\" Successful able to scroll to "+field.getName() + " element \");}\t\n\t\t\tcatch(Exception e){"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Assert.fail(\" Unable to scroll to "+field.getName() + " element \")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }

        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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

        //DriverAction.getElementText() of Gemjar Framework to get the the text of an element

        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String text = new String()"));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\ttext = getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\treturn text"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String text = \"\""));
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "text = $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getText" + "(" + ");\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets the text of " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not get text of " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t" + "return " + "text"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("return " + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "." + "getText" + "(" + ")")));
        }

        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());

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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "dropDown(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "," + "typeText" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User is able to select \" + " + "typeText" + " +\" visible text in the dropdown\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
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
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
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
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("\tWebElement element = wait.until(ExpectedConditions.elementToBeClickable(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "))"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Check if " + field.getName() + " is clickable\",\"" + field.getName() + " is clickable\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " is clickable\");}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Check if " + field.getName() + " is clickable\",\"" + field.getName() + " is not clickable\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " is not clickable\");}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("assertTrue(\"" + meaningFulName + " is not clickable\", " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "isClickable" + "(" + "))"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User verifies " + field.getName() + " is clickable\"" + ")"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("assertTrue(" + field.getName() + "Element" + "." + field.getName() + "." + "isClickable" + "(" + "))")));


        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").isEnabled()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify " + field.getName() + " field is enabled\",\"" + field.getName() + " field is enabled\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " field is enabled\");}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " field is enabled\",\"" + field.getName().substring(0, 1).toUpperCase() + " field is not enabled\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " field is not enabled\");}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("assertTrue(\"" + meaningFulName + " is not enabled\", " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "isEnabled" + "(" + "))"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("assertTrue(" + field.getName() + "Element" + "." + "isEnabled" + "(" + "))")));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User verifies the given " + field.getName() + " element is enabled\"" + ")"));
        }

        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetElement(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").isDisplayed()"));
            ASTHelper.addStmt(block, new NameExpr("\tGemTestReporter.addTestStep(\"Verify " + field.getName() + " field is visible\",\"" + field.getName() + " field is visible\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " field is visible\");}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " field is visible\",\"" + field.getName() + " field is not visible\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"" + field.getName() + " field is not visible\");}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("assertTrue(\"" + meaningFulName + " is not visible\", " + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "isDisplayed" + "(" + "))"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User verifies " + field.getName() + " element is displayed\"" + ")"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tif(getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").equals(typeText)){"));
            ASTHelper.addStmt(block, new NameExpr("\t\tGemTestReporter.addTestStep(\"Verify text of " + field.getName() + " field is equal to \" + typeText,\"Text of " + field.getName() + " field is equal to \" + typeText, STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Text of " + field.getName() + " field is equal to \" + typeText);}\t\n else{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify text of " + field.getName() + " field is equal to \" + typeText,\"Text of " + field.getName() + " field is not equal. Expected: \"+typeText+\" Actual: \"+getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "), STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Text of " + field.getName() + " field is not equal. Expected: \"+typeText+\" Actual: \"+getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "));}\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Check if text inside " + field.getName() + " is equal to \" + typeText,\"Text inside " + field.getName() + " is not equal\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "assertTrue(\"Actual text: \" + $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getText" + "(" + "), " + "StringUtils.equalsIgnoreCase(" + "typeText" + "," + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getText" + "(" + ")))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets the text of " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("assertTrue(" + "StringUtils.equalsIgnoreCase(" + "typeText" + "," + field.getName() + "Element" + "." + "getText" + "(" + ")))")));

        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tif(getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ").contains(typeText)){"));
            ASTHelper.addStmt(block, new NameExpr("\t\tGemTestReporter.addTestStep(\"Verify " + field.getName() + " field contains \" + typeText,\"Text of " + field.getName() + " field is equal to \" + typeText, STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Text of " + field.getName() + " field does not contain\" + typeText);\n\t\t\t}\n\t\t\telse{\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify " + field.getName() + " field contains \" + typeText,\"Text\" + typeText + \"is not present in " + field.getName() + " field. Expected: \"+typeText+\" Actual: \"+ getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "), STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(\"Text of " + field.getName() + " field does not contain\" + typeText + \". Expected: \"+typeText+\" Actual: \"+getElementText(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + "));}\t\n\t\t}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Check if text inside " + field.getName() + " is equal to \" + typeText,\"Text inside " + field.getName() + " is not equal\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "assertTrue(\"Actual text: \" + $(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getText" + "(" + "), " + "StringUtils.containsIgnoreCase($(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "getText" + "(" + "), " + "typeText))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User verifies " + field.getName() + " contains \"" + " + typeText)"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        Settings.LOGGER.info(String.valueOf(new NameExpr("assertTrue(" + "StringUtils.containsIgnoreCase(" + field.getName() + "." + "getText" + "(" + "))), " + "typeText")));

        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("getDriver(" + ")" + "." + "get" + "(" + "Settings.URL" + ")"));
            ASTHelper.addStmt(block, new NameExpr("Settings" + "." + "LOGGER" + "." + "info(" + "\"User launches the application\"" + ")"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tclick(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User clicks on " + field.getName() + " successfully\"" + ");}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User is unable to click on " + field.getName() + "\"" + ");}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + "." + "click" + "(" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User click on the " + field.getName() + " element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Serenity" + "." + "recordReportData().withTitle(\"Failure\").andContents(\"Could not click " + meaningFulName + "\")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e.getMessage()" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\t" + "Assert.fail(e.getMessage())"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t" + "Actions" + " " + "action" + "=" + "new" + " " + "Actions(" + "driver" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\taction" + "." + "doubleClick(" + "$(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")" + ")" + "." + "perform" + "(" + ")"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User double click on the element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());

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
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "GemTestReporter.addTestStep(\"Upload the\" + fileName + \"file\",\"Unable to upload\" + fileName + \"field\", STATUS.FAIL, takeSnapShot())"));
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
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    private static int countTowards(Set<Edge> links, Edge edge3) {

        int c = 0;

        for (Edge edge : links) {
            if (edge3.getTo().equals(edge.getTo())) {
                c++;
            }
        }

        return c;
    }

    /**
     * For each CompilationUnit c associated to a State s creates the form methods.
     * <p>
     * It follows a naive approach: parses the form objects and puts everything in
     * the method
     * </p>
     *
     * @param c CompilationUnit
     * @param s State
     */
    public static void setFormMethods(CompilationUnit c, State s) {

        if (s.getForms() == null) {
            return;
        }

        for (Form f : s.getForms()) {

            MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, f.getFormName());
            BlockStmt block = new BlockStmt();
            method.setBody(block);

            for (int i = 0; i < f.getFormFieldList().size(); i++) {

                addIndexedParameterToFormMethod(f, i, method);

            }

            for (FormField field : f.getFormFieldList()) {

                addFormInstructionToBlockMethod(block, f, field);

            }

            ASTHelper.addMember(c.getTypes().get(0), method);
        }

    }

    /**
     * For each CompilationUnit c associated to a State s creates the form
     * methods.
     * <p>
     * It follows a more sophisticated approach: parses the form objects and creates
     * a method for each submit/button only
     * </p>
     *
     * @param c CompilationUnit
     * @param s State
     * @throws Exception
     */
    public static void setFormMethodsFromButtonAndSubmit(CompilationUnit c, State s) throws Exception {

        if (s.getForms() == null) {
            return;
        }

        for (Form f : s.getForms()) {

            for (InputField i : f.getSubmitList()) {
                System.out.println("[LOG] " + f.getSubmitList().size() + " submit/button(s) found in form " + f.getFormName());

                MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, f.getFormName() + "_" + i.getVariableName());

                BlockStmt block = new BlockStmt();
                method.setBody(block);

                if (f.getSubmitList().size() == 1) {

                    for (int j = 0; j < f.getFormFieldList().size(); j++) {

                        addIndexedParameterToFormMethod(f, j, method);

                    }

                    for (FormField field : f.getFormFieldList()) {

                        addFormInstructionToBlockMethod(block, f, field);

                    }

                } else if (f.getSubmitList().size() > 1) {
                    addFormInstructionToBlockMethod(block, f, i);
                } else {
                    throw new Exception("Form does not contains any submit!");
                }

                ASTHelper.addMember(c.getTypes().get(0), method);

            }
        }

    }

    private static void addParameterToFormMethod(Form f, MethodDeclaration method) {

        Parameter par = ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "param");
        par.setVarArgs(false);
        ASTHelper.addParameter(method, par);

    }

    private static void addIndexedParameterToFormMethod(Form f, int i, MethodDeclaration method) {

        if (f.getFormFieldList().get(i).getDefaultAction().equals("sendKeys")) {
            Parameter par = ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "args" + i);
            par.setVarArgs(false);
            ASTHelper.addParameter(method, par);
        }

    }

    private static void addFormInstructionToBlockMethod(BlockStmt block, Form f, FormField field) {

        switch (field.getDefaultAction()) {
            case "sendKeys":
                ASTHelper.addStmt(block, new NameExpr(field.getVariableName() + "." + field.getDefaultAction() + "(args" + f.getFormFieldList().indexOf(field) + ")"));
                break;

            case "click":
                ASTHelper.addStmt(block, new NameExpr(field.getVariableName() + "." + field.getDefaultAction() + "()"));
                break;
            default:
                break;
        }

    }

    /**
     * formats the string to get a valid variable name
     *
     * @param s
     * @return
     */
    public static String formatToVariableName(String s) {

        String res = s;

        res = UtilsStaticAnalyzer.toSentenceCase(res);
        res = res.replaceAll(" ", "");
        res = StringUtils.uncapitalize(res);

        return res;
    }

    /**
     * Creates getters methods to be used for assertions. Created on differences
     * between adjacent pages
     *
     * @param c
     * @param s
     */
    public static void setGettersMethods(CompilationUnit c, State s) {

        if (s.getDiffs() == null) {
            return;
        }

        for (Getter d : s.getDiffs()) {

            // /////////////////////////////////////////////////////////////
            // Add the WebElement
            // /////////////////////////////////////////////////////////////
            VariableDeclarator webElement = new VariableDeclarator();
            webElement.setId(new VariableDeclaratorId(d.getWebElementName()));

            FieldDeclaration field = ASTHelper.createFieldDeclaration(ModifierSet.PRIVATE, ASTHelper.createReferenceType("WebElement", 0), webElement);

            List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();

            NormalAnnotationExpr na = new NormalAnnotationExpr();
            na.setName(new NameExpr("FindBy"));

            List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
            MemberValuePair mvp = new MemberValuePair();

            String xpathLocator = d.getLocator();
            xpathLocator = "\"" + xpathLocator + "\"";
            mvp = new MemberValuePair("xpath", new NameExpr(xpathLocator));

            list_mvp.add(mvp);
            na.setPairs(list_mvp);
            list_espr.add(0, na);

            field.setAnnotations(list_espr);
            ASTHelper.addMember(c.getTypes().get(0), field);

            // /////////////////////////////////////////////////////////////
            // Add the Getter method
            // /////////////////////////////////////////////////////////////
            MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "get_" + d.getWebElementName());

            // add a body to the method
            BlockStmt block = new BlockStmt();
            method.setBody(block);

            /**
             * public String getGroupsName() { return groupContainer.getText(); }
             */
            JavadocComment javaDoc = new JavadocComment("\n\t\tsource: " + d.getSourceState() + "" + "\n\t\ttarget: " + d.getTargetState() + "" + "\n\t\tcause: " + d.getCause() + "" + "\n\t\tbefore: " + d.getBefore() + "" + "\n\t\tafter: " + d.getAfter() + "" + " \n\t");
            method.setJavaDoc(javaDoc);

            // add a statement do the method body

            ASTHelper.addStmt(block, new NameExpr("return " + d.getWebElementName() + ".getText()"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("return " + d.getWebElementName() + ".getText()")));
            ASTHelper.addMember(c.getTypes().get(0), method);
            Settings.LOGGER.info(method.toString());
            Settings.LOGGER.info(c.getTypes().get(0).toString());
        }

    }

    public static void setSourceCode(CompilationUnit c, State s) {
        s.setSourceCode(StringUtils.replace(c.toString(), "private WebElement", "public static WebElement"));
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
//		if(BooleanUtils.isTrue(isMethodCall)) {
//			res = res.replaceAll("_", " ");
//		}else {
//			res = res.replaceAll("_", "");
//		}

//		res = StringUtils.capitalize(res);

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
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().back()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully navigated back\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsSwitchToActiveElement(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchActiveElement");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tswitchToActiveElement()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().activeElement()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to active element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().activeElement()")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsSwitchToParentFrame(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchParentFrame");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tswitchToParentFrame()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().parentFrame()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to parent frame\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().parentFrame()")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
                ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
                ASTHelper.addStmt(block, new NameExpr("}"));
            } else {
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().frame(nameOrId)"));
                ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to frame\"" + ")"));
                ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
                ASTHelper.addStmt(block, new NameExpr("}"));
                Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().frame(nameOrId)")));
            }
        } else {
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "index"));
            if (readProperties("Framework").contains("GEMJAR")) {
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tswitchToFrame(index)"));
                ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
                ASTHelper.addStmt(block, new NameExpr("}"));
            } else {
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().frame(index)"));
                ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to frame\"" + ")"));
                ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
                ASTHelper.addStmt(block, new NameExpr("}"));
                Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().frame(index)")));
            }
        }

        method.setParameters(parameters);

        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsSwitchWindow(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchWindow");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "nameOrHandle"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDriverManager.getWebDriver().switchTo().window(nameOrHandle)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().window(nameOrHandle)"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to window\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().window(nameOrHandle)")));
        }

        method.setParameters(parameters);

        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("waitABit(duration)")));
        }

        method.setParameters(parameters);

        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }
    public static void setLinkMethodsClickAndHold(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "clicksAndHold");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("By", 0), "locator"));
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnew Actions(DriverManager.getWebDriver()).moveToElement((WebElement) locator).clickAndHold().build().perform()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully clicks and holds \" + locator + \" element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnew SerenityActions(getDriver()).moveToElement($(locator)).clickAndHold().build().perform()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully clicks and holds \" + locator + \" element\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + " e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("new SerenityActions(getDriver()).moveToElement(locator).clickAndHold().build().perform()")));
        }

        method.setParameters(parameters);

        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsSwitchToDefaultContent(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "switchDefaultContent");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tswitchToDefaultContent()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().defaultContent()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully switched to default content\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver().switchTo().defaultContent()")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsQuit(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "tearDown");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDriverManager.quitDriver()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully closed driver\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "quit()")));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().quit()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully closed driver\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "quit()")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsNavigateForward(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "forwardNavigation");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tnavigateForward()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().forward()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully navigated Forward\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDriverManager.getWebDriver().navigate().to(url)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().to(url)"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully navigated Forward\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsVerifyUrl(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyURL");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString text = getCurrentURL()"));
            ASTHelper.addStmt(block, new NameExpr("\tif(text.equals(typeText)) {\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify if current URL matches <a href ='\" +typeText+\"'>\"+typeText+\"</a>\",\"Validation Successfull\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t\telse {\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify if current URL matches <a href ='\" +typeText+\"'>\"+typeText+\"</a>\",\"Validation Failed\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString text = getDriver().getCurrentUrl()"));
            ASTHelper.addStmt(block, new NameExpr("\tassertTrue(text.equals(typeText))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully navigated back\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsGetUrl(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "getURL");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString text = getCurrentURL()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn getCurrentURL()"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString text = getDriver().getCurrentUrl()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully navigated Forward\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn getDriver().getCurrentUrl()"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsGetBrowserSize(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("Object",0), "browserSize");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("Object size = getBrowserSize()"));
            ASTHelper.addStmt(block, new NameExpr("Integer sizeOfBrowser = null"));
            ASTHelper.addStmt(block, new NameExpr("if(size!=null){\n\t\t\tGemTestReporter.addTestStep(\"Get Browser Size \",\"Browser Size fetched successfully.\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tsizeOfBrowser = Integer.valueOf(size.toString());"+ "\n\t\t}\n\t\telse\n\t\t{\n\t\t\tGemTestReporter.addTestStep(\"Get Browser Size \",\"Unable to fetch browser size.\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
            ASTHelper.addStmt(block, new NameExpr("return sizeOfBrowser"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("Object size = getDriver().manage().window().getSize()"));
            ASTHelper.addStmt(block, new NameExpr("Integer sizeOfBrowser = null"));
            ASTHelper.addStmt(block, new NameExpr("if(size!=null)"));
            ASTHelper.addStmt(block, new NameExpr("\tsizeOfBrowser = Integer.valueOf(size.toString())"));
            ASTHelper.addStmt(block, new NameExpr("return sizeOfBrowser"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsGetBrowserLocation(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("Object",0), "browserPosition");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("Object position = getBrowserLocation()"));
            ASTHelper.addStmt(block, new NameExpr("Integer positionOfBrowser = null"));
            ASTHelper.addStmt(block, new NameExpr("if(position!=null){\n\t\t\tGemTestReporter.addTestStep(\"Get Browser Position \",\"Browser Position fetched successfully.\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\tpositionOfBrowser = Integer.valueOf(position.toString());"+ "\n\t\t}\n\t\telse\n\t\t{\n\t\t\tGemTestReporter.addTestStep(\"Get Position Size \",\"Unable to fetch browser position.\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
            ASTHelper.addStmt(block, new NameExpr("return positionOfBrowser"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("Object position = getDriver().manage().window().getPosition()"));
            ASTHelper.addStmt(block, new NameExpr("Integer positionOfBrowser = null"));
            ASTHelper.addStmt(block, new NameExpr("if(position!=null)\t"));
            ASTHelper.addStmt(block, new NameExpr("\tpositionOfBrowser = Integer.valueOf(position.toString())"));
            ASTHelper.addStmt(block, new NameExpr("return positionOfBrowser"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsGetWindowHandle(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("Object",0), "windowHandle");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String windowHandle = getWindowHandle().toString()"));
            ASTHelper.addStmt(block, new NameExpr("if(windowHandle!=null){\n\t\t\tGemTestReporter.addTestStep(\"Get Window Handle \",\"Window Handle fetched successfully.\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\n\t\t}\n\t\telse\n\t\t{\n\t\t\tGemTestReporter.addTestStep(\"Get Window Handle \",\"Unable to fetch Window Handle.\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
            ASTHelper.addStmt(block, new NameExpr("return windowHandle"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String windowHandle = getDriver().getWindowHandle()"));
            ASTHelper.addStmt(block, new NameExpr("if(windowHandle==null){\n\t\t\tAssert.fail(\"Unable to get window handle\")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
            ASTHelper.addStmt(block, new NameExpr("return windowHandle"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsGetWindowHandles(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String",0), "windowHandles");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String windowHandles = getWindowHandles().toString()"));
            ASTHelper.addStmt(block, new NameExpr("if(windowHandles!=null){\n\t\t\tGemTestReporter.addTestStep(\"Get Window Handles \",\"Window Handles fetched successfully.\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\n\t\t}\n\t\telse\n\t\t{\n\t\t\tGemTestReporter.addTestStep(\"Get Window Handles \",\"Unable to fetch Window Handles.\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
            ASTHelper.addStmt(block, new NameExpr("return windowHandles"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String windowHandles = getDriver().getWindowHandles().toString()"));
            ASTHelper.addStmt(block, new NameExpr("if(windowHandles==null){\n\t\t\tAssert.fail(\"Unable to get window handles\")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
            ASTHelper.addStmt(block, new NameExpr("return windowHandles"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsGetPageSource(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String",0), "pageSource");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("String pageSource = getPageSource()"));
            ASTHelper.addStmt(block, new NameExpr("if(pageSource!=null){\n\t\t\tGemTestReporter.addTestStep(\"Get Page Source \",\"Page Source fetched successfully.\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\n\t\t}\n\t\telse\n\t\t{\n\t\t\tGemTestReporter.addTestStep(\"Get Page Source \",\"Unable to fetch Page Source.\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
            ASTHelper.addStmt(block, new NameExpr("return pageSource"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("String pageSource = getDriver().getPageSource()"));
            ASTHelper.addStmt(block, new NameExpr("if(pageSource==null){\n\t\t\tAssert.fail(\"Unable to get page source\")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
            ASTHelper.addStmt(block, new NameExpr("return pageSource"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsCloseCurrentTab(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "closeTab");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tcloseCurrentTab()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().close()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodSwitchToAlert(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "alertSwitch");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tswitchToAlert()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodAcceptAlert(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "alertAccept");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tacceptAlert()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert().accept()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodDismissAlert(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "alertDismiss");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tdismissAlert()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert().dismiss()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().switchTo().alert().sendKeys(input)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodScrollToTop(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollUp");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tscrollToTop()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();\n\t\t\tjs.executeScript(\"window.scrollTo(0, -document.body.scrollHeight)\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodScrollToBottom(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "scrollDown");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tscrollToBottom()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\t JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();\n\t\t\tjs.executeScript(\"window.scrollBy(0,document.body.scrollHeight)\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tpageScroll(x,y)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();\n\t\t\tjs.executeScript(\"window.scrollBy(\"+x+\",\"+y+\")\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tscrollAnElementToSpecificPosition(x,y)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tJavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();\n\t\t\tjs.executeScript(\"window.scrollBy(\"+x+\",\"+y+\")\")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodRefresh(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "refreshPage");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\trefresh()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().navigate().refresh()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
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
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        } else {
            List<Parameter> parameters = new LinkedList<>();
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
            method.setParameters(parameters);
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tTakesScreenshot scrShot =((TakesScreenshot)getDriver());\n\t\t\tFile SrcFile=scrShot.getScreenshotAs(OutputType.FILE);\n\t\t\tFile DestFile=new File(filePath);\n\t\t\tFileUtils.copyFile(SrcFile, DestFile)"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\t"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsMinimizeBrowser(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "minimizeGivenBrowser");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tminimizeBrowser()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        else {
                ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().manage().window().minimize()"));
                ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Verify Browser Maximized to Default Size \",\"Browser Maximization successful.\");"+ "\t}\n\t\tcatch(Exception e){"));
                ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Verify Browser Maximized to Default Size \",\"Unable to maximize browser.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
                ASTHelper.addStmt(block, new NameExpr("}"));
            }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsMaximizeBrowserToDefault(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "maximizeBrowserToDefault");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tSTATUS status = maximizeToDefaultBrowserSize()"));
            ASTHelper.addStmt(block, new NameExpr("Boolean maximizeStatus = Objects.equals(status, \"PASS\");"));
            ASTHelper.addStmt(block, new NameExpr("if(maximizeStatus) \t{\n\t\t\tGemTestReporter.addTestStep(\"Verify Browser Maximized to Default Size \",\"Browser Maximization successful.\", STATUS.PASS, takeSnapShot());"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Verify Browser Maximized to Default Size \",\"Browser Maximization successful.\" );"+ "\t}\n\t\t\telse {\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify Browser Maximized to Default Size \",\"Unable to maximize browser.\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Verify Browser Maximized to Default Size \",\"Unable to maximize browser.\");"+"\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().manage().window().maximize()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Verify Browser Maximized to Default Size \",\"Browser Maximization successful.\");"+ "\t}\n\t\t\tcatch(Exception e){"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Verify Browser Maximized to Default Size \",\"Unable to maximize browser.\");\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tgetDriver().manage().window().setPosition(new Point(x,y))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully minimizes browser\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());}

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
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tDimension newDimension = new Dimension(width, height);\n\t\t\tgetDriver().manage().window().setSize(newDimension)\t\t"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully minimizes browser\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());}

    public static void setLinkMethodsVerifyTitle(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, "verifyTitle");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString text = getTitle(getCurrentURL())"));
            ASTHelper.addStmt(block, new NameExpr("\tif(text.equals(typeText)) {\n\t\t\t\t" + "GemTestReporter.addTestStep(\"Verify page title \",\"Page title verified successfully. Expected: '\" +typeText+\"' Actual: '\" +text+\"'\", STATUS.PASS, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Page title verified successfully. Expected: '\" +typeText+\"' Actual: '\" +text+\"'\" );" + "\t}\n\t\t\telse {\n\t\t\t" + "GemTestReporter.addTestStep(\"Verify page title \",\"Unable to verify page title. Expected: '\" +typeText+\"' Actual: '\" +text+\"'\", STATUS.FAIL, takeSnapShot())"));
            ASTHelper.addStmt(block, new NameExpr("\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"Unable to verify page title. Expected: '\" +typeText+\"' Actual: '\" +text+\"'\" );" + "\t}\n\t\t}\n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString title = getDriver().getTitle()"));
            ASTHelper.addStmt(block, new NameExpr("\tassertTrue(\"Actual title: \" + title, title.equals(typeText))"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully verifies title\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.TITLE" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkMethodsGetTitle(CompilationUnit c) throws IOException {
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.createReferenceType("String", 0), "getTitle");
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        if (readProperties("Framework").contains("GEMJAR")) {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString text = getTitle()"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn getCurrentURL()"));
        } else {
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tString text = getDriver().getTitle()"));
            ASTHelper.addStmt(block, new NameExpr("\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User successfully navigated Forward\"" + ")"));
            ASTHelper.addStmt(block, new NameExpr("} \n\t\tcatch(" + "Exception e" + "){" + "\n\t\t\tSettings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
            ASTHelper.addStmt(block, new NameExpr("}\n\t\treturn getDriver().getCurrentUrl()"));
            Settings.LOGGER.info(String.valueOf(new NameExpr("getDriver()" + "." + "get" + "(" + "Settings.URL" + ")")));
        }
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
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
            ASTHelper.addStmt(block, new NameExpr("try{\n\t\t\tclick(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
            ASTHelper.addStmt(block, new NameExpr("}" + "\n\t\tcatch(" + "Exception e" + "){\n\t\t\t" + "Settings" + "." + "LOGGER" + "." + "info(" + "\"User gets an exception: \"" + "+" + "e" + ")"));
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
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

}
