package tutorials

import react.FC
import react.Props
import react.dom.events.ChangeEventHandler
import react.dom.events.FormEventHandler
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.input
import react.useState
import web.html.HTMLFormElement
import web.html.HTMLInputElement
import web.html.InputType

// Props erweitern mit eigenem InputProps
// onSubmit ist Funktion, welche String bekommt
external interface InputProps : Props {
    var onSubmit: (String) -> Unit
}

val inputComponent = FC<InputProps> { props ->
    // Variable und Funktion zum Setzen
    val (text, setText) = useState("")

    // Text submitten
    // Text State clearen, dann ???
    val submitHandler: FormEventHandler<HTMLFormElement> =
        {
            it.preventDefault()
            setText("")
            props.onSubmit(text)
        }

    // Text ändern
    val changeHandler: ChangeEventHandler<HTMLInputElement> = {
        setText(it.target.value)
    }

    // JSX Html "Form" Element
    form {
        onSubmit = submitHandler
        input {
            type = InputType.text
            onChange = changeHandler
            value = text
        }
    }
}