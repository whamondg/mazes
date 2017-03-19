package maze.algorithm

import maze.Cell
import maze.Grid
import spock.lang.Specification
import spock.lang.Unroll

class BinaryTreeAlgorithmTest extends Specification {

    def "binary tree algorithm visits each cell in the grid"() {
        setup:
        def algorithm = new BinaryTreeAlgorithm()
        Grid grid = Mock()

        when:
        algorithm.on( grid )

        then:
        1 * grid.visitEachCell( algorithm )
    }

    def "binary tree algorithm filters cell neighbours for north and east"() {
        setup:
        def algorithm = new BinaryTreeAlgorithm()
        Cell cell = Mock()
        Grid grid = Stub( Grid ) {
            visitEachCell( _ as CellVisitor ) >> { CellVisitor visitor ->
                visitor.visitCell( cell )
            }
        }

        when:
        algorithm.on( grid )

        then:
        1 * cell.neighbours( ['north', 'east'] ) >> []
    }

    @Unroll
    def "binary tree algorithm links #onlyLink if it's the only neighbour"() {
        setup:
        def algorithm = new BinaryTreeAlgorithm()

        Cell northCell = new Cell( 1, 2 )
        Cell eastCell = new Cell( 2, 3 )
        Cell cell = new Cell( 2, 2 )

        if ( north ) cell.north = northCell
        if ( east ) cell.east = eastCell

        Grid grid = Stub( Grid ) {
            visitEachCell( _ as CellVisitor ) >> { CellVisitor visitor ->
                visitor.visitCell( cell )
            }
        }

        Cell expected = (north) ? northCell : eastCell

        when:
        algorithm.on( grid )

        then:
        cell.links.size() == 1
        assert cell.links.contains( expected )

        where:
        onlyLink | north | east
        'north ' | true  | false
        'east '  | true  | false
    }

    def "binary tree algorithm creates one link when multiple neighbours"() {
        setup:
        def algorithm = new BinaryTreeAlgorithm()

        Cell cell = new Cell( 2, 2 )
        cell.north = new Cell( 1, 2 )
        cell.east = new Cell( 2, 3 )

        Grid grid = Stub( Grid ) {
            visitEachCell( _ as CellVisitor ) >> { CellVisitor visitor ->
                visitor.visitCell( cell )
            }
        }

        when:
        algorithm.on( grid )

        then:
        cell.links.size() == 1
    }

}
