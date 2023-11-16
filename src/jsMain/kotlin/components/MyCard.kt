package components

import mui.icons.material.PlusOne
import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.img
import web.cssom.*

val MyCard2 = FC<Props> { props ->

    Grid {
        container = true
        spacing = responsive(2)
        sx {
            alignItems = AlignItems.center
            justifyContent = JustifyContent.center
        }

        Grid {
            item = true

            Card {
                //raised = true
                sx {
                    width = 400.px
                }
                CardContent {
                    /*Typography {
                        variant = h1
                        component = div
                        gutterBottom = true
                        +"Was ist HealthGrind?"
                    }*/
                    h1 { +"Was ist HealthGrind?" }

                    +"HealthGrind ist eine mit Hilfe von Jetpack Compose Wear, Kotlin und Firebase entwickelte Wearable App für\n"
                    +"    WearOs-Geräte. Das Konzept ist, dass der Nutzer sportliche Übungen in Form von \"Challenges\" absolviert, diese mit\n"
                    +"    Hilfe der Smartwatch-Sensoren aufgezeichnet werden und bei erfolgreichem Abschluss von genügend Challenges Rewards\n"
                    +"    in Form von In-Game-Artikeln freischaltet. Das Ziel ist es somit Gamer zu mehr Sport zu motivieren."
                }
                CardActions {
                    Button {
                        +"Klick mich"
                    }
                    Button {
                        PlusOne()
                    }
                }
            }
        }

        Grid {
            item = true

            img {
                src = "thumbnail.png"
                height = 500.0
            }
        }
    }
}

val MyCard = FC<Props> { props ->

    Card {
        sx {
            //alignItems = AlignItems.center
            justifyContent = JustifyContent.center
        }

        Box {
            sx {
                flexDirection = FlexDirection.row
                display = Display.flex
            }
            CardContent {
                Typography {
                    sx {
                        //fontFamily = "Roboto Black".unsafeCast<FontFamily>()
                    }
                    variant = TypographyVariant.h2
                    gutterBottom = true

                    +"Was ist HealthGrind?"
                }

                Typography {
                    variant = TypographyVariant.body1
                    +"HealthGrind ist eine mit Hilfe von Jetpack Compose Wear, Kotlin und Firebase entwickelte Wearable App für\n"
                    +"    WearOs-Geräte. Das Konzept ist, dass der Nutzer sportliche Übungen in Form von \"Challenges\" absolviert, diese mit\n"
                    +"    Hilfe der Smartwatch-Sensoren aufgezeichnet werden und bei erfolgreichem Abschluss von genügend Challenges Rewards\n"
                    +"    in Form von In-Game-Artikeln freischaltet. Das Ziel ist es somit Gamer zu mehr Sport zu motivieren."
                }
                //h1 { +"Was ist HealthGrind?" }
            }
            /*CardActions {
                Button {
                    +"Klick mich"
                }
                Button {
                    PlusOne()
                }
            }*/
            img {
                height = 600.0
                src = "thumbnail.png"
            }
        }

        /*CardMedia {
            sx {
                height = 200.px
                //width = 100.px
            }
            image = "thumbnail.png"
        }*/

    }
}