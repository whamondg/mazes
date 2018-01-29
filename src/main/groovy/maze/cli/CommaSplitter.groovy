package maze.cli

import com.beust.jcommander.converters.IParameterSplitter

class CommaSplitter implements IParameterSplitter {
    List<String> split(String value) {
        value.split(",") as List
    }
}
