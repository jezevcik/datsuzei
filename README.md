# Better MCP for 1.8.9
Better MCP is an updated version of Minecraft Coder Pack based on the [MavenMCP project by Marcelektro](https://github.com/Marcelektro/MavenMCP-1.8.9) with OptiFine HD U L5 and the latest version of LWJGL3 added. Better MCP fully supports both Windows and Linux and includes natives to (after some tweaks) be able to run on the ARM platform.

### Structure
The code is split into two groups: resources (assets, graphics, shaders, etc.) and code. <br>
Libraries are loaded from Maven; you can add additional dependencies by specifying them in pom.xml, and you can update the LWJGL version by changing the lwjgl.version property.

### Setting up workspace
Better MCP runs natively on IntelliJ, so there's nothing easier than setting it up on it:

1. Clone the repository.
2. Let it set up and index (just wait).
4. Specify the project SDK to **Java 8 or newer** It will not work with older versions of Java.
5. Once it indexes, the project should be ready to go! :)

If you are using a different IDE, follow its guides on how to import a Maven project.

### Building
To build a working version, you just need to run the `mvn clean package` command.
<br>You can also use the Maven menu *on the right side*, or add a new run configuration and run it from there (the simplest way). <br>
<br>Once the process is complete, artifacts will be in the `/target` directory, after which you can put the file in your `/versions` directory.
<br>There's no requirement to delete MANIFEST from the jar before putting it in the `/versions` directory.

### Running
To launch the client in the IDE, you need to execute Start.java **and specify the working directory as `./test_run/`**, If you fail to do that, your IDE will default the run directory to the project root and cause a mess, along with the game not being able to launch. <br>

An example run configuration:
<img src="https://developers.marcloud.net/i/launchConfig.png"/>

Minecraft's directory will be `./test_run/`. All saves, resource packs, etc. will be there.
This folder is by default included in `.gitignore` and therefore won't be put in your git repository after its contents change; however, it is recommended to put the default contents of the directory onto your repository so other users of your repository will be able to clone it and have the necessary assest index included.

### Migrating from old version of MCP
Move your existing java code to `/src/main/java`, and any resources i.e. shaders, fonts etc. to `/src/main/resources`.<br>

If your old code was based on LWJGL2, you may need to change functions to match the LWJGLX compatibility layer.
For most classes, you simply need to change the import from org.lwjgl to org.lwjglx; however, some classes are not included, and methods inside of them won't match.
If that is the case, most of the time, these are going to `getFloat`, `glUniform`, or `glUniformMatrix` functions; in that case, you need to append 'f' or 'fv' to the end of the method name, depending on the arguments Minecraft is calling the method with.
If the method parameters contain both floats and integers, it's 'fv'; if it does not, it's 'v'.
If this solution does not work, you may need to look it up in the LWJGL documentation and find its modern counterpart.

If you added new libraries, make sure to add them to pom.xml, and you're set :D

### Support
If you have any questions about this repository or need help with how to use it, you may be given help at my [my Discord server](https://dsc.gg/alexanderr).

<br>

**May 1.8.9 survive!**
