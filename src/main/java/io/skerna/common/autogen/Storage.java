package io.skerna.common.autogen;

import java.util.Map;
import java.util.Set;

/**
 * @author Ronald Cárdenas
 * project: skerna-commons created at 26/03/19
 **/
public interface Storage {
    /**
     * Write services 1 clase puede implementar N interface || Abstract definition services
     * A services is a well-known set of interfaces and (usually abstract) classes.
     * A services provider is identified by placing a provider-configuration file
     * in the resource directory META-INF/services.
     * The file's name is the fully-qualified binary name of the services's type.
     * @param extensions
     */
    void write(Map<String, Set<String>> extensions);
}
