package maze.grid

import groovy.util.logging.Slf4j
import maze.grid.converter.GridConverter
import maze.grid.converter.UnicodeGridConverter

@Slf4j
class Grid {
    List<Row> gridRows = []
    int rows
    int columns
    GridConverter stringConverter = new UnicodeGridConverter()

    Grid( int rows, int columns ) {
        this.rows = rows
        this.columns = columns
        prepareGrid()
        configureCells()
    }

    void prepareGrid() {
        log.debug "Preparing grid: rows:$rows, columns:$columns"
        rows.times { rowIndex ->
            log.debug "Creating row: $rowIndex"
            def row = new Row(rowIndex)
            columns.times { column ->
                row.addCell()
            }
            gridRows << row
        }
    }

    void configureCells() {
        gridRows.eachWithIndex { row, rowIdx ->
            row.eachWithIndex { cell, colIdx ->
                log.debug "Configure cell $rowIdx, $colIdx"
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

    String cellContent( Cell cell) {
        " "
    }

    protected Cell gridCell( int row, int column ) {
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
        stringConverter.convertGrid( this )
    }

}
