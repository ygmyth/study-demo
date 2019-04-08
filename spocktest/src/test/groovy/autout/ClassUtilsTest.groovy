package autout

import spock.lang.Specification

class ClassUtilsTest extends Specification {
    def "GetClassName"() {
        List<String> names = ClassUtils.getClassName("myth", true)
        print(names)
        expect:true
    }
}
