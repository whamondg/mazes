package maze.grid.converter

import maze.grid.Cell
import maze.grid.Grid

class UnicodeGridConverter implements GridConverter<String> {
    static final String LINE_END = System.getProperty( "line.separator" )
    def parts = [
            TOP_LEFT       : '┏',
            TOP_JOINT      : '┳',
            TOP_RIGHT      : '┓',
            BOTTOM_LEFT    : '┗',
            BOTTOM_JOINT   : '┻',
            BOTTOM_RIGHT   : '┛',
            LEFT_JOINT     : '┣',
            RIGHT_JOINT    : '┫',
            CORNER         : '╋',
            VERTICAL_EDGE  : '┃',
            VERTICAL_LINK  : ' ',
            HORIZONTAL_EDGE: '━━━━',
            HORIZONTAL_LINK: '    '
    ]

    String convertGrid( Grid grid ) {
        def buffer = new StringBuffer()
        buffer << parts.TOP_LEFT
        (grid.columns - 1).times {
            buffer <<  parts.HORIZONTAL_EDGE << parts.TOP_JOINT
        }
        buffer <<  parts.HORIZONTAL_EDGE << parts.TOP_RIGHT << LINE_END


        grid.gridRows.eachWithIndex { row, rowIdx ->
            def middleRow = [parts.VERTICAL_EDGE]
            def bottomRow = [] << (grid.lastRow( rowIdx ) ? parts.BOTTOM_LEFT : parts.LEFT_JOINT)

            row.eachWithIndex { cell, cellIdx ->
                middleRow << " ${grid.cellContent( cell ).padRight(3)}"
                middleRow << (cell?.linkedTo( cell.east ) ? parts.VERTICAL_LINK : parts.VERTICAL_EDGE)

                bottomRow << (cell?.linkedTo( cell.south ) ? parts.HORIZONTAL_LINK : parts.HORIZONTAL_EDGE)

                if ( grid.lastRow( rowIdx ) ) {
                    bottomRow << (grid.lastColumn( cellIdx ) ? parts.BOTTOM_RIGHT : parts.BOTTOM_JOINT)
                } else {
                    bottomRow << (grid.lastColumn( cellIdx ) ? parts.RIGHT_JOINT : parts.CORNER)
                }
            }

            middleRow.each { buffer << it}
            buffer << LINE_END
            bottomRow.each { buffer << it}
            buffer << LINE_END
        }

        buffer.toString()
    }

}
