package maze

import groovy.transform.ToString

@ToString( includeNames = true, excludes = ['links'] )
class Cell {
    int row
    int column
    Set links = []

    Cell( int row, int column ) {
        this.row = row
        this.column = column
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