
package pageobjectgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjectgenerator.methodgenerator.GenericPageMethodGenerator;
import pageobjectgenerator.methodgenerator.PageMethodGenerator;
import pageobjectgenerator.stepdefinitiongenerator.GenericStepDefinitionGenerator;
import pageobjectgenerator.stepdefinitiongenerator.StepDefinitionGenerator;

import java.io.IOException;

public class CodeGeneratorRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGeneratorRunner.class);

    public static void main(String[] args) {

        ClassLoader classLoader = PageMethodGenerator.class.getClassLoader();
        for (int i = 0; i < args.length; i++) {

            Settings.LOCATOR_FILE_NAME = args[i];

            try {
                Class aClass = classLoader.loadClass("locators" + "." + Settings.LOCATOR_FILE_NAME);
                PageMethodGenerator.generatePageMethods(aClass.getDeclaredFields(), aClass);
                Thread.sleep(2000);
                StepDefinitionGenerator.generateStepMethods(aClass.getDeclaredFields(), aClass);

            } catch (ClassNotFoundException e) {
                throw new ArithmeticException();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Class aClass = classLoader.loadClass("locators" + "." + Settings.LOCATOR_FILE_NAME);
            GenericPageMethodGenerator.generateGenericPageMethods();
            Thread.sleep(2000);
            GenericStepDefinitionGenerator.generateGenericStepMethods();
        } catch (ClassNotFoundException e) {
            throw new ArithmeticException();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
