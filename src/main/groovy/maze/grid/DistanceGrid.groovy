package maze.grid

import groovy.util.logging.Slf4j

@Slf4j
class DistanceGrid extends Grid {
    CellDistances distances

    DistanceGrid( int rows, int columns ) {
        super( rows, columns )
    }

    void start( int row, int column ) {
        distances = new CellDistances( cell(row, column) )
        distances = distances.calculate()
    }

    String cellContent( Cell cell ) {
        (distances && distances[cell] != null) ? distances[cell].toString(  ) : super.cellContent()
    }
}
