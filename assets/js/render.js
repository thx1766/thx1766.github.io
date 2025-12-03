const TRANSITION_DURATION = 420;

function createBufferCanvas() {
    const canvas = document.createElement('canvas');
    const ctx = canvas.getContext('2d');
    return { canvas, ctx };
}

function getViewport() {
    const dpr = window.devicePixelRatio || 1;
    return { width: window.innerWidth, height: window.innerHeight, dpr };
}

function drawHat(gameCtx, x, y, style) {
    gameCtx.fillStyle = style.hatColor;
    gameCtx.beginPath();
    const hatRadius = style.headRadius + (style.hatSizeBoost || 0);
    if (style.hatType === 'triangle') {
        gameCtx.moveTo(x, y - hatRadius);
        gameCtx.lineTo(x - hatRadius, y + hatRadius);
        gameCtx.lineTo(x + hatRadius, y + hatRadius);
    } else if (style.hatType === 'square') {
        gameCtx.rect(x - hatRadius, y - hatRadius, hatRadius * 2, hatRadius * 2);
    } else {
        const r = hatRadius + 2;
        for (let i = 0; i < 5; i++) {
            const angle = (-Math.PI / 2) + (i * 2 * Math.PI / 5);
            const px = x + Math.cos(angle) * r;
            const py = y + Math.sin(angle) * r;
            if (i === 0) gameCtx.moveTo(px, py);
            else gameCtx.lineTo(px, py);
        }
    }
    gameCtx.closePath();
    gameCtx.fill();
}

function drawWeapon(gameCtx, enemy, torsoTopY) {
    const s = enemy.style;
    const armY = enemy.y - 2;
    const handX = enemy.hand === 'left' ? enemy.x - s.armLength : enemy.x + s.armLength;
    const dir = enemy.hand === 'left' ? -1 : 1;
    gameCtx.lineWidth = 3;
    if (s.weapon === 'gloves') {
        gameCtx.fillStyle = '#d35400';
        gameCtx.beginPath();
        gameCtx.rect(handX - 6, armY - 6, 12, 12);
        gameCtx.fill();
    } else if (s.weapon === 'sword') {
        gameCtx.strokeStyle = '#95a5a6';
        gameCtx.beginPath();
        gameCtx.moveTo(handX, armY);
        gameCtx.lineTo(handX + dir * 18, armY - 14);
        gameCtx.stroke();
        gameCtx.strokeStyle = s.bodyColor;
        gameCtx.beginPath();
        gameCtx.moveTo(handX, armY);
        gameCtx.lineTo(handX + dir * 6, armY + 6);
        gameCtx.stroke();
    } else if (s.weapon === 'cup') {
        gameCtx.fillStyle = '#bdc3c7';
        gameCtx.beginPath();
        gameCtx.rect(handX - 5, armY - 7, 10, 14);
        gameCtx.fill();
    }
}

function drawTargetZone(gameCtx, targetZone, emphasize) {
    gameCtx.save();
    gameCtx.strokeStyle = '#27ae60';
    gameCtx.lineWidth = 3;
    gameCtx.setLineDash(emphasize ? [8, 4] : [4, 4]);
    gameCtx.strokeRect(targetZone.x, targetZone.y, targetZone.size, targetZone.size);
    if (emphasize) {
        gameCtx.fillStyle = 'rgba(46, 204, 113, 0.18)';
        gameCtx.fillRect(targetZone.x, targetZone.y, targetZone.size, targetZone.size);
    }
    gameCtx.restore();
}

