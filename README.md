# Elevators

A simple block-based elevator plugin for Paper.

Place elevator blocks vertically and jump to move up, or sneak to move down instantly.

## Features

- Customizable elevator blocks
- Configurable maximum search range (0 for unlimited range)
- Custom teleport sounds (type, volume, and pitch)
- Prevents teleportation when the destination is blocked
- Supports Paper 1.21.x

## Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `elevator.use` | Allows players to use elevators | `true` |
| `elevator.reload` | Allows reloading the configuration | `op` |

## Configuration

```yml
# Permissions:
# elevator.use - Allows players to use elevators (Default: true)
# elevator.reload - Allows reloading the configuration (Default: op)

# Materials to be used as elevator blocks (e.g., IRON_BLOCK, GOLD_BLOCK)
materials:
  - IRON_BLOCK

# Maximum search range for elevators. Set to 0 for infinite range.
max-search-range: 64

# Sound Settings
sound:
  enable: true
  name: "ENTITY_ENDERMAN_TELEPORT"
  volume: 1.0
  jump-pitch: 1.2
  sneak-pitch: 0.8
