package components

import mui.icons.material.EmojiEvents
import mui.material.BottomNavigation
import mui.material.BottomNavigationAction
import react.FC
import react.Props
import react.ReactNode
import react.useState

val MyBottomNavigation = FC<Props> {
    var state = useState(0)

    BottomNavigation {
        showLabels = true
        value = state

        // Event Handler als Lambda
        // _ ist Event Argument und unnötig hier
        // tabIndex ist der Index vom bestimmten Tab
        onChange = { _, tabIndex -> state = tabIndex }

        BottomNavigationAction {
            label = ReactNode("Profile")
            //AccountCircle()
        }
        BottomNavigationAction {
            label = ReactNode("Challenges")
            //icon = ReactNode("$SportsEsports")
            //icon = jso { SportsEsports }
        }
        BottomNavigationAction {
            label = ReactNode("Rewards")
            EmojiEvents()
        }
    }
}