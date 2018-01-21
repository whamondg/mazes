package maze.algorithm

import maze.Cell

class BinaryTreeAlgorithm implements CellVisitor {

    static final VALID_NEIGHBOURS = ['north', 'east']

    int randomIndex( int limit ) {
        new Random().nextInt( limit )
    }

    Cell randomCell( Set<Cell> cells ) {
        (cells.size() == 0) ? null : cells[randomIndex( cells.size() )]
    }

    Cell randomNeighbour(Cell cell) {
        randomCell( cell.neighbours( VALID_NEIGHBOURS ) )
    }

    void visitCell( Cell cell ) {
        def neighbour = randomNeighbour(cell)
        if ( neighbour ) {
            cell.link( neighbour )
        }
    }

    def on( grid ) {
        grid.visitEachCell( this )
    }
}
