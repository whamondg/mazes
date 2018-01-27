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
        int rowIndex = cell.row - 1
        int colIndex = cell.column - 1

        log.debug "Linking cell $rowIndex, $colIndex to neighbours"
        cell.north = grid.gridCell(rowIndex - 1, colIndex)
        cell.south = grid.gridCell(rowIndex + 1, colIndex)
        cell.east = grid.gridCell(rowIndex, colIndex + 1)
        cell.west = grid.gridCell(rowIndex, colIndex - 1)
    }
}
