package maze

class Grid {
    int rows
    int columns
    List gridRows = [ ]

    Grid( rows, columns ) {
        this.rows = rows
        this.columns = columns
        prepareGrid()
        configureCells()
        println()
    }

    List prepareGrid() {
        rows.times { row ->
            gridRows << [ ]
            columns.times { column ->
                gridRows[row] << new Cell( row + 1, column + 1 )
            }
        }
        gridRows
    }

    void configureCells() {
        gridRows.eachWithIndex { row, rowIdx ->
            row.eachWithIndex { currentCell, colIdx ->
                currentCell.north = cell( rowIdx - 1, colIdx )
                currentCell.south = cell( rowIdx + 1, colIdx )
                currentCell.east = cell( rowIdx, colIdx + 1 )
                currentCell.west = cell( rowIdx, colIdx - 1 )
            }
        }
    }

    int size() {
        rows * columns
    }

    Cell cell( int row, int column ) {
        if ( ! ((row >= 0) && (row < gridRows.size() - 1)) ) {
            return null
        }
        if ( !(column >= 0) && (column < gridRows[row].size() - 1) ) {
            return null
        }

        gridRows[row][column] as Cell
    }
}
