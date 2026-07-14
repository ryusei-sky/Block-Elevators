# Block Elevators

<table>
	<thead>
		<tr>
			<th align="center">English</th>
			<th align="center"><a href="README_ja.md">日本語</a></th>
		</tr>
	</thead>
</table>

A Paper plugin that lets you create elevators by stacking blocks vertically.

Stand on an elevator block, then jump to go up or sneak to go down.

## Demo

<a href="https://youtu.be/RzJluRqwEJk">
  <img src="https://img.youtube.com/vi/RzJluRqwEJk/maxresdefault.jpg" alt="Block Elevators Demo" width="500">
</a>

## Download

- [GitHub Releases](https://github.com/ryusei-sky/Block-Elevators/releases/latest)
- [Modrinth](https://modrinth.com/plugin/block-elevators)

## Features

- Configurable elevator blocks
- Customizable elevator behavior
- Create hidden elevators using liquids, carpets, slabs, and other blocks
- Supports Paper 1.21.x

## Installation

1. Place the JAR file into your server's `plugins` folder.
2. Start or restart your server.
3. Edit `config.yml` to customize the plugin.

## Commands

| Command | Description |
|----------|-------------|
| `/elevator reload` | Reload the configuration |

## Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `elevator.use` | Allows players to use elevators | `true` |
| `elevator.reload` | Allows reloading the configuration | `op` |

## config.yml

```yml
# Permissions:
# elevator.use - Permission to use elevators (enabled for all players by default)
# elevator.reload - Permission to reload the configuration (enabled for OPs by default)

# Worlds where elevators are enabled
# Leave empty to enable elevators in all worlds
worlds: []
#  - world

# Cooldown between elevator uses (seconds)
# Set to 0 to disable
cooldown: 0

# Blocks that can be used as elevators
materials:
  - IRON_BLOCK

# Maximum distance to search for elevators
# Set to 0 for unlimited distance
max-search-range: 0

# Sound settings
sound:
  enable: true
  name: entity.iron_golem.attack
  volume: 1.0
  jump-pitch: 1.2
  sneak-pitch: 0.8
```

## License

[MIT License](https://files.ryusei.space/docs/mit.txt)
