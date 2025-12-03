export function setupSettingsUI({ settingsPanel, settingsToggle, formRefs, settings, onSettingsChanged }) {
    let settingsOpen = true;

    function setSettingsOpen(open) {
        settingsOpen = open;
        settingsPanel.classList.toggle('hidden', !open);
        settingsToggle.textContent = open ? '✕' : '⚙';
        settingsToggle.setAttribute('aria-expanded', String(open));
    }

    function toggleSettings() {
        setSettingsOpen(!settingsOpen);
    }

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

    function setupSettingsPanel() {
        formRefs.enableJoystick.addEventListener('change', syncSettingsFromUI);
        formRefs.enableArrowKeys.addEventListener('change', syncSettingsFromUI);
        formRefs.enableWASD.addEventListener('change', syncSettingsFromUI);
        formRefs.keyboardJoystickIndicator.addEventListener('change', syncSettingsFromUI);
        formRefs.enableWeapons.addEventListener('change', syncSettingsFromUI);
        formRefs.handednessRadios.forEach(radio => radio.addEventListener('change', syncSettingsFromUI));
        settingsToggle.addEventListener('click', toggleSettings);
    }

    return {
        setSettingsOpen,
        toggleSettings,
        syncSettingsFromUI,
        setupSettingsPanel,
    };
}
