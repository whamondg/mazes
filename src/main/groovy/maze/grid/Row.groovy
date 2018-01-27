package maze.grid

import groovy.util.logging.Slf4j

@Slf4j
class Row {
    @Delegate
    List<Cell> cells = []

    int rowPosition
    boolean lastRow = false

    Row(rowIndex) {
        log.debug "Creating row: $rowIndex"
        this.rowPosition = rowIndex + 1
    }

    Row(int rowIndex, List<Cell> cells) {
        this(rowIndex)
        this.cells = cells
    }

    Row withCells(int cellNumber) {
        cellNumber.times { addCell() }
        return this
    }

    void addCell() {
        if(cells.size() > 0) {
            cells.last().lastCell = false
        }
        cells << new Cell(rowPosition, cells.size() + 1)
        cells.last().lastCell = true
    }

    void visitEachCell(CellVisitor visitor) {
        cells.each { cell ->
            visitor.visitCell(cell)
        }
    }
}
