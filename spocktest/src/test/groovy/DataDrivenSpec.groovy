import spock.lang.*
import static spock.util.matcher.HamcrestMatchers.closeTo

@Unroll
class DataDrivenSpec extends Specification{
    def "maximum of tow nubmers"() {
        expect:
        Math.max(a, b) == c

        where:
        a << [1,2,3,4,5]
        b << [2,3,4,5,6]
        c << [2,3,5,6,7]
    }

    def "maximum of #a and #b is #c"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        3 | 7 || 7
        2 | 5 || 5
    }

    @IgnoreRest
    def "#person.name is a #sex.toLowerCase() person"() {
        expect:
        person.getSex() == sex

        where:
        person                  | sex
        new Person(name:"fred") | "male"
        new Person(name:"jack") | "female"
    }

    def "comparing two decimal numbers"() {
        def myPi = 3.14

        expect:
        myPi closeTo(Math.PI, 0.01)
    }

        class Person{
            String name
            String getSex() {
                name == "fred" ? "male" : "female"
            }
        }
}
