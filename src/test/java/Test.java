import io.skerna.common.autogen.MetaServicesGenerator;
import org.joor.CompileOptions;
import org.joor.Reflect;

import java.util.function.Supplier;

/**
 * @author Ronald Cárdenas
 * project: skerna-commons created at 26/03/19
 **/
public class Test {
    public void test() {
        MetaServicesGenerator metaServicesGenerator = new MetaServicesGenerator();
        Reflect a = Reflect.compile("RobotTalker.java",
                "import io.skerna.common.autogen.ServiceProvider;\n" +
                        "\n" +
                        "import javax.annotation.processing.AbstractProcessor;\n" +
                        "import javax.annotation.processing.RoundEnvironment;\n" +
                        "import javax.lang.model.element.TypeElement;\n" +
                        "import java.util.Set;\n" +
                        "\n" +
                        "/**\n" +
                        " * @author Ronald Cárdenas\n" +
                        " * project: skerna-commons created at 26/03/19\n" +
                        " **/\n" +
                        "@ServiceProvider(AbstractProcessor.class)\n" +
                        " class RobotTalker extends AbstractProcessor {\n" +
                        "    @Override\n" +
                        "    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {\n" +
                        "        return false;\n" +
                        "    }\n" +
                        "}\n",
                new CompileOptions()
                        .processors(metaServicesGenerator));

        System.out.println(a);
    }
}
