@file:JsModule("react-player")
@file:JsNonModule

package tutorials

import react.ComponentClass
import react.Props

@JsName("default")
external val ReactPlayer: ComponentClass<ReactPlayerProps>

external interface ReactPlayerProps : Props {
    var url: String
    var controls: Boolean
}