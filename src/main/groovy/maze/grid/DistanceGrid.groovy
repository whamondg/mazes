package maze.grid

class DistanceGrid extends Grid {
    CellDistances distances

    DistanceGrid( int rows, int columns ) {
        super( rows, columns )
    }

    void start( Cell cell ) {
        distances = cell.distances()
    }

    String cellContent( Cell cell ) {
        (distances && distances[cell] != null) ? distances[cell].toString(  ) : super.cellContent()
    }

}
