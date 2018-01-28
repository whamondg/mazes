import com.beust.jcommander.JCommander
import maze.algorithm.BinaryTreeAlgorithm
import maze.algorithm.SidewinderAlgorithm
import maze.cli.Settings
import maze.grid.DistanceGrid
import maze.grid.Grid

Settings settings = new Settings();
new JCommander(settings, args);

def algorithms = [
        new BinaryTreeAlgorithm(),
        new SidewinderAlgorithm()
]

println """
Creating Maze:
    Dimensions = ${settings.rows} x ${settings.cols}
"""

algorithms.each { drawMaze(it, settings) }

//println "Distance grid solved with Dijkstra's Algorithm"
//DistanceGrid distanceGrid = new DistanceGrid(rows,cols)
//new SidewinderAlgorithm(  ).on(distanceGrid)
//
//distanceGrid.start(1,1)
//println distanceGrid
//
//distanceGrid.distances = distanceGrid.distances.pathTo(distanceGrid.cell(distanceGrid.rows,distanceGrid.columns))
//println distanceGrid

void drawMaze(algorithm, settings) {
    println "Algorithm: ${algorithm.name}"
    Grid grid = new Grid( settings.rows, settings.cols )
    new BinaryTreeAlgorithm().on( grid )
    println grid
}