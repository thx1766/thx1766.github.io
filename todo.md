features and bugs

feature:
number of enemies proportional to screen size
solution:
change the number of bad guys moving around the screen to be sized to the screen area, for example if the screen is small for a handheld phone, use 7 guys, and if the screen is larger for a tablet use 14. may need some tweaking

bug:
bad guy enemy types are not differentiated quite uniquely enough
solution:
we want to change their head type to be a circle for a head with a hat that is either triangular or square or pentagon
change the colors to be connected to the hat type for each enemy

feature:
add bad guy enemy handheld weapons
solution:
each type of bad guy should be holding a weapon in one hand
could be fisticuffs, with arms bent and hands to the left or right with square boxing gloves
could be sword in left or right hand
could be coffee cup a la vin diesel scene in movie where he threatens to attack another prison inmate with a metal coffee cup

feature: settings to be left or right handed
solution: give an option when the page is loaded to switch the handedness of the interface - swap the location of the attack button and joystick so that the user can use their left thumb sliding around the joystick to move and right thumb hovering to tap attack, or switch to using their left thumb to hover and tap with the right thumb sliding to move the joystick

bug: rotating portrait mode on phone changes joystick and attack button locations but the bad guys are off screen when rotated from portrait to landscape or when its initially landscape and then rotated to portrait, probably an issue on iPad as well
solution: move enemies to the part of the screen that is not cut off when the device is rotated

implementation tasks (ordered; original item text preserved above):
1. [x] Bug fix: rotate portrait/landscape enemy visibility — clamp and reposition enemies within the active viewport on resize/orientation events.
2. [x] Combined bug/feature: enemy differentiation plus handheld weapons — redesign heads into circles with triangle/square/pentagon hats tied to matching colors and assign weapon types per enemy with a mature-content visibility toggle.
3. [x] Feature: number of enemies proportional to screen size — compute enemy count from viewport area (e.g., small ~7, large ~14) and rebalance when the screen size changes.
4. [x] Feature: settings for handedness and input accessibility — add UI toggles for left/right-handed layout, joystick/arrow/WASD enablement, keyboard-driven joystick indicator, and weapon visibility.
