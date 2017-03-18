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
        if ( row > this.rows || column > this.columns ) {
            throw new IllegalArgumentException( "No cell $row,$column in grid with dimensions ${ rows }x${ columns }" )
        }
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

    boolean lastRow( int rowIdx ) {
        rowIdx == rows - 1
    }

    boolean lastColumn( int colIdx ) {
        colIdx == columns - 1
    }

    static final String LINE_END = System.getProperty( "line.separator" )

    String toString() {
        def topLeft = '┏'
        def topJoint = '┳'
        def topRight = '┓'
        def bottomLeft = '┗'
        def bottomJoint = '┻'
        def bottomRight = '┛'
        def leftjoint = '┣'
        def rightJoint = '┫'
        def cellBody = '   '
        def corner = '╋'
        def verticalEdge = '┃'
        def verticalLink = ' '
        def horizontalEdge = '━━━'
        def horizontalLink = '   '

        def buffer = new StringBuffer( topLeft )

        buffer << "${ horizontalEdge }$topJoint" * (columns - 1) << horizontalEdge << topRight << LINE_END

        gridRows.eachWithIndex { row, rowIdx ->
            def topBuffer = new StringBuffer( verticalEdge )
            def cellBottomLeft = lastRow( rowIdx ) ? bottomLeft : leftjoint

            def bottomBuffer = new StringBuffer( cellBottomLeft )
            row.eachWithIndex { cell, cellIdx ->
                def eastBoundary = cell?.linkedTo( cell.east ) ? verticalLink : verticalEdge
                def southBoundary = cell?.linkedTo( cell.south ) ? horizontalLink : horizontalEdge

                def cellBottomRight = ((lastRow( rowIdx ) && lastColumn( cellIdx )) ? bottomRight : (lastRow( rowIdx )) ? bottomJoint : (lastColumn( cellIdx )) ? rightJoint : corner)

                topBuffer << cellBody << eastBoundary
                bottomBuffer << southBoundary << cellBottomRight
            }
            buffer << topBuffer << LINE_END
            buffer << bottomBuffer << LINE_END
        }
        buffer.toString()
    }
}
