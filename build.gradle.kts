import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.jvm.tasks.Jar

group = "top.ourfor"
version = "0.0.1"

plugins {
	java
	`build-scan`
	`maven-publish`
	id("org.jetbrains.dokka") version "0.9.17"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.allopen") version "1.3.50"
}

buildscript {
    var kotlinVersion = "1.3.50"
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:2.1.6.RELEASE")
	}
}

apply {
	plugin("java")
	plugin("idea")
}

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	implementation(kotlin("reflect"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
	testImplementation("junit:junit:4.12")

}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}



val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val dokkaJar by tasks.creating(Jar::class) {
	group = JavaBasePlugin.DOCUMENTATION_GROUP
	description = "Assembles Kotlin docs with Dokka"
	classifier = "javadoc"
	from(tasks.dokka)
}

buildScan {
	termsOfServiceUrl = "https://gradle.com/terms-of-service"
	termsOfServiceAgree = "yes"
	publishAlways()
}

publishing {
	publications {
		create<MavenPublication>("default") {
			from(components["java"])
			artifact(dokkaJar)
		}
	}
	repositories {
		mavenCentral()
//		maven {
//			url = uri("build/repository")
//		}
	}
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.dokka {
	outputFormat = "html"
	outputDirectory = "$buildDir/javadoc"
}




