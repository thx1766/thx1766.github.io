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

    const dirX = Math.cos(player.facing);
    const dirY = Math.sin(player.facing);
    const indicatorStartY = player.y - 6;
    const indicatorLength = 20;
    const tipX = player.x + dirX * indicatorLength;
    const tipY = indicatorStartY + dirY * indicatorLength;
    gameCtx.fillStyle = 'rgba(52, 152, 219, 0.85)';
    gameCtx.beginPath();
    gameCtx.moveTo(tipX, tipY);
    gameCtx.lineTo(tipX - dirY * 5, tipY + dirX * 5);
    gameCtx.lineTo(tipX + dirY * 5, tipY - dirX * 5);
    gameCtx.closePath();
    gameCtx.fill();
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

function drawSkyAndGround(gameCtx, camera, viewport) {
    const gradient = gameCtx.createLinearGradient(0, 0, 0, viewport.height);
    gradient.addColorStop(0, '#dde8f3');
    gradient.addColorStop(Math.max(0.05, camera.horizon / viewport.height - 0.08), '#d7e2ee');
    gradient.addColorStop(1, '#9aa7b4');
    gameCtx.fillStyle = gradient;
    gameCtx.fillRect(0, 0, viewport.width, viewport.height);

    const groundGradient = gameCtx.createLinearGradient(0, camera.horizon, 0, viewport.height);
    groundGradient.addColorStop(0, 'rgba(52, 73, 94, 0.2)');
    groundGradient.addColorStop(1, 'rgba(52, 73, 94, 0.05)');
    gameCtx.fillStyle = groundGradient;
    gameCtx.fillRect(0, camera.horizon, viewport.width, viewport.height - camera.horizon);

    gameCtx.save();
    gameCtx.strokeStyle = 'rgba(44, 62, 80, 0.3)';
    const vanishingLines = 14;
    for (let i = -vanishingLines; i <= vanishingLines; i++) {
        const angle = i * 0.04;
        gameCtx.beginPath();
        gameCtx.moveTo(viewport.width / 2, camera.horizon);
        gameCtx.lineTo((viewport.width / 2) + Math.tan(angle) * viewport.height, viewport.height);
        gameCtx.stroke();
    }
    const depthLines = 12;
    for (let i = 0; i < depthLines; i++) {
        const depth = i / depthLines;
        const y = camera.horizon + depth * (viewport.height - camera.horizon);
        const spread = 120 + depth * viewport.width * 0.6;
        gameCtx.beginPath();
        gameCtx.moveTo((viewport.width / 2) - spread, y);
        gameCtx.lineTo((viewport.width / 2) + spread, y);
        gameCtx.stroke();
    }
    gameCtx.restore();
}

function buildCamera(player, viewport, introLerp = 1) {
    const clamped = Math.min(1, Math.max(0, introLerp));
    const horizon = viewport.height * (0.42 - 0.07 * (1 - clamped));
    const eyeHeight = 46 + 60 * (1 - clamped * 0.6);
    const viewDistance = 520;
    return { horizon, eyeHeight, viewDistance, facing: player.facing, viewportWidth: viewport.width, viewportHeight: viewport.height };
}

function rotateToView(dx, dy, facing) {
    const cos = Math.cos(-facing);
    const sin = Math.sin(-facing);
    return { x: dx * cos - dy * sin, y: dx * sin + dy * cos };
}

function projectToScreen(rotated, camera) {
    const depth = rotated.y + 40;
    if (depth <= 12) return null;
    const scale = camera.viewDistance / depth;
    const screenX = camera.viewportWidth / 2 + rotated.x * scale;
    const groundY = camera.horizon + camera.eyeHeight * scale;
    return { screenX, groundY, scale, depth };
}

function drawFirstPersonTargets(gameCtx, camera, targetZone, player) {
    const centerX = targetZone.x + targetZone.size / 2;
    const centerY = targetZone.y + targetZone.size / 2;
    const rotated = rotateToView(centerX - player.x, centerY - player.y, camera.facing);
    const projected = projectToScreen(rotated, camera);
    if (!projected) return;
    const columnHeight = Math.max(30, 140 * projected.scale);
    const columnWidth = Math.max(6, 18 * projected.scale);
    const baseY = projected.groundY;
    const topY = baseY - columnHeight;
    gameCtx.save();
    gameCtx.strokeStyle = 'rgba(39, 174, 96, 0.9)';
    gameCtx.fillStyle = 'rgba(39, 174, 96, 0.3)';
    gameCtx.lineWidth = Math.max(2, 4 * projected.scale);
    gameCtx.beginPath();
    gameCtx.rect(projected.screenX - columnWidth / 2, topY, columnWidth, columnHeight);
    gameCtx.stroke();
    gameCtx.fill();
    gameCtx.restore();
}

