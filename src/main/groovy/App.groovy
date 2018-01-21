import maze.Grid
import maze.algorithm.BinaryTreeAlgorithm
import maze.algorithm.SidewinderAlgorithm

int row = args[0] as int
int col = args[1] as int

Grid grid = new Grid( row, col )

println """
Creating Maze:
    Size = ${ grid.size() }
    Dimensions = ${ grid.dimensions() }
"""

new BinaryTreeAlgorithm().on( grid )
println "Binary Tree maze"
println grid

Grid grid2 = new Grid( row, col )
new SidewinderAlgorithm().on( grid2 )
println "Sidewinder maze"
println grid2