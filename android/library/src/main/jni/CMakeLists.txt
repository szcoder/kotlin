cmake_minimum_required(VERSION 3.10)

project("demo")

add_library(
        ${PROJECT_NAME}
        SHARED
        demo.cpp
)

target_include_directories(
        ${PROJECT_NAME}
        PRIVATE
        "${CMAKE_CURRENT_SOURCE_DIR}/kmmLibs/${ANDROID_ABI}"
)

target_link_libraries(
        ${PROJECT_NAME}
        log
        android
        "${CMAKE_CURRENT_SOURCE_DIR}/kmmLibs/${ANDROID_ABI}/libkmm.a"
)