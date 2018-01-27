package maze.grid

import groovy.transform.EqualsAndHashCode

//@EqualsAndHashCode( includes = ['cells'] )
class Row {
    @Delegate
    List<Cell> cells = []

    int rowIndex

    Row(rowIndex) {
        this.rowIndex = rowIndex
    }

    Row(int rowIndex, List<Cell> cells) {
        this.rowIndex = rowIndex
        this.cells = cells
    }

    int rowPosition() {
        rowIndex + 1
    }

    void addCell() {
        cells << new Cell(rowPosition(), cells.size() + 1)
    }
}
