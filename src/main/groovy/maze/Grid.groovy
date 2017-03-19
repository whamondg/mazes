package maze

import maze.algorithm.CellVisitor
import maze.algorithm.RowVisitor

class Grid {
    List<List<Cell>> gridRows = []
    int rows
    int columns
    def linePrinter = new UnicodeLinePrinter()

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
            row.eachWithIndex { cell, colIdx ->
                cell.north = gridCell( rowIdx - 1, colIdx )
                cell.south = gridCell( rowIdx + 1, colIdx )
                cell.east = gridCell( rowIdx, colIdx + 1 )
                cell.west = gridCell( rowIdx, colIdx - 1 )
            }
        }
    }

    int size() {
        rows * columns
    }

    String dimensions() {
        "${ rows }x$columns"
    }

    Cell cell( int row, int column ) {
        if ( row > this.rows || column > this.columns ) {
            throw new IllegalArgumentException( "No cell $row,$column in grid with dimensions ${ dimensions() }" )
        }
        gridCell( row - 1, column - 1 )
    }

    private Cell gridCell( int row, int column ) {
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

    void visitEachRow( RowVisitor visitor ) {
        gridRows.each { row ->
            visitor.visitRow( row )
        }
    }

    void visitEachCell( CellVisitor visitor ) {
        gridRows.each { row ->
            row.each { cell ->
                visitor.visitCell( cell )
            }
        }
    }

    String toString() {
        linePrinter.add( 'TOP_LEFT' )
        (columns - 1).times {
            linePrinter.add( 'HORIZONTAL_EDGE' ).add( 'TOP_JOINT' )
        }
        linePrinter.add( 'HORIZONTAL_EDGE' ).add( 'TOP_RIGHT' ).lineEnd()

        gridRows.eachWithIndex { row, rowIdx ->
            def middleRow = ['VERTICAL_EDGE']
            def bottomRow = [] << (lastRow( rowIdx ) ? 'BOTTOM_LEFT' : 'LEFT_JOINT')

            row.eachWithIndex { cell, cellIdx ->
                middleRow << 'CELL_BODY'
                middleRow << (cell?.linkedTo( cell.east ) ? 'VERTICAL_LINK' : 'VERTICAL_EDGE')

                bottomRow << (cell?.linkedTo( cell.south ) ? 'HORIZONTAL_LINK' : 'HORIZONTAL_EDGE')

                if ( lastRow( rowIdx ) ) {
                    bottomRow << (lastColumn( cellIdx ) ? 'BOTTOM_RIGHT' : 'BOTTOM_JOINT')
                } else {
                    bottomRow << (lastColumn( cellIdx ) ? 'RIGHT_JOINT' : 'CORNER')
                }
            }

            linePrinter.add( middleRow ).lineEnd()
            linePrinter.add( bottomRow ).lineEnd()
        }

        linePrinter.toString()
    }

}
