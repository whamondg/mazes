import maze.Grid
import maze.algorithm.BinaryTreeAlgorithm

int row = args[0] as int
int col = args[1] as int

println()
println "Creating Maze with dimensions $row $col"
println()

def grid = new Grid( row, col )

new BinaryTreeAlgorithm().on( grid )

println grid