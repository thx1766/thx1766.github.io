# Planning and future work

## Feature and platform ideas
- Break out the single-page implementation to make the project easier to maintain.
- Experiment with Lightning JS and Solid JS for smart-TV friendly rendering and remote control input.
- Explore platform-specific builds (e.g., Apple TV) while keeping keyboard, touch, and limited-input devices playable.
- Iterate on enemy variety and narrative beats inspired by GTA, State of Emergency, Cyberpunk 2077, and Red Faction-style destructible environments.

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
