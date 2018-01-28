package maze.grid

import groovy.transform.ToString
import groovy.util.logging.Slf4j

@Slf4j
@ToString(includeNames = true, includes = ['root', 'cells'])
class CellDistances {

    Cell root
    Map<Cell, Integer> cells = [:]

    CellDistances(Cell root) {
        this.root = root
        cells[root] = 0
    }

     CellDistances calculate(){
        List<Cell> frontier = [root]
        while ( frontier.size() > 0 ) {
            frontier = calculateFrontierDistances( frontier )
        }
        this
    }

    def getAt(Cell cell) {
        cells[cell]
    }

    void add(Cell cell, int distance) {
        cells[cell] = distance
    }

    boolean contains(Cell cell) {
        cells.keySet().contains(cell)
    }


    List<Cell> calculateFrontierDistances( List<Cell> frontier) {
        List<Cell> newFrontier = []
        frontier.each { Cell cell ->
            cell.links.each { Cell link ->
                if (!contains(link)) {
                    add(link, (cells[cell] + 1))
                    newFrontier << link
                }
            }
        }
        newFrontier
    }

    CellDistances pathTo(Cell goal) {
        log.debug("Calculating distance from $goal to $root")

        CellDistances breadCrumbs = new CellDistances(root)
        breadCrumbs.add(goal, cells.get(goal))

        Cell current = goal;
        while (current != root) {
            for (Cell link : current.links) {
                if (cells[link] < cells.get(current)) {
                    breadCrumbs.add(link, cells.get(link))
                    current = link
                    break
                }
            }
        }
        breadCrumbs
    }
}
