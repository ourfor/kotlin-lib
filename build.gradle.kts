import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.jvm.tasks.Jar

group = "top.ourfor"
version = "0.0.5"

plugins {
	java
    signing
	`maven-publish`
	id("org.jetbrains.dokka") version "0.9.17"
	kotlin("jvm") version "1.3.70"
	kotlin("plugin.allopen") version "1.3.70"
}

buildscript {
    var kotlinVersion = "1.3.71"
	repositories {
		mavenCentral()
	}
	dependencies {
	}
}

apply {
	plugin("java")
	plugin("idea")
	plugin("signing")
}

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	implementation(kotlin("reflect"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.3")
	implementation("com.squareup.okhttp3:okhttp:4.4.1")
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

val javadocJar by tasks.registering(Jar::class) {
	archiveClassifier.set("javadoc")
	from(tasks.javadoc)
}

val sourcesJar by tasks.registering(Jar::class) {
	archiveClassifier.set("sources")
	from(sourceSets["main"].allSource)
}



val sonatypeRepository = publishing.repositories.maven {
	name = "sonatype"
	url = if (isSnapshot) {
		uri("https://oss.sonatype.org/content/repositories/snapshots/")
	} else {
		uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
	}
	credentials {
		username = project.findProperty("ossrhUsername") as? String
		password = project.findProperty("ossrhPassword") as? String
	}
}


publishing {
	publications {
		create<MavenPublication>("default") {
			from(components["java"])
			artifact(javadocJar.get())
			artifact(sourcesJar.get())
			groupId = "top.ourfor"
			artifactId = "lib"
			pom {
				name.set(provider { "$groupId:$artifactId" })
				description.set(provider { project.description ?: name.get() })

				url.set("https://github.com/ourfor/kotlin-lib")
				developers {
					developer {
						name.set("catalina")
						email.set("ourfor@qq.com")
					}
				}
				scm {
					connection.set("https://github.com/ourfor/kotlin-lib.git")
					developerConnection.set("scm:git:ssh://github.com:ourfor/kotlin-lib.git")
					url.set("https://github.com/ourfor/kotlin-lib.git")
				}
				licenses {
					license {
						name.set("The Apache License, Version 2.0")
						url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
					}
				}
			}
		}
	}
	
	repositories {
		maven {
			name = "sonatype"
			url = if (isSnapshot) {
				uri("https://oss.sonatype.org/content/repositories/snapshots/")
			} else {
				uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
			}
			credentials {
				username = project.findProperty("ossrhUsername") as? String
				password = project.findProperty("ossrhPassword") as? String
			}
		}
	}
	
}

signing {
	useGpgCmd()
	isRequired = !isSnapshot
	sign(publishing.publications)
}

inline val Project.isSnapshot
	get() = version.toString().endsWith("-SNAPSHOT")



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


