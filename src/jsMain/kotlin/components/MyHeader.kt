package components

import kotlinx.browser.window
import mui.icons.material.GitHub
import mui.icons.material.YouTube
import mui.material.*
import mui.system.sx
import react.FC
import react.ReactNode
import react.dom.html.ReactHTML.img
import web.cssom.number
import web.cssom.px

val MyHeader = FC {
    Box {
        sx {
            flexGrow = number(1.0)
            paddingBottom = 100.px
        }

        AppBar {
            position = AppBarPosition.fixed

            Toolbar {
                IconButton {
                    Link {
                        href = "/static"
                        img {
                            src = "logo.png"
                            alt = "Homepage"
                            width = 80.0
                            height = 80.0
                        }
                    }
                }

                Typography {
                    sx {
                        flexGrow = number(1.0)
                    }
                }

                // Tooltip für Extra Infos beim Hovern
                Tooltip {
                    title = ReactNode("GitHub Repo")
                    IconButton {
                        onClick = {
                            window.location.href = "https://github.com/Ashkan-san/Health-Grind"
                        }
                        GitHub() {
                            fontSize = SvgIconSize.large
                        }
                    }
                }

                Tooltip {
                    title = ReactNode("YouTube Video")
                    IconButton {
                        onClick = {
                            window.location.href = "https://www.youtube.com/watch?v=5COvqCY2ekk&ab_channel=Ashman"
                        }
                        YouTube() {
                            fontSize = SvgIconSize.large
                        }
                    }
                }
            }
        }
    }
}