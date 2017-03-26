package maze

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString( includes = ['row', 'column'] )
@EqualsAndHashCode( includes = ['row', 'column'] )
class Cell {
    static final String[] POSSIBLE_NEIGHBOURS = ['north', 'south', 'east', 'west']
    Set<Cell> links = []
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

    boolean onNorthEdge() {
        cellMissing( north )
    }

    boolean onSouthEdge() {
        cellMissing( south )
    }

    boolean onEastEdge() {
        cellMissing( east )
    }

    boolean onWestEdge() {
        cellMissing( west )
    }

    Set neighbours( filter = POSSIBLE_NEIGHBOURS ) {
        filter.collect { this."$it" }.findAll { it != null }
    }

    boolean linkedTo( Cell cell ) {
        links.contains( cell )
    }

    void link( Cell cell ) {
        links << cell
        if ( !cell.linkedTo( this ) ) {
            cell.link( this )
        }
    }

    void unlink( Cell cell ) {
        links.remove( cell )
        if ( cell.linkedTo( this ) ) {
            cell.unlink( this )
        }
    }

    private static boolean cellMissing( Cell cell ) {
        !(cell as boolean)
    }
}
