import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.browser.window
import kotlinx.coroutines.async
import kotlinx.coroutines.await
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.Json

// Hier Methoden, welche bei gewissen HTTP Requests aufgerufen werden sollen

// HTTP Client
val jsonClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

// Die Methode ruft HTTP GET auf
suspend fun getShoppingList(): List<ShoppingListItem> {
    return jsonClient.get(ShoppingListItem.path).body()
}

// HTTP POST
suspend fun addShoppingListItem(shoppingListItem: ShoppingListItem) {
    jsonClient.post(ShoppingListItem.path) {
        contentType(ContentType.Application.Json)
        setBody(shoppingListItem)
    }
}

// HTTP DELETE
suspend fun deleteShoppingListItem(shoppingListItem: ShoppingListItem) {
    jsonClient.delete(ShoppingListItem.path + "/${shoppingListItem.id}")
}

// APP2 METHODEN
// 25 Videos fetchen
suspend fun fetchVideos(): List<Video> = coroutineScope {
    (1..25).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}

suspend fun fetchVideo(id: Int): Video {
    // Video aus Api holen, body lesen, in Video Objekt umwandeln
    val response = window
        .fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        .await()
        .text()
        .await()
    return Json.decodeFromString(response)
}
