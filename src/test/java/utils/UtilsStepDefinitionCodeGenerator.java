package utils;

import japa.parser.ASTHelper;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.PackageDeclaration;
import japa.parser.ast.body.*;
import japa.parser.ast.expr.*;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.type.ClassOrInterfaceType;
import locatorstrategyform.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import pageobjectgenerator.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class UtilsStepDefinitionCodeGenerator {

    private static String meaningFulName = "";

    private static String pageName = "^For the <page> page, ";

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
    public static CompilationUnit createEnhancedCompilationUnit(String name, String type) {
        CompilationUnit cu = new CompilationUnit();
        cu.setPackage(new PackageDeclaration(ASTHelper.createNameExpr(name)));
        cu.setImports(UtilsStepDefinitionCodeGenerator.getAllImports(type));
        return cu;

    }

    /**
     * set the TypeDeclaration of a CompilationUnit i.e., whether is a class or an
     * interface
     *
     * @param c
     * @param className
     */
    public static void setTypeDeclaration(CompilationUnit c, String className) {
        // create the type declaration and class
        ClassOrInterfaceDeclaration type = new ClassOrInterfaceDeclaration(ModifierSet.PUBLIC, false, className);
        ASTHelper.addTypeDeclaration(c, type);
    }

    /**
     * adds a WebDriver instance to the CompilationUnit c
     *
     * @param c CompilationUnit
     */
    public static void setWebDriverVariable(CompilationUnit c) {
        //setting the driver for the current class
        VariableDeclarator v = new VariableDeclarator();
        v.setId(new VariableDeclaratorId("driver"));
        FieldDeclaration f = ASTHelper.createFieldDeclaration(ModifierSet.PRIVATE,
                ASTHelper.createReferenceType("WebDriver", 0), v);

        ASTHelper.addMember(c.getTypes().get(0), f);
    }

    /**
     * adds a WebDriver instance to the CompilationUnit c
     *
     * @param c CompilationUnit
     */
    public static void setStepDefinitionVariable(CompilationUnit c, String classname) {
        //setting the variable for step definition
        VariableDeclarator v = new VariableDeclarator();
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        if (classname.contains("Utils"))
            nameOfFile = "utils";
        else if (classname.contains("UtilFunctions")) nameOfFile = "utilFunctions";
        v.setId(new VariableDeclaratorId(nameOfFile));
        v.setInit(new ObjectCreationExpr(null, new ClassOrInterfaceType(null, classname), null));
        FieldDeclaration f = ASTHelper.createFieldDeclaration(ModifierSet.PRIVATE,
                ASTHelper.createReferenceType(classname, 0), v);

        ASTHelper.addMember(c.getTypes().get(0), f);
    }

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
        constructor.setJavaDoc(
                new JavadocComment("\n\t\tPage Object for " + s.getName() + " (" + s.getStateId() + ") \n\t"));

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

    public static String readProperties(String property) throws IOException { // Function to read Data from Properties File
        FileReader read = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
        Properties credential = new Properties();
        credential.load(read);
        return credential.getProperty(property);
    }

    /**
     * adds Selenium imports to the compilation unit
     */
    private static List<ImportDeclaration> getAllImports(String type) {
        List<ImportDeclaration> imports = new LinkedList<>();

        if (StringUtils.equalsIgnoreCase(type, "PageObject")) {
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium"), false, true));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.FindBy"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.PageFactory"), false, false));
        } else {
            //adding imports for stepDefinition class
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.And"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.Before"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.Given"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.Then"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("io.cucumber.java.en.When"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.By"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.support.PageFactory"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("java.util.concurrent.TimeUnit"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("implementation" + "." + Settings.LOCATOR_FILE_NAME + "Implementation"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("locators" + "." + Settings.LOCATOR_FILE_NAME), false, false));
            imports.add(new ImportDeclaration(new NameExpr("implementation" + "." + "UtilsImplementation"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("utils" + "." + "UtilFunctions"), false, false));
            imports.add(new ImportDeclaration(new NameExpr("org.openqa.selenium.*"), false, false));

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
        setWebDriverVariable(c);
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

            FieldDeclaration field = ASTHelper.createFieldDeclaration(ModifierSet.PRIVATE,
                    ASTHelper.createReferenceType("WebElement", 0), webElement);
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

    // before class for driver initialisation
    public static void setBeforeClass(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String blockToEnter = "driver";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = Settings.BEFORE_FUNCTION;
        annotationType = "Before";
        Settings.LOGGER.info("Step created for initialisation of the driver");
        NameExpr typeOfException = new NameExpr("InterruptedException");
        List<NameExpr> throws_ = new LinkedList<>();
        throws_.add(typeOfException);
        MethodDeclaration method = new MethodDeclaration(null, ModifierSet.PUBLIC, null, null, ASTHelper.VOID_TYPE, functionName, null, 0, throws_, null);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        na.setName(new NameExpr(annotationType));
        ASTHelper.addStmt(block, new NameExpr("//This function is for initialisation of driver"));
        ASTHelper.addStmt(block, new NameExpr("System" + "." + "setProperty(" + "\"webdriver.chrome.driver\"" + "," + "\"src/test/resources/drivers/chromedriver.exe\"" + ")"));
        ASTHelper.addStmt(block, new NameExpr(Settings.LOCATOR_FILE_NAME + " index" + "=" + "PageFactory" + "." + "initElements(" + Settings.LOCATOR_FILE_NAME + "." + blockToEnter + "," + Settings.LOCATOR_FILE_NAME + "." + "class" + ")"));
        ASTHelper.addStmt(block, new NameExpr(Settings.LOCATOR_FILE_NAME + "." + blockToEnter + "." + "manage(" + ")" + "." + "window(" + ")" + "." + "maximize(" + ")"));
        ASTHelper.addStmt(block, new NameExpr(Settings.LOCATOR_FILE_NAME + "." + blockToEnter + "." + "manage(" + ")" + "." + "timeouts(" + ")" + "." + "wait(" + "5000" + ")"));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionMethodValueThenVisibility(CompilationUnit c, Field field,
                                                                      boolean textOrVisibilityParameter) {

        String functionName = getMeaningFullName(field.getName(), false);
        meaningFulName = getMeaningFullName(field.getName(), true);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        if (!textOrVisibilityParameter) {
            functionName = "verify" + functionName + "IsDisplayed";
            annotationValue = "\"" + "^User verifies " + meaningFulName + " is visible" + "$" + "\"";
            blockToEnter = functionName + "(" + ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        } else {
            functionName = "verify" + functionName + "Value";
            annotationValue = "\"" + "^" + "User verifies " + "\\\"(.*)\\\"" + meaningFulName + " value" + "$" + "\"";
            blockToEnter = functionName + "(" + "typeText" + ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
            method.setParameters(parameters);
        }
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionMethod(CompilationUnit c, Field field, String type, String className) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);

        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        if (StringUtils.equalsIgnoreCase(type, "button") || StringUtils.equalsIgnoreCase(type, "click") || StringUtils.equalsIgnoreCase(type, "image") || StringUtils.equalsIgnoreCase(type, "link")) {
            functionName = Settings.USER_CLICK_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_CLICK_ANNOTATION + " " + meaningFulName + " " + type + "$" + "\""; //changed the step definition
            textToEnter = "clickOn" + meaningFulName;
            blockToEnter = textToEnter + "(" + ")";
            annotationType = "When";
            Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);
        }
        if (StringUtils.equalsIgnoreCase(type, "click")) {
            functionName = Settings.USER_SCROLL_CLICK_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_SCROLL_CLICK_ANNOTATION + " " + meaningFulName + " " + "element" + "$" + "\""; //changed the step definition
            textToEnter = "scrollClick" + meaningFulName;
            blockToEnter = textToEnter + "(" + ")";
            annotationType = "When";
            Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);
        }
        if (StringUtils.equalsIgnoreCase(type, "input")) {
            functionName = Settings.USER_INPUT_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_INPUT_ANNOTATION + " input for " + meaningFulName + "$" + "\"";
            textToEnter = "typeTextInto" + meaningFulName;
            blockToEnter = textToEnter + "(" + "typeText" + ")";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
            annotationType = "When";

        }
        if (StringUtils.equalsIgnoreCase(type, "dropdown")) {
            functionName = Settings.USER_SELECT_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_SELECT_ANNOTATION + " " + meaningFulName + " " + type + "$" + "\""; //changed the step definition
            textToEnter = "select" + meaningFulName;
            blockToEnter = textToEnter + "(" + "selectValue" + ")";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "selectValue"));
            annotationType = "When";

        }
        if (StringUtils.equalsIgnoreCase(type, "image")) {
            functionName = Settings.USER_IMAGE_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_IMAGE_ANNOTATION + " " + meaningFulName + " " + type + "$" + "\""; //changed the step definition
            textToEnter = "uploadFileTo" + meaningFulName;
            blockToEnter = textToEnter + "(" + "filePath" + ")";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
            annotationType = "When";
        }
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        Settings.LOGGER.info(String.valueOf(new NameExpr(nameOfFile + "." + blockToEnter)));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionAndNavigateBack(CompilationUnit c, Field field, String type, String className) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);

        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = Settings.USER_CLICK_FUNCTION + meaningFulName + Settings.USER_NAVIGATE_FUNCTION;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_CLICK_ANNOTATION + " " + meaningFulName + " "+type+" " + Settings.USER_NAVIGATE_ANNOTATION + "$" + "\""; //changed the step definition
        textToEnter = "clickOn" + meaningFulName + Settings.USER_NAVIGATE_FUNCTION;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        Settings.LOGGER.info(String.valueOf(new NameExpr(nameOfFile + "." + blockToEnter)));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    //is selected verification step
    public static void setLinkStepDefinitionMethodThenSelected(CompilationUnit c, Field field, String className) {
        String functionName = getMeaningFullName(field.getName(), false);
        meaningFulName = getMeaningFullName(field.getName(), true);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        functionName = "verify" + functionName + "IsSelected";
        annotationValue = "\"" + pageName.replace("<page>", className) + "User verifies " + meaningFulName + " is selected" + "$" + "\"";
        blockToEnter = functionName + "(" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    //verification of attribute value
    public static void setLinkStepDefinitionAttributeGetter(CompilationUnit c, Field field, boolean valueVerification, String className) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        if (valueVerification) {
            functionName = Settings.USER_VERIFIES_VALUE + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + "User verifies " + "\\\"(.*)\\\"" + " is the value for " + meaningFulName + "$" + "\""; //changed the step definition
            textToEnter = "verifyValueFrom" + meaningFulName;
            blockToEnter = textToEnter + "(" + "valueOfElement" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
            annotationType = "Then";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "valueOfElement"));
        } else {
            functionName = Settings.USER_GET_ATTRIBUTE_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_GET_ATTRIBUTE_ANNOTATION + meaningFulName + " element" + "$" + "\""; //changed the step definition
            textToEnter = "getAttributeFrom" + meaningFulName;
            blockToEnter = textToEnter + "(" + "attributeValue" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
            annotationType = "When";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeValue"));
        }
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
//        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());

    }

    public static void setLinkStepDefinitionHoverOver(CompilationUnit c, Field field, String className) {
        String functionName = getMeaningFullName(field.getName(), false);
        meaningFulName = getMeaningFullName(field.getName(), true);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        functionName = "hoverOver" + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + "User hovers over " + meaningFulName + " and checks \\\"(.*)\\\" label$" + "\""; //changed the step definition
        blockToEnter = functionName + "("+"label"+")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "label"));
        // add a body to the method
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionClickAndHold(CompilationUnit c) {
//        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_CLICK_HOLD;
        annotationValue = "\"^User clicks and holds " + "\\\"(.*?)\\\"" + " element" + "$" + "\""; //changed the step definition
        textToEnter = "clicksAndHold";
        blockToEnter = textToEnter + "(" + "locator" + ")";
//        blockToEnter = functionName + "(" + "" + ")";
        annotationType = "Then";
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("By", 0), "locator"));

        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