function drawFirstPersonEnemies(gameCtx, camera, player, enemies, settings) {
    const projections = [];
    enemies.forEach(enemy => {
        const rotated = rotateToView(enemy.x - player.x, enemy.y - player.y, camera.facing);
        const projected = projectToScreen(rotated, camera);
        if (!projected || projected.depth > 1200) return;
        projections.push({ enemy, projected });
    });

    projections.sort((a, b) => b.projected.depth - a.projected.depth);

    const time = performance.now() * 0.005;

    projections.forEach(({ enemy, projected }) => {
        const size = enemy.size + 6;
        const height = Math.max(34, size * 4.5 * projected.scale);
        const baseY = projected.groundY;
        const topY = baseY - height;
        const width = Math.max(3, size * 0.6 * projected.scale);
        const headRadius = Math.max(4, enemy.style.headRadius * projected.scale * 0.9);
        gameCtx.save();
        gameCtx.strokeStyle = enemy.style.bodyColor;
        gameCtx.fillStyle = enemy.style.bodyColor;
        gameCtx.lineWidth = Math.max(2, 3 * projected.scale);

        gameCtx.beginPath();
        gameCtx.moveTo(projected.screenX, topY + headRadius * 2);
        gameCtx.lineTo(projected.screenX, baseY - headRadius);
        gameCtx.stroke();

        gameCtx.beginPath();
        const armAnchorY = baseY - height * 0.45;
        const swingPhase = time + enemy.x * 0.08 + enemy.y * 0.05;
        const swingAmount = Math.sin(swingPhase) * height * 0.08;
        const liftAmount = Math.cos(swingPhase * 1.2) * height * 0.05;
        gameCtx.moveTo(projected.screenX, armAnchorY);
        gameCtx.lineTo(projected.screenX - width * 3, armAnchorY + swingAmount + liftAmount);
        gameCtx.moveTo(projected.screenX, armAnchorY);
        gameCtx.lineTo(projected.screenX + width * 3, armAnchorY - swingAmount + liftAmount);
        gameCtx.stroke();

        gameCtx.beginPath();
        gameCtx.moveTo(projected.screenX, baseY - height * 0.2);
        gameCtx.lineTo(projected.screenX - width * 2, baseY);
        gameCtx.moveTo(projected.screenX, baseY - height * 0.2);
        gameCtx.lineTo(projected.screenX + width * 2, baseY);
        gameCtx.stroke();

        gameCtx.beginPath();
        gameCtx.arc(projected.screenX, topY + headRadius, headRadius, 0, Math.PI * 2);
        gameCtx.fill();
        drawHat(gameCtx, projected.screenX, topY, { ...enemy.style, headRadius: headRadius * 0.8 });

        if (settings.enableWeapons) {
            gameCtx.fillStyle = 'rgba(0,0,0,0.35)';
            const offset = enemy.hand === 'left' ? -enemy.style.armLength : enemy.style.armLength;
            const scaledOffset = offset * projected.scale * 0.5;
            gameCtx.fillRect(projected.screenX + scaledOffset - width, baseY - height * 0.5, width * 2, width * 4);
        }
        gameCtx.restore();
    });
}

function drawContainmentColumns(gameCtx, camera, level2State, player) {
    const { containment, walls, charges, planted } = level2State;
    const anchors = [
        { x: containment.x, y: containment.y },
        { x: containment.x + containment.size, y: containment.y },
        { x: containment.x, y: containment.y + containment.size },
        { x: containment.x + containment.size, y: containment.y + containment.size },
    ];
    gameCtx.save();
    gameCtx.strokeStyle = 'rgba(39, 174, 96, 0.45)';
    anchors.forEach(anchor => {
        const rotated = rotateToView(anchor.x - player.x, anchor.y - player.y, camera.facing);
        const projected = projectToScreen(rotated, camera);
        if (!projected) return;
        const height = Math.max(18, 120 * projected.scale);
        gameCtx.lineWidth = Math.max(1.5, 3 * projected.scale);
        gameCtx.beginPath();
        gameCtx.moveTo(projected.screenX, projected.groundY);
        gameCtx.lineTo(projected.screenX, projected.groundY - height);
        gameCtx.stroke();
    });
    gameCtx.restore();

    charges.forEach(charge => {
        const rotated = rotateToView(charge.x - player.x, charge.y - player.y, camera.facing);
        const projected = projectToScreen(rotated, camera);
        if (!projected) return;
        const radius = Math.max(5, 12 * projected.scale);
        const glow = radius * 4;
        const gradient = gameCtx.createRadialGradient(projected.screenX, projected.groundY - radius, 2, projected.screenX, projected.groundY - radius, glow);
        gradient.addColorStop(0, 'rgba(255, 195, 113, 0.9)');
        gradient.addColorStop(1, 'rgba(243, 156, 18, 0.05)');
        gameCtx.fillStyle = gradient;
        gameCtx.beginPath();
        gameCtx.arc(projected.screenX, projected.groundY - radius, radius, 0, Math.PI * 2);
        gameCtx.fill();
    });

    planted.forEach(charge => {
        const rotated = rotateToView(charge.x - player.x, charge.y - player.y, camera.facing);
        const projected = projectToScreen(rotated, camera);
        if (!projected) return;
        const radius = Math.max(8, 14 * projected.scale);
        const blast = charge.radius * projected.scale * 0.45;
        const gradient = gameCtx.createRadialGradient(projected.screenX, projected.groundY - radius, 2, projected.screenX, projected.groundY - radius, blast);
        gradient.addColorStop(0, 'rgba(231, 76, 60, 0.55)');
        gradient.addColorStop(1, 'rgba(231, 76, 60, 0.05)');
        gameCtx.fillStyle = gradient;
        gameCtx.beginPath();
        gameCtx.arc(projected.screenX, projected.groundY - radius, blast, 0, Math.PI * 2);
        gameCtx.fill();
    });
}

