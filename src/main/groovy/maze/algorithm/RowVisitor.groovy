package maze.algorithm

import maze.Cell

interface RowVisitor {
    void visitRow( List<Cell> row )
}
