/*
 * Copyright (c) 2019-2025 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

import gradle.kotlin.dsl.accessors._3f80d1312ff9f42b2eab22e9378c2e48.*
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.expand
import org.gradle.kotlin.dsl.withType
import org.gradle.language.jvm.tasks.ProcessResources

/*
 * Copyright (c) 2019-2025 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

fun Project.minecraftVersion(version: String): Map<String, String> {
    return this.minecraftVersion(version, HashMap())
}

fun Project.minecraftVersion(version: String, bonusData: Map<String, String>): Map<String, String> {
    val metaProperties = HashMap<String, String>()
    metaProperties["id"] = "geyser_modded_adapters_${version.replace('.', '_')}"
    metaProperties["version"] = version

    val fabricLoaderVersion = "0.17.2"

    dependencies {
        minecraft("com.mojang:minecraft:$version")
        mappings(loom.officialMojangMappings())
        modImplementation("net.fabricmc:fabric-loader:$fabricLoaderVersion")
    }

    architectury {
        minecraft = version
        platformSetupLoomIde()
    }

    tasks {
        withType<ProcessResources> {
            filesMatching(listOf("fabric.mod.json", "neoforge.mods.toml")) {
                expand(
                    "id" to metaProperties["id"],
                    "minecraft-version" to metaProperties["version"],
                    "version" to version,
                    "name" to "Geyser Mod Adapter",
                    "description" to "An adapter to access version-specific data in modded versions of Minecraft.",
                    "author" to "GeyserMC",
                    "website" to "https://geysermc.org",
                    "license" to "MIT"
                )

                bonusData.forEach { entry ->
                    expand(entry.key, entry.value)
                }
            }
        }
    }

    return metaProperties
}