function drawContainment2D(gameCtx, level2State, player) {
    const { containment, charges, planted, blastRadius, walls, carryingCharge } = level2State;
    gameCtx.save();
    gameCtx.strokeStyle = '#27ae60';
    gameCtx.setLineDash([6, 4]);
    gameCtx.strokeRect(containment.x, containment.y, containment.size, containment.size);
    gameCtx.setLineDash([]);

    walls.forEach(wall => {
        gameCtx.fillStyle = wall.destroyed ? 'rgba(189, 195, 199, 0.4)' : '#34495e';
        gameCtx.fillRect(wall.x, wall.y, wall.width, wall.height);
    });

    charges.forEach(charge => {
        gameCtx.fillStyle = '#f39c12';
        gameCtx.beginPath();
        gameCtx.arc(charge.x, charge.y, 8, 0, Math.PI * 2);
        gameCtx.fill();
    });

    planted.forEach(charge => {
        gameCtx.fillStyle = '#e74c3c';
        gameCtx.beginPath();
        gameCtx.arc(charge.x, charge.y, 10, 0, Math.PI * 2);
        gameCtx.fill();
        gameCtx.strokeStyle = 'rgba(231, 76, 60, 0.5)';
        gameCtx.beginPath();
        gameCtx.arc(charge.x, charge.y, charge.radius, 0, Math.PI * 2);
        gameCtx.stroke();
    });

    if (carryingCharge) {
        gameCtx.strokeStyle = 'rgba(230, 126, 34, 0.7)';
        gameCtx.beginPath();
        gameCtx.arc(player.x, player.y, blastRadius, 0, Math.PI * 2);
        gameCtx.stroke();
    }

    gameCtx.restore();
}

function drawDebugColliders(gameCtx, player, enemies) {
    gameCtx.save();
    gameCtx.strokeStyle = 'rgba(0,0,0,0.45)';
    gameCtx.setLineDash([4, 4]);
    gameCtx.beginPath();
    gameCtx.arc(player.x, player.y, player.radius, 0, Math.PI * 2);
    gameCtx.stroke();
    enemies.forEach(enemy => {
        gameCtx.beginPath();
        gameCtx.arc(enemy.x, enemy.y, enemy.size, 0, Math.PI * 2);
        gameCtx.stroke();
    });
    gameCtx.restore();
}

function drawPlayer2D(gameCtx, player) {
    gameCtx.fillStyle = player.color;
    gameCtx.beginPath();
    gameCtx.arc(player.x, player.y - 15, 5, 0, Math.PI * 2);
    gameCtx.fill();
    gameCtx.beginPath();
    gameCtx.moveTo(player.x, player.y - 10);
    gameCtx.lineTo(player.x, player.y + 5);
    gameCtx.stroke();
    gameCtx.beginPath();
    gameCtx.moveTo(player.x - 8, player.y - 5);
    gameCtx.lineTo(player.x + 8, player.y - 5);
    gameCtx.stroke();
    gameCtx.beginPath();
    gameCtx.moveTo(player.x, player.y + 5);
    gameCtx.lineTo(player.x - 5, player.y + 15);
    gameCtx.moveTo(player.x, player.y + 5);
    gameCtx.lineTo(player.x + 5, player.y + 15);
    gameCtx.stroke();
}

function drawEnemies2D(gameCtx, enemies, settings) {
    for (const enemy of enemies) {
        const s = enemy.style;
        const torsoTopY = enemy.y - s.bodyLength * 0.5;
        const torsoBottomY = enemy.y + s.bodyLength * 0.5;
        gameCtx.strokeStyle = s.bodyColor;
        gameCtx.fillStyle = s.bodyColor;
        gameCtx.lineWidth = 2;

        gameCtx.beginPath();
        gameCtx.arc(enemy.x, torsoTopY - s.headRadius, s.headRadius, 0, Math.PI * 2);
        gameCtx.fill();
        drawHat(gameCtx, enemy.x, torsoTopY - s.headRadius * 2, s);
        gameCtx.beginPath();
        gameCtx.moveTo(enemy.x, torsoTopY);
        gameCtx.lineTo(enemy.x, torsoBottomY);
        gameCtx.stroke();
        gameCtx.beginPath();
        gameCtx.moveTo(enemy.x - s.armLength, enemy.y - 2);
        gameCtx.lineTo(enemy.x + s.armLength, enemy.y - 2);
        gameCtx.stroke();
        gameCtx.beginPath();
        gameCtx.moveTo(enemy.x, torsoBottomY);
        gameCtx.lineTo(enemy.x - s.legLength * 0.5, torsoBottomY + s.legLength);
        gameCtx.moveTo(enemy.x, torsoBottomY);
        gameCtx.lineTo(enemy.x + s.legLength * 0.5, torsoBottomY + s.legLength);
        gameCtx.stroke();
        if (settings.enableWeapons) {
            drawWeapon(gameCtx, enemy, torsoTopY);
        }
    }
}

