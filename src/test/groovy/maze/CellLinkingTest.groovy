package maze

import spock.lang.Specification

class CellLinkingTest extends Specification {
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
