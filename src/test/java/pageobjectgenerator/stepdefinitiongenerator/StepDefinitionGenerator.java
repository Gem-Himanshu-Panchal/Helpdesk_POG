
package pageobjectgenerator.stepdefinitiongenerator;

import annotation.LocatorType;
import japa.parser.ast.CompilationUnit;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjectgenerator.PageMethodGenerator;
import pageobjectgenerator.Settings;
import serializer.LocatorSerializer;
import utils.UtilsStepDefinitionCodeGenerator;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class StepDefinitionGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(StepDefinitionGenerator.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = PageMethodGenerator.class.getClassLoader();
        Class aClass = classLoader.loadClass("locators" + "." + Settings.LOCATOR_FILE_NAME);
        generateStepMethods(aClass.getDeclaredFields(), aClass);
    }

    public static void generateStepMethods(Field[] fields, Class aClass) throws IOException {
        CompilationUnit c = UtilsStepDefinitionCodeGenerator.createEnhancedCompilationUnit("stepdefinition", "StepDefinition");
        UtilsStepDefinitionCodeGenerator.setTypeDeclaration(c, aClass.getSimpleName() + "StepDefinition");

        UtilsStepDefinitionCodeGenerator.setStepDefinitionVariable(c, Settings.LOCATOR_FILE_NAME + "Implementation");
        String locator;
        String locatorType;

        //setting before class for driver initialisation
//        UtilsStepDefinitionCodeGenerator.setBeforeClass(c);

        for (Field field : fields) {
            locator = " ";
            locatorType = " ";
            Settings.LOGGER.info(field.getName());

            Annotation[] ann = field.getDeclaredAnnotations();

            for (Annotation an : ann) {
                if (an instanceof FindBy) {
                    FindBy findBY = (FindBy) an;
                    if (!findBY.xpath().isEmpty()) {
                        locator = findBY.xpath();
                        Settings.LOGGER.info(findBY.xpath());

                    } else if (!findBY.id().isEmpty()) {
                        locator = findBY.id();
                        Settings.LOGGER.info(findBY.id());

                    } else if (!findBY.className().isEmpty()) {
                        locator = findBY.className();
                        Settings.LOGGER.info(findBY.className());

                    } else if (!findBY.name().isEmpty()) {
                        locator = findBY.name();
                        Settings.LOGGER.info(findBY.name());

                    } else if (!findBY.linkText().isEmpty()) {
                        locator = findBY.linkText();
                        Settings.LOGGER.info(findBY.linkText());

                    } else if (!findBY.partialLinkText().isEmpty()) {
                        locator = findBY.partialLinkText();
                        Settings.LOGGER.info(findBY.partialLinkText());

                    } else if (!findBY.tagName().isEmpty()) {
                        locator = findBY.tagName();
                        Settings.LOGGER.info(findBY.xpath());

                    } else {
                        locator = findBY.css();
                        Settings.LOGGER.info(findBY.xpath());
                    }
                } else {
                    if (field.isAnnotationPresent(LocatorType.class)) {
                        locatorType = LocatorSerializer.getSerializedKey(field);
                    }

                }


            }

            if (StringUtils.equalsIgnoreCase(locatorType, "button")) {
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName());// Radio and CheckBox and Normal Click Operation
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionElementPresence(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), locatorType);
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "checkbox")) {
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodForSelect(c, field, locatorType, aClass.getSimpleName());// Radio and CheckBox and Normal Click Operation
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenSelected(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNotChecked(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionElementPresence(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName());
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "input")) {
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionClear(c, field, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTextGetter(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTypeTextAndEnter(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTypeTextAndTab(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName());// Radio and CheckBox and Normal Click Operation
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeVerification(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionVerifyClear(c, field, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionElementPresence(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName());
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "click")) {
                UtilsStepDefinitionCodeGenerator.setClickStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionDoubleCLick(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTextGetter(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName());// Radio and CheckBox and Normal Click Operation
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionElementPresence(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName());
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "image")) {
                UtilsStepDefinitionCodeGenerator.setClickStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName());// Radio and CheckBox and Normal Click Operation
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenSelected(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionElementPresence(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName());
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "a")) {
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName());// Radio and CheckBox and Normal Click Operation
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAndNavigateBack(c, field, locatorType, aClass.getSimpleName());// Radio and CheckBox and Normal Click Operation
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionElementPresence(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), locatorType);
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "radio button")) {
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodForSelect(c, field, locatorType, aClass.getSimpleName());// Radio and CheckBox and Normal Click Operation
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenSelected(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionElementPresence(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName());
            }

            if (StringUtils.equalsIgnoreCase(locatorType, "dropdown")) {
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenClickable(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenEnabled(c, field, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTextGetter(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethod(c, field, locatorType, aClass.getSimpleName());// Radio and CheckBox and Normal Click Operation
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodDeselects(c, field, aClass.getSimpleName(), locatorType);// Radio and CheckBox and Normal Click Operation
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionHoverOver(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionElementPresence(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName());
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "div")) {
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodCountChildElements(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodCountElements(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionElementPresence(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToView(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodContains(c, field, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, false, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodThenVisibility(c, field, true, aClass.getSimpleName(), locatorType);
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, false, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeGetter(c, field, true, aClass.getSimpleName());
                UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAttributeContainsText(c, field, aClass.getSimpleName());
            }

            }
        UtilsStepDefinitionCodeGenerator.savePageObjectsOnFileSystem(Settings.STEP_DEFINITION_PO_DIR,
                aClass.getSimpleName() + "StepDefinition", c, true);
    }
}