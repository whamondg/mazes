package maze

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString( includes = ['row', 'column'] )
@EqualsAndHashCode( includes = ['row', 'column'] )
class Cell {
    Set links = []
    Cell north
    Cell south
    Cell east
    Cell west
    int row
    int column

    Cell( int row, int column ) {
        this.row = row
        this.column = column
    }

    Set neighbours( filter ) {
        filter.collect { this."$it" }.findAll { it != null }
    }

    Set neighbours() {
        [north, south, east, west].findAll { it != null }
    }


    boolean linkedTo( Cell cell ) {
        links.contains( cell )
    }

    void link( Cell cell, boolean bidirectional = true ) {
        links << cell
        if ( bidirectional ) {
            cell.link( this, false )
        }
    }

    void unlink( Cell cell, boolean bidirectional = true ) {
        links.remove( cell )
        if ( bidirectional ) {
            cell.unlink( this, false )
        }
    }
}
