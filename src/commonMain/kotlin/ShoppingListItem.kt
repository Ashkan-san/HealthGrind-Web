import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
//import org.bson.codecs.pojo.annotations

// Serializable Annotation sagt dass Klasse in z.B. JSON serialisiert werden kann
// BsonId geht hier nicht, stattdessen SerialName für Id und Context
@Serializable
data class ShoppingListItem(
    //@BsonId
    @SerialName("_id")
    val id: Int?,
    val description: String,
    val priority: Int
) {
    // Weitere Infos über das Objekt speichern, hier der Zugriffspfad
    companion object {
        const val path = "/shoppingList"
    }
}