project(Controleur)
cmake_minimum_required(VERSION 2.8)


# Tell CMake to run moc when necessary:
set(CMAKE_AUTOMOC ON)
# As moc files are generated in the binary dir, tell CMake
# to always look for includes there:
set(CMAKE_INCLUDE_CURRENT_DIR ON)
set( CMAKE_EXE_LINKER_FLAGS  "${CMAKE_EXE_LINKER_FLAGS} -lcgraph" )

add_definitions(-std=c99 -g -Wall -lcgraph)

  
file(GLOB_RECURSE source_files src/*.c)
file(GLOB_RECURSE header_files src/*.h)

add_executable(${PROJECT_NAME} ${source_files} ${header_files})

