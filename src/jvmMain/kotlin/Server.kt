@file:Suppress("ExtractKtorModule")

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.toList


// MONGODB
val uri = "mongodb+srv://AshkanHaghighiFashi:Ashkanazarali23@cluster0.03ppuse.mongodb.net/?retryWrites=true&w=majority"
val mongoClient = MongoClient.create(uri)
val database = mongoClient.getDatabase("shoppingList")
val collection = database.getCollection<ShoppingListItem>("shoppingList")

fun main() {
    // Den Backend HTTP Server erstellen
    // Embedded web server ist wie eine kleine lokale Website
    // Netty ist populäres Kotlin Framework für Networking
    embeddedServer(
        Netty,
        watchPaths = listOf("classes", "resources"),
        port = 8080
    ) {
        serverSettings()
        configureRouting()
        // Am Ende wird der Server gestartet
    }.start(wait = true)
}

private fun Application.configureRouting() {
    // Eine Route erstellen, welche beim Zugriff auf den PATH Code ausführt
    // respondText ist eine simple Textantwort
    // einzelne Fälle für get, post und delete
    routing {
        // statische Ressourcen, welche nicht generiert werden (Bilder, Html, Css usw.)
        // Alles was unter der URL / requested wird, ist unter static zu finden
        staticResources("/", "static")

        // TODO neue routes

        route("/about") {
            get {
                call.respondText("Wechsel")
            }
        }

        route(ShoppingListItem.path) {
            get { call.respond(collection.find().toList()) }
            post {
                collection.insertOne(call.receive<ShoppingListItem>())

                call.respond(HttpStatusCode.OK)
            }
            delete("/{id}") {
                val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
                //println("TEST ${ShoppingListItem::id.}, $id")
                val filter = Filters.eq(ShoppingListItem::id.name, id)
                collection.deleteOne(filter)

                call.respond(HttpStatusCode.OK)
            }
        }
    }
}

private fun Application.serverSettings() {
    // Features zum Server hinzufügen
    // Automatische JSON als gemeinsames Format wählen
    install(ContentNegotiation) {
        json()
    }

    // Zugriff einschränken
    install(CORS) {
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Delete)
        anyHost()
    }

    // Output zippen für kleinere Daten
    install(Compression) {
        gzip()
    }
}