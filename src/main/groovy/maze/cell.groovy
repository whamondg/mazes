package maze

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

    def link( Cell cell ) {
        links[cell] = true
    }


}
