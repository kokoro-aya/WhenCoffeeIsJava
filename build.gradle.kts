plugins {
    id("java")
    id("maven-publish")
}

java {
    targetCompatibility = JavaVersion.VERSION_1_8
}

group = "moe.irony.java.when"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "moe.irony.java.when"
            artifactId = "when-coffee-is-java"
            version = "1.0-SNAPSHOT"

            from(components["java"])

            pom {
                name = "When the Coffee is Java"
                description = "A type-safe Java 8 library to simulate the Kotlin \"when\" construct"
                url = "https://github.com/kokoro-aya/WhenCoffeeIsJava"
                properties = mapOf(
                )
                licenses {
                    license {
                        name = "BSD-3-Clause license"
                        url = "https://github.com/kokoro-aya/WhenCoffeeIsJava/blob/main/LICENSE.md"
                    }
                }
                developers {
                    developer {
                        id = "kokoro-aya"
                        name = "irony"
                    }
                }
            }

        }
    }
}