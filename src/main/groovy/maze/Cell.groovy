package maze

import groovy.transform.ToString

@ToString( includeNames = true, excludes = ['links'] )
class Cell {
    int row
    int column
    Map links = [ : ]

    Cell( int row, int column ) {
        this.row = row
        this.column = column
    }

    boolean linkedTo( Cell cell ) {
        links.containsKey( cell )
    }

    def link( Cell cell, boolean bidirectional = true ) {
        links[cell] = true
        if ( bidirectional ) {
            cell.link( this, false )
        }
    }


}
