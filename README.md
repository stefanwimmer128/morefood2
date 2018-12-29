# MoreFood 2

Bringing the next logical step from vanilla food. More food-items and a new crop on top.

## How to use MoreFood 2 in your project?

To use MoreFood 2 in your project add the following code to your `build.gradle`:

``` groovy
repositories {
    maven {
        url = "https://dl.bintray.com/stefanwimmer128/maven"
    }
}

dependencies {
    // compile against MoreFood 2 API
    provided "eu.stefanwimmer128.morefood2:morefood2-api:${version}"
    // at runtime include full MoreFood 2 mod
    runtime "eu.stefanwimmer128.morefood2:morefood2:${version}"
} 
```
