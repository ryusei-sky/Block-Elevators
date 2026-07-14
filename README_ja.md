# Block Elevators

<table>
	<thead>
		<tr>
			<th align="center"><a href="README.md">English</a></th>
			<th align="center">日本語</th>
		</tr>
	</thead>
</table>

ブロックを縦に並べるだけで使える、Paper 向けエレベータープラグインです。

エレベーターブロック（デフォルトは鉄ブロック）の上で、ジャンプすると上へ、スニークすると下へ移動できます。

## デモ

<p align="center">
  <a href="https://youtu.be/RzJluRqwEJk">
    <img src="https://img.youtube.com/vi/RzJluRqwEJk/maxresdefault.jpg" alt="Block Elevators Demo" width="800">
  </a>
</p>

## ダウンロード

- [GitHub Releases](https://github.com/ryusei-sky/Block-Elevators/releases/latest)
- [Modrinth](https://modrinth.com/plugin/block-elevators)

## 特徴

- エレベーターブロックを自由に設定可能
- 動作を細かくカスタマイズ可能
- 液体やカーペット、ハーフブロックなどを利用してカモフラージュ可能
- Paper 1.21.x に対応

## 導入方法

1. JARファイルをサーバーの `plugins` フォルダへ配置します。
2. サーバーを起動または再起動します。
3. 必要に応じて `config.yml` を編集してください。

## コマンド

| コマンド | 説明 |
|----------|------|
| `/elevator reload` | 設定を再読み込みします |

## 権限

| 権限 | 説明 | デフォルト |
|------|------|-----------|
| `elevator.use` | エレベーターを使用 | `true` |
| `elevator.reload` | 設定を再読み込み | `op` |

## config.yml

```yml
# 権限:
# elevator.use - エレベーターを使う権限（デフォルトで全員に有効）
# elevator.reload - 設定をリロードする権限（デフォルトでOPのみに有効）

# エレベーターを使えるワールド
# リストが空の場合は全ワールドで有効
worlds: []
#  - world

# エレベーター使用時のクールダウン（秒）
# 0で無効
cooldown: 0

# エレベーターとして使うブロック
materials:
  - IRON_BLOCK

# エレベーターの最大検索距離
# 0で無制限
max-search-range: 0

# サウンド設定
sound:
  enable: true
  name: entity.iron_golem.attack
  volume: 1.0
  jump-pitch: 1.2
  sneak-pitch: 0.8
```

## ライセンス

[MIT License](https://files.ryusei.space/docs/mit.txt)
