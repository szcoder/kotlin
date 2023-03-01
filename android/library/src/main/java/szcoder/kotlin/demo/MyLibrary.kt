package szcoder.kotlin.demo

object MyLibrary {
    init {
        System.loadLibrary("demo")
    }

    external fun callMeToCrash()
}