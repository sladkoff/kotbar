[![Maven Central](https://img.shields.io/maven-central/v/de.sldk/kotbar.svg?style=flat-square)](https://mvnrepository.com/artifact/de.sldk/kotbar/latest)

<img src="https://raw.githubusercontent.com/sladkoff/kotbar/feature/docs/docs/kotbar.png" width="50px" height="50px" align="right">

# Kotbar

A crappy progress-bar for sysout written in Kotlin.

## Usage

Use the `forEachWithProgressBar` extension method on `Iterables`.

```kotlin
val collection = listOf(1, 2, 3, 4, 5)

collection.forEachWithProgressBar {
    Thread.sleep(500)
}
```

Declare a Kotbar manually with an initial size and call `.inc()` to progress the bar.

Call `.done()` when finished.

```kotlin
val kotbar = Kotbar(size = 10)

for (i in 1..10) {
    // do something
    Thread.sleep(500)
    
    kotbar.inc()
}

kotbar.done()
```

## Peview

![](https://raw.githubusercontent.com/sladkoff/kotbar/feature/docs/docs/kotbar_normal.gif)

![](https://raw.githubusercontent.com/sladkoff/kotbar/feature/docs/docs/kotbar_error.gif)