import components.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mui.material.Stack
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul
import react.useEffectOnce
import react.useState
import theme.ThemeModule
import tutorials.VideoList
import tutorials.VideoPlayer
import tutorials.inputComponent
import web.cssom.px
import kotlin.random.Random

// Scope für Coroutine
private val scope = MainScope()

// FC = Functional Component
// Code darin ist Verhalten und Rendering Logik der Komponente
val App = FC<Props> {
    // Hooks wie useState, useEffect usw. sind von React
    // useState ist wie eine Art Variable?
    // useEffect ist eine Funktion?
    var shoppingList by useState(emptyList<ShoppingListItem>())

    useEffectOnce {
        // scope.launch startet Kotlin Coroutine! Nutzen, wenn ich mit DB interagiere oder Dateien schreibe...
        scope.launch {
            shoppingList = getShoppingList()
        }
    }

    // JSX Syntax, Erweiterung von JavaScript
    h1 {
        +"Full-Stack-Shoppingff asssaaaafffaListtaattttttttttaaa"
    }

    ul {
        shoppingList.sortedByDescending(ShoppingListItem::priority).forEach { item ->
            li {
                key = item.toString()

                // Listenelement onClick geben
                onClick = {
                    scope.launch {
                        deleteShoppingListItem(item)
                        shoppingList = getShoppingList()
                    }
                }

                +"[${item.priority}] ${item.description}"
            }
        }
    }

    inputComponent {
        onSubmit = { input ->
            // Neues Item erstellen
            // TODO ID
            val cartItem = ShoppingListItem(
                id = Random.nextInt(),
                description = input.replace("!", ""),
                priority = input.count { it == '!' }
            )

            scope.launch {
                // Item hinzufügen zum Server via HTTP
                addShoppingListItem(cartItem)
                // neue Liste vom Server holen und React neu Rendern lassen dadurch
                shoppingList = getShoppingList()
            }
        }
    }
}

val App2 = FC<Props> {
    var currentVideo: Video? by useState(null)
    var unwatchedVideos: List<Video> by useState(emptyList())
    var watchedVideos: List<Video> by useState(emptyList())

    useEffectOnce {
        scope.launch {
            unwatchedVideos = fetchVideos()
        }
    }

    h1 { +"KotlinConf Explorer" }
    div {
        h3 { +"Videos to watch" }
        VideoList {
            videos = unwatchedVideos
            selectedVideo = currentVideo
            onSelectVideo = { video ->
                currentVideo = video
            }
        }
        h3 { +"Videos watched" }
        VideoList {
            videos = watchedVideos
            selectedVideo = currentVideo
            onSelectVideo = { video ->
                currentVideo = video
            }
        }
        // .let ruft nur auf, wenn variable nicht null ist!
        currentVideo?.let { current ->
            VideoPlayer {
                video = current
                unwatchedVideo = current in unwatchedVideos
                onWatchedButtonPressed = {
                    if (video in unwatchedVideos) {
                        unwatchedVideos = unwatchedVideos - video
                        watchedVideos = watchedVideos + video
                    } else {
                        watchedVideos = watchedVideos - video
                        unwatchedVideos = unwatchedVideos + video
                    }
                }
            }
        }
    }
}

val App3 = FC<Props> {
    ThemeModule {
        MyHeader()
        Stack {
            sx {
                padding = 50.px
            }
            spacing = responsive(2)

            MyCard()
            CardGrid()
            MyTable()
            MyBottomNavigation()
        }
    }

}