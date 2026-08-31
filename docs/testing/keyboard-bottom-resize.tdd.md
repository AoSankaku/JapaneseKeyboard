# Keyboard bottom resize TDD evidence

## Expected behavior

- Dragging the bottom resize handle changes the bottom edge while keeping the top edge fixed.
- The behavior is shared by portrait and landscape keyboard-size settings.
- Height and bottom-margin limits do not make the top edge jump.

## Red

Command:

```text
.\gradlew.bat :app:testLiteStandardDebugUnitTest --tests "com.kazumaproject.markdownhelperkeyboard.setting_activity.ui.keyboard_size_setting.KeyboardVerticalResizeTest"
```

Result: failed during test compilation because `KeyboardVerticalResize` did not exist.

## Green

The same command passed after adding the resize calculation and applying it to both settings fragments.

Additional verification:

```text
.\gradlew.bat :app:assembleLiteStandardDebug
.\gradlew.bat :app:lintLiteStandardDebug
```

Both commands completed successfully.
