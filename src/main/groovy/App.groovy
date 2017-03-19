import maze.Grid
import maze.algorithm.BinaryTreeAlgorithm

int row = args[0] as int
int col = args[1] as int

Grid grid = new Grid( row, col )

println()
println """
Creating Maze:
    Size = ${ grid.size() }
    Dimensions = ${ grid.dimensions() }
"""
println()



new BinaryTreeAlgorithm().on( grid )

println grid