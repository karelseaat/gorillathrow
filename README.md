# gorillathrow

A top-down physics-based throwing game written in Java. Players control a gorilla character and throw objects (bananas, logs, rocks) at targets with realistic trajectory simulation.

## Features

- Physics simulation using simple Euler integration (gravity, air resistance, restitution)
- Real-time rendering with custom lightweight 2D engine (no external graphics libraries)
- Interactive aim-and-release mechanics with trajectory preview
- Multiple target types (breakable crates, moving platforms, moving enemies)
- Progressive difficulty levels with increasing environmental complexity
- Keyboard and mouse controls: aim with mouse, click-and-drag to set throw power/direction
- Save/load high scores to local JSON file

## Getting Started

### Build and Run from Source

```bash
# Clone repository
git clone https://github.com/example/gorillathrow.git
cd gorillathrow

# Compile (requires Java 17+)
javac -d out src/main/java/**/*.java

# Run
java -cp out Main
```

### Run via JAR

```bash
java -jar build/gorillathrow.jar
```

*(JAR must be built first using Gradle/Maven or equivalent; default build output location is `build/`)*

## Requirements

- Java 17 or later (`java --version` to verify)
- No external runtime dependencies
- 800×600 minimum screen resolution (game window is fixed size)
- Mouse and keyboard required for input

## More from Karelseaat

For more projects and experiments, visit my GitHub Pages site: [karelseaat.github.io](https://karelseaat.github.io/)

<!-- KEEP-EXPLORING-START -->
## Keep Exploring

If you made it to the bottom, jump straight into a few related repos:

- [Nodeventure](https://github.com/karelseaat/nodeventure)
- [Pygamepad](https://github.com/karelseaat/pygamepad)
- [Socialpad](https://github.com/karelseaat/socialpad)

- [Full project index](https://karelseaat.github.io/)
<!-- KEEP-EXPLORING-END -->
