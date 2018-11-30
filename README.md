# MoreFood 2

Bringing the next logical step from vanilla food. More food-items and a new crop on top.

## How to use MoreFood 2 in your project?

``` groovy
// build.gradle

repositories {
    maven {
        url = "https://dl.bintray.com/stefanwimmer128/maven"
    }
}

dependencies {
    deobfCompile "eu.stefanwimmer128.morefood2:morefood2:${version}"
    // or
    compile "eu.stefanwimmer128.morefood2:morefood2:${version}:deobf"
    // or
    compile "eu.stefanwimmer128.morefood2:morefood2:${version}:api"
} 
```
