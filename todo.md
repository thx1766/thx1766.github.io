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
resolution: seems to be implemented well, though not sure about pentagon hats

feature:
add bad guy enemy handheld weapons
solution:
each type of bad guy should be holding a weapon in one hand
could be fisticuffs, with arms bent and hands to the left or right with square boxing gloves
could be sword in left or right hand
could be coffee cup a la vin diesel scene in movie where he threatens to attack another prison inmate with a metal coffee cup
resolution: i see coffee cups or fisticufs as red square in hand, and swords, but not differentiatig red squares

feature: settings to be left or right handed
solution: give an option when the page is loaded to switch the handedness of the interface - swap the location of the attack button and joystick so that the user can use their left thumb sliding around the joystick to move and right thumb hovering to tap attack, or switch to using their left thumb to hover and tap with the right thumb sliding to move the joystick
resolution: seems implemented well, but settoings menu is always on top, would be nice to add close button and add settongs button at top corner of screen and with q toggling menu open closed for keyboard

bug: rotating portrait mode on phone changes joystick and attack button locations but the bad guys are off screen when rotated from portrait to landscape or when its initially landscape and then rotated to portrait, probably an issue on iPad as well
solution: move enemies to the part of the screen that is not cut off when the device is rotated
resolution: seems implemented wel but a little hard to confirm with settings menu overly on the whole time


bug:
pentagon hats and fisticufs vs coffee cup - what was implemented?
solution:
turns out the coffee cups are there in grey and the fisticufs are red
pentagons and triangles are too close in color, they should contrast with each other more

bug:
seems like on desktop there are more enemies bad guys like intended, but the starting postion of the good guy dude is not in the center of the screen, also not in center on mobilr
solution:
start in the center of the screen on both desktop and mobilr

implementation tasks (ordered; original item text preserved above):
1. [x] Bug fix: rotate portrait/landscape enemy visibility — clamp and reposition enemies within the active viewport on resize/orientation events.
2. [x] Combined bug/feature: enemy differentiation plus handheld weapons — redesign heads into circles with triangle/square/pentagon hats tied to matching colors and assign weapon types per enemy with a mature-content visibility toggle.
3. [x] Feature: number of enemies proportional to screen size — compute enemy count from viewport area (e.g., small ~7, large ~14) and rebalance when the screen size changes.
4. [x] Feature: settings for handedness and input accessibility — add UI toggles for left/right-handed layout, joystick/arrow/WASD enablement, keyboard-driven joystick indicator, and weapon visibility.

bug: menu should hide when closed and open when q is tapped or button in top corner is tapped
solution: top right with menu on left, but menu close button should be the same as settings open button), button should be an X when open and a gear or asterisk in a round-cornered rectangle button, and the outline of the settings menu should expand from the round rectangle around the button to the space needed

bug: contrast for hats
solution: change hat colors for triangle and pentagon to make them more contrasting with each other, maybe make the hats a bit larger relative to stick figure

bug: good guy stsrting position
solution: start at center

bug fix summary:
- settings menu can now be opened or closed from a top-right button or the q key, with the menu expanding from the toggle.
- triangle and pentagon hat colors are more contrasted and the hats are larger to make enemy types clearer.
- the player character now starts at the centered position on both desktop and mobile viewports.
