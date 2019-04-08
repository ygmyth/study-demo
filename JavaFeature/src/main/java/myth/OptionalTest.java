package myth;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @description:
 * @author: yuang gang
 * @create: 2018-11-25 15:23
 **/
public class OptionalTest {
    public  void excute() {
        Optional<String> maybeString = Optional.of("fas");
        String newString = maybeString.map(this::runIfExist).orElse(runIfNotExist());
        System.out.println(newString);
    }

    public   String runIfExist(String str) {
        System.out.println("run only if exist");
        return str;
    }

    public   String runIfNotExist() {
        System.out.println("run only if not exist");
        return "empty";
    }

    public static void main(String[] args) {
        OptionalTest optionalTest = new OptionalTest();
        optionalTest.excute();
        List<String> listString = Lists.newArrayList("1Hello world ", "1Hit the world, hah", "world", "God");
        Optional<String> longest = listString.stream()
                                    .filter(name -> name.startsWith("H"))
                                    .findFirst();
        longest.ifPresent(name -> {
            String s = name.toUpperCase();
            System.out.println(s);
        });

        String result1 = longest.map(String::toUpperCase).orElse("no result found");
        System.out.println(result1);

        String result2 = longest.map(String::toUpperCase).orElseGet(() -> "HEEE");
        System.out.println(result2);

        String result3 = longest.map(String::toUpperCase).orElseThrow(NoSuchElementException::new);
        System.out.println(result3);
    }
}
