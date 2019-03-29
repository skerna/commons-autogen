import io.skerna.common.autogen.ServiceProvider;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @author Ronald Cárdenas
 * project: skerna-commons created at 26/03/19
 **/
@ServiceProvider(AbstractProcessor.class)
public class RobotTalker extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