function drawMissionText(gameCtx, mission, killCount, extractionReady) {
    gameCtx.fillStyle = 'black';
    gameCtx.font = '16px sans-serif';
    if (mission === 1) {
        const prefix = extractionReady ? 'Mission: Extraction unlocked — head to the green square' : 'Mission: Eliminate 5 enemies';
        const tally = extractionReady ? '' : ` (${killCount}/5)`;
        gameCtx.fillText(`${prefix}${tally}`, 10, 20);
    } else if (mission === 2) {
        gameCtx.fillText('Mission: Collect and arm mining charges inside the containment square', 10, 20);
    } else {
        gameCtx.fillText('Mission: Reach extraction after breaching the wall', 10, 20);
    }
}

function draw2DScene(gameCtx, state, settings, debugState, viewport) {
    const { player, enemies, missionState } = state;
    const { mission, killCount, targetZone, level2State, extractionReady } = missionState();

    gameCtx.clearRect(0, 0, viewport.width, viewport.height);
    gameCtx.fillStyle = 'white';
    gameCtx.fillRect(0, 0, viewport.width, viewport.height);

    const showTarget = (mission === 1 && extractionReady) || mission === 3;
    if (showTarget) {
        drawTargetZone(gameCtx, targetZone, mission !== 3 || extractionReady);
    }

    if (mission === 2 || mission === 3) {
        drawContainment2D(gameCtx, level2State, player);
    }

    drawPlayer2D(gameCtx, player);
    drawEnemies2D(gameCtx, enemies, settings);

    if (debugState.showColliders) {
        drawDebugColliders(gameCtx, player, enemies);
    }

    drawMissionText(gameCtx, mission, killCount, extractionReady);
}

function drawContainment3D(gameCtx, level2State) {
    const { containment, walls, charges, planted } = level2State;
    gameCtx.save();
    gameCtx.strokeStyle = 'rgba(39, 174, 96, 0.9)';
    gameCtx.lineWidth = 4;
    gameCtx.strokeRect(containment.x, containment.y, containment.size, containment.size);
    gameCtx.fillStyle = 'rgba(39, 174, 96, 0.08)';
    gameCtx.fillRect(containment.x, containment.y, containment.size, containment.size);

    walls.forEach(wall => {
        const fill = wall.destroyed ? 'rgba(236, 240, 241, 0.45)' : 'rgba(52, 73, 94, 0.95)';
        gameCtx.fillStyle = fill;
        gameCtx.shadowColor = 'rgba(0,0,0,0.25)';
        gameCtx.shadowBlur = wall.destroyed ? 2 : 6;
        gameCtx.fillRect(wall.x, wall.y, wall.width, wall.height);
    });

    charges.forEach(charge => {
        const gradient = gameCtx.createRadialGradient(charge.x, charge.y, 2, charge.x, charge.y, 14);
        gradient.addColorStop(0, 'rgba(255, 195, 113, 0.9)');
        gradient.addColorStop(1, 'rgba(243, 156, 18, 0.15)');
        gameCtx.fillStyle = gradient;
        gameCtx.beginPath();
        gameCtx.arc(charge.x, charge.y, 12, 0, Math.PI * 2);
        gameCtx.fill();
    });

    planted.forEach(charge => {
        const gradient = gameCtx.createRadialGradient(charge.x, charge.y, 2, charge.x, charge.y, charge.radius);
        gradient.addColorStop(0, 'rgba(231, 76, 60, 0.8)');
        gradient.addColorStop(1, 'rgba(231, 76, 60, 0)');
        gameCtx.fillStyle = gradient;
        gameCtx.beginPath();
        gameCtx.arc(charge.x, charge.y, charge.radius, 0, Math.PI * 2);
        gameCtx.fill();
        gameCtx.fillStyle = '#c0392b';
        gameCtx.beginPath();
        gameCtx.arc(charge.x, charge.y, 12, 0, Math.PI * 2);
        gameCtx.fill();
    });

    gameCtx.restore();
}

