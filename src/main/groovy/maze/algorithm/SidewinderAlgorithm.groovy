package maze.algorithm

import maze.Cell

class SidewinderAlgorithm implements RowVisitor {
    List<Cell> run = []

    Random random = new Random();

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

    void visitRow( List<Cell> row ) {
        row.each { cell ->
            run << cell
            (closeRun( cell )) ? linkRunAndClear() : cell.link( cell.east )
        }
    }

    def on( grid ) {
        grid.visitEachRow( this )
    }
}
