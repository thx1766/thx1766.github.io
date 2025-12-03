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

function drawWeapon(gameCtx, enemy, torsoTopY, torsoBottomY) {
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

function drawContainment(gameCtx, level2State, player) {
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

function drawGame(gameCtx, state, settings, debugState) {
    const { player, enemies, missionState } = state;
    const { mission, killCount, targetZone, level2State } = missionState();

    gameCtx.clearRect(0, 0, window.innerWidth, window.innerHeight);
    gameCtx.fillStyle = 'white';
    gameCtx.fillRect(0, 0, window.innerWidth, window.innerHeight);

    if (mission === 2 || mission === 3) {
        drawContainment(gameCtx, level2State, player);
    }

    if (mission === 2 || mission === 3) {
        gameCtx.fillStyle = 'lightgreen';
        gameCtx.fillRect(targetZone.x, targetZone.y, targetZone.size, targetZone.size);
    }

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

    for (let enemy of enemies) {
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
            drawWeapon(gameCtx, enemy, torsoTopY, torsoBottomY);
        }
    }

    if (debugState.showColliders) {
        drawDebugColliders(gameCtx, player, enemies);
    }

    gameCtx.fillStyle = 'black';
    gameCtx.font = '16px sans-serif';
    if (mission === 1) {
        gameCtx.fillText(`Mission: Eliminate 5 enemies (${killCount})`, 10, 20);
    } else if (mission === 2) {
        gameCtx.fillText('Mission: Collect and arm mining charges inside the containment square', 10, 20);
    } else {
        gameCtx.fillText('Mission: Reach extraction after breaching the wall', 10, 20);
    }
}

function drawUI(uiCtx, joystickState, actionState, settings) {
    uiCtx.clearRect(0, 0, window.innerWidth, window.innerHeight);
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

    function resizeCanvas() {
        const dpr = window.devicePixelRatio || 1;
        gameCanvas.width = window.innerWidth * dpr;
        gameCanvas.height = window.innerHeight * dpr;
        gameCanvas.style.width = window.innerWidth + 'px';
        gameCanvas.style.height = window.innerHeight + 'px';
        gameCtx.setTransform(1, 0, 0, 1, 0, 0);
        gameCtx.scale(dpr, dpr);
        uiCanvas.width = window.innerWidth * dpr;
        uiCanvas.height = window.innerHeight * dpr;
        uiCanvas.style.width = window.innerWidth + 'px';
        uiCanvas.style.height = window.innerHeight + 'px';
        uiCtx.setTransform(1, 0, 0, 1, 0, 0);
        uiCtx.scale(dpr, dpr);
        setControlPositions();
    }

    function render() {
        drawGame(gameCtx, state, settings, debugState);
        drawUI(uiCtx, joystickState, actionState, settings);
    }

    return {
        resizeCanvas,
        render,
    };
}
