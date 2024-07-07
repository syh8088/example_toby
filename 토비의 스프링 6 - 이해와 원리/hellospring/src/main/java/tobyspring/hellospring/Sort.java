package tobyspring.hellospring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {

    public List<String> sortByLength(final List<String> list) {
        list.sort((o1, o2) -> o1.length() - o2.length());
        return list;
    }

}
