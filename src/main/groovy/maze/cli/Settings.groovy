package maze.cli

import com.beust.jcommander.Parameter

class Settings {
    int defaultSize = 10

    @Parameter(names = ["-h", "--help"], help = true)
    private boolean help

    @Parameter(names = ["-r", "--rows"], description = "Number of rows to add to the grid")
    Integer rows = defaultSize

    @Parameter(names = ["-c", "--cols"], description = "Number of columns to add to the grid")
    Integer cols = defaultSize

    @Parameter(names= ["-a", "--algorithm"], description = "The algorithm to use when drawing the maze.  If absent a gallery of mazes will be produced using each algorithm in turn")
    String algorithm

    @Parameter(names = ["-s", "--start"], description = "Cell position to use as the start of the maze", splitter = CommaSplitter.class)
    List<Integer> start

    @Parameter(names = ["-e", "--end"], description = "Cell position to use as the end of the maze", splitter = CommaSplitter.class)
    List<Integer> end
}
