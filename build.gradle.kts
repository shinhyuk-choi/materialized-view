import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
    id ("com.palantir.docker") version "0.35.0" apply false
	kotlin("jvm") version "1.9.21"
	kotlin("plugin.spring") version "1.9.21"
    kotlin("plugin.jpa") version "1.9.21"

    kotlin("plugin.noarg") version "1.9.22"

    kotlin("kapt") version "1.8.22"
}
allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    tasks.withType<JavaCompile>{
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    repositories {
        mavenCentral()
    }
}



subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "com.palantir.docker")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlin.plugin.noarg")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
        testImplementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.3.0")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("com.fasterxml.uuid:java-uuid-generator:4.3.0")
        implementation("org.springframework.kafka:spring-kafka")
        implementation("com.google.code.gson:gson:2.10.1")
        implementation("com.vladmihalcea:hibernate-types-60:2.21.1")

        runtimeOnly("com.mysql:mysql-connector-j")
        runtimeOnly("com.h2database:h2")
        testImplementation("com.h2database:h2")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}
