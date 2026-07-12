# Elevators

<table>
	<thead>
		<tr>
			<th style="text-align:center"><a href="README.md">English</a></th>
			<th style="text-align:center">日本語</th>
		</tr>
	</thead>
</table>

ブロックを縦に並べるだけで使える、シンプルなエレベータープラグインです。

デフォルトでは鉄ブロックを使用します。
エレベーターブロックの上でジャンプすると上階へ、スニークすると下階へ瞬時に移動できます。

## 特徴

- エレベーターとして使用するブロックを自由に変更可能
- エレベーターの探索距離を設定可能（0で無制限）
- テレポート時のサウンド（種類・音量・ピッチ）を変更可能
- 移動先が安全でない場合はテレポートをキャンセル (そりゃそう)
- Paper 1.21.x に対応

## 権限

| 権限 | 説明 | デフォルト |
|------------|-------------|---------|
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
  name: entity_iron_golem_attack
  volume: 1.0
  jump-pitch: 1.2
  sneak-pitch: 0.8
```

コメントが英語で読みづらくてすいません (日本語にするのめんどい)
