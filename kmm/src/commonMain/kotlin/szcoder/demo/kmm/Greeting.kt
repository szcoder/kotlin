package szcoder.demo.kmm

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    fun callMeToCrash() {
        print("callMeToCrash")
        stack1()
    }

    private fun stack1() {
        print("stack1")
        stack2()
    }

    private fun stack2() {
        print("stack2")
        stack3()
    }

    private fun stack3() {
        print("stack3")
        crash()
    }

    private fun crash() {
        print("crash")
        throw Exception("dead")
    }
}