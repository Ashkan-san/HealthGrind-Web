package theme

import js.core.jso
import mui.material.PaletteMode
import mui.material.styles.TypographyOptions
import mui.material.styles.TypographyVariant
import mui.material.styles.createTheme
import web.cssom.*
import web.cssom.atrule.maxWidth


private val TYPOGRAPHY_OPTIONS = TypographyOptions {
    fontWeight = integer(500)

    TypographyVariant.h2 {
        fontWeight = FontWeight.bold
    }

    TypographyVariant.body1 {
        fontSize = 1.2.rem
    }

    TypographyVariant.h6 {
        fontSize = 1.5.rem
        color = Color("#393A75")

        media(maxWidth(599.px)) {
            fontSize = 1.25.rem
        }
    }
}

object Themes {
    val HealthGrindTheme = createTheme(
        jso {
            palette = jso {
                primary = jso { main = "#393A75" }
                secondary = jso { main = "#FAD3D1" }
                background = jso { default = "#FAD3D1" }
                mode = PaletteMode.light
            }
            typography = TYPOGRAPHY_OPTIONS
        }
    )
}