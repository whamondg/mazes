package maze.algorithm

import maze.Cell

class BinaryTreeAlgorithm implements CellVisitor {

    def randomCell( Set cells ) {
        Random random = new Random()
        if ( cells.size() == 0 ) {
            return null
        }
        int idx = random.nextInt( cells.size() )
        cells[idx]
    }

    void visitCell( Cell cell ) {
        def neighbours = cell.neighbours( ['north', 'east'] )

        def neighbour = randomCell( neighbours )

        if ( neighbour != null ) {
            cell.link( neighbour )
        }
    }

    def on( grid ) {
        grid.visitEachCell( this )
    }
}
