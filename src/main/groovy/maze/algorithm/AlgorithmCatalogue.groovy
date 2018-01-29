package maze.algorithm

class AlgorithmCatalogue implements Iterable {
    def algorithms = [
            new BinaryTreeAlgorithm(),
            new SidewinderAlgorithm()
    ]

    def find(String name) {
        algorithms.find { it.name == name }
    }

    String toString() {
        def buffer = new StringBuffer("Available algorithms:")
        buffer << System.getProperty("line.separator")
        algorithms.each {
            buffer << " - ${it.name}: ${it.description}" << System.getProperty("line.separator")
        }
        buffer
    }

    @Override
    Iterator iterator() {
        algorithms.iterator()
    }
}

