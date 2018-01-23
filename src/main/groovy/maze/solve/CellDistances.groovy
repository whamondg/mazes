package maze.solve

import groovy.transform.ToString
import maze.grid.Cell

@ToString( includeNames=true, includes = ['root', 'cellSet'] )
class CellDistances {

    Cell root
    Map<Cell, Integer> cellSet = [:]

    CellDistances( Cell root ) {
        this.root = root
        cellSet[root] = 0
    }

    def getAt( Cell cell ) {
        cellSet[cell]
    }

    void add( Cell cell, int distance ) {
        cellSet[cell] = distance
    }

    Set<Cell> cells() {
        cellSet.keySet()
    }

    boolean contains( Cell cell ) {
        cellSet.keySet().contains( cell )
    }



    List<Cell> calculateFrontierDistances( CellDistances distances, List<Cell> frontier ) {
        List<Cell> newFrontier = []
        frontier.each { Cell cell ->
            cell.links.each { Cell link ->
                if ( !distances.contains( link ) ) {
                    distances.add( link, (distances[cell] + 1) )
                    newFrontier << link
                }
            }
        }
        newFrontier
    }





}
