package maze.grid

import spock.lang.Specification
import spock.lang.Unroll

class CellNeighboursTest extends Specification {
    def testCell
    def neighbour

    void setup() {
        testCell = new Cell( 1, 1 )
        neighbour = new Cell( 2, 2 )
    }

    def "cell knows it's not on the north Edge"() {
        when:
        testCell.north = neighbour

        then:
        testCell.onNorthEdge() == false
    }

    def "cell knows it's on the north Edge"() {
        expect:
        testCell.onNorthEdge() == true
    }

    def "cell knows it's not on the east Edge"() {
        when:
        testCell.east = neighbour

        then:
        testCell.onEastEdge() == false
    }

    def "cell knows it's on the east Edge"() {
        expect:
        testCell.onEastEdge() == true
    }

    def "cell knows it's not on the south Edge"() {
        when:
        testCell.south = neighbour

        then:
        testCell.onSouthEdge() == false
    }

    def "cell knows it's on the south Edge"() {
        expect:
        testCell.onSouthEdge() == true
    }

    def "cell knows it's not on the west Edge"() {
        when:
        testCell.west = neighbour

        then:
        testCell.onWestEdge() == false
    }

    def "cell knows it's on the west Edge"() {
        expect:
        testCell.onWestEdge() == true
    }

    @Unroll
    def "cell neighbours correct when north #north south #south east #east west #west"() {
        setup:
        if ( north ) {
            cell.north = north
        }
        if ( south ) {
            cell.south = south
        }
        if ( east ) {
            cell.east = east
        }
        if ( west ) {
            cell.west = west
        }

        when:
        def result = cell.neighbours()

        then:
        if ( north ) {
            assert result.contains( north )
        }
        if ( south ) {
            assert result.contains( south )
        }
        if ( east ) {
            assert result.contains( east )
        }
        if ( west ) {
            assert result.contains( west )
        }

        where:
        cell             | north            | south            | east             | west
        new Cell( 1, 1 ) | null             | new Cell( 2, 1 ) | new Cell( 1, 2 ) | null
        new Cell( 1, 2 ) | null             | new Cell( 2, 2 ) | new Cell( 1, 3 ) | new Cell( 1, 1 )
        new Cell( 1, 3 ) | null             | new Cell( 2, 3 ) | false            | new Cell( 1, 2 )
        new Cell( 2, 1 ) | new Cell( 1, 1 ) | new Cell( 3, 1 ) | new Cell( 2, 2 ) | null
        new Cell( 2, 2 ) | new Cell( 1, 2 ) | new Cell( 3, 2 ) | new Cell( 2, 1 ) | new Cell( 2, 3 )
        new Cell( 2, 3 ) | new Cell( 1, 3 ) | new Cell( 3, 3 ) | null             | new Cell( 2, 2 )
        new Cell( 3, 1 ) | new Cell( 2, 1 ) | null             | new Cell( 3, 2 ) | null
        new Cell( 3, 2 ) | new Cell( 2, 2 ) | null             | new Cell( 3, 3 ) | new Cell( 3, 1 )
        new Cell( 3, 3 ) | new Cell( 2, 3 ) | null             | null             | new Cell( 3, 2 )

    }

    @Unroll
    def "cell neighbours can be filtered by #filter"() {
        setup:
        def testCell = new Cell( 1, 1 )
        testCell.north = northCell
        testCell.south = southCell
        testCell.east = eastCell
        testCell.west = westCell

        when:
        def result = testCell.neighbours( filter )

        then:
        result == expected as Set

        where:
        northCell        | southCell        | eastCell         | westCell         | filter             | expected
        new Cell( 1, 2 ) | new Cell( 3, 2 ) | new Cell( 2, 3 ) | new Cell( 2, 1 ) | ['north']          | [northCell]
        new Cell( 1, 2 ) | new Cell( 3, 2 ) | new Cell( 2, 3 ) | new Cell( 2, 1 ) | ['south']          | [southCell]
        new Cell( 1, 2 ) | new Cell( 3, 2 ) | new Cell( 2, 3 ) | new Cell( 2, 1 ) | ['east']           | [eastCell]
        new Cell( 1, 2 ) | new Cell( 3, 2 ) | new Cell( 2, 3 ) | new Cell( 2, 1 ) | ['west']           | [westCell]
        new Cell( 1, 2 ) | new Cell( 3, 2 ) | new Cell( 2, 3 ) | new Cell( 2, 1 ) | ['north', 'south'] | [northCell, southCell]
        new Cell( 1, 2 ) | new Cell( 3, 2 ) | new Cell( 2, 3 ) | new Cell( 2, 1 ) | ['east', 'west']   | [eastCell, westCell]
    }
}
