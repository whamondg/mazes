package maze

import maze.grid.DistanceGrid

class MazeCreator {

    void drawMaze(algorithm, settings) {
        DistanceGrid grid = new DistanceGrid(settings.rows, settings.cols)
        algorithm.on(grid)
        if (settings.start) {
            grid.start(settings.start[0], settings.start[1])
        }
        if (settings.end) {
            grid.distances = grid.distances.pathTo(grid.cell(settings.end[0], settings.end[1]))
        }
        println grid
    }

}
