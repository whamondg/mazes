package maze

class UnicodeLinePrinter {
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
            CELL_BODY      : '   ',
            CORNER         : '╋',
            VERTICAL_EDGE  : '┃',
            VERTICAL_LINK  : ' ',
            HORIZONTAL_EDGE: '━━━',
            HORIZONTAL_LINK: '   '
    ]


    def buffer = new StringBuffer()

    def add( String element ) {
        buffer << parts[element]
        this
    }

    def add( def elements ) {
        elements.each { buffer << parts[it] }
        this
    }

    String toString() {
        buffer.toString()
    }

    def lineEnd() {
        buffer << LINE_END
        this
    }
}
