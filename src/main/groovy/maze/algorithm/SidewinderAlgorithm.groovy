package maze.algorithm

import maze.grid.Cell
import maze.grid.Grid
import maze.grid.Row
import maze.grid.RowVisitor

class SidewinderAlgorithm implements RowVisitor {
    static final String name = "Sidewinder"
    static final String description = "For each cell randomly link east keeping track of the run.  When not linking east pick a single cell randomly in the run and link it north."

    Random random = new Random();

    List<Cell> run = []

    boolean randomRunClose() {
        random.nextInt( 2 ) == 0
    }

    boolean closeRun( Cell cell ) {
        cell.onEastEdge() || (!cell.onNorthEdge() && randomRunClose())
    }

    Cell randomCellFromRun() {
        run[random.nextInt( run.size() )]
    }

    void linkRunAndClear() {
        Cell chosenCell = randomCellFromRun()
        if ( chosenCell.north ) {
            chosenCell.link( chosenCell.north )
        }
        run.clear()
    }

    void visitRow(Row row) {
        row.each { cell ->
            run << cell
            (closeRun( cell )) ? linkRunAndClear() : cell.link( cell.east )
        }
    }

    def on( grid ) {
        grid.visitEachRow( this )
    }
}
