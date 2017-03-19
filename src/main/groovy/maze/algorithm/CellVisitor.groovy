package maze.algorithm

import maze.Cell

interface CellVisitor {
    void visitCell( Cell cell )
}