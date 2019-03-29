package io.skerna.common.autogen;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

/**
 * @author Ronald Cárdenas
 * project: skerna-commons created at 26/03/19
 **/
public class Reporter {

    private ProcessingEnvironment processingEnv;

    private Reporter(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    public static final Reporter get(ProcessingEnvironment processingEnv){
        return new Reporter(processingEnv);
    }

    public void error(String message, Object... args) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format(message, args));
    }

    public void error(Element element, String message, Object... args) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, String.format(message, args), element);
    }

    public void info(String message, Object... args) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, String.format(message, args));
    }

    public void info(Element element, String message, Object... args) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, String.format(message, args), element);
    }
}