function draw3DCharacters(gameCtx, player, enemies, settings) {
    gameCtx.save();
    gameCtx.shadowColor = 'rgba(0,0,0,0.25)';
    gameCtx.shadowBlur = 8;

    const drawCharacter = (x, y, color, size) => {
        const gradient = gameCtx.createLinearGradient(x, y - size, x, y + size);
        gradient.addColorStop(0, '#ecf0f1');
        gradient.addColorStop(1, color);
        gameCtx.fillStyle = gradient;
        gameCtx.beginPath();
        gameCtx.ellipse(x, y, size * 0.8, size, 0, 0, Math.PI * 2);
        gameCtx.fill();
    };

    enemies.forEach(enemy => {
        drawCharacter(enemy.x, enemy.y, enemy.style.bodyColor, enemy.size + 6);
        if (settings.enableWeapons) {
            gameCtx.fillStyle = 'rgba(0,0,0,0.4)';
            const offset = enemy.hand === 'left' ? -enemy.style.armLength : enemy.style.armLength;
            gameCtx.fillRect(enemy.x + offset - 4, enemy.y - 6, 8, 16);
        }
    });

    drawCharacter(player.x, player.y, '#3498db', player.radius + 12);

    gameCtx.restore();
}

function draw3DGround(gameCtx, viewport) {
    const horizon = viewport.height * 0.35;
    const gradient = gameCtx.createLinearGradient(0, 0, 0, viewport.height);
    gradient.addColorStop(0, '#dfe6e9');
    gradient.addColorStop(1, '#bdc3c7');
    gameCtx.fillStyle = gradient;
    gameCtx.fillRect(0, 0, viewport.width, viewport.height);

    gameCtx.save();
    gameCtx.strokeStyle = 'rgba(52, 73, 94, 0.3)';
    for (let i = 0; i < 12; i++) {
        const depth = i / 12;
        const y = horizon + depth * (viewport.height - horizon);
        const spread = 120 + depth * viewport.width * 0.6;
        gameCtx.beginPath();
        gameCtx.moveTo((viewport.width / 2) - spread, y);
        gameCtx.lineTo((viewport.width / 2) + spread, y);
        gameCtx.stroke();
    }
    for (let i = -6; i <= 6; i++) {
        const angleSpread = i * 0.08;
        gameCtx.beginPath();
        gameCtx.moveTo(viewport.width / 2, horizon - 20);
        gameCtx.lineTo((viewport.width / 2) + Math.tan(angleSpread) * viewport.height, viewport.height);
        gameCtx.stroke();
    }
    gameCtx.restore();
}

function draw3DScene(gameCtx, state, settings, debugState, viewport) {
    const { player, enemies, missionState } = state;
    const { mission, killCount, targetZone, level2State, extractionReady } = missionState();
    gameCtx.setTransform(1, 0, -0.08, 1, viewport.width * 0.08, 0);
    draw3DGround(gameCtx, viewport);

    if ((mission === 1 && extractionReady) || mission === 3) {
        drawTargetZone(gameCtx, targetZone, true);
    }

    if (mission === 2 || mission === 3) {
        drawContainment3D(gameCtx, level2State);
    }

    draw3DCharacters(gameCtx, player, enemies, settings);

    if (debugState.showColliders) {
        drawDebugColliders(gameCtx, player, enemies);
    }

    gameCtx.setTransform(1, 0, 0, 1, 0, 0);
    drawMissionText(gameCtx, mission, killCount, extractionReady);
}

