# Scala Practice (Public Version) - Archived
This is the public version of our Practice MI. Our private version is more up to date by over 1k commits and is also utilizing Kubernetes now.

A highly scalable multi-instance Practice core developed by **Scala Studios**, a software division of **LunarLabs LLC**.

Originally built for internal competitive Minecraft networks such as:

- `potpvp.com`
- `solara.gg`
- `duels.us`
- `tropic.gg`
- `pvp.bar`

**This repository is publicly viewable for transparency, educational reference, and portfolio showcase purposes.**

---

## Overview

Scala Practice is a distributed Practice platform designed for larger networks requiring multiple servers, shared services, and regional scalability.

Core capabilities include:

- Multi-instance game server support
- Shared matchmaking queues
- Global leaderboards
- Centralized backend services
- Fast arena replication systems
- Multi-region deployment support
- Competitive PvP focused architecture

---

## Infrastructure

### Components

- **Game Servers** — Hosts active matches across multiple instances.
- **Lobby Server** *(optional)* — Frontend access point for players.
- **Standalone Application Layer** — Handles shared services across all servers.

### Shared Services

- **Queue System**  
  Matchmaking by kit, team size, restrictions, and queue state.

- **Leaderboards**  
  Cached global rankings and player statistics.

- **Game Manager**  
  Tracks ongoing matches across all instances.

- **Replication Manager**  
  Tracks available map replications and server health.

### Arena Replication

Maps are generated from templates and replicated for rapid match creation.

Supports:

- Fast startup times
- High concurrency
- Efficient server usage
- On-demand generation

---

## Documentation

- [Infrastructure Overview](docs/infrastructure.md)
- [Setup](docs/setup.md)

---

## Public Repository Notice

This repository is made publicly available for:

- Viewing source code quality
- Educational learning
- Architectural reference
- Portfolio demonstration

This repository is **not** intended as a turnkey public product.

It may rely on:

- Private internal dependencies
- Proprietary tooling
- Custom platform modifications
- Internal infrastructure components not included here

No support, setup assistance, maintenance, or guarantees are provided.

Please review the `LICENSE` file for full terms of use.

---

## Ownership

All source code, assets, systems, and related intellectual property in this repository remain the sole and exclusive property of **LunarLabs LLC**.

Scala Studios operates as the software engineering division of LunarLabs LLC.

---

## Contributors

- [GrowlyX](https://github.com/growlyx)
- [DripW](https://github.com/dripw)
- [Elb1to](https://github.com/elb1to)

---

![yklogo](https://github.com/scalagg/scpracticemi/assets/62861393/45cbbfbd-f936-4347-8516-61ca24a0d1f4)

YourKit supports software projects with intelligent tools for monitoring and profiling Java and .NET applications.

Creators of:

- YourKit Java Profiler
- YourKit .NET Profiler
- YourKit YouMonitor
