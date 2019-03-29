package io.skerna.common.autogen;

import javax.annotation.processing.Filer;
import javax.annotation.processing.FilerException;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author Ronald Cárdenas
 * project: skerna-commons created at 26/03/19
 **/
public class ServiceExtensionsStore implements Storage {

    public static final String EXTENSIONS_RESOURCE = "META-INF/services";

    private static final Pattern COMMENT = Pattern.compile("#.*");
    private static final Pattern WHITESPACE = Pattern.compile("\\s+");

    private MetaServicesGenerator processor;
    private Filer filer;
    public ServiceExtensionsStore(MetaServicesGenerator processor, Filer filer) {
        this.processor = processor;
        this.filer = filer;
    }


    @Override
    public void write(Map<String, Set<String>> extensions) {
        for (Map.Entry<String, Set<String>> entry : extensions.entrySet()) {
            String extensionPoint = entry.getKey();
            try {
                FileObject file = filer.createResource(StandardLocation.CLASS_OUTPUT, "", EXTENSIONS_RESOURCE
                    + "/" + extensionPoint);
                BufferedWriter writer = new BufferedWriter(file.openWriter());
                // write header
                writer.newLine();
                // write extensions
                for (String extension : entry.getValue()) {
                    writer.write(extension);
                    writer.newLine();
                }
                writer.close();
            } catch (FileNotFoundException e) {
                // it's the first time, create the file
            } catch (FilerException e) {
                // re-opening the file for reading or after writing is ignorable
            } catch (IOException e) {
                processor.getReporter().error(e.toString());
            }
        }
    }


}
