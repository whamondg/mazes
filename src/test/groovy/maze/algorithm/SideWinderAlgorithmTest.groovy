package maze.algorithm

import maze.Cell
import maze.Grid
import spock.lang.Specification

class SideWinderAlgorithmTest extends Specification {
    def algorithm

    void setup() {
        algorithm = new SidewinderAlgorithm()
    }

    def "sidewinder algorithm visits each row in the grid"() {
        setup:
        Grid grid = Mock()

        when:
        algorithm.on( grid )

        then:
        1 * grid.visitEachRow( algorithm )
    }

    def "sidewinder algorithm closes run at eastern edge"() {
        setup:
        Cell north = new Cell( 1, 1 )
        Cell cell = new Cell( 2, 1 )
        cell.north = north

        Grid grid = Stub( Grid ) {
            visitEachRow( _ as RowVisitor ) >> { RowVisitor visitor ->
                visitor.visitRow( [cell] )
            }
        }

        when:
        algorithm.on( grid )

        then:
        assert algorithm.run.size() == 0
    }

    def "sidewinder algorithm links north if possible when run closes"() {
        setup:
        Cell north = new Cell( 1, 1 )
        Cell cell = new Cell( 2, 1 )
        cell.north = north

        Grid grid = Stub( Grid ) {
            visitEachRow( _ as RowVisitor ) >> { RowVisitor visitor ->
                visitor.visitRow( [cell] )
            }
        }

        when:
        algorithm.on( grid )

        then:
        assert cell.linkedTo( north )
    }

    def "sidewinder algorithm doesn't close run if not on eastern edge and at northern edge"() {
        setup:
        Cell east = new Cell( 2, 2 )
        Cell cell = new Cell( 2, 1 )
        cell.east = east

        Grid grid = Stub( Grid ) {
            visitEachRow( _ as RowVisitor ) >> { RowVisitor visitor ->
                visitor.visitRow( [cell] )
            }
        }

        when:
        algorithm.on( grid )

        then:
        assert algorithm.run.size() == 1
    }

    def "sidewinder algorithm links east if run is not being closed"() {
        setup:
        Cell east = new Cell( 2, 2 )
        Cell cell = new Cell( 2, 1 )
        cell.east = east

        Grid grid = Stub( Grid ) {
            visitEachRow( _ as RowVisitor ) >> { RowVisitor visitor ->
                visitor.visitRow( [cell] )
            }
        }

        when:
        algorithm.on( grid )

        then:
        assert cell.linkedTo( east )
    }

    def "sidewinder algorithm randomly closes run if not on northern edge"() {
        setup:
        algorithm = Spy( SidewinderAlgorithm )

        Cell north = new Cell( 1, 1 )
        Cell east = new Cell( 2, 2 )
        Cell cell = new Cell( 2, 1 )
        cell.east = east
        cell.north = north

        Grid grid = Stub( Grid ) {
            visitEachRow( _ as RowVisitor ) >> { RowVisitor visitor ->
                visitor.visitRow( [cell] )
            }
        }

        when:
        algorithm.on( grid )

        then:
        1 * algorithm.randomRunClose()
    }

    def "sidewinder algorithm does not randomly close run if on northern edge"() {
        setup:
        algorithm = Spy( SidewinderAlgorithm )

        Cell cell1 = new Cell( 1, 1 )
        Cell cell2 = new Cell( 1, 2 )
        cell1.east = cell2
        cell2.east = new Cell( 1, 3 )

        Grid grid = Stub( Grid ) {
            visitEachRow( _ as RowVisitor ) >> { RowVisitor visitor ->
                visitor.visitRow( [cell1, cell2] )
            }
        }

        when:
        algorithm.on( grid )

        then:
        algorithm.run.size() == 2
    }


}