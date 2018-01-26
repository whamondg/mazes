package maze.grid

import groovy.transform.EqualsAndHashCode

//@EqualsAndHashCode( includes = ['cells'] )
class Row {
    @Delegate
    List<Cell> cells = []

    Row() {

    }

    Row(List<Cell> cells) {
        this.cells = cells
    }

}
