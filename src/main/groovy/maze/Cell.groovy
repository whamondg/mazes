package maze

import groovy.transform.ToString

@ToString( includePackage = false, excludes = ['adjacentCells', 'links'] )
class Cell {
    int row
    int column
    Map<String, Cell> adjacentCells = [ : ]
    Set links = [ ]

    Cell( int row, int column ) {
        this.row = row
        this.column = column
    }

    void north( Cell cell ) {
        adjacentCells.north = cell
    }

    void south( Cell cell ) {
        adjacentCells.south = cell
    }

    void east( Cell cell ) {
        adjacentCells.east = cell
    }

    void west( Cell cell ) {
        adjacentCells.west = cell
    }

    Set neighbours() {
        adjacentCells.values()
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