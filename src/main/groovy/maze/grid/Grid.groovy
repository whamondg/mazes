package maze.grid

import groovy.util.logging.Slf4j
import maze.grid.converter.GridConverter
import maze.grid.converter.UnicodeGridConverter

@Slf4j
class BasicCellLinker implements RowVisitor {

    @Override
    void visitRow(Row row, Grid grid) {
        row.eachWithIndex { cell, colIdx ->
            log.debug "Configure cell $row.rowIndex, $colIdx"
            cell.north = grid.gridCell(row.rowIndex - 1, colIdx)
            cell.south = grid.gridCell(row.rowIndex + 1, colIdx)
            cell.east = grid.gridCell(row.rowIndex, colIdx + 1)
            cell.west = grid.gridCell(row.rowIndex, colIdx - 1)
        }
    }
}

@Slf4j
class Grid {
    List<Row> gridRows = []
    int rows
    int columns
    GridConverter stringConverter = new UnicodeGridConverter()

    Grid(int rows, int columns) {
        this.rows = rows
        this.columns = columns
        createGrid()
    }

    private void createGrid() {
        log.debug "Creating grid: rows:$rows, columns:$columns"
        rows.times { rowIndex ->
            gridRows << new Row(rowIndex).withCells(columns)
        }
        this.visitEachRow(new BasicCellLinker())
    }

    int size() {
        rows * columns
    }

    String dimensions() {
        "${rows}x$columns"
    }

    Cell cell(int row, int column) {
        if (row > this.rows || column > this.columns) {
            throw new IllegalArgumentException("No cell $row,$column in grid with dimensions ${dimensions()}")
        }
        gridCell(row - 1, column - 1)
    }

    String cellContent(Cell cell) {
        " "
    }

    protected Cell gridCell(int row, int column) {
        if (!((row >= 0) && (row <= gridRows.size() - 1))) {
            return null
        }
        if (!(column >= 0) && (column <= gridRows[row].size() - 1)) {
            return null
        }

        gridRows[row][column] as Cell
    }

    boolean lastRow(int rowIdx) {
        rowIdx == rows - 1
    }

    boolean lastColumn(int colIdx) {
        colIdx == columns - 1
    }

    void visitEachRow(RowVisitor visitor) {
        gridRows.each { row ->
            visitor.visitRow(row, this)
        }
    }

    void visitEachCell(CellVisitor visitor) {
        gridRows.each { row ->
            row.visitEachCell(visitor)
        }
    }

    String toString() {
        stringConverter.convertGrid(this)
    }

}
