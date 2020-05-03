# MKD
MKD is a Kotlin-DSL markdown library based on GitHub's markdown specifications.

## Learn
```kotlin
val markdown: String = markdown {
  h1("MKD")
  + "MKD is a Kotlin-DSL markdown library based on GitHub's markdown specifications."
  
  h2("Learn")
  blockCode("kotlin", """
    ...
  """.trimIndent())
  
  h3("This isn't actually included!")
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

### This isn't actually included!

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
