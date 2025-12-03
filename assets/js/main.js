import { setupSettingsUI } from './ui.js';
import { createInputSystem } from './input.js';
import { createGameplaySystem } from './gameplay.js';
import { createRenderer } from './render.js';

(function () {
    const gameCanvas = document.getElementById('gameCanvas');
    const uiCanvas = document.getElementById('uiCanvas');
    const settingsPanel = document.getElementById('settingsPanel');
    const settingsToggle = document.getElementById('settingsToggle');

    const settings = {
        enableJoystick: true,
        enableArrowKeys: true,
        enableWASD: true,
        keyboardJoystickIndicator: true,
        handedness: 'right',
        enableWeapons: true,
    };

    const formRefs = {
        enableJoystick: document.getElementById('enableJoystick'),
        enableArrowKeys: document.getElementById('enableArrowKeys'),
        enableWASD: document.getElementById('enableWASD'),
        keyboardJoystickIndicator: document.getElementById('keyboardJoystickIndicator'),
        enableWeapons: document.getElementById('enableWeapons'),
        handednessRadios: Array.from(document.querySelectorAll('input[name="handedness"]')),
    };

    const uiControls = setupSettingsUI({
        settingsPanel,
        settingsToggle,
        formRefs,
        settings,
        onSettingsChanged: () => {
            inputSystem.setControlPositions();
            if (!settings.enableArrowKeys && !settings.enableWASD) {
                inputSystem.keyState.up = inputSystem.keyState.down = inputSystem.keyState.left = inputSystem.keyState.right = false;
            }
            if (!settings.enableJoystick) {
                inputSystem.joystickState.joystickActive = false;
                inputSystem.joystickState.joystickTouchId = null;
                inputSystem.joystickState.joystickKnob.x = inputSystem.joystickState.joystickBase.x;
                inputSystem.joystickState.joystickKnob.y = inputSystem.joystickState.joystickBase.y;
            }
        },
    });

    const inputSystem = createInputSystem(uiCanvas, settings, uiControls.toggleSettings);
    const gameplay = createGameplaySystem(settings, inputSystem.keyState, inputSystem.joystickState, inputSystem.actionState);
    const renderer = createRenderer(gameCanvas, uiCanvas, settings, gameplay, inputSystem.joystickState, inputSystem.actionState, inputSystem.setControlPositions);

    function resizeAndClamp() {
        renderer.resizeCanvas();
        gameplay.centerPlayer();
        gameplay.clampEnemiesToScreen();
        gameplay.adjustEnemyCount();
    }

    function gameLoop() {
        gameplay.update();
        renderer.render();
        requestAnimationFrame(gameLoop);
    }

    uiControls.setSettingsOpen(true);
    uiControls.setupSettingsPanel();
    uiControls.syncSettingsFromUI();
    inputSystem.setControlPositions();
    inputSystem.attachInputListeners();
    gameplay.initEnemies();
    resizeAndClamp();
    gameLoop();
    window.addEventListener('resize', resizeAndClamp);
})();
