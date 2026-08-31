# Settings management Up navigation TDD evidence

## Expected behavior

- User Dictionary, Learn Dictionary, User Template, Text Macro, and Custom Keyboard retain the shared ActionBar Up button when opened from Settings.
- Only the two Settings home destinations are top-level ActionBar destinations.
- Fragments do not override NavigationUI by disabling the shared Up indicator.

## Red

Command:

```text
.\gradlew.bat :app:testLiteStandardDebugUnitTest --tests "com.kazumaproject.markdownhelperkeyboard.setting_activity.SharedActionBarNavigationContractTest"
```

Result: 7 tests ran and 2 failed. The contract found User Dictionary and Learn Dictionary in the top-level AppBar configuration, and found User Template disabling the shared Up indicator.

## Green

The same command passed all 7 tests after the three conflicting navigation declarations were removed.

Additional verification:

```text
.\gradlew.bat :app:assembleLiteStandardDebug
.\gradlew.bat :app:testLiteStandardDebugUnitTest
.\gradlew.bat :app:lintLiteStandardDebug
```

The build and Android Lint passed. The full unit-test task ran 1,381 tests; 1,375 passed, 4 were skipped, and 2 pre-existing Room migration tests failed because SQLite could not open their databases on Windows. The focused ActionBar contract remained green.

## Coverage and known gaps

This project does not expose a Kover coverage task. The regression is covered by source-contract tests across every Settings management destination and every Kotlin fragment that could disable the shared Up indicator.
