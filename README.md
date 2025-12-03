# thx1766.github.io

Browser-based stick-figure arena game built on a single HTML canvas page. The current prototype supports both mobile touch and desktop keyboard input, scales enemy count to the viewport, and includes a simple two-step mission loop.

## Current gameplay
- **Objectives:** Start by eliminating five enemies; once cleared, reach the green zone to reset and spawn a fresh wave.
- **Characters:** Blue stick-figure hero versus roaming stick-figure enemies with color-coded hats and optional handheld weapons.
- **Controls:** On touch devices use the virtual joystick for movement and the red action button to attack. Keyboard users can move with WASD or arrow keys and attack while holding the action button (spacebar by default).
- **Accessibility and layout:** A settings panel lets players toggle joystick/keyboard input, show a keyboard-driven joystick indicator, swap joystick/action handedness, and hide mature weapon visuals. Control positions adapt when resizing or rotating the screen.

## Running the game
Open `index.html` locally or via GitHub Pages; everything is contained in the single file for now. The canvases automatically resize to fill the viewport.
