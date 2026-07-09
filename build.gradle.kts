plugins {
    `java-gradle-plugin`
    `maven-publish`
}

group = "pe.edu.nova.java"
version = findProperty("version") as String

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-gradle-plugin:4.0.5")
}

gradlePlugin {
    plugins {
        create("galaxyTrainingSpringBoot") {
            id = "pe.edu.nova.java.spring-boot"
            implementationClass = "pe.edu.nova.java.gradle.NovaSpringBootPlugin"
            displayName = "Galaxy Training Spring Boot Plugin"
            description = "Convention plugin que configura proyectos Spring Boot con el meta-framework Galaxy Training."
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ahincho/nova-java-spring-boot-gradle-plugin")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
