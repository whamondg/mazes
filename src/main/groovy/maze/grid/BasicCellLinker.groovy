package maze.grid

import groovy.util.logging.Slf4j

@Slf4j
class BasicCellLinker implements CellVisitor, RowVisitor {

    Grid grid

    BasicCellLinker(Grid grid) {
        this.grid = grid
    }

    @Override
    void visitRow(Row row) {
        row.visitEachCell(this)
    }

    @Override
    void visitCell(Cell cell) {
        log.debug "Linking cell $cell.rowIndex, $cell.columnIndex to neighbours"
        cell.north = grid.cellAtIndex(cell.rowIndex - 1, cell.columnIndex)
        cell.south = grid.cellAtIndex(cell.rowIndex + 1, cell.columnIndex)
        cell.east = grid.cellAtIndex(cell.rowIndex, cell.columnIndex + 1)
        cell.west = grid.cellAtIndex(cell.rowIndex, cell.columnIndex - 1)
    }
}
