package maze.grid.converter

import maze.grid.Grid

class UnicodeGridConverter implements GridConverter<String> {
    static final String LINE_END = System.getProperty("line.separator")
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

    String convertGrid(Grid grid) {
        def buffer = new StringBuffer()
        buffer << parts.TOP_LEFT
        (grid.columns - 1).times {
            buffer << parts.HORIZONTAL_EDGE << parts.TOP_JOINT
        }
        buffer << parts.HORIZONTAL_EDGE << parts.TOP_RIGHT << LINE_END

        grid.gridRows.each { row ->
            def middleRow = [parts.VERTICAL_EDGE]
            def bottomRow = [] << (row.lastRow ? parts.BOTTOM_LEFT : parts.LEFT_JOINT)

            row.each { cell ->
                middleRow << " ${grid.cellContent(cell).padRight(3)}"
                middleRow << (cell?.linkedTo(cell.east) ? parts.VERTICAL_LINK : parts.VERTICAL_EDGE)

                bottomRow << (cell?.linkedTo(cell.south) ? parts.HORIZONTAL_LINK : parts.HORIZONTAL_EDGE)

                if (row.lastRow) {
                    bottomRow << (cell.lastCell ? parts.BOTTOM_RIGHT : parts.BOTTOM_JOINT)
                } else {
                    bottomRow << (cell.lastCell ? parts.RIGHT_JOINT : parts.CORNER)
                }
            }

            middleRow.each { buffer << it }
            buffer << LINE_END
            bottomRow.each { buffer << it }
            buffer << LINE_END
        }

        buffer.toString()
    }
}
