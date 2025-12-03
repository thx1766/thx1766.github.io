# Planning and future work

## Feature and platform ideas
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

## Recent implementation summary
- Added a 2D/3D render toggle with a crossfade transition and persisted preference, placing the control next to the settings toggle.
- Shifted the Level 1 flow so extraction unlocks after five victories, guiding the hero to a visible green square before entering an enlarged containment box with cleared enemies and a smaller blast radius.
- Repositioned the inventory panel to the left (with a banner layout on small screens) and started the settings menu closed by default.
