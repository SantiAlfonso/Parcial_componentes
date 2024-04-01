package Model

class Letras {

    var nombres = arrayOf<String>()
        // Setter para nombres
        set(value) {
            field = value
        }
        // Getter para nombres
        get() = field

    var letras = arrayOf<String>()
        // Setter para Letra
        set(value) {
            field = value
        }
        // Getter para Letra
        get() = field

    var entero: Int = 0
        // Setter para entero
        set(value) {
            field = value
        }
        // Getter para entero
        get() = field
}
