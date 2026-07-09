package pe.edu.galaxy.training.java.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.jvm.toolchain.JavaLanguageVersion;

/**
 * Convention plugin de Gradle para proyectos basados en el meta-framework
 * Galaxy Training con Spring Boot.
 * <p>
 * Al aplicar este plugin, el proyecto se configura automáticamente con:
 * Java 25, Spring Boot 4.0.5, repositorios, y el starter del meta-framework
 * como dependencia.
 * </p>
 *
 * @author Galaxy Training
 * @version 1.0.0
 */
public class GalaxyTrainingSpringBootPlugin implements Plugin<Project> {

    /** Versión de Java requerida por el framework. */
    private static final int JAVA_VERSION = 25;

    /** Versión del meta-framework starter. */
    private static final String STARTER_VERSION = "1.0.0";

    /** Coordenadas del meta-framework starter. */
    private static final String STARTER_DEPENDENCY = "pe.edu.galaxy.training.java.starters:galaxy-training-spring-boot-starter:" + STARTER_VERSION;

    /** Constructor por defecto. */
    public GalaxyTrainingSpringBootPlugin() {}

    @Override
    public void apply(Project project) {
        // 1. Aplicar plugins
        project.getPluginManager().apply("java");
        project.getPluginManager().apply("org.springframework.boot");
        // 2. Configurar Java toolchain
        project.getExtensions().configure(JavaPluginExtension.class, java ->
                java.toolchain(toolchain ->
                        toolchain.getLanguageVersion().set(JavaLanguageVersion.of(JAVA_VERSION))
                )
        );
        // 3. Configurar repositorios
        project.getRepositories().mavenLocal();
        project.getRepositories().mavenCentral();
        // 4. Agregar dependencia al meta-framework starter
        project.getDependencies().add("implementation", STARTER_DEPENDENCY);
        // 5. Configurar tests con JUnit Platform
        project.getTasks().withType(org.gradle.api.tasks.testing.Test.class, test -> test.useJUnitPlatform());
    }
}