//        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());

    }
        public static void setLinkStepDefinitionElementPresence(CompilationUnit c, Field field, String className) {
        String functionName = getMeaningFullName(field.getName(), false);
        meaningFulName = getMeaningFullName(field.getName(), true);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        functionName = "verify" + meaningFulName+"Exists";
        annotationValue = "\"" + pageName.replace("<page>", className) + "User verifies " + meaningFulName + " is present on screen$" + "\""; //changed the step definition
        blockToEnter = functionName + "("+")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionScrollToView(CompilationUnit c, Field field, String className) {
        String functionName = getMeaningFullName(field.getName(), false);
        meaningFulName = getMeaningFullName(field.getName(), true);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        functionName = "scrollTo" + meaningFulName+"Element";
        annotationValue = "\"" + pageName.replace("<page>", className) + "User scrolls to " + meaningFulName + " element$" + "\""; //changed the step definition
        blockToEnter = functionName + "("+")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionChecked(CompilationUnit c, Field field, String className) {
        String functionName = getMeaningFullName(field.getName(), false);
        meaningFulName = getMeaningFullName(field.getName(), true);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        functionName = "verifyElementSelectedFor" + field.getName();
        annotationValue = "\"" + pageName.replace("<page>", className) + "User verifies " + meaningFulName + " checkbox is selected$" + "\""; //changed the step definition
        blockToEnter = functionName + "(" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionNotChecked(CompilationUnit c, Field field, String className) {
        String functionName = getMeaningFullName(field.getName(), false);
        meaningFulName = getMeaningFullName(field.getName(), true);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        functionName = "verifyElementNotSelectedFor" + field.getName();
        annotationValue = "\"" + pageName.replace("<page>", className) + "User verifies " + meaningFulName + " checkbox is not selected$" + "\""; //changed the step definition
        blockToEnter = functionName + "(" + ")";
        method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();
        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);
        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionAttributeVerification(CompilationUnit c, Field field, String className) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = Settings.USER_VERIFIES_VALUE_ATTRIBUTE + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + "User verifies " + "\\\"(.*)\\\"" + " attribute for " + meaningFulName + " as "  +"\\\"(.*)\\\""+ "$"+"\""; //changed the step definition
        textToEnter = "verifyAttributeValueFor" + meaningFulName;
        blockToEnter = textToEnter + "(" + "attributeName" + ","+"attributeValue"+")";
//        blockToEnter = functionName + "(" + "" + ")";
        annotationType = "Then";
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeName"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "attributeValue"));
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
//        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//This function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());

    }

    public static void setLinkStepDefinitionTextGetter(CompilationUnit c, Field field, String className) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_GETTEXT_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_GETTEXT_ANNOTATION + " " + meaningFulName + " " + "element" + "$" + "\""; //changed the step definition
        textToEnter = "getTextFrom" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());

    }

    public static void setLinkStepDefinitionTypeTextAndEnter(CompilationUnit c, Field field, String className) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_TYPE_AND_ENTER_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_TYPE_AND_ENTER_ANNOTATION + " " + meaningFulName + " " + "element and presses enter" + "$" + "\""; //changed the step definition
        blockToEnter = functionName + "(" + "typeText" + ")";
        annotationType = "When";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());

    }

    public static void setLinkStepDefinitionTypeTextAndTab(CompilationUnit c, Field field, String className) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_TYPE_AND_TAB_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_TYPE_AND_TAB_ANNOTATION + " " + meaningFulName + " " + "element and presses tab" + "$" + "\""; //changed the step definition
        blockToEnter = functionName + "(" + "typeText" + ")";
        annotationType = "When";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());

    }

    public static void setLinkStepDefinitionClear(CompilationUnit c, Field field, String className, String type) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_CLEAR_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_CLEAR_ANNOTATION + " " + "text for " + meaningFulName + " " + type + " element" + "$" + "\""; //changed the step definition
        textToEnter = "clear" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());

    }

    public static void setLinkStepDefinitionVerifyClear(CompilationUnit c, Field field, String className, String type) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_VERIFY_CLEAR_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_VERIFY_CLEAR_ANNOTATION + " " + "value for " + meaningFulName + " " + type + " element is cleared" + "$" + "\""; //changed the step definition
        textToEnter = "verifyValueClearedFor" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "Then";
