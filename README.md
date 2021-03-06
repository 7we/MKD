# MKD
MKD is a Kotlin-DSL markdown library based on GitHub's markdown specifications.

## Maven
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

```xml
<dependency>
  <groupId>com.github.7we</groupId>
  <artifactId>MKD</artifactId>
  <version>1.0.1</version>
</dependency>
```

## Gradle
```
maven {
  url 'https://jitpack.io'
}
```

```
implementation 'com.github.7we:MKD:1.0.1'
```

## Learn
```kotlin
val markdown: String = markdown {
  h1("MKD")
  + "MKD is a Kotlin-DSL markdown library based on GitHub's markdown specifications."
  
  h2("Learn")
  blockCode("kotlin", """
    ...
  """.trimIndent())
  
  h3("I'm a smaller heading!")
  + "${b("This text is bold")} and ${i("This text is in italics")}."
  
  + code("This is one line code block")
  
  + st("This text has a strike through it")
  
  link("https://google.com", "Take me to Google")
  
  table("Name", "Age", "City") {
    row("Micky", "65", "New York")
    row("Jan", "35", "Chicago")
  }

  ulist {
    + "This is an unordered list."
    + "It has no order."
  }
  
  olist {
    + "This is an ordered list."
    + "Hi! I'm labelled as entry 2."
  }
  
  tlist {
    + "I'm a task list."
    + "I'm an incomplete entry."
    c("I'm also an incomplete entry.", false)
    c("I'm a complete entry!")
  }
}.toMarkdown()
```

This outputs a string with some beautiful formatted markdown!

# MKD

MKD is a Kotlin-DSL markdown library based on GitHub's markdown specifications.

## Learn

```kotlin
...
```

### I'm a smaller heading!

**This text is bold** and *This text is in italics*.

`This is one line code block`

~~This text has a strike through it~~

[Take me to Google](https://google.com)

Name | Age | City
---- | --- | ----
Micky | 65 | New York
Jan | 35 | Chicago

- This is an unordered list.
- It has no order.

1. This is an ordered list.
1. Hi! I'm labelled as entry 2.

- [ ] I'm a task list.
- [ ] I'm an incomplete entry.
- [ ] I'm also an incomplete entry.
- [x] I'm a complete entry!
