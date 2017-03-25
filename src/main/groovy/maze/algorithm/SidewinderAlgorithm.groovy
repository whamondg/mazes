package maze.algorithm

import maze.Cell

class SidewinderAlgorithm implements RowVisitor {
    List<Cell> run = []

    Random random = new Random();

    boolean randomRunClose() {
        random.nextInt( 2 ) == 0
    }

    Cell randomCellFromRun() {
        run[random.nextInt( run.size() )]
    }

    void visitRow( List<Cell> row ) {
        row.each { cell ->
            run << cell

            def closeRun = cell.onEastEdge() || (!cell.onNorthEdge() && randomRunClose())

            if ( closeRun ) {
                Cell chosenCell = randomCellFromRun()
                if ( chosenCell.north ) {
                    chosenCell.link( chosenCell.north )
                }
                run.clear()
            } else {
                cell.link( cell.east )
            }
        }
    }

    def on( grid ) {
        grid.visitEachRow( this )
    }
}
