# Elevators

<table>
	<thead>
		<tr>
			<th align="center"><a href="README.md">English</a></th>
			<th align="center">日本語</th>
		</tr>
	</thead>
</table>

ブロックを縦に並べるだけで使える、シンプルで軽量な Paper 向けエレベータープラグインです。

デフォルトでは鉄ブロックを使用します。
エレベーターブロックの上でジャンプすると上階へ、スニークすると下階へ瞬時に移動できます。

## ダウンロード

最新版は [Releases](https://github.com/ryusei-sky/Elevators/releases/latest) からダウンロードできます。

## 特徴

- エレベーターとして使用するブロックを自由に設定可能
- エレベーターの最大検出距離を設定可能（`0` で無制限）
- テレポート時のサウンド（種類・音量・ピッチ）を設定可能
- 移動先が塞がれている場合はテレポートをキャンセル
- Paper 1.21.x に対応

## 導入方法

1. 最新版をダウンロードします。
2. JARファイルをサーバーの `plugins` フォルダへ配置します。
3. サーバーを起動または再起動します。
4. 必要に応じて `config.yml` を編集してください。

## コマンド

| コマンド | 説明 |
|----------|------|
| `/elevator reload` | 設定を再読み込みします |

## 権限

| 権限 | 説明 | デフォルト |
|------|------|-----------|
| `elevator.use` | エレベーターを使用する権限 | `true` |
| `elevator.reload` | 設定を再読み込みする権限 | `op` |

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

# エレベーターの最大検出距離
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

このプロジェクトは MIT License のもとで公開されています。
