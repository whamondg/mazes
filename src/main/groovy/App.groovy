import maze.algorithm.BinaryTreeAlgorithm
import maze.algorithm.SidewinderAlgorithm
import maze.grid.DistanceGrid
import maze.grid.Grid

int row = args[0] as int
int col = args[1] as int

Grid grid = new Grid( row, col )

println """
Creating Maze:
    Size = ${ grid.size() }
    Dimensions = ${grid.rows} x ${grid.columns}
"""

new BinaryTreeAlgorithm().on( grid )
println "Binary Tree maze"
println grid

Grid grid2 = new Grid( row, col )
new SidewinderAlgorithm().on( grid2 )
println "Sidewinder maze"
println grid2

println "Distance grid solved with Dijkstra's Algorithm"
DistanceGrid distanceGrid = new DistanceGrid(row,col)
new SidewinderAlgorithm(  ).on(distanceGrid)
distanceGrid.start(1,1)
println distanceGrid