function drawReticle(gameCtx, viewport) {
    const cx = viewport.width / 2;
    const cy = viewport.height * 0.52;
    gameCtx.save();
    gameCtx.strokeStyle = 'rgba(52, 73, 94, 0.7)';
    gameCtx.lineWidth = 2;
    gameCtx.beginPath();
    gameCtx.arc(cx, cy, 14, 0, Math.PI * 2);
    gameCtx.stroke();
    gameCtx.beginPath();
    gameCtx.moveTo(cx - 16, cy);
    gameCtx.lineTo(cx + 16, cy);
    gameCtx.moveTo(cx, cy - 16);
    gameCtx.lineTo(cx, cy + 16);
    gameCtx.stroke();
    gameCtx.restore();
}

function draw3DScene(gameCtx, state, settings, debugState, viewport, introLerp = 1) {
    const { player, enemies, missionState } = state;
    const { mission, killCount, targetZone, level2State, extractionReady } = missionState();
    gameCtx.clearRect(0, 0, viewport.width, viewport.height);
    const camera = buildCamera(player, viewport, introLerp);
    drawSkyAndGround(gameCtx, camera, viewport);

    if ((mission === 1 && extractionReady) || mission === 3) {
        drawFirstPersonTargets(gameCtx, camera, targetZone, player);
    }

    if (mission === 2 || mission === 3) {
        drawContainmentColumns(gameCtx, camera, level2State, player);
    }

    drawFirstPersonEnemies(gameCtx, camera, player, enemies, settings);
    drawReticle(gameCtx, viewport);

    if (debugState.showColliders) {
        drawDebugColliders(gameCtx, player, enemies);
    }

    drawMissionText(gameCtx, mission, killCount, extractionReady);
}

function drawUI(uiCtx, joystickState, lookJoystickState, actionState, settings, viewport) {
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
    const wants3D = (settings.pendingRenderMode || settings.renderMode) === '3d';
    if (wants3D) {
        uiCtx.globalAlpha = 0.5;
        uiCtx.beginPath();
        uiCtx.arc(lookJoystickState.joystickBase.x, lookJoystickState.joystickBase.y, lookJoystickState.joystickRadius, 0, Math.PI * 2);
        uiCtx.fillStyle = '#6c7a89';
        uiCtx.fill();
        uiCtx.globalAlpha = 1.0;
        uiCtx.beginPath();
        uiCtx.arc(lookJoystickState.joystickKnob.x, lookJoystickState.joystickKnob.y, 18, 0, Math.PI * 2);
        uiCtx.fillStyle = '#2c3e50';
        uiCtx.fill();
    }
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

function drawTiltedBuffer(gameCtx, buffer, viewport, tiltAmount, alpha = 1) {
    const lift = viewport.height * 0.18 * tiltAmount;
    const squash = 1 - 0.5 * tiltAmount;
    const shear = tiltAmount * 0.08;
    gameCtx.save();
    gameCtx.globalAlpha = alpha;
    gameCtx.translate(viewport.width / 2, viewport.height / 2 + lift);
    gameCtx.transform(1, shear, 0, squash, 0, 0);
    gameCtx.drawImage(buffer, -viewport.width / 2, -viewport.height / 2, viewport.width, viewport.height);
    gameCtx.restore();
}

export function createRenderer(gameCanvas, uiCanvas, settings, state, joystickState, lookJoystickState, actionState, setControlPositions, debugState) {
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
        const now = performance.now();
        const transitionProgress = transition ? Math.min(1, (now - transition.start) / transition.duration) : 1;
        const transitionTilt = transition
            ? (transition.to === '3d' ? transitionProgress : 1 - transitionProgress)
            : 1;

        draw2DScene(scenes['2d'].ctx, state, settings, debugState, viewport);
        draw3DScene(scenes['3d'].ctx, state, settings, debugState, viewport, transitionTilt);

        gameCtx.clearRect(0, 0, viewport.width, viewport.height);
        if (transition) {
            const t = transitionProgress;
            const tilt = transition.to === '3d' ? t : 1 - t;
            const fadeIn = transition.to === '3d' ? Math.pow(t, 0.9) : 1 - Math.pow(1 - t, 0.9);
            const fadeOut = 1 - fadeIn * 0.7;

            drawTiltedBuffer(gameCtx, scenes['2d'].canvas, viewport, tilt, Math.max(0.15, fadeOut));
            drawTiltedBuffer(gameCtx, scenes['3d'].canvas, viewport, Math.max(0, tilt - 0.18), fadeIn);

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

        drawUI(uiCtx, joystickState, lookJoystickState, actionState, settings, viewport);
    }

    return {
        resizeCanvas,
        render,
        requestRenderMode,
    };
}
