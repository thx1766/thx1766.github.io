export function setupSettingsUI({ settingsPanel, settingsToggle, debugPanel, debugToggle, inventoryPanel, inventoryToggle, renderModeToggle, debugRefs, formRefs, settings, debugState, onSettingsChanged, onDebugChanged, onSkipLevel2, onRenderModeChange }) {
    let settingsOpen = false;
    let debugOpen = false;
    let inventoryOpen = false;
    let renderTransitioning = false;
    let renderMode = settings.renderMode || '2d';

    function setPanelState(panel, toggle, open) {
        panel.classList.toggle('hidden', !open);
        toggle.setAttribute('aria-expanded', String(open));
        const openLabel = toggle.dataset.openLabel || '✕';
        const closedLabel = toggle.dataset.closedLabel || '⚙';
        toggle.textContent = open ? openLabel : closedLabel;
    }

    function setSettingsOpen(open) {
        settingsOpen = open;
        setPanelState(settingsPanel, settingsToggle, open);
    }

    function setDebugOpen(open) {
        debugOpen = open;
        setPanelState(debugPanel, debugToggle, open);
    }

    function setInventoryOpen(open) {
        inventoryOpen = open;
        setPanelState(inventoryPanel, inventoryToggle, open);
    }

    function setRenderMode(mode) {
        renderMode = mode;
        settings.renderMode = mode;
        renderModeToggle.textContent = mode === '2d' ? 'Switch to 3D' : 'Switch to 2D';
        renderModeToggle.setAttribute('aria-pressed', String(mode === '3d'));
        renderModeToggle.title = mode === '2d' ? 'Switch to 3D view' : 'Switch to 2D view';
    }

    function setRenderTransitioning(active) {
        renderTransitioning = active;
        renderModeToggle.disabled = active;
        renderModeToggle.classList.toggle('is-transitioning', active);
    }

    function toggleSettings() { setSettingsOpen(!settingsOpen); }
    function toggleDebug() { setDebugOpen(!debugOpen); }
    function toggleInventory() { setInventoryOpen(!inventoryOpen); }

    function syncSettingsFromUI() {
        settings.enableJoystick = formRefs.enableJoystick.checked;
        settings.enableArrowKeys = formRefs.enableArrowKeys.checked;
        settings.enableWASD = formRefs.enableWASD.checked;
        settings.keyboardJoystickIndicator = formRefs.keyboardJoystickIndicator.checked;
        settings.enableWeapons = formRefs.enableWeapons.checked;
        const picked = formRefs.handednessRadios.find(r => r.checked);
        settings.handedness = picked ? picked.value : 'right';
        if (typeof onSettingsChanged === 'function') {
            onSettingsChanged(settings);
        }
    }

    function syncDebugFromUI() {
        debugState.showColliders = debugRefs.showColliders.checked;
        debugState.freezeOnCollision = debugRefs.freezeOnCollision.checked;
        if (typeof onDebugChanged === 'function') {
            onDebugChanged(debugState);
        }
    }

    function setupSettingsPanel() {
        formRefs.enableJoystick.addEventListener('change', syncSettingsFromUI);
        formRefs.enableArrowKeys.addEventListener('change', syncSettingsFromUI);
        formRefs.enableWASD.addEventListener('change', syncSettingsFromUI);
        formRefs.keyboardJoystickIndicator.addEventListener('change', syncSettingsFromUI);
        formRefs.enableWeapons.addEventListener('change', syncSettingsFromUI);
        formRefs.handednessRadios.forEach(radio => radio.addEventListener('change', syncSettingsFromUI));
        settingsToggle.addEventListener('click', toggleSettings);
        debugToggle.addEventListener('click', toggleDebug);
        inventoryToggle.addEventListener('click', toggleInventory);
        debugRefs.showColliders.addEventListener('change', syncDebugFromUI);
        debugRefs.freezeOnCollision.addEventListener('change', syncDebugFromUI);
        debugRefs.skipLevel2.addEventListener('click', () => {
            if (typeof onSkipLevel2 === 'function') onSkipLevel2();
        });
        renderModeToggle.addEventListener('click', () => {
            if (renderTransitioning) return;
            const next = renderMode === '2d' ? '3d' : '2d';
            if (typeof onRenderModeChange === 'function') onRenderModeChange(next);
        });
    }

    return {
        setSettingsOpen,
        toggleSettings,
        setDebugOpen,
        setInventoryOpen,
        toggleDebug,
        toggleInventory,
        syncSettingsFromUI,
        syncDebugFromUI,
        setupSettingsPanel,
        setRenderMode,
        setRenderTransitioning,
    };
}
