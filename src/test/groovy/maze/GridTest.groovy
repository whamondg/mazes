package maze

import spock.lang.Specification

class GridTest extends Specification {
    static final UNIMPORTANT = 1

    def "a grid knows the number of rows it has"() {
        when:
        def grid = new Grid( rows, UNIMPORTANT )

        then:
        grid.rows == rows

        where:
        rows | _
        1    | _
        10   | _
        100  | _
    }

    def "a grid knows the number of columns it has"() {
        when:
        def grid = new Grid( UNIMPORTANT, columns )

        then:
        grid.columns == columns


        where:
        columns | _
        1       | _
        10      | _
        100     | _
    }

    

}
