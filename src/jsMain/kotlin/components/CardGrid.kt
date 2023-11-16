package components

import mui.material.*
import mui.material.styles.TypographyVariant
import mui.system.sx
import react.FC
import react.Props
import react.dom.html.ReactHTML
import theme.Themes.HealthGrindTheme
import web.cssom.*

val CardGrid = FC<Props> { props ->
    Grid {
        container = true
        //spacing = responsive(2)

        sx {
            //flexDirection = FlexDirection.row
            flexWrap = FlexWrap.nowrap
            display = Display.flex
            //flexGrow = number(1.0)
            alignItems = AlignItems.stretch
            justifyContent = JustifyContent.spaceBetween
            //justifyItems = JustifyItems.center
        }

        Grid {
            item = true
            Card {
                sx {
                    width = 450.px
                }
                CardMedia {
                    sx {
                        height = 200.px
                    }
                    image = "fitness.jpg"
                }
                CardContent {
                    Typography {
                        variant = TypographyVariant.h2
                        component = ReactHTML.div
                        gutterBottom = true
                        +"Fitness"
                    }
                    +"Körperliche Aktivität, Sport, Fitness: unterschiedliche Begriffe für eine der besten Aktivitäten für den Menschen. Zig Studien zeigen, dass Bewegung unmengen an Vorteilen für den Menschen bringt. Bessere Gesundheit, fähigeres Gehirn und tieferer Schlaf. Doch wie motiviert man sich am besten, wenn man Gamer ist?"
                }
            }
        }

        Grid {
            item = true
            Card {
                sx {
                    width = 450.px
                }
                CardMedia {
                    sx {
                        height = 200.px
                    }
                    image = "gaming.jpg"
                }
                CardContent {
                    Typography {
                        variant = TypographyVariant.h2
                        component = ReactHTML.div
                        gutterBottom = true
                        +"Gaming"
                    }
                    +"Gaming ist eines der beliebtesten Hobbies heutzutage. Es spielen mittlerweile rund 50% der Deutschen Videospiele und die Zahl wächst jeden Tag weiter. Auch In-Game-Käufe zeigen sich immer mehr Beliebtheit. Doch was tun, wenn einem mal das Geld dafür fehlt?"
                }
            }
        }

        Grid {
            item = true

            Card {
                sx {
                    width = 450.px
                }
                CardMedia {
                    sx {
                        height = 200.px
                        //width = 100.pct
                        padding = 10.px
                        objectFit = ObjectFit.contain
                        background = HealthGrindTheme.palette.primary.main
                    }
                    component = ReactHTML.img
                    image = "combine.png"
                }
                CardContent {
                    Typography {
                        variant = TypographyVariant.h2
                        component = ReactHTML.div
                        gutterBottom = true
                        +"Combine"
                    }
                    +"Einfach mit HealthGrind das beste von beiden Welten kombinieren! Sportliche Challenges bewältigen und In-Game-Artikel für deine Lieblings Games freischalten. Worauf wartest du noch?"
                }
            }
        }
    }
}