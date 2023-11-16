import react.create
import react.dom.client.createRoot

// Hier beginnt der ganze Spaß, Entrypoint
// React wird hier beim Rendern und Applikation erstellen genutzt
fun main() {
    // JS Code Operation, um Html Element im DOM (Html Baum) zu finden
    val container = web.dom.document.getElementById("root") ?: error("Container konnte nicht gefunden werden.")
    createRoot(container).render(App3.create())
}