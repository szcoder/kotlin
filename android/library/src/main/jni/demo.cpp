#include <jni.h>
#include "libkmm_api.h"

extern "C"
JNIEXPORT void JNICALL
Java_szcoder_kotlin_demo_MyLibrary_callMeToCrash(JNIEnv *env, jobject thiz) {
    auto lib = libkmm_symbols();
    auto greetings = lib->kotlin.root.szcoder.demo.kmm.Greeting.Greeting();
    lib->kotlin.root.szcoder.demo.kmm.Greeting.callMeToCrash(greetings);
}