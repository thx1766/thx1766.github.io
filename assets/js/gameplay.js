function createEnemyStyles() {
    return [
        { type: 'grunt', bodyColor: '#c0392b', headRadius: 5, bodyLength: 14, armLength: 10, legLength: 12, hatType: 'triangle', hatColor: '#f1c40f', hatSizeBoost: 3, weapon: 'gloves' },
        { type: 'brute', bodyColor: '#8e44ad', headRadius: 6, bodyLength: 18, armLength: 12, legLength: 14, hatType: 'square', hatColor: '#2980b9', hatSizeBoost: 3, weapon: 'sword' },
        { type: 'scout', bodyColor: '#e67e22', headRadius: 4, bodyLength: 12, armLength: 11, legLength: 16, hatType: 'pentagon', hatColor: '#2ecc71', hatSizeBoost: 3, weapon: 'cup' }
    ];
}

export function createGameplaySystem(settings, keyState, joystickState, actionState) {
    const player = { x: 100, y: 100, radius: 10, speed: 2, color: 'blue' };
    const enemies = [];
    const enemyStyles = createEnemyStyles();
    let playerCentered = false;
    let killCount = 0;
    let mission = 1;
    const targetZone = { x: 200, y: 200, size: 50 };

    function pickEnemyStyle() {
        return enemyStyles[Math.floor(Math.random() * enemyStyles.length)];
    }

    function computeEnemyCount() {
        const area = window.innerWidth * window.innerHeight;
        if (area < 450000) return 7;
        if (area < 900000) return 10;
        return 14;
    }

    function spawnEnemy(style) {
        const ex = Math.random() * window.innerWidth;
        const ey = Math.random() * window.innerHeight;
        const evx = (Math.random() - 0.5) * 2;
        const evy = (Math.random() - 0.5) * 2;
        const hitRadius = Math.max(style.headRadius + style.bodyLength * 0.5, style.legLength);
        const hand = Math.random() > 0.5 ? 'left' : 'right';
        enemies.push({ x: ex, y: ey, dx: evx, dy: evy, size: hitRadius, color: style.bodyColor, style, hand });
    }

    function initEnemies() {
        enemies.length = 0;
        const count = computeEnemyCount();
        for (let i = 0; i < count; i++) {
            const style = pickEnemyStyle();
            spawnEnemy(style);
        }
    }

    function adjustEnemyCount() {
        const desired = computeEnemyCount();
        if (enemies.length > desired) {
            enemies.splice(desired, enemies.length - desired);
        } else {
            for (let i = enemies.length; i < desired; i++) {
                const style = pickEnemyStyle();
                spawnEnemy(style);
            }
        }
    }

    function clampEnemiesToScreen() {
        for (const enemy of enemies) {
            enemy.x = Math.max(enemy.size, Math.min(window.innerWidth - enemy.size, enemy.x));
            enemy.y = Math.max(enemy.size, Math.min(window.innerHeight - enemy.size, enemy.y));
        }
    }

    function centerPlayer() {
        if (!playerCentered) {
            player.x = window.innerWidth / 2;
            player.y = window.innerHeight / 2;
            playerCentered = true;
        }
        player.x = Math.max(player.radius, Math.min(window.innerWidth - player.radius, player.x));
        player.y = Math.max(player.radius, Math.min(window.innerHeight - player.radius, player.y));
    }

    function updatePlayerMovement() {
        let dx = 0, dy = 0;
        if (keyState.up) dy -= 1;
        if (keyState.down) dy += 1;
        if (keyState.left) dx -= 1;
        if (keyState.right) dx += 1;

        if (joystickState.joystickActive) {
            dx += (joystickState.joystickKnob.x - joystickState.joystickBase.x) / joystickState.joystickRadius;
            dy += (joystickState.joystickKnob.y - joystickState.joystickBase.y) / joystickState.joystickRadius;
        }

        const length = Math.sqrt(dx * dx + dy * dy);
        if (length > 0) {
            dx = dx / length;
            dy = dy / length;
            player.x += dx * player.speed * joystickState.maxSpeed;
            player.y += dy * player.speed * joystickState.maxSpeed;
        }

        player.x = Math.max(player.radius, Math.min(window.innerWidth - player.radius, player.x));
        player.y = Math.max(player.radius, Math.min(window.innerHeight - player.radius, player.y));
    }

    function updateEnemies() {
        for (const enemy of enemies) {
            enemy.x += enemy.dx;
            enemy.y += enemy.dy;
            if (enemy.x < enemy.size || enemy.x > window.innerWidth - enemy.size) enemy.dx *= -1;
            if (enemy.y < enemy.size || enemy.y > window.innerHeight - enemy.size) enemy.dy *= -1;
        }
    }

    function handleCombat() {
        if (!actionState.attackPressed) return;
        for (let i = enemies.length - 1; i >= 0; i--) {
            const enemy = enemies[i];
            const dist = Math.hypot(enemy.x - player.x, enemy.y - player.y);
            if (dist < player.radius + enemy.size + 5) {
                enemies.splice(i, 1);
                killCount++;
                break;
            }
        }
    }

    function updateMission() {
        if (mission === 1 && killCount >= 5) {
            mission = 2;
        }
        if (mission === 2) {
            if (player.x >= targetZone.x && player.x <= targetZone.x + targetZone.size && player.y >= targetZone.y && player.y <= targetZone.y + targetZone.size) {
                mission = 1;
                killCount = 0;
                targetZone.x = Math.random() * (window.innerWidth - targetZone.size);
                targetZone.y = Math.random() * (window.innerHeight - targetZone.size);
                initEnemies();
            }
        }
    }

    function update() {
        updatePlayerMovement();
        updateEnemies();
        handleCombat();
        updateMission();
    }

    return {
        player,
        enemies,
        missionState: () => ({ mission, killCount, targetZone }),
        initEnemies,
        adjustEnemyCount,
        clampEnemiesToScreen,
        centerPlayer,
        update,
    };
}
