# When the coffee is Java

[![](https://img.shields.io/maven-central/v/moe.irony.java.when/when-coffee-is-java
)](https://central.sonatype.com/artifact/moe.irony.java.when/when-coffee-is-java)

A simple Java-8 pattern matching library to give Java language the similar
feature of `when` expressions/statements of Kotlin.

Due to limitation of Java-8 syntax and language extensibility, this project
is not aimed to bring a "pattern matching" mechanism, which Kotlin doesn't 
support at the moment as well.

Example code:

```java
interface Coffee {}

class Java implements Coffee {}
class Cappuccino implements Coffee {}
class Latte implements Coffee {}
// Other eventually coffee classes

Coffee someCoffee = new Java();

String greeting = new Match<Coffee, String>(someCoffee)
        .is(Java.class, (Java java) -> "Today's coffee is Java")
        .is(new Cappuccino(), (Cappuccino cappuccino) -> "Tomorrow's coffee will be Cappuccino")
        // other arms
        .otherwise((Coffee coffee) -> {
          System.out.println("You have found a secret recipe!");
        })
        .get();

System.out.println(greeting); // greeting will be "Today's coffee is Java"
```

Notice that due to rigidity of Java typing system and type annotation, you
will need to explicitly write down type arguments for `Match` constructor.

Sometimes, you may don't need to write down types if you are using `When`
variant (i.e. the statement that won't return anything).

But you should be familiar to it since you need to explicitly annotate 
types a bit everywhere in Java 8.