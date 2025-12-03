# Agent Notes for thx1766.github.io

This project is a small browser game rendered on layered canvases with a togglable settings panel. Use this guide when updating the code with Codex or other agentic tooling.

## Conventions and tips for code assistants
- JavaScript modules live in `assets/js/` and are imported as ES modules from `index.html`. Avoid introducing global variables; extend the existing factory-style systems (`createInputSystem`, `createGameplaySystem`, `createRenderer`, `setupSettingsUI`).
- Keep user-interaction wiring declarative: read DOM elements once in `main.js`, then pass references into subsystem constructors rather than re-querying inside modules.
- Do not wrap imports in `try/catch` blocks.
- Prefer pure functions for calculations and keep side effects (event listeners, canvas mutations) scoped to their modules.
- When adding settings, wire the checkbox/radio in `index.html`, add styling in `assets/css/style.css` if needed, store the value in `settings` in `assets/js/main.js`, and sync/dispatch changes via `setupSettingsUI`.

## File map and responsibilities
- `index.html`: bootstraps the app, declares two canvases (`#gameCanvas`, `#uiCanvas`), and hosts the settings UI.
- `assets/css/style.css`: layout/styling for canvases and settings panel; canvases are absolutely positioned, settings float in the top-right.
- `assets/js/main.js`: orchestrates setup. Reads DOM nodes, builds `settings`, instantiates input/gameplay/render subsystems, runs the game loop, and responds to window resize.
- `assets/js/input.js`: user input system. Manages keyboard state, on-screen joystick positions, and an action button; exposes `setControlPositions` and listener attachment.
- `assets/js/gameplay.js`: game state and rules. Tracks player, enemies (styles, movement, combat), mission progression, and provides helpers to clamp/center and adjust enemy counts.
- `assets/js/render.js`: drawing layer. Renders game world and enemies (hats/weapons) onto `gameCanvas`, paints UI controls onto `uiCanvas`, and resizes canvases according to DPR.
- `assets/js/ui.js`: settings panel logic. Toggles visibility, syncs form controls into the shared `settings` object, and notifies listeners on change.

## Asset/feature organization
- **Rendering assets**: No external images; all visuals are canvas primitives defined in `assets/js/render.js`.
- **Input features**: Touch and keyboard handling reside in `assets/js/input.js` with configurable control schemes and handedness offsets.
- **Gameplay features**: Enemy styles, spawn logic, missions, and combat are encapsulated in `assets/js/gameplay.js`.
- **UI/Settings features**: Panel structure is in `index.html`, styles in `assets/css/style.css`, and behavior in `assets/js/ui.js`. Toggle hotkey `Q` is handled in `assets/js/input.js`.
- **App lifecycle**: Initialization, resizing, and the main animation loop live in `assets/js/main.js`.

Use this map to quickly locate modules when updating controls, visuals, or game rules.

## Recently added expectations
- The 2D/3D render toggle lives next to the settings toggle in `index.html`; its state is stored in `settings.renderMode` with a transition guard (`settings.isRenderTransitioning`) and persisted via `localStorage`.
- The render pipeline in `assets/js/render.js` now crossfades between `draw2DScene` and `draw3DScene` using offscreen buffers; prefer updating those helpers instead of reintroducing a monolithic draw path.
- Mission flow now requires five victories before heading to a visible green target zone; entering it moves the player into an enlarged containment box, clears enemies, and uses a reduced blast radius for charges.
- The settings panel should load closed, and the inventory panel is docked to the left (becoming a banner on small screens); keep positioning changes in CSS aligned with these defaults.
