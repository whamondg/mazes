package maze.grid

class DistanceGrid extends Grid {
    CellDistances distances

    DistanceGrid( int rows, int columns ) {
        super( rows, columns )
    }

    void start( int row, int column ) {
        distances = cell(row, column).distances()
    }

    String cellContent( Cell cell ) {
        (distances && distances[cell] != null) ? distances[cell].toString(  ) : super.cellContent()
    }
}
