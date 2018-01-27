package maze.grid

import groovy.util.logging.Slf4j

@Slf4j
class Row {
    @Delegate
    List<Cell> cells = []

    int rowIndex
    int rowPosition

    Row(rowIndex) {
        log.debug "Creating row: $rowIndex"
        this.rowIndex = rowIndex
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
        cells << new Cell(rowPosition, cells.size() + 1)
    }

    void visitEachCell(CellVisitor visitor) {
        cells.each { cell ->
            visitor.visitCell(cell)
        }
    }
}
