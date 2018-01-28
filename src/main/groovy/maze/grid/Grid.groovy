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
        gridRows.last().lastRow = true
        this.visitEachRow(new BasicCellLinker(this))
    }

    int size() {
        rows * columns
    }

    boolean cellInGrid(int row, int column) {
        row >= 0 && row <= rows && column >= 0 && column <= columns
    }

    Cell cell(int row, int column) {
        if (!cellInGrid(row, column)) {
            throw new IllegalArgumentException("No cell $row,$column in grid with dimensions ${rows}x${columns}")
        }
        cellAtIndex(row - 1, column - 1)
    }

    Cell cellAtIndex(int rowIndex, int columnIndex) {
        if (!((rowIndex >= 0) && (rowIndex <= gridRows.size() - 1))) {
            return null
        }

        if (!(columnIndex >= 0) && (columnIndex <= gridRows[rowIndex].size() - 1)) {
            return null
        }

        log.debug "Returning cell with index $rowIndex,$columnIndex"
        gridRows[rowIndex][columnIndex] as Cell
    }

    void visitEachRow(RowVisitor visitor) {
        gridRows.each { row ->
            visitor.visitRow(row)
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

    String cellContent(Cell cell) {
        " "
    }
}
