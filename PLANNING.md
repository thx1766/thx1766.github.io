# Planning and future work

## Feature and platform ideas
- Break out the single-page implementation to make the project easier to maintain.
- Experiment with Lightning JS and Solid JS for smart-TV friendly rendering and remote control input.
- Explore platform-specific builds (e.g., Apple TV) while keeping keyboard, touch, and limited-input devices playable.
- Iterate on enemy variety and narrative beats inspired by GTA, State of Emergency, and Cyberpunk 2077.
- Add Red Faction-style destructible environments for mining-charge gameplay.

### Narrative direction and inspirations
- Blend GTA/State of Emergency chaos with a customizable stick-figure hero who can adopt Cyberpunk 2077 gear and style.
- Track defeated enemy loadouts to unlock hats, weapons, or credits that can be redeemed in a vending-machine style locker for cosmetics and upgrades.

### Geo-mod mining charge progression
- **Level 1:** Eliminate enemies to reach a marked green extraction zone.
- **Level 2:** Collect mining charges inside a containment square; carrying a charge swaps the attack button into a drop/arm action and shows a blast-radius preview that must be exited before detonation.
- **Level 2 failure loop:** If a blast misses walls, respawn charges elsewhere in the square to encourage exploration and retries.
- **Level 3:** Escape into a larger room with wireframe walls and stick-figure characters, hinting at a 3D transition while preserving top-down readability.

### Encounter variety and pacing
- Introduce 2D geo-mod walls that can be destroyed when the blast radius overlaps them, opening traversal routes.
- Consider alternate Level 2 paths (e.g., post-extraction customization) where loot gathered in Level 1 influences available gear and upgrades.

## File organization proposal
To make the project easier to iterate on, split the current monolithic `index.html` into focused assets:

- **HTML shell:** Keep `index.html` lean, loading external CSS/JS and defining the minimal canvas and settings markup.
- **Styles:** Move inline styles into `assets/css/style.css` (or similar), isolating layout rules for the canvases, settings panel, and controls.
- **JavaScript:** Organize scripts under `assets/js/` (or `src/`). Suggested modules:
  - `input.js` for joystick, keyboard state, and handedness toggles.
  - `gameplay.js` for missions, player/enemy state, and progression logic.
  - `render.js` for drawing the game layer and UI layer, including hats and weapons.
  - `ui.js` for settings panel wiring and configuration persistence.
- **Assets:** Reserve `assets/media/` for future art, audio, or localization files.

Link the HTML to the new CSS/JS files with `<link>` and `<script type="module">` tags, and use ES module imports between the JavaScript files to keep responsibilities clear.
