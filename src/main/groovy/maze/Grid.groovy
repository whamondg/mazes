package maze

class Grid {
    List gridRows = []
    int rows
    int columns

    Grid( rows, columns ) {
        this.rows = rows
        this.columns = columns
        prepareGrid()
        configureCells()
    }

    void prepareGrid() {
        rows.times { row ->
            gridRows << []
            columns.times { column ->
                gridRows[row] << new Cell( row + 1, column + 1 )
            }
        }
    }

    void configureCells() {
        gridRows.eachWithIndex { row, rowIdx ->
            row.eachWithIndex { currentCell, colIdx ->
                currentCell.north = gridCell( rowIdx - 1, colIdx )
                currentCell.south = gridCell( rowIdx + 1, colIdx )
                currentCell.east = gridCell( rowIdx, colIdx + 1 )
                currentCell.west = gridCell( rowIdx, colIdx - 1 )
            }
        }
    }

    int size() {
        rows * columns
    }

    Cell cell( int row, int column ) {
        gridCell( row - 1, column - 1 )
    }

    Cell gridCell( int row, int column ) {
        if ( !((row >= 0) && (row <= gridRows.size() - 1)) ) {
            return null
        }
        if ( !(column >= 0) && (column <= gridRows[row].size() - 1) ) {
            return null
        }

        gridRows[row][column] as Cell
    }
}
