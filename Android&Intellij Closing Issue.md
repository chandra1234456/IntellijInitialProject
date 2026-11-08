# Fix: IntelliJ IDEA / Android Studio "still running and does not respond"

## Error

```
DirectoryLock$CannotActivateException: Process "...idea64.exe" (PID) is still running and does not respond.
Suppressed: java.net.BindException: Address already in use: bind
Suppressed: java.net.ConnectException: Connection refused: connect
```

The same error appears for Android Studio (`studio64.exe`). Both are JetBrains IDEs and use the same lock mechanism.

## Cause

An old IDE process is still running in the background, or it left stale lock files behind. A new instance can't take the lock on the config folder, so it won't start.

Common triggers:
- Clicking the IDE icon again while it is still loading (starts a second process)
- A crash or forced shutdown
- Antivirus blocking the IDE's local socket
- Windows Fast Startup keeping stale state after Shut down

## Quick fix

1. Close any IDE windows that respond. Unsaved changes in stuck ones are lost.
2. Run `fix-ide-lock.bat` (right-click, **Run as administrator** if you get "Access is denied").
3. Start **one** IDE and wait 1-2 minutes.

If it still fails, restart Windows using **Restart**, not Shut down.

## Manual commands (Command Prompt)

| Purpose | Command |
|---|---|
| Show running IDE processes | `tasklist \| findstr /I "idea64 studio64 fsnotifier"` |
| Kill IntelliJ | `taskkill /F /IM idea64.exe` |
| Kill Android Studio | `taskkill /F /IM studio64.exe` |
| Kill both plus file watcher | `taskkill /F /IM idea64.exe /IM studio64.exe /IM fsnotifier.exe` |
| Kill one process by PID (from the error message) | `taskkill /F /PID 14104` |
| Confirm nothing is left | `tasklist \| findstr /I "idea64 studio64"` |
| List JetBrains folders | `dir /B "%LOCALAPPDATA%\JetBrains"` |
| List Android Studio folders | `dir /B "%LOCALAPPDATA%\Google"` |
| Find lock files in IntelliJ folder | `dir /A "%LOCALAPPDATA%\JetBrains\IdeaIC2025.2" \| findstr /I "lock port"` |
| Find lock files in Android Studio folder | `dir /A "%LOCALAPPDATA%\Google\AndroidStudio2025.x" \| findstr /I "lock port"` |
| Find all lock/port files | `dir /S /B /A "%LOCALAPPDATA%\JetBrains\*.lock" "%LOCALAPPDATA%\JetBrains\*.port"` |
| Delete a lock file | `del /F /Q "full\path\to\file"` |

Notes:
- `%LOCALAPPDATA%` is `C:\Users\<you>\AppData\Local`.
- `/F` forces, `/IM` is by image name, `/T` also kills child processes, `/B` shows names only, `/A` includes hidden files, `/S` includes subfolders.
- Don't kill `java.exe` unless you accept stopping Gradle daemons and other Java apps.
- `Remove-Item` is PowerShell only. In Command Prompt use `del`.

## Prevent it from recurring

- Start the IDE **once** and wait 1-2 minutes before clicking again.
- Don't open the same project in IntelliJ and Android Studio at the same time.
- Exclude these from antivirus scanning:
    - the IDE install folders
    - `%LOCALAPPDATA%\JetBrains`
    - `%LOCALAPPDATA%\Google`
- Use **Restart** instead of Shut down, or disable Fast Startup (Control Panel, Power Options, Choose what the power buttons do).
- After the IDE opens, run **File, Invalidate Caches, Invalidate and Restart** once.

## Related error: Kotlin `proto.tab` corrupted

```
CorruptedException: PersistentEnumerator storage corrupted ...\compile-server\...\proto.tab
```

Fix: Build, Rebuild Project. If that fails, Invalidate Caches. If it still fails, close the IDE and delete the project's compile-server cache:

```
rmdir /S /Q "%LOCALAPPDATA%\JetBrains\IdeaIC2025.2\compile-server\<project-folder>"
```
