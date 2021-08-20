import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Flattener {

    public List<Object> flatten(List<Object> list) {
        return new LinkedList<Object>() {{
            list.forEach((el) -> {
                if (el instanceof List) {
                    flatten((List<Object>) el).forEach(it -> safeAdd(this, it));
                } else {
                    safeAdd(this, el);
                }
            });
        }};
    }

    private void safeAdd(List<Object> list, Object element) {
        if (element != null) {
            list.add(element);
        }
    }
}
