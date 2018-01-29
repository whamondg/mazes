import com.beust.jcommander.JCommander
import maze.MazeCreator
import maze.algorithm.AlgorithmCatalogue
import maze.cli.Settings

AlgorithmCatalogue algorithms = new AlgorithmCatalogue()

Settings settings = new Settings();
JCommander cliParser = JCommander.newBuilder()
        .addObject(settings)
        .build()

cliParser.parse(args)

if (settings.help) {
    cliParser.usage()
    println algorithms
    System.exit(0)
}

def selectedAlgorithm = algorithms.find(settings.algorithm)
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