function drawUI(uiCtx, joystickState, actionState, settings, viewport) {
    uiCtx.clearRect(0, 0, viewport.width, viewport.height);
    uiCtx.globalAlpha = settings.enableJoystick ? 0.5 : 0.2;
    uiCtx.beginPath();
    uiCtx.arc(joystickState.joystickBase.x, joystickState.joystickBase.y, joystickState.joystickRadius, 0, Math.PI * 2);
    uiCtx.fillStyle = '#888';
    uiCtx.fill();
    uiCtx.globalAlpha = settings.enableJoystick ? 1.0 : 0.3;
    uiCtx.beginPath();
    uiCtx.arc(joystickState.joystickKnob.x, joystickState.joystickKnob.y, 20, 0, Math.PI * 2);
    uiCtx.fillStyle = '#444';
    uiCtx.fill();
    uiCtx.globalAlpha = actionState.actionTouchId ? 0.7 : 0.5;
    uiCtx.beginPath();
    uiCtx.arc(actionState.actionButton.x, actionState.actionButton.y, actionState.actionButton.radius, 0, Math.PI * 2);
    uiCtx.fillStyle = '#f00';
    uiCtx.fill();

    uiCtx.globalAlpha = 1;
    uiCtx.fillStyle = '#fff';
    uiCtx.font = '14px sans-serif';
    uiCtx.textAlign = 'center';
    uiCtx.fillText(actionState.label || 'Attack', actionState.actionButton.x, actionState.actionButton.y + 5);
}

export function createRenderer(gameCanvas, uiCanvas, settings, state, joystickState, actionState, setControlPositions, debugState) {
    const gameCtx = gameCanvas.getContext('2d');
    const uiCtx = uiCanvas.getContext('2d');
    const scenes = { '2d': createBufferCanvas(), '3d': createBufferCanvas() };
    let currentMode = settings.renderMode || '2d';
    let transition = null;

    function resizeCanvas() {
        const viewport = getViewport();
        const { width, height, dpr } = viewport;
        gameCanvas.width = width * dpr;
        gameCanvas.height = height * dpr;
        gameCanvas.style.width = width + 'px';
        gameCanvas.style.height = height + 'px';
        gameCtx.setTransform(1, 0, 0, 1, 0, 0);
        gameCtx.scale(dpr, dpr);

        uiCanvas.width = width * dpr;
        uiCanvas.height = height * dpr;
        uiCanvas.style.width = width + 'px';
        uiCanvas.style.height = height + 'px';
        uiCtx.setTransform(1, 0, 0, 1, 0, 0);
        uiCtx.scale(dpr, dpr);

        Object.values(scenes).forEach(scene => {
            scene.canvas.width = width * dpr;
            scene.canvas.height = height * dpr;
            scene.ctx.setTransform(1, 0, 0, 1, 0, 0);
            scene.ctx.scale(dpr, dpr);
        });

        setControlPositions();
    }

    function requestRenderMode(nextMode, onTransitionComplete) {
        if (nextMode === currentMode || (transition && transition.to === nextMode)) {
            if (typeof onTransitionComplete === 'function') onTransitionComplete(nextMode);
            return;
        }
        transition = { from: currentMode, to: nextMode, start: performance.now(), duration: TRANSITION_DURATION, callback: onTransitionComplete };
    }

    function render() {
        const viewport = getViewport();
        draw2DScene(scenes['2d'].ctx, state, settings, debugState, viewport);
        draw3DScene(scenes['3d'].ctx, state, settings, debugState, viewport);

        gameCtx.clearRect(0, 0, viewport.width, viewport.height);
        if (transition) {
            const now = performance.now();
            const elapsed = now - transition.start;
            const t = Math.min(1, elapsed / transition.duration);
            gameCtx.globalAlpha = 1 - t;
            gameCtx.drawImage(scenes[transition.from].canvas, 0, 0, viewport.width, viewport.height);
            gameCtx.globalAlpha = t;
            gameCtx.drawImage(scenes[transition.to].canvas, 0, 0, viewport.width, viewport.height);
            gameCtx.globalAlpha = 1;
            if (t >= 1) {
                currentMode = transition.to;
                settings.renderMode = currentMode;
                const cb = transition.callback;
                transition = null;
                if (typeof cb === 'function') cb(currentMode);
            }
        } else {
            gameCtx.globalAlpha = 1;
            gameCtx.drawImage(scenes[currentMode].canvas, 0, 0, viewport.width, viewport.height);
        }

        drawUI(uiCtx, joystickState, actionState, settings, viewport);
    }

    return {
        resizeCanvas,
        render,
        requestRenderMode,
    };
}
