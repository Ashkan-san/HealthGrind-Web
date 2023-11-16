package components

import mui.material.*
import react.FC
import react.Props
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th

val MyTable = FC<Props> { props ->
    TableContainer {
        component = Paper
        Table {
            TableHead {
                TableRow {
                    th { +"Header 1" }
                    th { +"Header 2" }
                }
            }

            TableBody {
                TableRow {
                    td { +"Row 1, Cell 1" }
                    td { +"Row 1, Cell 2" }
                }
                TableRow {
                    td { +"Row 2, Cell 1" }
                    td { +"Row 2, Cell 2" }
                }
            }

            TableFooter {
                TableRow {
                    td {
                        colSpan = 2
                        +"Footer 1 "
                    }
                    td {
                        colSpan = 2
                        +"Footer 2"
                    }
                }
            }
        }
    }
}