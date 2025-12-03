1  # 2D ↔ 3D Mode Toggle Plan
     2
     3  ## Goals
     4  - Add a clear toggle near the Settings button that switches between 2D stick-figure mode and a 3D perspective with walls
/characters.
     5  - Ensure the toggle is easily discoverable, keyboard/focus accessible, and reversible with a single tap/click.
     6  - Animate the transition so the scene smoothly shifts between the two presentation styles without interrupting gameplay.
     7
     8  ## High-Level Behavior
     9  - The game maintains a single authoritative game state; the toggle swaps only the rendering layer and relevant UI afford
ances.
    10  - A `renderMode` state (`'2d' | '3d'`) drives which renderer and assets are active. Gameplay systems (movement, collisio
ns, missions) stay unchanged.
    11  - Tapping the new toggle flips `renderMode` and triggers a brief crossfade/transform animation that morphs stick figures
 into their 3D counterparts and introduces walls/ground plane depth cues.
    12
    13  ## UI/UX Updates
    14  - Place a compact "2D/3D" toggle button adjacent to the existing Settings toggle (`#settingsToggle`), sharing spacing ru
les in CSS for consistent layout.
    15  - Button states:
    16    - Label/Icon updates (e.g., `2D` ↔ `3D`, or a stacked-square icon) reflecting the destination mode.
    17    - `aria-pressed` reflects current mode; tooltips announce the action ("Switch to 3D view").
    18  - Visual feedback: subtle glow on hover/focus; pressed state while the animation runs to prevent double toggles.
    19
    20  ## State & Control Wiring
    21  - Extend settings/state with `renderMode: '2d'` default and `isTransitioning` flag to debounce rapid taps.
    22  - Expose a `setRenderMode(mode)` helper in `ui.js` that updates UI affordances, persists preference (localStorage), and
notifies renderer.
    23  - `main.js` listens for toggle presses, updates the shared settings/state object, and instructs the renderer to kick off
 an animated swap.
    24
    25  ## Rendering Strategy
    26  - Split render paths in `render.js`:
    27    - **2D mode:** reuse current stick-figure drawing logic (extracted into `draw2DScene`).
    28    - **3D mode:** introduce `draw3DScene` that uses a minimal pseudo-3D projection for walls/ground and renders character
 billboards or simple meshes; source textures/models from new assets once available.
    29  - Extract shared camera/sizing logic into a `getViewport()` helper so both modes honor device pixel ratio and resize han
dling.
    30  - Keep UI canvas overlays (joystick/action button) unchanged but allow mild parallax in 3D mode for depth feel.
    31
    32  ## Transition Animation
    33  - On toggle, run a 300–500ms animation:
    34    - Canvas crossfade between 2D and 3D layers (two offscreen buffers or layered canvases with alpha tween).
    35    - Optional transform: scale/tilt 2D canvas (CSS `transform: perspective()` or context transforms) while 3D fades in.
    36    - Animate characters morphing: interpolate colors/positions; in 3D mode, ease-in walls and ground grid.
    37  - Use `requestAnimationFrame` in `render.js` to manage the transition timeline; block further toggles until completion,
then reset `isTransitioning`.
    38
    39  ## Asset Considerations
    40  - Placeholder assets: add references for 3D character/wall textures or simple gradient fills until final art is supplied
.
    41  - Keep asset paths configurable (e.g., `assets/3d/characters/`, `assets/3d/walls/`) and load lazily on first entry into
3D mode.
    42  - Provide a graceful fallback (stay in 2D) if assets fail to load; log a warning to the console.
    43
    44  ## Implementation Steps (per file)
    45  - **index.html**: Add the 2D/3D toggle button next to `#settingsToggle`; ensure ARIA labels and initial text reflect def
ault 2D mode.
    46  - **assets/css/style.css**: Add styles for the toggle (spacing, hover/focus, pressed/disabled while transitioning) and o
ptional canvas layering for crossfade.
    47  - **assets/js/main.js**: Extend settings with `renderMode` and `isTransitioning`; wire up toggle click handler; pass ren
der mode changes to the renderer; persist preference via `localStorage`.
    48  - **assets/js/ui.js**: Provide `setRenderMode`/`getRenderMode` helpers, manage button state/labels, and expose a transit
ion guard to disable input during swaps.
    49  - **assets/js/render.js**: Refactor current drawing into `draw2DScene`; implement `draw3DScene` (initially minimal with
placeholder assets); add transition manager that blends scenes; support mode-aware resize logic.
    50  - **assets/js/gameplay.js** (if needed): Ensure any rendering references remain decoupled; no gameplay changes expected,
 but verify spawn/clamp logic works with the 3D viewport dimensions.
    51
    52  ## How It Works (Conceptual Flow)
    53  1. Player taps the new toggle button.
    54  2. `ui.js` updates `renderMode` state, sets `aria-pressed`, and notifies `render.js` via a callback.
    55  3. `render.js` prepares both scenes (2D and 3D) using offscreen buffers, then animates a crossfade/transform until the n
ew mode is fully visible.
    56  4. Once the animation completes, `renderMode` is locked in; controls remain responsive because the gameplay loop never p
auses.
    57  5. A subsequent tap reverses the process, fading back to the 2D stick-figure presentation.
