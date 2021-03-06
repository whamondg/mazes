package maze.grid

import spock.lang.Specification

class CellDistancesTest extends Specification {

    def "Cell distances are correct for 2x2 grid when all cells are linked"() {
        setup:
        Grid grid = new Grid( 2, 2 )
        grid.cell( 1, 1 ).link( grid.cell( 1, 2 ) )
        grid.cell( 1, 1 ).link( grid.cell( 2, 1 ) )
        grid.cell( 1, 2 ).link( grid.cell( 2, 2 ) )
        grid.cell( 2, 1 ).link( grid.cell( 2, 2 ) )

        when:

        CellDistances distances = new CellDistances( grid.cell( 1, 1 )).calculate()

        then:
        distances.cells[grid.cell( 1, 1 )] == 0
        distances.cells[grid.cell( 1, 2 )] == 1
        distances.cells[grid.cell( 2, 2 )] == 2
        distances.cells[grid.cell( 2, 1 )] == 1
    }

    def "Cell distances are correct for 2x2 grid with a wall"() {
        setup:
        Grid grid = new Grid( 2, 2 )
        grid.cell( 1, 1 ).link( grid.cell( 1, 2 ) )
        grid.cell( 1, 2 ).link( grid.cell( 2, 2 ) )
        grid.cell( 2, 2 ).link( grid.cell( 2, 1 ) )

        when:
        CellDistances distances = new CellDistances( grid.cell( 1, 1 )).calculate()

        then:
        distances.cells[grid.cell( 1, 1 )] == 0
        distances.cells[grid.cell( 1, 2 )] == 1
        distances.cells[grid.cell( 2, 2 )] == 2
        distances.cells[grid.cell( 2, 1 )] == 3
    }
}
