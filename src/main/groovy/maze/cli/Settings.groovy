package maze.cli

import com.beust.jcommander.Parameter

class Settings {
    Random random = new Random()
    int randomGridLimit = 15

    @Parameter(names = ["-r", "-rows"], description = "Number of rows to add to the grid")
    Integer rows = random.nextInt(randomGridLimit)

    @Parameter(names = ["-c", "-cols"], description = "Number of columns to add to the grid")
    Integer cols = random.nextInt(randomGridLimit)
}