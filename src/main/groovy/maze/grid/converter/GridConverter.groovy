package maze.grid.converter

import maze.grid.Grid

interface GridConverter<T> {
    T convertGrid( Grid grid )
}