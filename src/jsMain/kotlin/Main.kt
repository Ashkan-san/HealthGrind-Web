import emotion.react.css
import react.create
import react.dom.client.createRoot
import kotlinx.browser.document
import react.Fragment
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.p
import web.cssom.Position
import web.cssom.px

// Hier beginnt der ganze Spaß, Entrypoint
// React wird hier beim Rendern und Applikation erstellen genutzt
fun main() {
    // JS Code Operation, um Html Element im DOM (Html Baum) zu finden
    val container = web.dom.document.getElementById("root") ?: error("Container konnte nicht gefunden werden.")
    createRoot(container).render(App2.create())
}