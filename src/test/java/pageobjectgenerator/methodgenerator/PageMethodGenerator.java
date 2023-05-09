
package pageobjectgenerator.methodgenerator;

import annotation.LocatorType;
import japa.parser.ast.CompilationUnit;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjectgenerator.Settings;
import serializer.LocatorSerializer;
import utils.UtilsMethodCodeGenerator;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class PageMethodGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageMethodGenerator.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = pageobjectgenerator.PageMethodGenerator.class.getClassLoader();
        Class aClass = classLoader.loadClass("locators" + "." + Settings.LOCATOR_FILE_NAME);
        generatePageMethods(aClass.getDeclaredFields(), aClass);
    }

    public static void generatePageMethods(Field[] fields, Class aClass) throws IOException {

        String locator;
        String locatorType;
        boolean isByOrFindBy = false;
        CompilationUnit c = UtilsMethodCodeGenerator.createEnhancedCompilationUnit("implementation", "Method");
        UtilsMethodCodeGenerator.setTypeDeclaration(c, aClass.getSimpleName() + "Implementation");
        UtilsMethodCodeGenerator.setWebDriverVariable(c);


        for (Field field : fields) {
            locator = " ";
            locatorType = " ";
            LOGGER.info(field.getName());
            Annotation[] ann = field.getDeclaredAnnotations();
            isByOrFindBy = false;
            for (Annotation an : ann) {

                if (an instanceof FindBy) {
                    isByOrFindBy = true;
                    FindBy findBY = (FindBy) an;
                    if (!findBY.xpath().isEmpty()) {
                        locator = findBY.xpath();
                        LOGGER.info(findBY.xpath());

                    } else if (!findBY.id().isEmpty()) {
                        locator = findBY.id();
                        LOGGER.info(findBY.id());

                    } else if (!findBY.className().isEmpty()) {
                        locator = findBY.className();
                        LOGGER.info(findBY.className());

                    } else if (!findBY.name().isEmpty()) {
                        locator = findBY.name();
                        LOGGER.info(findBY.name());

                    } else if (!findBY.linkText().isEmpty()) {
                        locator = findBY.linkText();
                        LOGGER.info(findBY.linkText());

                    } else if (!findBY.partialLinkText().isEmpty()) {
                        locator = findBY.partialLinkText();
                        LOGGER.info(findBY.partialLinkText());

                    } else if (!findBY.tagName().isEmpty()) {
                        locator = findBY.tagName();
                        LOGGER.info(findBY.xpath());

                    } else {
                        locator = findBY.css();
                        LOGGER.info(findBY.xpath());
                    }
                } else {
                    if (field.isAnnotationPresent(LocatorType.class)) {
                        locatorType = LocatorSerializer.getSerializedKey(field);
                    }

                }

            }
// All input functions are covered for Gemjar reporting
            if (StringUtils.contains(locatorType, "input")) {
                UtilsMethodCodeGenerator.setLinkMethodsTypeSetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsTypeAndEnter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsTypeAndTab(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsTypeGetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForEnabled(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsClear(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsAttributeGetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsValueVerification(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsAttributeValueVerification(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsVerifyClear(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsHoverOver(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsElementPresence(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsScrollToView(c, field);
            }
            if (StringUtils.contains(locatorType, "div") || StringUtils.contains(locatorType, "span")) {
                UtilsMethodCodeGenerator.setLinkMethodForEnabled(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsAttributeGetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsValueVerification(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsAttributeValueVerification(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsHoverOver(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsElementPresence(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsScrollToView(c, field);
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "button")) {
                UtilsMethodCodeGenerator.setLinkMethodsClick(c, field);// Radio and CheckBox and Normal Click Operation
                UtilsMethodCodeGenerator.setMethodClickable(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsAttributeGetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsValueVerification(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsHoverOver(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsElementPresence(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsScrollToView(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForEnabled(c, field);
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "checkbox")) {
                UtilsMethodCodeGenerator.setLinkMethodsSelect(c, field);// Radio and CheckBox and Normal Click Operation
                UtilsMethodCodeGenerator.setMethodClickable(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsAttributeGetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForIsSelected(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsIsNotSelected(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsValueVerification(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsElementPresence(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsScrollToView(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForEnabled(c, field);
              }
            if (StringUtils.equalsIgnoreCase(locatorType, "click")) {
                UtilsMethodCodeGenerator.setLinkMethodsDoubleClick(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsClick(c, field);// Radio and CheckBox and Normal Click Operation
                UtilsMethodCodeGenerator.setMethodClickable(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsTypeGetter(c, field);
                UtilsMethodCodeGenerator.setMethodScrollClick(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsAttributeGetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsElementPresence(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsScrollToView(c, field);
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "a")) {
                UtilsMethodCodeGenerator.setLinkMethodsClickAndNavigateBack(c, field);// Radio and CheckBox and Normal Click Operation
                UtilsMethodCodeGenerator.setLinkMethodsClick(c, field);// Radio and CheckBox and Normal Click Operation
                UtilsMethodCodeGenerator.setMethodClickable(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsAttributeGetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsValueVerification(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsHoverOver(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsElementPresence(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsScrollToView(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForEnabled(c, field);
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "dropdown")) {
                UtilsMethodCodeGenerator.setLinkMethodsDropDown(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsDeselects(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForEnabled(c, field);
                UtilsMethodCodeGenerator.setMethodClickable(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsTypeGetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsAttributeGetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsValueVerification(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsHoverOver(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsElementPresence(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsScrollToView(c, field);
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "image") || StringUtils.equalsIgnoreCase(locatorType, "file")) {
                UtilsMethodCodeGenerator.setLinkMethodsClick(c, field);// Radio and CheckBox and Normal Click Operation
                UtilsMethodCodeGenerator.setMethodClickable(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForEnabled(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForUpload(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForIsSelected(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsAttributeGetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsValueVerification(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsHoverOver(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsElementPresence(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsScrollToView(c, field);
            }
            if (StringUtils.equalsIgnoreCase(locatorType, "radio button")) {
                UtilsMethodCodeGenerator.setLinkMethodsSelect(c, field);// Radio and CheckBox and Normal Click Operation
                UtilsMethodCodeGenerator.setLinkMethodForEnabled(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForIsSelected(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsAttributeGetter(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsValueVerification(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsHoverOver(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsElementPresence(c, field);
                UtilsMethodCodeGenerator.setLinkMethodsScrollToView(c, field);
                UtilsMethodCodeGenerator.setMethodClickable(c, field);
            }

            if (!StringUtils.equalsIgnoreCase(field.getName(), "driver")) {
                UtilsMethodCodeGenerator.setLinkMethodForVisibility(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForContains(c, field);
                UtilsMethodCodeGenerator.setLinkMethodForText(c, field);
            }
        }
        UtilsMethodCodeGenerator.savePageObjectsOnFileSystem(Settings.IMPLEMENTATION_PO_DIR,
                aClass.getSimpleName() + "Implementation", c, false);

    }
}