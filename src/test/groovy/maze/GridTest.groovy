package maze

import spock.lang.Specification
import spock.lang.Unroll

class GridTest extends Specification {
    static final UNIMPORTANT = 1
    static final LINE_END = System.getProperty( "line.separator" )

    def "a grid knows the number of rows it has"() {
        when:
        def grid = new Grid( rows, UNIMPORTANT )

        then:
        grid.rows == rows

        where:
        rows << [1, 10, 100]
    }

    def "a grid knows the number of columns it has"() {
        when:
        def grid = new Grid( UNIMPORTANT, columns )

        then:
        grid.columns == columns


        where:
        columns << [1, 10, 100]
    }

    @Unroll
    def "a grid cell can be accessed row=#row column=#column"() {
        setup:
        def grid = new Grid( 3, 3 )

        when:
        def cell = grid.cell( row, column )

        then:
        cell == new Cell( row, column )

        where:
        row | column
        1   | 1
        1   | 2
        1   | 3
        2   | 1
        2   | 2
        2   | 3
        3   | 1
        3   | 2
        3   | 3
    }



    @Unroll
    def "grid size is #size when rows=#rows and columns=#columns"() {

        when:
        def grid = new Grid( rows, columns )

        then:
        grid.size() == size

        where:
        rows | columns || size
        1    | 1       || 1
        1    | 2       || 2
        2    | 1       || 2
        2    | 2       || 4
        10   | 10      || 100
    }

    @Unroll
    def "grid cell #row,#column knows it's neighbours"() {
        setup:
        def grid = new Grid( 3, 3 )

        when:
        def cell = grid.cell( row, column )

        then:
        neighbours.each { neighbour, coordinates ->
            def expected = (coordinates == null) ? null : new Cell( coordinates[0], coordinates[1] )
            assert cell."$neighbour" == expected
        }

        where:
        row | column | neighbours
        1   | 1      | ['north': null, 'south': [2, 1], 'east': [1, 2], 'west': null]
        1   | 2      | ['north': null, 'south': [2, 2], 'east': [1, 3], 'west': [1, 1]]
        1   | 3      | ['north': null, 'south': [2, 3], 'east': null, 'west': [1, 2]]
        2   | 1      | ['north': [1, 1], 'south': [3, 1], 'east': [2, 2], 'west': null]
        2   | 2      | ['north': [1, 2], 'south': [3, 2], 'east': [2, 3], 'west': [2, 1]]
        2   | 3      | ['north': [1, 3], 'south': [3, 3], 'east': null, 'west': [2, 2]]
        3   | 1      | ['north': [2, 1], 'south': null, 'east': [3, 2], 'west': null]
        3   | 2      | ['north': [2, 2], 'south': null, 'east': [3, 3], 'west': [3, 1]]
        3   | 3      | ['north': [2, 3], 'south': null, 'east': null, 'west': [3, 2]]
    }

    def "a grid with no links can be output as a string"() {
        setup:
        def grid = new Grid( 3, 5 )
        def expected = "┏━━━┳━━━┳━━━┳━━━┳━━━┓${ LINE_END }" +
                "┃   ┃   ┃   ┃   ┃   ┃${ LINE_END }" +
                "┣━━━╋━━━╋━━━╋━━━╋━━━┫${ LINE_END }" +
                "┃   ┃   ┃   ┃   ┃   ┃${ LINE_END }" +
                "┣━━━╋━━━╋━━━╋━━━╋━━━┫${ LINE_END }" +
                "┃   ┃   ┃   ┃   ┃   ┃${ LINE_END }" +
                "┗━━━┻━━━┻━━━┻━━━┻━━━┛${ LINE_END }"

        when:
        def result = grid.toString()
        println result

        then:
        result == expected as String
    }

    def "a grid with some links can be output as a string"() {
        setup:
        def grid = new Grid( 3, 5 )
        grid.cell( 1, 1 ).link( grid.cell( 1, 2 ) )
        grid.cell( 1, 2 ).link( grid.cell( 2, 2 ) )
        grid.cell( 2, 2 ).link( grid.cell( 2, 1 ) )
        grid.cell( 1, 4 ).link( grid.cell( 2, 4 ) )
        grid.cell( 2, 4 ).link( grid.cell( 3, 4 ) )
        grid.cell( 1, 4 ).link( grid.cell( 1, 5 ) )

        def expected = "┏━━━┳━━━┳━━━┳━━━┳━━━┓${ LINE_END }" +
                "┃       ┃   ┃       ┃${ LINE_END }" +
                "┣━━━╋   ╋━━━╋   ╋━━━┫${ LINE_END }" +
                "┃       ┃   ┃   ┃   ┃${ LINE_END }" +
                "┣━━━╋━━━╋━━━╋   ╋━━━┫${ LINE_END }" +
                "┃   ┃   ┃   ┃   ┃   ┃${ LINE_END }" +
                "┗━━━┻━━━┻━━━┻━━━┻━━━┛${ LINE_END }"

        when:
        def result = grid.toString()
        println result

        then:
        result == expected as String
    }

    def "a grid with all links can be output as a string"() {
        setup:
        def grid = new Grid( 3, 3 )
        grid.cell( 1, 1 ).link( grid.cell( 1, 2 ) )
        grid.cell( 1, 2 ).link( grid.cell( 1, 3 ) )

        grid.cell( 1, 1 ).link( grid.cell( 2, 1 ) )
        grid.cell( 1, 2 ).link( grid.cell( 2, 2 ) )
        grid.cell( 1, 3 ).link( grid.cell( 2, 3 ) )

        grid.cell( 2, 1 ).link( grid.cell( 2, 2 ) )
        grid.cell( 2, 2 ).link( grid.cell( 2, 3 ) )

        grid.cell( 2, 1 ).link( grid.cell( 3, 1 ) )
        grid.cell( 2, 2 ).link( grid.cell( 3, 2 ) )
        grid.cell( 2, 3 ).link( grid.cell( 3, 3 ) )

        grid.cell( 3, 1 ).link( grid.cell( 3, 2 ) )
        grid.cell( 3, 2 ).link( grid.cell( 3, 3 ) )

        def expected = "┏━━━┳━━━┳━━━┓${ LINE_END }" +
                "┃           ┃${ LINE_END }" +
                "┣   ╋   ╋   ┫${ LINE_END }" +
                "┃           ┃${ LINE_END }" +
                "┣   ╋   ╋   ┫${ LINE_END }" +
                "┃           ┃${ LINE_END }" +
                "┗━━━┻━━━┻━━━┛${ LINE_END }"

        when:
        def result = grid.toString()

        then:
        result == expected as String
    }
}
