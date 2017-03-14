package maze

import spock.lang.Specification
import spock.lang.Unroll

class CellNeighboursTest extends Specification {
    public static final NORTH_CELL = new Cell( 1, 2 )
    public static final EAST_CELL = new Cell( 2, 3 )
    public static final SOUTH_CELL = new Cell( 3, 2 )
    public static final WEST_CELL = new Cell( 2, 1 )

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


}
