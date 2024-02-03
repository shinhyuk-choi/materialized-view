group = "com.example.stock"
version = "0.0.1"

dependencies {
    implementation(project(":common"))
}


docker {
    name = rootProject.name + '-' + project.name + ":" + version
    setDockerfile(file("../Dockerfile"))
    files(tasks.named("bootJar").get().outputs.files)
    buildArgs(mapOf("JAR_FILE" to tasks.named("bootJar").get().outputs.files.singleFile.name))
}
