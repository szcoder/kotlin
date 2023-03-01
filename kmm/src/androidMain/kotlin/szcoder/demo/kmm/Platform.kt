package szcoder.demo.kmm

class AndroidPlatform : Platform {
    override val name: String = "Android Native"
}

actual fun getPlatform(): Platform {
    return AndroidPlatform()
}