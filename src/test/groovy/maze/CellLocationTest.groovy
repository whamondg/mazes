package maze

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

    def "cell links are initially empty"() {
        setup:
        def cell = new Cell( 1, 1 )

        when:
        def result = cell.links

        then:
        result.size() == 0
    }

    def "cell link"() {
        setup:
        def firstCell = new Cell( firstCellRow, firstCellColumn )
        def secondCell = new Cell( secondCellRow, secondCellColumn )

        when:
        firstCell.link( secondCell )

        then:
        assert firstCell.linkedTo( secondCell )

        where:
        firstCellRow | firstCellColumn | secondCellRow | secondCellColumn
        1            | 2               | 2             | 1

    }
}
