package io.skerna.common.autogen;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import java.util.*;


/**
 * @author Ronald Cárdenas
 * project: skerna-commons created at 26/03/19
 **/
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class MetaServicesGenerator extends AbstractProcessor {
    private Map<String, Set<String>> extensions = new HashMap<>(); // the key is the extension point
    private Storage storage;
    private Reporter reporter;



    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        this.reporter = Reporter.get(processingEnv);
        this.storage = createStorage(processingEnv);
        reporter.info("Initialized @%s", "autogen");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new HashSet<>();
        annotationTypes.add(ServiceProvider.class.getName());
        return annotationTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        reporter.info("Processing @%s", ServiceProvider.class);
        try {
            Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ServiceProvider.class);
            reporter.info("Total elements annotated with @%s %s",ServiceProvider.class,elements.size());
            for (Element element : elements) {
                // check if @Extension is put on class and not on method or constructor
                if (!(element instanceof TypeElement)) {
                    reporter.info("Solo se acepta clases, omitiendo @%s",element.getSimpleName());
                    continue;
                }

                TypeElement typeElement = (TypeElement) element;
                String cannonicalClassname = typeElement.getQualifiedName().toString();

                List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();

                reporter.info("Inspect class @%s", cannonicalClassname);

                for (AnnotationMirror annotationMirror : annotationMirrors) {

                    // Get the ExecutableElement:AnnotationValue pairs from the annotation element
                    Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                            = annotationMirror.getElementValues();
                    for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                            : elementValues.entrySet()) {
                        String key = entry.getKey().getSimpleName().toString();
                        Object value = entry.getValue().getValue();
                        switch (key) {
                            case "value":
                                List<? extends AnnotationValue> typeMirrors = (List<? extends AnnotationValue>) value;

                                for (AnnotationValue typeMirror : typeMirrors) {
                                    String providerContract = typeMirror.getValue().toString();
                                    reporter.info(">>>> "+ providerContract);
                                    getSetOfProviders(providerContract).add(cannonicalClassname);
                                }
                                break;
                        }
                    }
                }
                /**
                Class[] spis = activador.value();
                if(spis.length<=0){
                    reporter.info("Provider @%s empty provider definition",spis.length);
                    continue;
                }
                String extensionPoint = typeElement.getQualifiedName().toString();

                Set<String> extensionPoints = extensions.get(extensionPoint);
                for (Class spi : spis) {
                    extensionPoints.add(spi.getCanonicalName());
                }
                extensions.put(extensionPoint, extensionPoints);**/

            }
            storage.write(extensions);

        }catch (Exception ex){
            reporter.error("Error inesperado",ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }


        return false;
    }
    private Set<String> getSetOfProviders(String providerSpec){
        Set<String> providers = extensions.computeIfAbsent(providerSpec, k -> new HashSet<>());
        return providers;
    }

    public ProcessingEnvironment getProcessingEnvironment() {
        return processingEnv;
    }

    @SuppressWarnings("unchecked")
    private Storage createStorage(ProcessingEnvironment processingEnvironment) {
        if (storage == null) {
            // default storage
            storage = new ServiceExtensionsStore(this,processingEnvironment.getFiler());
        }

        return storage;
    }

    public Reporter getReporter() {
        return reporter;
    }
}
