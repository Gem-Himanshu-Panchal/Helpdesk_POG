
package pageobjectgenerator.stepdefinitiongenerator;

import japa.parser.ast.CompilationUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjectgenerator.PageMethodGenerator;
import pageobjectgenerator.Settings;
import utils.UtilsStepDefinitionCodeGenerator;

import java.io.IOException;

public class GenericStepDefinitionGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericStepDefinitionGenerator.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = PageMethodGenerator.class.getClassLoader();
        Class aClass = classLoader.loadClass("locators" + "." + Settings.LOCATOR_FILE_NAME);
        generateGenericStepMethods();
    }

    public static void generateGenericStepMethods() throws IOException {
        CompilationUnit c = UtilsStepDefinitionCodeGenerator.createEnhancedCompilationUnit("stepdefinition", "StepDefinition");
        UtilsStepDefinitionCodeGenerator.setTypeDeclaration(c,  "UtilsStepDefinition");
        UtilsStepDefinitionCodeGenerator.setStepDefinitionVariable(c, "UtilsImplementation");
        UtilsStepDefinitionCodeGenerator.setStepDefinitionVariable(c, "UtilFunctions");
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetUrl(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionPressEnter(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSelectAll(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionCopy(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionPaste(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetWindowFocus(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionVerifyUrl(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetTitle(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionVerifyTitle(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNavigateTo(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNavigateForward(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNavigateBack(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMaximizeBrowserToDefault(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMinimizeBrowser(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetBrowserSize(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSetBrowserSize(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSetBrowserPosition(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetBrowserLocation(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetWindowHandle(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetWindowHandles(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionGetPageSource(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionCloseCurrentTab(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionCloseTabAndSwitch(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSwitchToAlert(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAcceptAlert(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionDismissAlert(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionAlertInput(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToTop(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollToBottom(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionPageScroll(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionScrollElementToPosition(c);
//        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionNavigateToUrl(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionRefresh(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionTakeSnapshot(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionActiveElement(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionParentFrame(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionDefaultContent(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSwitchFrame(c, true);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSwitchFrame(c, false);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionSwitchWindow(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionWait(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionJSClick(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionClickAndHold(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionDragAndDrop(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionFileUpload(c);
//        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionFileDownload(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionQuit(c);
        UtilsStepDefinitionCodeGenerator.setLinkStepDefinitionMethodGiven(c, "homePage");
        UtilsStepDefinitionCodeGenerator.savePageObjectsOnFileSystem(Settings.STEP_DEFINITION_PO_DIR,
                "UtilsStepDefinition", c, true);
    }
}
