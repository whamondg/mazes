import maze.Grid

int row = args[0] as int
int col = args[1] as int

println()
println "Creating Maze with dimensions $row $col"
def grid = new Grid( row, col )

println()
println grid
