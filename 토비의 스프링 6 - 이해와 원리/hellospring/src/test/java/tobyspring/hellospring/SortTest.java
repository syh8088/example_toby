package tobyspring.hellospring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {

    private Sort sort;

    @BeforeEach
    void beforeEach() {
        sort = new Sort();
    }

    @Test
    void sort() {

        // 준비 (given)
//        Sort sort = new Sort();

        // 실행 (when)
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        // 검증 (then)
        Assertions.assertThat(list).isEqualTo(Arrays.asList("b", "aa"));
    }

    @Test
    void sort3Items() {

        // 준비 (given)
//        Sort sort = new Sort();

        // 실행 (when)
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        // 검증 (then)
        Assertions.assertThat(list).isEqualTo(Arrays.asList("b", "aa", "ccc"));
    }

    @Test
    void sortAlreadySorted() {

        // 준비 (given)
//        Sort sort = new Sort();

        // 실행 (when)
        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

        // 검증 (then)
        Assertions.assertThat(list).isEqualTo(Arrays.asList("b", "aa", "ccc"));
    }


}
