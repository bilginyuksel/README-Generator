package readme.generator;

import java.util.List;
import java.util.Map;

public interface RGComponentGenerator {
    String makeTable(RGComponent component, String heading, String ...columns);
    String mergedTable(List<Map<String, String>> components, String heading, String ...columns);
}
