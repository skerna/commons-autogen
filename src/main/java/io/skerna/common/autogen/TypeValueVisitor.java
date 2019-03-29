package io.skerna.common.autogen;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor6;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ronald Cárdenas
 * project: skerna-commons created at 26/03/19
 **/
public class TypeValueVisitor extends SimpleAnnotationValueVisitor6<Void,Void> {
    private Set<String> classNames;
    public TypeValueVisitor(){
        this.classNames = new HashSet<>();
    }
    @Override
    public Void visitType(TypeMirror t, Void aVoid) {

        String className = t.toString();
        classNames.add(className);
        return aVoid;
    }
}
