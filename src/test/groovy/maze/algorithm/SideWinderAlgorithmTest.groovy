package maze.algorithm

import maze.grid.Cell
import maze.grid.Grid
import maze.grid.Row
import maze.grid.RowVisitor
import spock.lang.Specification

class SideWinderAlgorithmTest extends Specification {
    int UNIMPORTANT = 0
    def algorithm

    void setup() {
        algorithm = new SidewinderAlgorithm()
    }

    Row testRow(List<Cell> cells) {
        new Row(UNIMPORTANT, cells)
    }

    def "sidewinder algorithm visits each row in the grid"() {
        setup:
        Grid grid = Mock()

        when:
        algorithm.on(grid)

        then:
        1 * grid.visitEachRow(algorithm)
    }

    def "sidewinder algorithm closes run at eastern edge"() {
        setup:
        Cell testCell = new Cell(1, 1)
        Grid grid = Stub(Grid) {
            visitEachRow(_ as RowVisitor) >> { RowVisitor visitor ->
                visitor.visitRow(testRow([testCell]))
            }
        }

        when:
        algorithm.on(grid)

        then:
        assert algorithm.run.size() == 0
    }

    def "sidewinder algorithm links north if possible when run closes"() {
        setup:
        Cell north = new Cell(1, 1)
        Cell testCell = new Cell(2, 1)
        testCell.north = north

        Grid grid = Stub(Grid) {
            visitEachRow(_ as RowVisitor) >> { RowVisitor visitor ->
                visitor.visitRow(testRow([testCell]))
            }
        }

        when:
        algorithm.on(grid)

        then:
        assert testCell.linkedTo(north)
    }

    def "sidewinder algorithm doesn't close run if not on eastern edge and at northern edge"() {
        setup:
        Cell testCell = new Cell(2, 1)
        testCell.east = new Cell(2, 2)

        Grid grid = Stub(Grid) {
            visitEachRow(_ as RowVisitor) >> { RowVisitor visitor ->
                visitor.visitRow(testRow([testCell]))
            }
        }

        when:
        algorithm.on(grid)

        then:
        assert algorithm.run.size() == 1
    }

    def "sidewinder algorithm links east if run is not being closed"() {
        setup:
        Cell east = new Cell(2, 2)
        Cell testCell = new Cell(2, 1)
        testCell.east = east

        Grid grid = Stub(Grid) {
            visitEachRow(_ as RowVisitor) >> { RowVisitor visitor ->
                visitor.visitRow(testRow([testCell]))
            }
        }

        when:
        algorithm.on(grid)

        then:
        assert testCell.linkedTo(east)
    }

    def "sidewinder algorithm randomly closes run if not on northern or eastern edges"() {
        setup:
        algorithm = Spy(SidewinderAlgorithm)

        Cell north = new Cell(1, 1)
        Cell east = new Cell(2, 2)
        Cell testCell = new Cell(2, 1)
        testCell.east = east
        testCell.north = north

        Grid grid = Stub(Grid) {
            visitEachRow(_ as RowVisitor) >> { RowVisitor visitor ->
                visitor.visitRow(testRow([testCell]))
            }
        }

        when:
        algorithm.on(grid)

        then:
        1 * algorithm.randomRunClose()
    }

    def "sidewinder algorithm does not randomly close run if on northern edge"() {
        setup:
        algorithm = Spy(SidewinderAlgorithm)

        Cell cell1 = new Cell(1, 1)
        Cell cell2 = new Cell(1, 2)
        cell1.east = cell2
        cell2.east = new Cell(1, 3)

        Grid grid = Stub(Grid) {
            visitEachRow(_ as RowVisitor) >> { RowVisitor visitor ->
                visitor.visitRow(testRow([cell1, cell2]))
            }
        }

        when:
        algorithm.on(grid)

        then:
        algorithm.run.size() == 2
    }
}