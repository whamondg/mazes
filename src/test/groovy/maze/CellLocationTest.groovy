package maze

import maze.grid.Cell
import spock.lang.Specification
import spock.lang.Unroll

class CellLocationTest extends Specification {
    @Unroll
    def "cell row is #row when location is #row, #column"() {
        setup:
        def cell = new Cell( row, column )

        when:
        def result = cell.row

        then:
        result == row

        where:
        row | column
        1   | 1
        1   | 2
        2   | 1
        2   | 2
    }

    @Unroll
    def "cell column is #column when location is #row, #column"() {
        setup:
        def cell = new Cell( row, column )

        when:
        def result = cell.column

        then:
        result == column

        where:
        row | column
        1   | 1
        1   | 2
        2   | 1
        2   | 2
    }

}
