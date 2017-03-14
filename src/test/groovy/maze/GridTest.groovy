package maze

import spock.lang.Specification
import spock.lang.Unroll

class GridTest extends Specification {
    static final UNIMPORTANT = 1

    def "a grid knows the number of rows it has"() {
        when:
        def grid = new Grid( rows, UNIMPORTANT )

        then:
        grid.rows == rows

        where:
        rows << [1, 10, 100]
    }

    def "a grid knows the number of columns it has"() {
        when:
        def grid = new Grid( UNIMPORTANT, columns )

        then:
        grid.columns == columns


        where:
        columns << [1, 10, 100]
    }

    @Unroll
    def "grid size is #size for #rows rows and #columns columns"() {

        when:
        def grid = new Grid( rows, columns )

        then:
        grid.size() == size

        where:
        rows | columns || size
        1    | 1       || 1
        1    | 2       || 2
        2    | 1       || 2
        2    | 2       || 4
        10   | 10      || 100
    }

    @Unroll
    def "grid cell #row,#column knows it's neighbours"() {
        setup:
        def grid = new Grid( 3, 3 )

        when:
        def cell = grid.cell( row - 1, column - 1 )

        then:
        neighbours.each { neighbour, coordinates ->
            assert cell."$neighbour" == new Cell( coordinates[0], coordinates[1] )
        }

        where:
        row | column | neighbours
        1   | 1      | ['east': [1, 2]]


    }


}
