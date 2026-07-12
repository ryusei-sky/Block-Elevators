# Elevators

<table>
	<thead>
    	<tr>
      		<th style="text-align:center">English</th>
      		<th style="text-align:center"><a href="README_ja.md">日本語</a></th>
    	</tr>
  	</thead>
</table>

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

# Worlds where elevators are enabled
# Empty list means all worlds
worlds: []
#  - world

# Cooldown between elevator uses (seconds)
# 0 disables cooldown
cooldown: 0

# Materials to be used as elevator blocks
materials:
  - IRON_BLOCK

# Maximum search range
# 0 means unlimited
max-search-range: 0

sound:
  enable: true
  name: entity.iron_golem.attack
  volume: 1.0
  jump-pitch: 1.2
  sneak-pitch: 0.8
  ```