//        blockToEnter = functionName + "(" + "" + ")";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());

    }

    public static void setLinkStepDefinitionMethodGiven(CompilationUnit c, String homePage) {

        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        if (StringUtils.isNotBlank(homePage)) {
            functionName = Settings.USER_HOME_PAGE;
            annotationType = "Given";
            annotationValue = "\"" + Settings.USER_HOME_PAGE_ANNOTATION + "$" + "\"";
            textToEnter = Settings.USER_HOME_PAGE;
            blockToEnter = textToEnter + "(" + ")";

        }
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionDoubleCLick(CompilationUnit c, Field field, String className) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_DOUBLE_CLICK_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_DOUBLE_CLICK_ANNOTATION + " " + meaningFulName + " " + "element" + "$" + "\""; //changed the step definition
        textToEnter = "doubleClickOn" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionMethodThenVisibility(CompilationUnit c, Field field,
                                                                 boolean textOrVisibilityParameter, String className, String type) {
        if (type.equals("a")) {
            type = "link";
        }
        String functionName = getMeaningFullName(field.getName(), false);
        meaningFulName = getMeaningFullName(field.getName(), true);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String annotationValue = "";
        String blockToEnter = "";
        String annotationType = "";
        annotationType = "Then";
        MethodDeclaration method = null;
        List<Parameter> parameters = new LinkedList<>();
        if (!textOrVisibilityParameter) {
            functionName = "verify" + functionName + "IsDisplayed";
            annotationValue = "\"" + pageName.replace("<page>", className) + "User verifies" + " " + meaningFulName + " " + type + " is visible" + "$" + "\"";
            blockToEnter = functionName + "(" + ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        } else {
            functionName = "verify" + functionName + "Text";
            annotationValue = "\"" + pageName.replace("<page>", className) + "User verifies" + " " + "\\\"(.*)\\\"" + " is the text of " + meaningFulName + " " + type + "$" + "\"";
            blockToEnter = functionName + "(" + "typeText" + ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
            method.setParameters(parameters);
        }
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        ASTHelper.addStmt(block, new NameExpr("//The below function is for web element @FindBy(" + Settings.LOCATOR_FILE_NAME + "." + field.getName() + ")"));
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionMethodThenClickable(CompilationUnit c, Field field, String className) {

        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_CLICKABLE_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_CLICKABLE_ANNOTATION + " " + meaningFulName + " " + "element" + "$" + "\""; //changed the step definition
        textToEnter = "elementIsClickable" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "Then";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionMethodContains(CompilationUnit c, Field field, String className) {

        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_CONTAINS_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_CONTAINS_ANNOTATION + " " + meaningFulName + " " + "element" + "$" + "\""; //changed the step definition
        textToEnter = "verify" + meaningFulName + "ContainsText";
        blockToEnter = textToEnter + "(" + "typeText" + ")";
        annotationType = "Then";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "typeText"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionMethodThenEnabled(CompilationUnit c, Field field, String className, String type) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_ENABLED_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_ENABLED_ANNOTATION + " " + meaningFulName + " " + type + " is enabled" + "$" + "\""; //changed the step definition
        textToEnter = "verify" + meaningFulName + "IsEnabled";
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionMethodDeselects(CompilationUnit c, Field field, String className, String type) {
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();

        functionName = Settings.USER_DESELECT_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_DESELECT_ANNOTATION + " " + meaningFulName + " " + type + "$" + "\""; //changed the step definition
        textToEnter = "deselect" + meaningFulName;
        blockToEnter = textToEnter + "(" + "deselectValue" + ")";
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "deselectValue"));
        annotationType = "When";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
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
                ASTHelper.addStmt(block, new NameExpr(field.getVariableName() + "." + field.getDefaultAction() + "(args"
                        + f.getFormFieldList().indexOf(field) + ")"));
                break;

            case "click":
                ASTHelper.addStmt(block, new NameExpr(field.getVariableName() + "." + field.getDefaultAction() + "()"));
                break;
            default:
                break;
        }

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
        for (String name : res.split("_")
        ) {
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
        String fileNameToCreate = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                + File.separator + "java" + File.separator + directoryName + className;
        // String fileNameToCreate = System.getProperty("user.dir") +poName;
        File f = new File(fileNameToCreate + ".java");

        if (BooleanUtils.isTrue(stepGeneration)) {
            data = StringUtils.replace(c.toString(), "xpath = ", "").toString();
        } else {
            data = c.toString();
        }
        FileUtils.writeStringToFile(f, data);

    }

    public static void setLinkStepDefinitionNavigateBack(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "navigateBack";
        annotationType = "When";
        annotationValue = "\"" + Settings.USER_NAVIGATE_BACK + "$" + "\"";
        textToEnter = "backwardNavigation";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionActiveElement(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "switchActiveElement";
        annotationType = "When";
        annotationValue = "\"" + Settings.SWITCH_ACTIVE_ELEMENT + "$" + "\"";
        textToEnter = "switchActiveElement";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionParentFrame(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "switchParentFrame";
        annotationType = "When";
        annotationValue = "\"" + Settings.SWITCH_PARENT_FRAME + "$" + "\"";
        textToEnter = "switchParentFrame";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionDefaultContent(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "switchDefaultContent";
        annotationType = "When";
        annotationValue = "\"" + Settings.SWITCH_DEFAULT_CONTENT + "$" + "\"";
        textToEnter = "switchDefaultContent";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionSwitchWindow(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "switchWindow";
        annotationType = "When";
        annotationValue = "\"" + Settings.SWITCH_WINDOW + "$" + "\"";
        textToEnter = "switchWindow";
        blockToEnter = textToEnter + "(nameOrHandle" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "nameOrHandle"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }
    public static void setLinkStepDefinitionWait(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "waitFor";
        annotationType = "When";
        annotationValue = "\"" + Settings.USER_WAITS + "$" + "\"";
        textToEnter = "wait";
        blockToEnter = textToEnter + "(duration*1000" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "duration"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionSwitchFrame(CompilationUnit c, boolean argumentType) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "switchFrame";
        annotationType = "When";
        textToEnter = "switchFrame";
        if (argumentType) {
            annotationValue = "\"" + Settings.SWITCH_FRAME_STRING + "$" + "\"";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "nameOrId"));
            blockToEnter = textToEnter + "(nameOrId" + ")";
        } else {
            annotationValue = "\"" + Settings.SWITCH_FRAME_INT + "$" + "\"";
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("int", 0), "index"));
            blockToEnter = textToEnter + "(index" + ")";
        }
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionQuit(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "closeBrowser";
        annotationType = "Then";
        annotationValue = "\"" + Settings.USER_CLOSES_BROWSER + "$" + "\"";
        textToEnter = "tearDown";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionNavigateForward(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "navigateForward";
        annotationType = "When";
        annotationValue = "\"" + Settings.USER_NAVIGATE_FORWARD + "$" + "\"";
        textToEnter = "forwardNavigation";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionNavigateTo(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        functionName = "navigateTo";
        annotationType = "Given";
        annotationValue = "\"" + Settings.USER_NAVIGATE_TO + "\"";
        textToEnter = "navigateTo";
        blockToEnter = textToEnter + "(url" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setClickStepDefinitionMethod(CompilationUnit c, Field field, String type, String className) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);

        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        if (StringUtils.equalsIgnoreCase(type, "button") || StringUtils.equalsIgnoreCase(type, "click") || StringUtils.equalsIgnoreCase(type, "image") || StringUtils.equalsIgnoreCase(type, "a")) {
            functionName = Settings.USER_CLICK_FUNCTION + meaningFulName;
            annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_CLICK_ANNOTATION + " " + meaningFulName + " " + type + "$" + "\""; //changed the step definition
            textToEnter = "clickOn" + meaningFulName;
            blockToEnter = textToEnter + "(" + ")";
            annotationType = "When";
            Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);
        }

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        Settings.LOGGER.info(String.valueOf(new NameExpr(nameOfFile + "." + blockToEnter)));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionVerifyUrl(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        functionName = "verifyUrl";
        annotationType = "And";
        annotationValue = "\"" + Settings.USER_VERIFY_URL + "\"";
        textToEnter = "verifyURL";
        blockToEnter = textToEnter + "(url" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionGetUrl(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "getUrl";
        annotationType = "Then";
        annotationValue = "\"" + Settings.USER_GET_URL + "$" + "\"";
        textToEnter = "getURL";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionGetTitle(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "getTitle";
        annotationType = "Then";
        annotationValue = "\"" + Settings.USER_GET_TITLE + "$" + "\"";
        textToEnter = "getTitle";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionVerifyTitle(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "title"));
        functionName = "verifyTitle";
        annotationType = "And";
        annotationValue = "\"" + Settings.USER_VERIFY_TITLE + "\"";
        textToEnter = "verifyTitle";
        blockToEnter = textToEnter + "(title" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionMethodForSelect(CompilationUnit c, Field field, String type, String className) {
        if (type.equals("a")) {
            type = "link";
        }
        meaningFulName = UtilsStepDefinitionCodeGenerator.getMeaningFullName(field.getName(), false);
        Settings.LOGGER.info("Name of field: " + meaningFulName);

        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = Settings.USER_SELECTS_FUNCTION + meaningFulName;
        annotationValue = "\"" + pageName.replace("<page>", className) + Settings.USER_SELECTS_ANNOTATION + " " + meaningFulName + " " + type + "$" + "\""; //changed the step definition
        textToEnter = "select" + meaningFulName;
        blockToEnter = textToEnter + "(" + ")";
        annotationType = "When";
        Settings.LOGGER.info("Step created: " + annotationValue + "and function created: " + functionName);

        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);
        String firstLetter = Settings.LOCATOR_FILE_NAME.substring(0, 1).toLowerCase();
        String nameOfFile = firstLetter + Settings.LOCATOR_FILE_NAME.substring(1);
        ASTHelper.addStmt(block, new NameExpr(nameOfFile + "." + blockToEnter));
        Settings.LOGGER.info(String.valueOf(new NameExpr(nameOfFile + "." + blockToEnter)));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionPressEnter(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "pressEnter";
        annotationType = "And";
        annotationValue = "\"" + Settings.USER_PRESSES_ENTER + "\"";
        textToEnter = "pressEnter";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utilFunctions." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionCopy(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "copy";
        annotationType = "And";
        annotationValue = "\"" + Settings.USER_COPY + "\"";
        textToEnter = "copy";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utilFunctions." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionPaste(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "paste";
        annotationType = "Then";
        annotationValue = "\"" + Settings.USER_PASTE + "\"";
        textToEnter = "paste";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utilFunctions." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionGetWindowFocus(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "windowHandle"));
        functionName = "windowFocus";
        annotationType = "Then";
        annotationValue = "\"" + Settings.USER_WINDOW_FOCUS + "\"";
        textToEnter = "getWindowFocus";
        blockToEnter = textToEnter + "(" + "windowHandle" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utilFunctions." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionSelectAll(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "selectAll";
        annotationType = "When";
        annotationValue = "\"" + Settings.USER_SELECT_ALL + "\"";
        textToEnter = "selectAll";
        blockToEnter = textToEnter + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utilFunctions." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionMaximizeBrowserToDefault(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "maximizeBrowserToDefault";
        annotationType = "When";
        annotationValue = "\"" +Settings.MAXIMIZE_TO_DEFAULT + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionMinimizeBrowser(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "minimizeGivenBrowser";
        annotationType = "When";
        annotationValue = "\"" +Settings.MINIMIZE_TO_DEFAULT + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionGetBrowserSize(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "browserSize";
        annotationType = "When";
        annotationValue = "\"" +Settings.BROWSER_SIZE + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionSetBrowserSize(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "setSizeOfBrowser";
        annotationType = "When";
        annotationValue = "\"" +Settings.SET_BROWSER_SIZE + "\"";
        blockToEnter = functionName + "(" + "width"+","+"height"+ ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "width"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "height"));
        method.setParameters(parameters);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionSetBrowserPosition(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "setPositionOfBrowser";
        annotationType = "When";
        annotationValue = "\"" +Settings.SET_BROWSER_POSITION + "\"";
        blockToEnter = functionName + "(" + "x"+","+"y"+ ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "y"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionGetBrowserLocation(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "browserPosition";
        annotationType = "When";
        annotationValue = "\"" +Settings.BROWSER_POSITION + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionGetWindowHandle(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "windowHandle";
        annotationType = "When";
        annotationValue = "\"" +Settings.GET_WINDOW_HANDLE + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionGetWindowHandles(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "windowHandles";
        annotationType = "When";
        annotationValue = "\"" +Settings.GET_WINDOW_HANDLES + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionGetPageSource(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "pageSource";
        annotationType = "When";
        annotationValue = "\"" +Settings.GET_PAGE_SOURCE + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionCloseCurrentTab(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "closeTab";
        annotationType = "When";
        annotationValue = "\"" +Settings.CLOSE_CURRENT_TAB + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }
    public static void setLinkStepDefinitionCloseTabAndSwitch(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "closeTabAndSwitch";
        annotationType = "When";
        annotationValue = "\"" +Settings.CLOSE_TAB_AND_SWITCH + "\"";
        blockToEnter = functionName + "(" +"windowName" +")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "windowName"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utilFunctions." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }
    public static void setLinkStepDefinitionSwitchToAlert(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "alertSwitch";
        annotationType = "When";
        annotationValue = "\"" +Settings.SWITCH_ALERT + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }
    public static void setLinkStepDefinitionAcceptAlert(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "alertAccept";
        annotationType = "When";
        annotationValue = "\"" +Settings.ACCEPT_ALERT + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionDismissAlert(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "alertDismiss";
        annotationType = "When";
        annotationValue = "\"" +Settings.DISMISS_ALERT + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionAlertInput(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "inputForAlert";
        annotationType = "When";
        annotationValue = "\"" +Settings.ALERT_INPUT + "\"";
        blockToEnter = functionName + "(" +"input"+ ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "input"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionScrollToTop(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "scrollUp";
        annotationType = "When";
        annotationValue = "\"" +Settings.SCROLL_UP + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionScrollToBottom(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "scrollDown";
        annotationType = "When";
        annotationValue = "\"" +Settings.SCROLL_DOWN + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionPageScroll(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "scrollPage";
        annotationType = "When";
        annotationValue = "\"" +Settings.SCROLL_PAGE + "\"";
        blockToEnter = functionName + "(" + "x"+","+"y"+ ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "y"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionScrollElementToPosition(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "elementScroll";
        annotationType = "When";
        annotationValue = "\"" +Settings.SCROLL_ELEMENT + "\"";
        blockToEnter = functionName + "(" + ")";
        blockToEnter = functionName + "(" + "x"+","+"y"+ ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "x"));
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("Integer", 0), "y"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionNavigateToUrl(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "urlNavigation";
        annotationType = "When";
        annotationValue = "\"" +Settings.NAVIGATE_URL + "\"";
        blockToEnter = functionName + "(" +"url"+ ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "url"));
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionRefresh(CompilationUnit c) {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";

        List<Parameter> parameters = new LinkedList<>();
        functionName = "refreshPage";
        annotationType = "When";
        annotationValue = "\"" +Settings.REFRESH_PAGE + "\"";
        blockToEnter = functionName + "(" + ")";
        MethodDeclaration method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
        method.setParameters(parameters);
        // add a body to the method
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

    public static void setLinkStepDefinitionTakeSnapshot(CompilationUnit c) throws IOException {
        String functionName = "";
        String annotationValue = "";
        String textToEnter = "";
        String blockToEnter = "";
        String annotationType = "";
        List<Parameter> parameters = new LinkedList<>();
        functionName = "takeScreenshot";
        annotationType = "When";
        annotationValue = "\"" + Settings.TAKE_SNAPSHOT + "\"";
        MethodDeclaration method = null;
        if(readProperties("Framework").contains("GEMJAR")) {
            blockToEnter = functionName + "(" + ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
            method.setParameters(parameters);
            // add a body to the method
        }
        else
        {
            blockToEnter = functionName + "(" +"filePath"+ ")";
            method = new MethodDeclaration(ModifierSet.PUBLIC, ASTHelper.VOID_TYPE, functionName);
            parameters.add(ASTHelper.createParameter(ASTHelper.createReferenceType("String", 0), "filePath"));
            method.setParameters(parameters);
        }
        BlockStmt block = new BlockStmt();
        method.setBody(block);
        NormalAnnotationExpr na = new NormalAnnotationExpr();
        na.setName(new NameExpr(annotationType));
        List<MemberValuePair> list_mvp = new LinkedList<MemberValuePair>();
        MemberValuePair mvp = new MemberValuePair();

        List<AnnotationExpr> list_espr = new LinkedList<AnnotationExpr>();
        mvp = new MemberValuePair("xpath", new NameExpr(annotationValue));
        list_mvp.add(mvp);
        na.setPairs(list_mvp);
        list_espr.add(0, na);

        method.setAnnotations(list_espr);

        ASTHelper.addStmt(block, new NameExpr("utils." + blockToEnter));
        ASTHelper.addMember(c.getTypes().get(0), method);
        Settings.LOGGER.info(method.toString());
        Settings.LOGGER.info(c.getTypes().get(0).toString());
    }

}