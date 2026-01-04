import { setupSettingsUI } from './ui.js';
import { createInputSystem } from './input.js';
import { createGameplaySystem } from './gameplay.js';
import { createRenderer } from './render.js';

(function () {
    const gameCanvas = document.getElementById('gameCanvas');
    const uiCanvas = document.getElementById('uiCanvas');
    const settingsPanel = document.getElementById('settingsPanel');
    const settingsToggle = document.getElementById('settingsToggle');
    const debugPanel = document.getElementById('debugPanel');
    const debugToggle = document.getElementById('debugToggle');
    const inventoryPanel = document.getElementById('inventoryPanel');
    const inventoryToggle = document.getElementById('inventoryToggle');
    const renderModeToggle = document.getElementById('renderModeToggle');
    const notificationBar = document.getElementById('notificationBar');
    const creditsDisplay = document.getElementById('creditsDisplay');
    const inventoryList = document.getElementById('inventoryList');
    const equippedDisplay = document.getElementById('equippedDisplay');

    const storedRenderMode = localStorage.getItem('renderModePreference');

    const settings = {
        enableJoystick: true,
        enableArrowKeys: true,
        enableWASD: true,
        keyboardJoystickIndicator: true,
        handedness: 'right',
        enableWeapons: true,
        renderMode: storedRenderMode === '3d' ? '3d' : '2d',
        isRenderTransitioning: false,
    };

    const debugState = {
        showColliders: false,
        freezeOnCollision: true,
    };

    const formRefs = {
        enableJoystick: document.getElementById('enableJoystick'),
        enableArrowKeys: document.getElementById('enableArrowKeys'),
        enableWASD: document.getElementById('enableWASD'),
        keyboardJoystickIndicator: document.getElementById('keyboardJoystickIndicator'),
        enableWeapons: document.getElementById('enableWeapons'),
        handednessRadios: Array.from(document.querySelectorAll('input[name="handedness"]')),
    };

    const debugRefs = {
        showColliders: document.getElementById('showColliders'),
        freezeOnCollision: document.getElementById('freezeOnCollision'),
        skipLevel2: document.getElementById('skipLevel2'),
    };

    let uiControls;
    const inputSystem = createInputSystem(uiCanvas, settings, () => uiControls.toggleSettings());
    const gameplay = createGameplaySystem(settings, inputSystem.keyState, inputSystem.joystickState, inputSystem.actionState, debugState);

    uiControls = setupSettingsUI({
        settingsPanel,
        settingsToggle,
        debugPanel,
        debugToggle,
        inventoryPanel,
        inventoryToggle,
        renderModeToggle,
        debugRefs,
        formRefs,
        settings,
        debugState,
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
        onDebugChanged: () => {},
        onSkipLevel2: () => gameplay.skipToLevelTwo(),
        onRenderModeChange: (mode) => handleRenderModeChange(mode),
    });

    const renderer = createRenderer(gameCanvas, uiCanvas, settings, gameplay, inputSystem.joystickState, inputSystem.actionState, inputSystem.setControlPositions, debugState);

    let inventoryCache = '';

    function renderInventory() {
        const snapshot = gameplay.inventorySnapshot();
        const serialized = JSON.stringify(snapshot);
        if (serialized === inventoryCache) return;
        inventoryCache = serialized;

        creditsDisplay.textContent = `Credits: ${snapshot.credits}`;
        const equippedWeapon = snapshot.equipped.weapon ? gameplay.itemCatalog.weapons[snapshot.equipped.weapon].label : 'None';
        const equippedArmor = snapshot.equipped.armor ? gameplay.itemCatalog.armor[snapshot.equipped.armor].label : 'None';
        equippedDisplay.textContent = `Equipped — Weapon: ${equippedWeapon} | Armor: ${equippedArmor}`;

        inventoryList.innerHTML = '';
        const addRows = (kind, label) => {
            Object.entries(snapshot.items[kind]).forEach(([key, qty]) => {
                const row = document.createElement('div');
                row.className = 'inventory-item';
                const meta = gameplay.itemCatalog[kind][key];
                const text = document.createElement('div');
                text.textContent = `${meta.label} (${label}) — Qty: ${qty} — ${meta.value} cr`;
                const actions = document.createElement('div');
                actions.className = 'inventory-actions';

                const sellBtn = document.createElement('button');
                sellBtn.textContent = 'Sell';
                sellBtn.addEventListener('click', () => {
                    gameplay.sellItem(kind, key);
                    inventoryCache = '';
                    renderInventory();
                });

                const equipBtn = document.createElement('button');
                equipBtn.textContent = 'Equip';
                equipBtn.addEventListener('click', () => {
                    gameplay.equipItem(kind, key);
                    inventoryCache = '';
                    renderInventory();
                });

                const buyBtn = document.createElement('button');
                buyBtn.textContent = 'Buy';
                buyBtn.addEventListener('click', () => {
                    gameplay.buyItem(kind, key);
                    inventoryCache = '';
                    renderInventory();
                });

                actions.appendChild(sellBtn);
                actions.appendChild(equipBtn);
                actions.appendChild(buyBtn);
                row.appendChild(text);
                row.appendChild(actions);
                inventoryList.appendChild(row);
            });
        };

        addRows('weapons', 'Weapon');
        addRows('armor', 'Armor');
    }

    function showNotifications() {
        const notes = gameplay.consumeNotifications();
        if (notes.length === 0) return;
        const last = notes[notes.length - 1];
        notificationBar.textContent = last.text;
        notificationBar.classList.remove('hidden');
        setTimeout(() => notificationBar.classList.add('hidden'), 1500);
    }

    function resizeAndClamp() {
        renderer.resizeCanvas();
        gameplay.centerPlayer();
        gameplay.clampEnemiesToScreen();
        gameplay.adjustEnemyCount();
    }

    function gameLoop() {
        inputSystem.updateGamepadState();
        gameplay.update();
        renderer.render();
        renderInventory();
        showNotifications();
        requestAnimationFrame(gameLoop);
    }

    uiControls.setSettingsOpen(false);
    uiControls.setRenderMode(settings.renderMode);
    uiControls.setupSettingsPanel();
    uiControls.syncSettingsFromUI();
    uiControls.syncDebugFromUI();
    uiControls.setRenderTransitioning(false);
    inputSystem.setControlPositions();
    inputSystem.attachInputListeners();
    gameplay.initEnemies();
    resizeAndClamp();
    if (settings.renderMode === '3d') {
        renderer.requestRenderMode('3d');
    }
    gameLoop();
    window.addEventListener('resize', resizeAndClamp);

    function handleRenderModeChange(mode) {
        if (settings.isRenderTransitioning || settings.renderMode === mode) return;
        settings.isRenderTransitioning = true;
        uiControls.setRenderTransitioning(true);
        renderer.requestRenderMode(mode, (appliedMode) => {
            settings.renderMode = appliedMode;
            settings.isRenderTransitioning = false;
            uiControls.setRenderMode(appliedMode);
            uiControls.setRenderTransitioning(false);
            localStorage.setItem('renderModePreference', appliedMode);
        });
    }
})();
