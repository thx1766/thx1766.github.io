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
    const lookJoystickState = {
        joystickTouchId: null,
        joystickBase: { x: 0, y: 0 },
        joystickRadius: 48,
        joystickKnob: { x: 0, y: 0 },
        joystickActive: false,
    };
    const actionState = {
        actionTouchId: null,
        actionButton: { x: 0, y: 0, radius: 30 },
        attackPressed: false,
        label: 'Attack',
    };
    const gamepadState = {
        activeIndex: null,
        deadzone: 0.15,
    };

    function updateKeyboardJoystickIndicator() {
        if (!settings.keyboardJoystickIndicator || joystickState.joystickTouchId !== null) return;
        let dx = 0;
        let dy = 0;
        if (keyState.up) dy -= 1;
        if (keyState.down) dy += 1;
        if (keyState.left) dx -= 1;
        if (keyState.right) dx += 1;

        const length = Math.hypot(dx, dy);
        if (length > 0) {
            const nx = dx / length;
            const ny = dy / length;
            joystickState.joystickKnob.x = joystickState.joystickBase.x + nx * joystickState.joystickRadius;
            joystickState.joystickKnob.y = joystickState.joystickBase.y + ny * joystickState.joystickRadius;
            joystickState.joystickActive = true;
        } else {
            joystickState.joystickActive = false;
            joystickState.joystickKnob.x = joystickState.joystickBase.x;
            joystickState.joystickKnob.y = joystickState.joystickBase.y;
        }
    }

    function setControlPositions() {
        const joystickOffset = 80;
        const secondaryOffset = 92;
        const actionOffset = 140;
        if (settings.handedness === 'right') {
            joystickState.joystickBase.x = joystickOffset;
            lookJoystickState.joystickBase.x = window.innerWidth - secondaryOffset;
            actionState.actionButton.x = lookJoystickState.joystickBase.x;
        } else {
            joystickState.joystickBase.x = window.innerWidth - joystickOffset;
            lookJoystickState.joystickBase.x = secondaryOffset;
            actionState.actionButton.x = joystickState.joystickBase.x;
        }
        joystickState.joystickBase.y = window.innerHeight - joystickOffset;
        lookJoystickState.joystickBase.y = window.innerHeight - secondaryOffset;
        lookJoystickState.joystickKnob.x = lookJoystickState.joystickBase.x;
        lookJoystickState.joystickKnob.y = lookJoystickState.joystickBase.y;
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
            const wants3D = (settings.pendingRenderMode || settings.renderMode) === '3d';
            if (settings.enableJoystick && joystickState.joystickTouchId === null && dx * dx + dy * dy <= joystickState.joystickRadius * joystickState.joystickRadius) {
                joystickState.joystickTouchId = e.pointerId;
                joystickState.joystickActive = true;
                joystickState.joystickKnob.x = px;
                joystickState.joystickKnob.y = py;
            }
            const ldx = px - lookJoystickState.joystickBase.x;
            const ldy = py - lookJoystickState.joystickBase.y;
            if (wants3D && lookJoystickState.joystickTouchId === null && ldx * ldx + ldy * ldy <= lookJoystickState.joystickRadius * lookJoystickState.joystickRadius) {
                lookJoystickState.joystickTouchId = e.pointerId;
                lookJoystickState.joystickActive = true;
                lookJoystickState.joystickKnob.x = px;
                lookJoystickState.joystickKnob.y = py;
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
            if (lookJoystickState.joystickTouchId === e.pointerId) {
                const dx = px - lookJoystickState.joystickBase.x;
                const dy = py - lookJoystickState.joystickBase.y;
                const dist = Math.sqrt(dx * dx + dy * dy);
                if (dist > lookJoystickState.joystickRadius) {
                    lookJoystickState.joystickKnob.x = lookJoystickState.joystickBase.x + (dx / dist) * lookJoystickState.joystickRadius;
                    lookJoystickState.joystickKnob.y = lookJoystickState.joystickBase.y + (dy / dist) * lookJoystickState.joystickRadius;
                } else {
                    lookJoystickState.joystickKnob.x = px;
                    lookJoystickState.joystickKnob.y = py;
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
            if (e.pointerId === lookJoystickState.joystickTouchId) {
                lookJoystickState.joystickTouchId = null;
                lookJoystickState.joystickActive = false;
                lookJoystickState.joystickKnob.x = lookJoystickState.joystickBase.x;
                lookJoystickState.joystickKnob.y = lookJoystickState.joystickBase.y;
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
            if (e.pointerId === lookJoystickState.joystickTouchId) {
                lookJoystickState.joystickTouchId = null;
                lookJoystickState.joystickActive = false;
                lookJoystickState.joystickKnob.x = lookJoystickState.joystickBase.x;
                lookJoystickState.joystickKnob.y = lookJoystickState.joystickBase.y;
            }
            if (e.pointerId === actionState.actionTouchId) {
                actionState.actionTouchId = null;
                actionState.attackPressed = false;
            }
        });
    }

    function attachGamepadEvents() {
        window.addEventListener('gamepadconnected', (event) => {
            if (gamepadState.activeIndex === null) {
                gamepadState.activeIndex = event.gamepad.index;
            }
        });

        window.addEventListener('gamepaddisconnected', (event) => {
            if (event.gamepad.index === gamepadState.activeIndex) {
                gamepadState.activeIndex = null;
                if (joystickState.joystickTouchId === null) {
                    joystickState.joystickActive = false;
                    joystickState.joystickKnob.x = joystickState.joystickBase.x;
                    joystickState.joystickKnob.y = joystickState.joystickBase.y;
                }
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
                    updateKeyboardJoystickIndicator();
                    break;
                case 'w':
                case 'W':
                    if (!settings.enableWASD) return;
                    keyState.up = true;
                    updateKeyboardJoystickIndicator();
                    break;
                case 'ArrowDown':
                    if (!settings.enableArrowKeys) return;
                    keyState.down = true;
                    updateKeyboardJoystickIndicator();
                    break;
                case 's':
                case 'S':
                    if (!settings.enableWASD) return;
                    keyState.down = true;
                    updateKeyboardJoystickIndicator();
                    break;
                case 'ArrowLeft':
                    if (!settings.enableArrowKeys) return;
                    keyState.left = true;
                    updateKeyboardJoystickIndicator();
                    break;
                case 'a':
                case 'A':
                    if (!settings.enableWASD) return;
                    keyState.left = true;
                    updateKeyboardJoystickIndicator();
                    break;
                case 'ArrowRight':
                    if (!settings.enableArrowKeys) return;
                    keyState.right = true;
                    updateKeyboardJoystickIndicator();
                    break;
                case 'd':
                case 'D':
                    if (!settings.enableWASD) return;
                    keyState.right = true;
                    updateKeyboardJoystickIndicator();
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
                    updateKeyboardJoystickIndicator();
                    break;
                case 'w':
                case 'W':
                    if (!settings.enableWASD) return;
                    keyState.up = false;
                    updateKeyboardJoystickIndicator();
                    break;
                case 'ArrowDown':
                    if (!settings.enableArrowKeys) return;
                    keyState.down = false;
                    updateKeyboardJoystickIndicator();
                    break;
                case 's':
                case 'S':
                    if (!settings.enableWASD) return;
                    keyState.down = false;
                    updateKeyboardJoystickIndicator();
                    break;
                case 'ArrowLeft':
                    if (!settings.enableArrowKeys) return;
                    keyState.left = false;
                    updateKeyboardJoystickIndicator();
                    break;
                case 'a':
                case 'A':
                    if (!settings.enableWASD) return;
                    keyState.left = false;
                    updateKeyboardJoystickIndicator();
                    break;
                case 'ArrowRight':
                    if (!settings.enableArrowKeys) return;
                    keyState.right = false;
                    updateKeyboardJoystickIndicator();
                    break;
                case 'd':
                case 'D':
                    if (!settings.enableWASD) return;
                    keyState.right = false;
                    updateKeyboardJoystickIndicator();
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
        attachGamepadEvents();
    }

    function updateGamepadState() {
        if (!settings.enableJoystick || joystickState.joystickTouchId !== null) return;
        if (typeof navigator.getGamepads !== 'function') return;

        const pads = navigator.getGamepads();
        let pad = null;

        if (gamepadState.activeIndex !== null) {
            pad = pads[gamepadState.activeIndex];
        }

        if (!pad) {
            for (const candidate of pads) {
                if (candidate && candidate.connected) {
                    pad = candidate;
                    gamepadState.activeIndex = candidate.index;
                    break;
                }
            }
        }

        if (!pad || !pad.axes || pad.axes.length < 2) {
            gamepadState.activeIndex = null;
            joystickState.joystickActive = false;
            joystickState.joystickKnob.x = joystickState.joystickBase.x;
            joystickState.joystickKnob.y = joystickState.joystickBase.y;
            lookJoystickState.joystickActive = false;
            lookJoystickState.joystickKnob.x = lookJoystickState.joystickBase.x;
            lookJoystickState.joystickKnob.y = lookJoystickState.joystickBase.y;
            return;
        }

        const applyAxesToStick = (targetState, rawX, rawY) => {
            const magnitude = Math.hypot(rawX, rawY);
            if (magnitude <= gamepadState.deadzone) {
                targetState.joystickActive = false;
                targetState.joystickKnob.x = targetState.joystickBase.x;
                targetState.joystickKnob.y = targetState.joystickBase.y;
                return;
            }
            const scaledMagnitude = Math.min(1, (magnitude - gamepadState.deadzone) / (1 - gamepadState.deadzone));
            const nx = (rawX / magnitude) * scaledMagnitude;
            const ny = (rawY / magnitude) * scaledMagnitude;
            targetState.joystickActive = true;
            targetState.joystickKnob.x = targetState.joystickBase.x + nx * targetState.joystickRadius;
            targetState.joystickKnob.y = targetState.joystickBase.y + ny * targetState.joystickRadius;
        };

        applyAxesToStick(joystickState, pad.axes[0], pad.axes[1]);
        if (pad.axes.length >= 4) {
            applyAxesToStick(lookJoystickState, pad.axes[2], pad.axes[3]);
        }
    }

    return {
        keyState,
        joystickState,
        lookJoystickState,
        actionState,
        setControlPositions,
        attachInputListeners,
        updateGamepadState,
    };
}
