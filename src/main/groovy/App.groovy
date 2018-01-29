import com.beust.jcommander.JCommander
import maze.MazeCreator
import maze.algorithm.BinaryTreeAlgorithm
import maze.algorithm.SidewinderAlgorithm
import maze.cli.Settings

Settings settings = new Settings();
JCommander cliParser = JCommander.newBuilder()
        .addObject(settings)
        .build()

cliParser.parse(args)

if (settings.help) {
    cliParser.usage()
    System.exit(0)
}

def algorithms = [
        new BinaryTreeAlgorithm(),
        new SidewinderAlgorithm()
]

def selectedAlgorithm = algorithms.find { it.name == settings.algorithm }
boolean galleryMode = (!selectedAlgorithm)

println """
Settings:
    Given algorithm: ${settings.algorithm}
    Selected algorithm: ${selectedAlgorithm}
    GalleryMode: ${galleryMode}
    Dimensions = ${settings.rows} x ${settings.cols}
"""

MazeCreator creator = new MazeCreator()

if (galleryMode) {
    algorithms.each { algorithm ->
        println "Algorithm: ${algorithm.name}"
        creator.drawMaze(algorithm, settings)
    }
} else {
    creator.drawMaze(selectedAlgorithm, settings)
}
