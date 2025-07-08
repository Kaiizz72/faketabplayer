# FakeTabPlayer (v1.21.5)

A lightweight Spigot plugin for Minecraft 1.21.5 that injects purely visual “fake” players into the in-game tab list. Perfect for UI demonstrations, stress-testing, or playful surprises, FakeTabPlayer uses NMS to manipulate the client’s tab overlay without spawning actual entities on the server.

---

## Table of Contents

1. [Features](#features)  
2. [Compatibility & Requirements](#compatibility--requirements)  
3. [Installation](#installation)  
4. [Usage](#usage)  
5. [Commands](#commands)  
6. [Configuration](#configuration)  
7. [Customization & Extension](#customization--extension)  
8. [Changelog (v1.21.5)](#changelog-v1215)  
9. [Contributing](#contributing)  
10. [License](#license)  

---

## Features

- **Dynamic Fake Players**  
  Add or remove arbitrary player names from every client’s tab UI—no real entities ever spawn.  
- **Zero Gameplay Impact**  
  Tab entries exist only in the client overlay; server mechanics, entity counts, and world state remain untouched.  
- **Operator-Only Control**  
  Commands restricted to server operators (OPs) safeguard against misuse.  
- **Rejoin Refresh**  
  Ensures a clean slate by requiring players to rejoin after removal, preventing tablist desync.  
- **Minimal Footprint**  
  No external dependencies; tiny JAR ~ 20 KB.  

---

## Compatibility & Requirements

- **Minecraft Version:** 1.21.5  
- **Spigot / Paper:** BuildTools-generated 1.21.5 (NMS access required)  
- **Java:** 17+  
- **Permissions:** OP (or equivalent `faketabplayer.*` permission)  

---

## Installation

1. **Build Spigot Server JAR**  
   ```bash
   # Using BuildTools.jar
   java -jar BuildTools.jar --rev 1.21.5
   
