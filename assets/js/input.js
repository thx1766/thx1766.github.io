export function createInputSystem(uiCanvas, settings, toggleSettings) {
    const keyState = { up: false, down: false, left: false, right: false };
    const joystickState = {
        joystickTouchId: null,
        joystickBase: { x: 80, y: 80 },
        joystickRadius: 50,
        joystickKnob: { x: 80, y: 80 },
        maxSpeed: 3,
        joystickActive: false,
    };
    const actionState = {
        actionTouchId: null,
        actionButton: { x: 0, y: 0, radius: 30 },
        attackPressed: false,
    };

    function setControlPositions() {
        const joystickOffset = 80;
        const actionOffset = 80;
        if (settings.handedness === 'right') {
            joystickState.joystickBase.x = joystickOffset;
            actionState.actionButton.x = window.innerWidth - actionOffset;
        } else {
            joystickState.joystickBase.x = window.innerWidth - joystickOffset;
            actionState.actionButton.x = actionOffset;
        }
        joystickState.joystickBase.y = window.innerHeight - joystickOffset;
        actionState.actionButton.y = window.innerHeight - actionOffset;
        joystickState.joystickKnob.x = joystickState.joystickBase.x;
        joystickState.joystickKnob.y = joystickState.joystickBase.y;
    }

    function attachPointerEvents() {
        uiCanvas.addEventListener('pointerdown', function (e) {
            e.preventDefault();
            const rect = uiCanvas.getBoundingClientRect();
            const px = e.clientX - rect.left;
            const py = e.clientY - rect.top;
            const dx = px - joystickState.joystickBase.x;
            const dy = py - joystickState.joystickBase.y;
            if (settings.enableJoystick && joystickState.joystickTouchId === null && dx * dx + dy * dy <= joystickState.joystickRadius * joystickState.joystickRadius) {
                joystickState.joystickTouchId = e.pointerId;
                joystickState.joystickActive = true;
                joystickState.joystickKnob.x = px;
                joystickState.joystickKnob.y = py;
            }
            const dax = px - actionState.actionButton.x;
            const day = py - actionState.actionButton.y;
            if (actionState.actionTouchId === null && dax * dax + day * day <= actionState.actionButton.radius * actionState.actionButton.radius) {
                actionState.actionTouchId = e.pointerId;
                actionState.attackPressed = true;
            }
        });

        uiCanvas.addEventListener('pointermove', function (e) {
            e.preventDefault();
            const rect = uiCanvas.getBoundingClientRect();
            const px = e.clientX - rect.left;
            const py = e.clientY - rect.top;
            if (settings.enableJoystick && joystickState.joystickTouchId === e.pointerId) {
                const dx = px - joystickState.joystickBase.x;
                const dy = py - joystickState.joystickBase.y;
                const dist = Math.sqrt(dx * dx + dy * dy);
                if (dist > joystickState.joystickRadius) {
                    joystickState.joystickKnob.x = joystickState.joystickBase.x + (dx / dist) * joystickState.joystickRadius;
                    joystickState.joystickKnob.y = joystickState.joystickBase.y + (dy / dist) * joystickState.joystickRadius;
                } else {
                    joystickState.joystickKnob.x = px;
                    joystickState.joystickKnob.y = py;
                }
            }
            if (actionState.actionTouchId === e.pointerId) {
                actionState.attackPressed = true;
            }
        });

        uiCanvas.addEventListener('pointerup', function (e) {
            e.preventDefault();
            if (e.pointerId === joystickState.joystickTouchId) {
                joystickState.joystickTouchId = null;
                joystickState.joystickActive = false;
                joystickState.joystickKnob.x = joystickState.joystickBase.x;
                joystickState.joystickKnob.y = joystickState.joystickBase.y;
            }
            if (e.pointerId === actionState.actionTouchId) {
                actionState.actionTouchId = null;
                actionState.attackPressed = false;
            }
        });

        uiCanvas.addEventListener('pointercancel', function (e) {
            if (e.pointerId === joystickState.joystickTouchId) {
                joystickState.joystickTouchId = null;
                joystickState.joystickActive = false;
                joystickState.joystickKnob.x = joystickState.joystickBase.x;
                joystickState.joystickKnob.y = joystickState.joystickBase.y;
            }
            if (e.pointerId === actionState.actionTouchId) {
                actionState.actionTouchId = null;
                actionState.attackPressed = false;
            }
        });
    }

    function attachKeyboardEvents() {
        window.addEventListener('keydown', function (e) {
            switch (e.key) {
                case 'q':
                case 'Q':
                    toggleSettings();
                    break;
                case 'ArrowUp':
                    if (!settings.enableArrowKeys) return;
                    keyState.up = true;
                    break;
                case 'w':
                case 'W':
                    if (!settings.enableWASD) return;
                    keyState.up = true;
                    break;
                case 'ArrowDown':
                    if (!settings.enableArrowKeys) return;
                    keyState.down = true;
                    break;
                case 's':
                case 'S':
                    if (!settings.enableWASD) return;
                    keyState.down = true;
                    break;
                case 'ArrowLeft':
                    if (!settings.enableArrowKeys) return;
                    keyState.left = true;
                    break;
                case 'a':
                case 'A':
                    if (!settings.enableWASD) return;
                    keyState.left = true;
                    break;
                case 'ArrowRight':
                    if (!settings.enableArrowKeys) return;
                    keyState.right = true;
                    break;
                case 'd':
                case 'D':
                    if (!settings.enableWASD) return;
                    keyState.right = true;
                    break;
                case ' ':
                    actionState.attackPressed = true;
                    break;
                default:
                    return;
            }
            e.preventDefault();
        });

        window.addEventListener('keyup', function (e) {
            switch (e.key) {
                case 'ArrowUp':
                    if (!settings.enableArrowKeys) return;
                    keyState.up = false;
                    break;
                case 'w':
                case 'W':
                    if (!settings.enableWASD) return;
                    keyState.up = false;
                    break;
                case 'ArrowDown':
                    if (!settings.enableArrowKeys) return;
                    keyState.down = false;
                    break;
                case 's':
                case 'S':
                    if (!settings.enableWASD) return;
                    keyState.down = false;
                    break;
                case 'ArrowLeft':
                    if (!settings.enableArrowKeys) return;
                    keyState.left = false;
                    break;
                case 'a':
                case 'A':
                    if (!settings.enableWASD) return;
                    keyState.left = false;
                    break;
                case 'ArrowRight':
                    if (!settings.enableArrowKeys) return;
                    keyState.right = false;
                    break;
                case 'd':
                case 'D':
                    if (!settings.enableWASD) return;
                    keyState.right = false;
                    break;
                case ' ':
                    actionState.attackPressed = false;
                    break;
                default:
                    return;
            }
            e.preventDefault();
        });
    }

    function attachInputListeners() {
        attachPointerEvents();
        attachKeyboardEvents();
    }

    return {
        keyState,
        joystickState,
        actionState,
        setControlPositions,
        attachInputListeners,
    };
}
