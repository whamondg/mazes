package maze

import spock.lang.Specification

class CellLinkingTest extends Specification {
    def cell
    def secondCell

    def setup() {
        cell = new Cell( 1, 1 )
        secondCell = new Cell( 1, 2 )
    }

    def "cell links are initially empty"() {
        when:
        def result = cell.links

        then:
        result.size() == 0
    }

    def "cells can be linked"() {
        when:
        cell.link( secondCell )

        then:
        assert cell.linkedTo( secondCell )
    }

    def "cell links are bidirectional"() {
        when:
        cell.link( secondCell )

        then:
        assert secondCell.linkedTo( cell )
    }

    def "linked cells can be retrieved from a cell"() {
        setup:
        cell.link( secondCell )

        when:
        def result = cell.links()

        then:
        result.size() == 1

        and:
        assert result.contains( secondCell )
    }

    def "cells can be unlinked"() {
        setup:
        cell.link( secondCell )

        when:
        cell.unlink( secondCell )

        then:
        assert !cell.linkedTo( secondCell )
    }

    def "cell unlinking is bidirectional"() {
        setup:
        cell.link( secondCell )

        when:
        cell.unlink( secondCell )

        then:
        assert !secondCell.linkedTo( cell )
    }
}
